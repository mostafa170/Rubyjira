package com.devartlab.rubyjira.app.presentation.editProfile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.devartlab.rubyjira.R
import com.devartlab.rubyjira.app.presentation.main.MainActivityEventsListener
import com.devartlab.rubyjira.app.utilsView.MyDialog
import com.devartlab.rubyjira.data.utils.*
import com.devartlab.rubyjira.databinding.DialogChooseCameraGalleryBinding
import com.devartlab.rubyjira.databinding.FragmentEditProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.*

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private val viewModel: EditProfileViewModel by viewModels()

    private var cameraPermissions = arrayOf(Manifest.permission.CAMERA)
    private var storagePermissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE
    )
    private var requestCodeCamera:Int = 0
    private var requestCodeGallery:Int = 2

    private val mainActivityEventsListener: MainActivityEventsListener by lazy {
        requireNotNull(activity){
            "Context must not be null"
        }
        activity as MainActivityEventsListener
    }

    private val myDialog: MyDialog by lazy {
        val activity = requireNotNull(this.activity) {
            "Activity is null"
        }
        MyDialog(activity)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding=FragmentEditProfileBinding.inflate(inflater)
        binding.lifecycleOwner=viewLifecycleOwner
        binding.viewModel = viewModel
        binding.executePendingBindings()
        val chooseBinding: DialogChooseCameraGalleryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(
                context
            ), R.layout._dialog_choose_camera_gallery, null, false)
        chooseBinding.lifecycleOwner = viewLifecycleOwner
        chooseBinding.viewModel = viewModel
        val dialogChoose = myDialog.getDialogInstance(
            chooseBinding.root,
            Gravity.BOTTOM,
            true)
        viewModel.setProfile()
        viewModel.error.observe(viewLifecycleOwner) {
            if (it != null) {
                mainActivityEventsListener.showErrorMessage(it)
                viewModel.onErrorMessageShown()
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it != null && it)
                mainActivityEventsListener.showLoading()
            else
                mainActivityEventsListener.hideLoading()
        }
        viewModel.back.observe(viewLifecycleOwner){
            if (it !=null && it){
                this.findNavController().navigateUp()
                viewModel.onBackNavigated()
            }
        }
        viewModel.userUpdate.observe(viewLifecycleOwner){
            if (it !=null){
                this.findNavController().navigate(R.id.profileFragment)
                mainActivityEventsListener.showSuccessMessage("profile updated successfully")
            }
        }
        viewModel.profilePictureUser.observe(viewLifecycleOwner) {
            if (it != null && it)
                dialogChoose.show()
        }
        viewModel.openCamera.observe(viewLifecycleOwner) {
            if (it != null) {
                dialogChoose.dismiss()
                Log.e("TAG", "openCamera: $it" )
                when (it) {
                    CAMERA_REQUEST_CODE1 -> viewModel.onProfilePictureDone()
                }
                if (!MyPermissions.isPermissionsGranted(requireActivity(), cameraPermissions)){
                    MyPermissions.requestPermissionFragment(
                        this,
                        cameraPermissions,
                        CAPTURE_IMAGES_PERMISSION)
                    requestCodeCamera = it
                } else
                    openCameraIntent(it)
            }
        }
        viewModel.openGallery.observe(viewLifecycleOwner) {
            if (it != null) {
                dialogChoose.dismiss()
                Log.e("TAG", "openGallery: $it" )
                when (it) {
                    GALLERY_REQUEST_CODE1 -> viewModel.onProfilePictureDone()
                }
                if (!MyPermissions.isPermissionsGranted(requireActivity(), storagePermissions)){
                    MyPermissions.requestPermissionFragment(
                        this,
                        storagePermissions,
                        READ_EXTERNAL_STORAGE_PERMISSION)
                    requestCodeGallery = it
                } else
                    openImageChooserIntent(it)
            }
        }

        setImages(binding)
        return binding.root
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if ((requestCode == READ_EXTERNAL_STORAGE_PERMISSION || requestCode == CAPTURE_IMAGES_PERMISSION) && grantResults.isNotEmpty()) {
            var isAllAccepted = true
            for (grantResult in grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    isAllAccepted = false
                    break
                }
            }
            if (isAllAccepted) {
                if (requestCode == READ_EXTERNAL_STORAGE_PERMISSION)
                    openImageChooserIntent(requestCodeGallery)
                else
                    openCameraIntent(requestCodeCamera)
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CAMERA_REQUEST_CODE1 && resultCode == Activity.RESULT_OK && data != null) {
            val extras: Bundle? = data.extras
            extras?.let {
                val imageBitmap = it.get("data") as Bitmap
                Log.e("TAG", "onActivityResult: $imageBitmap" )
                viewModel.setProfilePictureImageBitmap(imageBitmap)
            }
        }else if(requestCode == GALLERY_REQUEST_CODE1 && resultCode == Activity.RESULT_OK && data != null){
            val imageUri: Uri? = data.data
            imageUri?.let { uri ->
                try {
                    val imageStream: InputStream? = requireActivity().contentResolver.openInputStream(uri)
                    val imageBitmap = BitmapFactory.decodeStream(imageStream)
                    Log.e("TAG", "onActivityResult: $imageBitmap" )
                    viewModel.setProfilePictureImageBitmap(imageBitmap)
                }catch (ex: IOException){ }
            }
        }
    }
    private fun openCameraIntent(requestCode: Int){
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        this.startActivityForResult(takePictureIntent, requestCode)
    }
    private fun openImageChooserIntent(requestCode: Int){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        this.startActivityForResult(intent, requestCode)
    }
    private fun byteArray(imageBitmap: Bitmap, imageFile:File){
        val byteArrayOutputStream = ByteArrayOutputStream()
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val bitmapByteArray = byteArrayOutputStream.toByteArray()
        val fileOutputStream = FileOutputStream(imageFile)
        fileOutputStream.write(bitmapByteArray)
        fileOutputStream.flush()
        fileOutputStream.close()
    }
    private fun setImages(binding: FragmentEditProfileBinding) {
        viewModel.profilePictureImagePickedBitmap.observe(viewLifecycleOwner) {
            if (it != null) {
                try {
                    val imageFile =
                        File(requireContext().cacheDir, "${System.currentTimeMillis()}_NationalID")
                    imageFile.createNewFile()
                    byteArray(it,imageFile)

                    val requestFile = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
                    val bodyFile =
                        MultipartBody.Part.createFormData("NationalID", imageFile.name, requestFile)

                    Glide.with(this).load(imageFile).circleCrop().into(binding.editImage)
                    viewModel.onProfilePictureImageBitmapSet()

                    viewModel.updateProfilePictureImage(bodyFile)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

}