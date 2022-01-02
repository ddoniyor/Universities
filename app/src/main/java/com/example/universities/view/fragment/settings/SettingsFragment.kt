package com.example.universities.view.fragment.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.universities.App
import com.example.universities.R
import com.example.universities.databinding.FragmentSettingsBinding
import com.example.universities.view.adapter.SettingsAdapter
import java.lang.Exception


class SettingsFragment : Fragment(), SettingsAdapter.CallBackInterface {

    private companion object{
        const val TAG = "Settings Fragment"
    }
    private fun log(message: String) {
        Log.d(TAG, message)
    }
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var settingsAdapter: SettingsAdapter
    private var settings = arrayListOf<String>(*App.resourses.getStringArray(R.array.settings))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        log("onCreateView")
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        log("onViewCreated")
        settingsAdapter = SettingsAdapter(this)
        settingsAdapter.setSettingsList(settings)
        binding.settingsFragmentRecycler.adapter = settingsAdapter
    }
    override fun onDestroyView() {
        log("onDestroyView")
        super.onDestroyView()
        _binding = null
    }

    override fun settingsPosition(position: Int) {
        log("$position position")
        when(position){
            //Оставить отзыв
            0->{
                activity?.let {
                    val dialIntent = Intent(Intent.ACTION_SENDTO)
                    dialIntent.data = Uri.parse(getString(R.string.settings_fragment_owner_email))
                    dialIntent.putExtra(Intent.EXTRA_SUBJECT,getString(R.string.settings_fragment_feedback_app))

                    try {
                        it.startActivity(Intent.createChooser(dialIntent,getString(R.string.settings_fragment_choose_method)))
                    }catch (e:Exception){
                        Toast.makeText(requireContext(),"${e.message}",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            //Оценить приложение - ссылка на приложение
            1->{

            }
            //О приложении
            2->{
                if (findNavController().currentDestination?.id == R.id.settingsFragment){
                    findNavController().navigate(R.id.action_settingsFragment_to_aboutAppFragment)
                }
            }
        }
    }

}