package com.devartlab.rubyjira.app.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devartlab.rubyjira.R
import com.devartlab.rubyjira.app.presentation.main.MainActivityEventsListener
import com.devartlab.rubyjira.data.utils.SharedPreferencesData
import com.devartlab.rubyjira.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel:HomeViewModel by viewModels()

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
        val binding=FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.executePendingBindings()
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

        viewModel.unauthenticated.observe(viewLifecycleOwner) {
            if (it != null && it){
                mainActivityEventsListener.userUnauthenticated()
            }
        }
        viewModel.selectStatusTask.observe(viewLifecycleOwner) {
            if (it !=null && it){
                val popupMenu: android.widget.PopupMenu =
                    android.widget.PopupMenu(context, binding.edFilter)
                popupMenu.menuInflater.inflate(R.menu.task_popup_menu, popupMenu.menu)
                popupMenu.setOnMenuItemClickListener(android.widget.PopupMenu.OnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.action_index -> {
                            binding.edFilter.setText(R.string.index)
                            viewModel.setSelectTaskStatus("index")
                        }
                        R.id.action_today -> {
                            binding.edFilter.setText(R.string.today)
                            viewModel.setSelectTaskStatus("today")
                        }
                        R.id.action_upcoming -> {
                            binding.edFilter.setText(R.string.upcoming)
                            viewModel.setSelectTaskStatus("upcoming")
                        }
                        R.id.action_overdue -> {
                            binding.edFilter.setText(R.string.overdue)
                            viewModel.setSelectTaskStatus("overdue")
                        }
                        R.id.action_noOverdue -> {
                            binding.edFilter.setText(R.string.noOverdue)
                            viewModel.setSelectTaskStatus("no_overdue")
                        }
                        R.id.action_completed -> {
                            binding.edFilter.setText(R.string.completed)
                            viewModel.setSelectTaskStatus("completed")
                        }
                    }
                    true
                })
                popupMenu.show()
                viewModel.onSelectTaskDone()
            }
        }
        viewModel.selectProject.observe(viewLifecycleOwner){
            if (it !=null && it){
                viewModel.getSelectProjectApi()
                viewModel.onSelectProjectDone()
            }
        }

        viewModel.myProject.observe(viewLifecycleOwner){
            if (it != null){
                val popup = PopupMenu(
                    requireContext(), binding.tvProject)
                for (i in it.indices) {
                    popup.menu.add(
                        i, i, i,
                        it[i].name)
                }
                popup.setOnMenuItemClickListener { item ->
                    binding.tvProject.text = it[item.itemId].name
                    viewModel.setSelectProjectId(it[item.itemId].uuid)
                    false
                }
                popup.show()
            }

        }
        val onItemClickListener = OnIndexTaskClickListener{
            if (it !=null){
                Log.e("TAG", "onItemClickListener: $it" )
            }
        }
        binding.recyclerViewIndex.apply {
            adapter=IndexTaskAdapter(onItemClickListener)
        }
        val onItemClickListenerToday = OnTodayTaskClickListener{
            if (it !=null){
                Log.e("TAG", "onItemClickListener: $it" )
                viewModel.setSelectCompletedTask(it.id)
                viewModel.getSelectCompletedTaskApi()
            }
        }
        binding.recyclerViewToday.apply {
            adapter=TodayTaskAdapter(onItemClickListenerToday)
        }
        val onItemClickListenerUpcoming = OnUpcomingTaskClickListener{
            if (it !=null){
                Log.e("TAG", "onItemClickListener: $it" )
            }
        }
        binding.recyclerViewUpcoming.apply {
            adapter=UpcomingTaskAdapter(onItemClickListenerUpcoming)
        }
        val onItemClickListenerOverdue = OnOverdueTaskClickListener{
            if (it !=null){
                Log.e("TAG", "onItemClickListener: $it" )
            }
        }
        binding.recyclerViewOverdue.apply {
            adapter=OverdueTaskAdapter(onItemClickListenerOverdue)
        }
        val onItemClickListenerNoOverdue = OnNoOverdueTaskClickListener{
            if (it !=null){
                Log.e("TAG", "onItemClickListener: $it" )
            }
        }
        binding.recyclerViewNoOverdue.apply {
            adapter=NoOverdueTaskAdapter(onItemClickListenerNoOverdue)
        }
        val onItemClickListenerCompleted = OnCompletedTaskClickListener{
            if (it !=null){
                Log.e("TAG", "onItemClickListener: $it" )
            }
        }
        binding.recyclerViewCompleted.apply {
            adapter=CompletedTaskAdapter(onItemClickListenerCompleted)
        }
        viewModel.completedTask.observe(viewLifecycleOwner) {
            if (it !=null)
                viewModel.getMyTaskApi()
            mainActivityEventsListener.showSuccessMessage(it.message)
        }
        return binding.root
    }
}