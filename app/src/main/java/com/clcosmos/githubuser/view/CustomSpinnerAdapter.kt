/*
 * Copyright (C) 2024 The Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.clcosmos.githubuser.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.clcosmos.githubuser.R

/**
 * CustomSpinnerAdapter is a custom ArrayAdapter for displaying Spinner items with an icon and text.
 *
 * @param context The current context.
 * @param items The list of SpinnerItems to represent in the spinner.
 */
class CustomSpinnerAdapter(
    context: Context,
    private val items: List<SpinnerItem>
) : ArrayAdapter<SpinnerItem>(context, R.layout.spinner_item_layout, items) {

    /**
     * Get a View that displays the data at the specified position in the data set.
     *
     * @param position The position of the item within the adapter's data set of the item whose view we want.
     * @param convertView The old view to reuse, if possible.
     * @param parent The parent that this view will eventually be attached to.
     * @return A View corresponding to the data at the specified position.
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    /**
     * Get a View that displays in the drop down popup the data at the specified position in the data set.
     *
     * @param position The position of the item within the adapter's data set of the item whose view we want.
     * @param convertView The old view to reuse, if possible.
     * @param parent The parent that this view will eventually be attached to.
     * @return A View corresponding to the data at the specified position.
     */
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    /**
     * Get a custom View that displays the SpinnerItem at the specified position in the data set.
     *
     * @param position The position of the item within the adapter's data set of the item whose view we want.
     * @param convertView The old view to reuse, if possible.
     * @param parent The parent that this view will eventually be attached to.
     * @return A custom View corresponding to the SpinnerItem at the specified position.
     */
    private fun getCustomView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.spinner_item_layout, parent, false)

        val iconImageView = view.findViewById<ImageView>(R.id.iconImageView)
        val textTextView = view.findViewById<TextView>(R.id.textTextView)

        // Get the current item
        val currentItem = items[position]

        // Set icon and text for the item
        iconImageView.setImageResource(currentItem.iconId)
        textTextView.text = currentItem.text

        return view
    }
}