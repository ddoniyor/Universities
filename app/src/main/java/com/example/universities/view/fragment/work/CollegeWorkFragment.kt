package com.example.universities.view.fragment.work

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.universities.R
import com.example.universities.data.retrofit.RetrofitBuilder
import com.example.universities.databinding.FragmentCollegeWorkBinding
import com.example.universities.databinding.FragmentUniversityStudyBinding
import com.example.universities.view.adapter.GeneralListAdapter
import com.example.universities.viewmodel.CollegeWorkViewModel
import com.example.universities.viewmodel.factory.CollegeWorkFactory

class CollegeWorkFragment : Fragment() {

    private companion object{
        const val TAG = "College Work Fragment"
    }
    private fun log(message: String) {
        Log.d(TAG, message)
    }
    private var _binding: FragmentCollegeWorkBinding? = null
    private val binding get() = _binding!!
    private lateinit var collegeWorkViewModel : CollegeWorkViewModel
    private lateinit var generalListAdapter: GeneralListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setUpCollegeWorkViewModel()
        _binding = FragmentCollegeWorkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCollegeProfessions()
        getCollegeProfessionsResponse()

        generalListAdapter = GeneralListAdapter()
        binding.fragmentCollegeWorkRecycler.adapter = generalListAdapter

        binding.fragmentCollegeWorkSwipeRefresh.setOnRefreshListener {
            getCollegeProfessions()
        }
    }

    private fun getCollegeProfessions(){
        contentVisibility(false)
        collegeWorkViewModel.getCollegeProfessions()
    }
    private fun contentVisibility(visibility: Boolean){
        if (visibility){
            binding.fragmentCollegeWorkRecycler.visibility = View.VISIBLE
            binding.fragmentCollegeWorkSwipeRefresh.isRefreshing = false
        }else{
            binding.fragmentCollegeWorkRecycler.visibility = View.GONE
            binding.fragmentCollegeWorkSwipeRefresh.isRefreshing = true
        }
    }
    private fun getCollegeProfessionsResponse(){
        with(collegeWorkViewModel){
            collegesWorkResponse.observe(viewLifecycleOwner){professions->
                contentVisibility(true)
                if (professions!=null){
                    generalListAdapter.setGeneralItems(professions)
                }else{
                    Toast.makeText(requireContext(),"Упс, список профессий пуст",Toast.LENGTH_SHORT).show()
                }

                log("$professions collegesWorkResponse")
            }
            collegesWorkBadResponse.observe(viewLifecycleOwner){badResponse->
                contentVisibility(true)
                Toast.makeText(requireContext(), badResponse, Toast.LENGTH_SHORT).show()
                log("$badResponse collegesWorkBadResponse")
            }
            errorCollegesWork.observe(viewLifecycleOwner){error->
                contentVisibility(true)
                Toast.makeText(requireContext(), error,Toast.LENGTH_SHORT).show()
                log("$error errorCollegesWork")
            }
        }
    }
    private fun setUpCollegeWorkViewModel(){
        collegeWorkViewModel = ViewModelProvider(
            this,
            CollegeWorkFactory(RetrofitBuilder().getApiInterface(requireContext()))
        )[CollegeWorkViewModel::class.java]
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}