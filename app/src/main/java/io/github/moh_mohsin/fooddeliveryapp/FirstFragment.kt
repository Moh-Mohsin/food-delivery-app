package io.github.moh_mohsin.fooddeliveryapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import io.github.moh_mohsin.fooddeliveryapp.core.BaseFragment

class FirstFragment : BaseFragment(R.layout.fragment_first) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {

        }
    }

    override fun invalidate() {
        TODO("Not yet implemented")
    }
}