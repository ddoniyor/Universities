package com.example.universities.view.fragment.favorite

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.universities.R


class FavoriteFragment : Fragment() {

    private companion object{
        const val TAG = "Favorite Fragment"
    }
    private fun log(message: String) {
        Log.d(TAG, message)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }


}