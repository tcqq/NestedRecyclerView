package com.example.nestedrecyclerview.ext

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 * @author Perry Lance
 * @since 2020-08-16 Created
 */
fun EditText.onFocusChanged(hasFocus: (Boolean) -> Unit) {
    this.setOnFocusChangeListener { _, b -> hasFocus(b) }
}

fun EditText.OnTextChangedListener(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {}

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            p0?.let {
                afterTextChanged(editableText.toString())
            }
        }
    })
}

fun TabLayout.setupWithViewPager2(viewPager: ViewPager2, labels: List<String>) {
    if (labels.size != viewPager.adapter?.itemCount) {
        throw Exception("The size of list and the tab count should be equal!")
    }

    TabLayoutMediator(this, viewPager) { tab, position ->
        tab.text = labels[position]
    }.attach()
}

/**
 * To allow equal width for each tab, while (TabLayout.MODE_SCROLLABLE)
 */
fun TabLayout.allotEachTabWithEqualWidth() {
    val slidingTabStrip = getChildAt(0) as ViewGroup
    for (i in 0 until tabCount) {
        val tab = slidingTabStrip.getChildAt(i)
        val layoutParams = tab.layoutParams as LinearLayout.LayoutParams
        layoutParams.weight = 1f
        tab.layoutParams = layoutParams
    }
}

/** makes visible a view. */
fun View.visible() {
    visibility = View.VISIBLE
}

/** makes gone a view. */
fun View.gone() {
    visibility = View.GONE
}

fun Context.color(resource: Int): Int {
    return ContextCompat.getColor(this, resource)
}

fun Fragment.color(resource: Int): Int {
    context?.let {
        return ContextCompat.getColor(it, resource)
    }
    return 0
}

fun RecyclerView.ViewHolder.getString(@StringRes string: Int): String {
    return itemView.context.getString(string)
}

fun RecyclerView.ViewHolder.getString(@StringRes string: Int, vararg arg: String): String {
    return itemView.context.getString(string, *arg)
}

fun RecyclerView.ViewHolder.color(@ColorRes resource: Int): Int {
    return itemView.context.color(resource)
}
