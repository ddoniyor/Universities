package com.example.universities.view.fragment.favorites

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.universities.R
import com.example.universities.data.retrofit.RetrofitBuilder
import com.example.universities.databinding.FragmentFavoritesBinding
import com.example.universities.databinding.FragmentSettingsBinding
import com.example.universities.viewmodel.FavouriteViewModel
import com.example.universities.viewmodel.factory.FavouriteFactory


class FavoritesFragment : Fragment() {

    private companion object{
        const val TAG = "Favorite Fragment"
    }
    private fun log(message: String) {
        Log.d(TAG, message)
    }
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private lateinit var favoriteViewModel : FavouriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        log("onCreateView")
        setUpFavoriteViewModel()
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    private fun setUpFavoriteViewModel() {
        favoriteViewModel = ViewModelProvider(
            this,
            FavouriteFactory(RetrofitBuilder().getApiInterface(requireContext()))
        )[FavouriteViewModel::class.java]
    }

    override fun onDestroyView() {
        log("onDestroyView")
        super.onDestroyView()
        _binding = null
    }
}