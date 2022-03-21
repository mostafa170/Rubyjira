package com.devartlab.rubyjira.app.presentation.showProfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devartlab.rubyjira.R
import com.devartlab.rubyjira.app.presentation.main.MainActivityEventsListener
import com.devartlab.rubyjira.data.utils.SharedPreferencesData
import com.devartlab.rubyjira.databinding.FragmentShowProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowProfileFragment : Fragment() {
    private val viewModel:ShowProfileViewModel by viewModels()

    private val mainActivityEventsListener: MainActivityEventsListener by lazy {
        requireNotNull(activity){
            "Context must not be null"
        }
        activity as MainActivityEventsListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding=FragmentShowProfileBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.executePendingBindings()
        onObserverListener()
        return binding.root
    }

    private fun onObserverListener() {
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

        viewModel.logout.observe(viewLifecycleOwner) {
            if (it !=null)
                SharedPreferencesData.logout()
                this.findNavController().navigate(R.id.loginFragment)
            mainActivityEventsListener.showSuccessMessage(it.message)
        }
        viewModel.goToEditProfile.observe(viewLifecycleOwner){
            if (it != null && it){
                this.findNavController()
                    .navigate(ShowProfileFragmentDirections.actionProfileFragmentToEditProfileFragment())
                viewModel.onGoToEditProfileNavigated()
            }
        }

        viewModel.goToChangePassword.observe(viewLifecycleOwner){
            if (it != null && it){
                this.findNavController()
                    .navigate(ShowProfileFragmentDirections.actionProfileFragmentToChangePasswordFragment())
                viewModel.onGoToChangePasswordNavigated()
            }
        }
    }
}