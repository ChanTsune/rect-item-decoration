package com.github.chantsune.rectitemdecoration

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

class RectItemDecoration(
    var topLine: LineConfig = LineConfig(),
    var bottomLine: LineConfig = LineConfig(),
    var rightLine: LineConfig = LineConfig(),
    var leftLine: LineConfig = LineConfig(),
) : RecyclerView.ItemDecoration() {
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
        if (topLine.isEnable) {
            c.drawLine(
                rect.left.toFloat() + topLine.marginLeft,
                rect.top.toFloat() + topLine.marginTop,
                rect.right.toFloat() - topLine.marginRight,
                rect.top.toFloat() + topLine.marginTop,
                topLine.paint
            )
        }
        if (bottomLine.isEnable) {
            c.drawLine(
                rect.left.toFloat() + bottomLine.marginLeft,
                rect.bottom.toFloat() - bottomLine.marginBottom,
                rect.right.toFloat() - bottomLine.marginRight,
                rect.bottom.toFloat() - bottomLine.marginBottom,
                bottomLine.paint
            )
        }
        if (rightLine.isEnable) {
            c.drawLine(
                rect.right.toFloat() - rightLine.marginRight,
                rect.top.toFloat() + rightLine.marginTop,
                rect.right.toFloat() - rightLine.marginRight,
                rect.bottom.toFloat() - rightLine.marginBottom,
                rightLine.paint
            )
        }
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
}
