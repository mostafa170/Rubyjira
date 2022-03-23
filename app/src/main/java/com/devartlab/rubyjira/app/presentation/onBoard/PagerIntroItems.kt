package com.devartlab.rubyjira.app.presentation.onBoard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.devartlab.rubyjira.databinding.ItemIntroBinding

class PagerIntroItems(private val context: Context, private val items: List<IntroItem>) : PagerAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = ItemIntroBinding.inflate(inflater)
        container.addView(binding.root)

        binding.item = items[position]
        binding.executePendingBindings()

        return binding.root
    }
}