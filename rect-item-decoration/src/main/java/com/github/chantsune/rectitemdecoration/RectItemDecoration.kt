package com.github.chantsune.rectitemdecoration

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

open class RectItemDecoration(
    var topLine: LineConfig,
    var bottomLine: LineConfig,
    var rightLine: LineConfig,
    var leftLine: LineConfig,
) : RecyclerView.ItemDecoration() {

    constructor(line: LineConfig = LineConfig()) : this(
        line,
        line,
        line,
        line
    )

    data class LineConfig(
        var isEnable: Boolean = true,
        var marginTop: Int = 0,
        var marginBottom: Int = 0,
        var marginRight: Int = 0,
        var marginLeft: Int = 0,
        var paint: Paint = Paint(),
    )

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        c.save()
        for ((i, child) in parent.children.withIndex()) {
            val rect = Rect()
            parent.getDecoratedBoundsWithMargins(child, rect)
            rect.right -= 1
            rect.bottom -= 1
            onItemDraw(c, rect, child, parent, state, i)
        }
        c.restore()
    }

    private fun drawTopLine(
        c: Canvas,
        rect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
        position: Int,
        topLine: LineConfig
    ) {
        if (topLine.isEnable) {
            c.drawLine(
                rect.left.toFloat() + topLine.marginLeft,
                rect.top.toFloat() + topLine.marginTop,
                rect.right.toFloat() - topLine.marginRight,
                rect.top.toFloat() + topLine.marginTop,
                topLine.paint
            )
        }
    }

    private fun drawBottomLine(
        c: Canvas,
        rect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
        position: Int,
        bottomLine: LineConfig
    ) {
        if (bottomLine.isEnable) {
            c.drawLine(
                rect.left.toFloat() + bottomLine.marginLeft,
                rect.bottom.toFloat() - bottomLine.marginBottom,
                rect.right.toFloat() - bottomLine.marginRight,
                rect.bottom.toFloat() - bottomLine.marginBottom,
                bottomLine.paint
            )
        }

    }

    private fun drawRightLine(
        c: Canvas,
        rect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
        position: Int,
        rightLine: LineConfig
    ) {
        if (rightLine.isEnable) {
            c.drawLine(
                rect.right.toFloat() - rightLine.marginRight,
                rect.top.toFloat() + rightLine.marginTop,
                rect.right.toFloat() - rightLine.marginRight,
                rect.bottom.toFloat() - rightLine.marginBottom,
                rightLine.paint
            )
        }

    }

    private fun drawLeftLine(
        c: Canvas,
        rect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
        position: Int,
        leftLine: LineConfig
    ) {
        if (leftLine.isEnable) {
            c.drawLine(
                rect.left.toFloat() + leftLine.marginLeft,
                rect.top.toFloat() + leftLine.marginTop,
                rect.left.toFloat() + leftLine.marginLeft,
                rect.bottom.toFloat() - leftLine.marginBottom,
                leftLine.paint
            )
        }
    }

    private fun onItemDraw(
        c: Canvas,
        rect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
        position: Int
    ) {
        drawTopLine(c, rect, view, parent, state, position, topLine.copy())
        drawBottomLine(c, rect, view, parent, state, position, bottomLine.copy())
        drawRightLine(c, rect, view, parent, state, position, rightLine.copy())
        drawLeftLine(c, rect, view, parent, state, position, leftLine.copy())
    }
}
