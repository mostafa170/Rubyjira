package com.devartlab.rubyjira.app.presentation.editProfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devartlab.rubyjira.app.presentation.main.MainActivityEventsListener
import com.devartlab.rubyjira.databinding.FragmentEditProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private val viewModel: EditProfileViewModel by viewModels()

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
        val binding=FragmentEditProfileBinding.inflate(inflater)
        binding.lifecycleOwner=viewLifecycleOwner
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
        viewModel.back.observe(viewLifecycleOwner){
            if (it !=null && it){
                this.findNavController().navigateUp()
                viewModel.onBackNavigated()
            }
        }
    }
}