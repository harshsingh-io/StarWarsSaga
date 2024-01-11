package com.codeenemy.assignmentapp.ui

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.codeenemy.assignmentapp.R

/**
 * [SplashFragment] is a fragment displaying a splash screen while navigating to the characters list.
 */
class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        // Use a Handler to delay navigation to the characters list after 3 seconds
        Handler().postDelayed(Runnable {
            findNavController().navigate(R.id.action_splashFragment_to_charactersFragment)
        }, 3000)

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
}
