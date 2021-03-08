package com.github.chantsune.rectitemdecoration

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

open class RectItemDecoration(
    var topLineConfigLookUp: LineConfigLookUp,
    var bottomLineConfigLookUp: LineConfigLookUp,
    var rightLineConfigLookUp: LineConfigLookUp,
    var leftLineConfigLookUp: LineConfigLookUp,
) : RecyclerView.ItemDecoration() {
    constructor(
        topLine: LineConfig,
        bottomLine: LineConfig,
        rightLine: LineConfig,
        leftLine: LineConfig,
    ) : this(
        DefaultLineConfigLookUp(topLine),
        DefaultLineConfigLookUp(bottomLine),
        DefaultLineConfigLookUp(rightLine),
        DefaultLineConfigLookUp(leftLine),
    )

    constructor(line: LineConfig = LineConfig()) : this(
        line,
        line,
        line,
        line,
    )

    constructor(lineConfigLookUp: LineConfigLookUp) : this(
        lineConfigLookUp,
        lineConfigLookUp,
        lineConfigLookUp,
        lineConfigLookUp,
    )

    data class LineConfig(
        var isEnable: Boolean = true,
        var marginTop: Int = 0,
        var marginBottom: Int = 0,
        var marginRight: Int = 0,
        var marginLeft: Int = 0,
        var paint: Paint = Paint(),
    )

    abstract class LineConfigLookUp {
        abstract fun getIsEnable(position: Int): Boolean
        abstract fun getMarginTop(position: Int): Int
        abstract fun getMarginBottom(position: Int): Int
        abstract fun getMarginRight(position: Int): Int
        abstract fun getMarginLeft(position: Int): Int
        abstract fun getPaint(position: Int): Paint
    }

    abstract class SimpleLineConfigLookUp : LineConfigLookUp() {
        override fun getIsEnable(position: Int): Boolean = true
        override fun getMarginTop(position: Int): Int = 0
        override fun getMarginBottom(position: Int): Int = 0
        override fun getMarginRight(position: Int): Int = 0
        override fun getMarginLeft(position: Int): Int = 0
        override fun getPaint(position: Int): Paint = Paint()
    }

    private class DefaultLineConfigLookUp(val line: LineConfig) : LineConfigLookUp() {
        override fun getIsEnable(position: Int): Boolean = line.isEnable
        override fun getMarginTop(position: Int): Int = line.marginTop
        override fun getMarginBottom(position: Int): Int = line.marginBottom
        override fun getMarginRight(position: Int): Int = line.marginRight
        override fun getMarginLeft(position: Int): Int = line.marginLeft
        override fun getPaint(position: Int): Paint = line.paint
    }

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
    ) {
        if (topLineConfigLookUp.getIsEnable(position)) {
            c.drawLine(
                rect.left.toFloat() + topLineConfigLookUp.getMarginLeft(position),
                rect.top.toFloat() + topLineConfigLookUp.getMarginTop(position),
                rect.right.toFloat() - topLineConfigLookUp.getMarginRight(position),
                rect.top.toFloat() + topLineConfigLookUp.getMarginTop(position),
                topLineConfigLookUp.getPaint(position)
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
    ) {
        if (bottomLineConfigLookUp.getIsEnable(position)) {
            c.drawLine(
                rect.left.toFloat() + bottomLineConfigLookUp.getMarginLeft(position),
                rect.bottom.toFloat() - bottomLineConfigLookUp.getMarginBottom(position),
                rect.right.toFloat() - bottomLineConfigLookUp.getMarginRight(position),
                rect.bottom.toFloat() - bottomLineConfigLookUp.getMarginBottom(position),
                bottomLineConfigLookUp.getPaint(position)
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
    ) {
        if (rightLineConfigLookUp.getIsEnable(position)) {
            c.drawLine(
                rect.right.toFloat() - rightLineConfigLookUp.getMarginRight(position),
                rect.top.toFloat() + rightLineConfigLookUp.getMarginTop(position),
                rect.right.toFloat() - rightLineConfigLookUp.getMarginRight(position),
                rect.bottom.toFloat() - rightLineConfigLookUp.getMarginBottom(position),
                rightLineConfigLookUp.getPaint(position)
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
    ) {
        if (leftLineConfigLookUp.getIsEnable(position)) {
            c.drawLine(
                rect.left.toFloat() + leftLineConfigLookUp.getMarginLeft(position),
                rect.top.toFloat() + leftLineConfigLookUp.getMarginTop(position),
                rect.left.toFloat() + leftLineConfigLookUp.getMarginLeft(position),
                rect.bottom.toFloat() - leftLineConfigLookUp.getMarginBottom(position),
                leftLineConfigLookUp.getPaint(position)
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
        drawTopLine(c, rect, view, parent, state, position)
        drawBottomLine(c, rect, view, parent, state, position)
        drawRightLine(c, rect, view, parent, state, position)
        drawLeftLine(c, rect, view, parent, state, position)
    }
}
