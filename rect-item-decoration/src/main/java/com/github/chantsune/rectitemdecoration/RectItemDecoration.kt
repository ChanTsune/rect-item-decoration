package com.github.chantsune.rectitemdecoration

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

class RectItemDecoration : RecyclerView.ItemDecoration() {
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        c.save()
        for ((i, child) in parent.children.withIndex()) {
            val rect = Rect()
            parent.getDecoratedBoundsWithMargins(child, rect)
            onItemDraw(c, rect, child, parent, state, i)
        }
        c.restore()
    }

    private fun onItemDraw(
        c: Canvas,
        rect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
        position: Int
    ) {
        val paint = Paint().apply {
            strokeWidth = 2f
            style = Paint.Style.STROKE
        }
        val marginTop = 20
        val marginBottom = 20
        val marginRight = 20
        val marginLeft = 20
        // Left
        c.drawLine(
            rect.left.toFloat() + marginLeft,
            rect.top.toFloat() + marginTop,
            rect.left.toFloat() + marginLeft,
            rect.bottom.toFloat() - marginBottom, paint
        )
        // Right
        c.drawLine(
            rect.right.toFloat() - marginRight,
            rect.top.toFloat() + marginTop,
            rect.right.toFloat() - marginRight,
            rect.bottom.toFloat() - marginBottom, paint
        )
        // Top
        c.drawLine(
            rect.left.toFloat() + marginLeft,
            rect.top.toFloat() + marginTop,
            rect.right.toFloat() - marginRight,
            rect.top.toFloat() + marginTop, paint
        )
        // Bottom
        c.drawLine(
            rect.left.toFloat() + marginLeft,
            rect.bottom.toFloat() - marginBottom,
            rect.right.toFloat() - marginRight,
            rect.bottom.toFloat() - marginBottom, paint
        )
    }
}
