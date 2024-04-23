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
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * ViewColor is a custom View that displays a circle with a specified color.
 *
 * @param context The current context.
 * @param attrs The attribute set, which can include custom attributes defined in XML.
 * @param defStyleAttr The default style to apply to this view.
 */
class ViewColor @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var backgroundColor: Int = Color.WHITE
    private val circlePaint = Paint()

    init {
        // Initialize paint for drawing the circle
        circlePaint.color = Color.BLACK
        circlePaint.style = Paint.Style.FILL
    }

    /**
     * Set the background color of the view.
     *
     * @param color The color to set as the background.
     */
    override fun setBackgroundColor(color: Int) {
        backgroundColor = Color.TRANSPARENT
        circlePaint.color = color
        invalidate() // Trigger redraw
    }

    /**
     * Called when the view should render its content.
     *
     * @param canvas The canvas on which the background will be drawn.
     */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(backgroundColor) // Set background color
        val centerX = width / 2f
        val centerY = height / 2f
        val radius = (Math.min(width, height) / 2 * 0.8).toFloat()
        canvas.drawCircle(centerX, centerY, radius, circlePaint) // Draw circle
    }
}