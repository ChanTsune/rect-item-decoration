package com.github.chantsune.rectitemdecoration.demo

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class MainFragment : Fragment(R.layout.fragment_main) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<AppCompatButton>(R.id.default_button).also { button ->
            button.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToDefaultFragment())
            }
        }
    }
}
