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

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Custom item decorator for a RecyclerView.
 *
 * This class is used to add custom spacing and decoration color to the items of a RecyclerView.
 *
 * @property spacing The spacing to be added around the items.
 * @property decorationColor The color of the decoration.
 */
class CustomItemDecorator(private val spacing: Int, private val decorationColor: Int) : RecyclerView.ItemDecoration() {

    private val paint = Paint()

    init {
        paint.color = decorationColor
    }

    /**
     * Called to calculate the offsets for an item.
     *
     * This method is used to add spacing around the item.
     */
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.left = spacing
        outRect.right = spacing
        outRect.bottom = spacing

        // Add top margin for the first item to avoid double spacing between items
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = spacing
        } else {
            outRect.top = 0
        }
        val itemPosition = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter?.itemCount ?: 0
        if (itemPosition == itemCount - 1) {
            // Skip adding bottom offset for the last item
            outRect.bottom = 0
        } else {
            outRect.bottom = spacing
        }
    }

    /**
     * Called to draw decorations on the canvas.
     *
     * This method is used to draw a rectangle of the decoration color at the bottom of each item.
     */
    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(canvas, parent, state)
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + spacing

            canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
        }
    }
}