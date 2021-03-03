package com.github.chantsune.rectitemdecoration.demo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.chantsune.rectitemdecoration.RectItemDecoration

class MarginFragment : Fragment(R.layout.fragment_default) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.recycler_view).also { recyclerView ->

            recyclerView.adapter = Adapter(20)
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

            recyclerView.addItemDecoration(
                RectItemDecoration(
                    RectItemDecoration.LineConfig(
                        marginTop = 10,
                        marginBottom = 10,
                        marginLeft = 10,
                        marginRight = 10,
                    )
                )
            )
        }
    }
}
