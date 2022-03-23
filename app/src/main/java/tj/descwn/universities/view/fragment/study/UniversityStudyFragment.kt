package tj.descwn.universities.view.fragment.study

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import tj.descwn.universities.R
import tj.descwn.universities.data.retrofit.RetrofitBuilder
import tj.descwn.universities.databinding.FragmentUniversityStudyBinding
import tj.descwn.universities.view.adapter.InstitutionsListAdapter
import tj.descwn.universities.viewmodel.UniversityStudyViewModel
import tj.descwn.universities.viewmodel.factory.UniversityStudyFactory

class UniversityStudyFragment : Fragment(), InstitutionsListAdapter.CallBackInterface {
    private companion object{
        const val TAG = "University Study Fragment"
    }
    private fun log(message: String) {
        Log.d(TAG, message)
    }

    private var _binding: FragmentUniversityStudyBinding? = null
    private val binding get() = _binding!!
    private lateinit var universityStudyViewModel: UniversityStudyViewModel
    private lateinit var institutionsListAdapter: InstitutionsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        log("onCreateView")
        setUpUniversityStudyViewModel()
        _binding = FragmentUniversityStudyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAllUniversities()
        getAllUniversitiesResponse()
        institutionsListAdapter = InstitutionsListAdapter(this)
        binding.fragmentUniversityStudyRecycler.adapter = institutionsListAdapter

        binding.fragmentUniversityStudySwipeRefresh.setOnRefreshListener {
            getAllUniversities()
        }
    }
    private fun getAllUniversities(){
        contentVisibility(false)
        universityStudyViewModel.getAllUniversities()
    }
    private fun contentVisibility(visibility: Boolean){
        if (visibility){
            binding.fragmentUniversityStudyRecycler.visibility = View.VISIBLE
            binding.fragmentUniversityStudySwipeRefresh.isRefreshing = false
        }else{
            binding.fragmentUniversityStudyRecycler.visibility = View.GONE
            binding.fragmentUniversityStudySwipeRefresh.isRefreshing = true

        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun getAllUniversitiesResponse(){
        with(universityStudyViewModel){
            universitiesStudyResponse.observe(viewLifecycleOwner) { institutions->
                contentVisibility(true)
                institutionsListAdapter.setInstitutions(institutions)

                log("$institutions universitiesStudyResponse")
            }
            universitiesStudyBadResponse.observe(viewLifecycleOwner){ badResponse->
                contentVisibility(true)
                Toast.makeText(requireContext(), badResponse, Toast.LENGTH_SHORT).show()
                log("$badResponse universitiesStudyBadResponse")
            }
            errorUniversitiesStudy.observe(viewLifecycleOwner){ error->
                contentVisibility(true)
                Toast.makeText(requireContext(), error,Toast.LENGTH_SHORT).show()
                log("$error errorUniversitiesStudy")
            }
        }
    }
    private fun setUpUniversityStudyViewModel(){
        universityStudyViewModel = ViewModelProvider(
            this,
            UniversityStudyFactory(RetrofitBuilder().getApiInterface(requireContext()))
        )[UniversityStudyViewModel::class.java]
    }
    override fun onDestroyView() {
        log("onDestroyView")
        super.onDestroyView()
        _binding = null
    }

    override fun goToInstitutionsDetail(id: Int) {
        log("$id goToInstitutionsDetail")

        val bundle = Bundle()
        id.let { bundle.putInt("institutionId", it)}
        if (findNavController().currentDestination?.id == R.id.universityStudyFragment) {
            findNavController().navigate(R.id.action_universityStudyFragment_to_institutions_detail, bundle)
        }
    }

}