package com.github.chantsune.rectitemdecoration.demo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.chantsune.rectitemdecoration.RectItemDecoration

class LineConfigLookUpFragment : Fragment(R.layout.fragment_default) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.recycler_view).also { recyclerView ->

            recyclerView.adapter = Adapter(20)
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 2).also {
                it.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (position in 0..1) 2 else 1
                    }
                }
            }

            recyclerView.addItemDecoration(
                RectItemDecoration().also {
                    it.topLineConfigLookUp = object : RectItemDecoration.SimpleLineConfigLookUp() {
                        override fun getIsEnable(position: Int): Boolean = false
                    }
                    it.bottomLineConfigLookUp =
                        object : RectItemDecoration.SimpleLineConfigLookUp() {
                            override fun getIsEnable(position: Int): Boolean = true
                        }
                    it.rightLineConfigLookUp =
                        object : RectItemDecoration.SimpleLineConfigLookUp() {
                            override fun getIsEnable(position: Int): Boolean = true
                        }
                    it.leftLineConfigLookUp = object : RectItemDecoration.SimpleLineConfigLookUp() {
                        override fun getIsEnable(position: Int): Boolean = false
                    }
                }
            )
        }
    }
}