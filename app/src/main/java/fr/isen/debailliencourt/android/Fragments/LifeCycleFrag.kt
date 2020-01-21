package fr.isen.debailliencourt.android.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.isen.debailliencourt.android.R
import kotlinx.android.synthetic.main.fragment_life_cycle.*


class LifeCycleFrag : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("TAG", "onCreateView")
        return inflater.inflate(R.layout.fragment_life_cycle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragText.text= "onViewCreated"
    }


}