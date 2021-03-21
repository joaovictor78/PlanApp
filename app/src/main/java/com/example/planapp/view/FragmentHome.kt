package com.example.planapp.view
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.planapp.R
import com.example.planapp.controllers.HomeController


class FragmentHome: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        val controller = HomeController()
        val recyclerView: RecyclerView  = view.findViewById(R.id.rowlistview);
        recyclerView.adapter = HomeAdapter(controller.getBrokers())
        return view
    }
}