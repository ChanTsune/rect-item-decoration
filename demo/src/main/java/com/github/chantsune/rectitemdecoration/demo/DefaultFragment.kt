package com.github.chantsune.rectitemdecoration.demo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.chantsune.rectitemdecoration.RectItemDecoration

class DefaultFragment : Fragment(R.layout.fragment_default) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.recycler_view).also { recyclerView ->

            recyclerView.adapter = Adapter(20)
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

            recyclerView.addItemDecoration(
                RectItemDecoration()
            )
        }
    }
}
