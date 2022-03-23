package com.devartlab.rubyjira.app.presentation.changePassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devartlab.rubyjira.R
import com.devartlab.rubyjira.app.presentation.main.MainActivityEventsListener
import com.devartlab.rubyjira.databinding.FragmentChangePasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangePasswordFragment : Fragment() {
    private val viewModel: ChangePasswordViewModel by viewModels()
    private val mainActivityEventsListener: MainActivityEventsListener by lazy {
        requireNotNull(activity) {
            "Context must not be null"
        }
        activity as MainActivityEventsListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentChangePasswordBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.executePendingBindings()
        onObserverListener()
        return binding.root
    }

    private fun onObserverListener() {
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
        viewModel.back.observe(viewLifecycleOwner) {
            if (it != null && it) {
                this.findNavController().navigateUp()
                viewModel.onBackNavigated()
            }
        }
        viewModel.changePassword.observe(viewLifecycleOwner) {
            if (it != null) {
                this.findNavController().navigate(R.id.profileFragment)
                mainActivityEventsListener.showSuccessMessage(it.message)
            }
        }
    }

}