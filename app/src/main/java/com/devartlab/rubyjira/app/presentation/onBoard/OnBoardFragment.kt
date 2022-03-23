package com.devartlab.rubyjira.app.presentation.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.devartlab.rubyjira.R
import com.devartlab.rubyjira.databinding.FragmentOnBoardBinding

class OnBoardFragment : Fragment() {

    private val viewModel: IntrosViewModel by lazy {
        requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, IntrosViewModel.Factory()).get(IntrosViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentOnBoardBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.executePendingBindings()

        val listOf = listOf(
            IntroItem(getString(R.string.label_introWelcome_title),getString(R.string.label_introWelcome1_content), R.drawable.ic_intro_img_1),
            IntroItem(getString(R.string.label_introWelcome_title), getString(R.string.label_introWelcome1_title),R.drawable.ic_intro_img_2),
            IntroItem(getString(R.string.label_introWelcome_title),getString(R.string.label_introWelcome3_content) ,R.drawable.ic_intro_img_3),
        )
        val onBoardAdapter = OnBoardPagerAdapter()
        onBoardAdapter.submitList(listOf)
        binding.pagerIntros.apply {
            adapter = onBoardAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrollStateChanged(state: Int) {}
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
                override fun onPageSelected(position: Int) {
                    viewModel.setCurrentIntroItem(position)
                }
            })
        }

        viewModel.currentIntroItem.observe(viewLifecycleOwner) {
            if (it != null)
                binding.pagerIntros.setCurrentItem(it, true)
        }
        viewModel.skipIntro.observe(viewLifecycleOwner) {
            if (it != null && it) {
                this.findNavController()
                    .navigate(OnBoardFragmentDirections.actionToAuthFragment())
                viewModel.onIntrosSkipped()
            }
        }

        return binding.root
    }


}