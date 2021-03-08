package com.github.chantsune.rectitemdecoration.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.chantsune.rectitemdecoration.RectItemDecoration

class MainFragment : Fragment(R.layout.fragment_main) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.recycler_view).also { recyclerView ->

            recyclerView.adapter = object : DemoListAdapter() {
                override fun onItemClick(item: Demo, position: Int) {
                    when (item) {
                        Demo.DEFAULT -> findNavController().navigate(
                            MainFragmentDirections.actionMainFragmentToDefaultFragment()
                        )
                        Demo.MARGIN -> findNavController().navigate(
                            MainFragmentDirections.actionMainFragmentToMarginFragment()
                        )
                        Demo.CUSTOM -> findNavController().navigate(
                            MainFragmentDirections.actionMainFragmentToCustomFragment()
                        )
                        Demo.LINE_CONFIG_LOOK_UP -> findNavController().navigate(
                            MainFragmentDirections.actionMainFragmentToLineConfigLookUpFragment()
                        )
                    }
                }
            }
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)

            recyclerView.addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
        }
    }

    abstract class DemoListAdapter : RecyclerView.Adapter<DemoListAdapter.ViewHolder>() {
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.view_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = Demo.values()[position]
            holder.itemView.findViewById<AppCompatTextView>(R.id.text_view).also { textView ->
                textView.isClickable = true
                textView.text = item.name
                textView.setOnClickListener {
                    onItemClick(item, position)
                }
            }
        }

        override fun getItemCount(): Int = Demo.values().size

        abstract fun onItemClick(item: Demo, position: Int)
    }

    enum class Demo {
        DEFAULT,
        MARGIN,
        CUSTOM,
        LINE_CONFIG_LOOK_UP
    }
}
