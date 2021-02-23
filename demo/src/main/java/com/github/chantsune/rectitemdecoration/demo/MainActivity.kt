package com.github.chantsune.rectitemdecoration.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.chantsune.rectitemdecoration.RectItemDecoration

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.recycler_view).also { recyclerView ->
            recyclerView.adapter = object: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
                inner class ViewHolder(view: View): RecyclerView.ViewHolder(view)
                override fun onCreateViewHolder(
                    parent: ViewGroup,
                    viewType: Int
                ): RecyclerView.ViewHolder {
                    val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item, parent, false)
                    return ViewHolder(view)
                }

                override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                    holder.itemView.findViewById<AppCompatTextView>(R.id.text_view).also { textView ->
                        textView.text = "# Item $position"
                    }
                }

                override fun getItemCount(): Int = 20

            }
            recyclerView.layoutManager = GridLayoutManager(this, 2)
            recyclerView.addItemDecoration(RectItemDecoration())
        }
    }
}