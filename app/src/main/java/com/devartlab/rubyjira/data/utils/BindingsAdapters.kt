package com.devartlab.rubyjira.data.utils

import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devartlab.rubyjira.R
import com.devartlab.rubyjira.app.presentation.home.*
import com.devartlab.rubyjira.domain.entities.tasks.*

@BindingAdapter("myImageResource")
fun bindImageViewResourceId(imageView: ImageView, resourceId: Int) {
    imageView.setImageResource(resourceId)
}



@BindingAdapter("app:tint")
fun ImageView.setImageTint(@ColorInt color: Int) {
    setColorFilter(color)
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view.context).load(url).into(view)
}


@BindingAdapter("imageUrlCircle")
fun bindImageViewWithCircleGlideUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView.context).load(PATH_IMAGE+url)
        .error(R.drawable.user_logo)
        .circleCrop().into(imageView)
}

@BindingAdapter("indexTaskItems")
fun bindIndexTaskItemsRecyclerView(recyclerView: RecyclerView, data: List<IndexEntities>?) {
    recyclerView.adapter?.let {
        val adapter = it as IndexTaskAdapter
        adapter.submitList(data)
    }
}

@BindingAdapter("todayTaskItems")
fun bindTodayTaskItemsRecyclerView(recyclerView: RecyclerView, data: List<TodayEntities>?) {
    recyclerView.adapter?.let {
        val adapter = it as TodayTaskAdapter
        adapter.submitList(data)
    }
}

@BindingAdapter("upcomingTaskItems")
fun bindUpcomingTaskItemsRecyclerView(recyclerView: RecyclerView, data: List<UpcomingEntities>?) {
    recyclerView.adapter?.let {
        val adapter = it as UpcomingTaskAdapter
        adapter.submitList(data)
    }
}

@BindingAdapter("overdueTaskItems")
fun bindOverdueTaskItemsRecyclerView(recyclerView: RecyclerView, data: List<OverdueEntities>?) {
    recyclerView.adapter?.let {
        val adapter = it as OverdueTaskAdapter
        adapter.submitList(data)
    }
}

@BindingAdapter("noOverdueTaskItems")
fun bindNoOverdueTaskItemsRecyclerView(recyclerView: RecyclerView, data: List<NoOverdueEntities>?) {
    recyclerView.adapter?.let {
        val adapter = it as NoOverdueTaskAdapter
        adapter.submitList(data)
    }
}

@BindingAdapter("completedTaskItems")
fun bindCompletedTaskItemsRecyclerView(recyclerView: RecyclerView, data: List<CompletedEntities>?) {
    recyclerView.adapter?.let {
        val adapter = it as CompletedTaskAdapter
        adapter.submitList(data)
    }
}

