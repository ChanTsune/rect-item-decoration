package com.github.chantsune.rectitemdecoration.demo

import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.chantsune.rectitemdecoration.RectItemDecoration

class CustomFragment : Fragment(R.layout.fragment_default) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.recycler_view).also { recyclerView ->

            recyclerView.adapter = Adapter(20)
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

            val paint = Paint().apply {
                color = resources.getColor(R.color.purple_700, null)
                strokeWidth = 2f
            }
            recyclerView.addItemDecoration(
                RectItemDecoration(
                    topLine = RectItemDecoration.LineConfig(isEnable = false),
                    bottomLine = RectItemDecoration.LineConfig(
                        marginBottom = -2,
                        marginRight = 15,
                        marginLeft = 15,
                        paint = paint,
                    ),
                    rightLine = RectItemDecoration.LineConfig(
                        marginRight = -2,
                        marginBottom = 15,
                        marginTop = 15,
                        paint = paint,
                    ),
                    leftLine = RectItemDecoration.LineConfig(isEnable = false),
                )
            )
        }
    }
}
