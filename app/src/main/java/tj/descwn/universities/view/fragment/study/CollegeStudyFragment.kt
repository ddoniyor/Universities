package tj.descwn.universities.view.fragment.study

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import tj.descwn.universities.R
import tj.descwn.universities.data.retrofit.RetrofitBuilder
import tj.descwn.universities.databinding.FragmentCollegeStudyBinding
import tj.descwn.universities.view.adapter.InstitutionsListAdapter
import tj.descwn.universities.viewmodel.CollegeStudyViewModel
import tj.descwn.universities.viewmodel.factory.CollegeStudyFactory


class CollegeStudyFragment : Fragment(), InstitutionsListAdapter.CallBackInterface {

    private companion object{
        const val TAG = "College Study Fragment"
    }
    private fun log(message: String) {
        Log.d(TAG, message)
    }

    private var _binding: FragmentCollegeStudyBinding? = null
    private val binding get() = _binding!!
    private lateinit var collegeStudyViewModel: CollegeStudyViewModel
    private lateinit var institutionsListAdapter: InstitutionsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setUpCollegeStudyViewModel()
        _binding = FragmentCollegeStudyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAllColleges()
        getAllCollegesResponse()
        institutionsListAdapter = InstitutionsListAdapter(this)
        binding.fragmentCollegeStudyRecycler.adapter = institutionsListAdapter

        binding.fragmentCollegeStudySwipeRefresh.setOnRefreshListener {
            getAllColleges()
        }
    }
    private fun getAllColleges(){
        contentVisibility(false)
        collegeStudyViewModel.getAllColleges()
    }
    private fun contentVisibility(visibility: Boolean){
        if (visibility){
            binding.fragmentCollegeStudyRecycler.visibility = View.VISIBLE
            binding.fragmentCollegeStudySwipeRefresh.isRefreshing = false
        }else{
            binding.fragmentCollegeStudyRecycler.visibility = View.GONE
            binding.fragmentCollegeStudySwipeRefresh.isRefreshing = true
        }
    }
    private fun getAllCollegesResponse(){
        with(collegeStudyViewModel){
            collegesStudyResponse.observe(viewLifecycleOwner){institutions->
                contentVisibility(true)
                institutionsListAdapter.setInstitutions(institutions)
                log("$institutions collegesStudyResponse")
            }
            collegesStudyBadResponse.observe(viewLifecycleOwner){badResponse->
               contentVisibility(true)
                Toast.makeText(requireContext(), badResponse,Toast.LENGTH_SHORT).show()
                log("$badResponse collegesStudyBadResponse")
            }
            errorCollegesStudy.observe(viewLifecycleOwner){error->
                contentVisibility(true)
                Toast.makeText(requireContext(), error,Toast.LENGTH_SHORT).show()
                log("$error errorCollegesStudy")
            }
        }
    }
    private fun setUpCollegeStudyViewModel(){
        collegeStudyViewModel = ViewModelProvider(
            this,
            CollegeStudyFactory(RetrofitBuilder().getApiInterface(requireContext()))
        )[CollegeStudyViewModel::class.java]
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun goToInstitutionsDetail(id: Int) {
        log("$id goToInstitutionsDetail")
        val bundle = Bundle()
        id.let { bundle.putInt("institutionId", it)}
        if (findNavController().currentDestination?.id == R.id.collegeStudyFragment) {
            findNavController().navigate(R.id.action_collegeStudyFragment_to_institutions_detail, bundle)
        }
    }


}