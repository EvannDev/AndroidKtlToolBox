package fr.isen.debailliencourt.android.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.isen.debailliencourt.android.R
import kotlinx.android.synthetic.main.fragment_life_cycle.*


class LifeCycleFrag2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("TAG", "onCreateView")
        return inflater.inflate(R.layout.fragment_life_cycle_frag2, container, false)
    }

}
