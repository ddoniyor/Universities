package tj.descwn.universities.view.fragment.work

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import tj.descwn.universities.data.retrofit.RetrofitBuilder
import tj.descwn.universities.databinding.FragmentUniversityWorkBinding
import tj.descwn.universities.view.adapter.GeneralListAdapter
import tj.descwn.universities.viewmodel.UniversityWorkViewModel
import tj.descwn.universities.viewmodel.factory.UniversityWorkFactory


class UniversityWorkFragment : Fragment() {

    private companion object{
        const val TAG = "University Work Fragment"
    }
    private fun log(message: String) {
        Log.d(TAG, message)
    }
    private var _binding: FragmentUniversityWorkBinding? = null
    private val binding get() = _binding!!
    private lateinit var universityWorkViewModel : UniversityWorkViewModel
    private lateinit var generalListAdapter: GeneralListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setUpUniversityWorkViewModel()
        _binding = FragmentUniversityWorkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getUniversityProfessions()
        getUniversityProfessionsResponse()

        generalListAdapter = GeneralListAdapter()
        binding.fragmentUniversityWorkRecycler.adapter = generalListAdapter

        binding.fragmentUniversityWorkSwipeRefresh.setOnRefreshListener {
            getUniversityProfessions()
        }

    }

    private fun getUniversityProfessions(){
        contentVisibility(false)
        universityWorkViewModel.getUniversityProfessions()
    }
    private fun contentVisibility(visibility: Boolean){
        if (visibility){
            binding.fragmentUniversityWorkRecycler.visibility = View.VISIBLE
            binding.fragmentUniversityWorkSwipeRefresh.isRefreshing = false
        }else{
            binding.fragmentUniversityWorkRecycler.visibility = View.GONE
            binding.fragmentUniversityWorkSwipeRefresh.isRefreshing = true
        }
    }
    private fun getUniversityProfessionsResponse(){
        with(universityWorkViewModel){
            universitiesWorkResponse.observe(viewLifecycleOwner){professions->
                contentVisibility(true)
                if (professions!=null){
                    generalListAdapter.setGeneralItems(professions)
                }else{
                    Toast.makeText(requireContext(),"Упс, список профессий пуст",Toast.LENGTH_SHORT).show()
                }

                log("$professions universitiesWorkResponse")
            }
            universitiesWorkBadResponse.observe(viewLifecycleOwner){badResponse->
                contentVisibility(true)
                Toast.makeText(requireContext(), badResponse, Toast.LENGTH_SHORT).show()
                log("$badResponse universitiesWorkBadResponse")
            }
            errorUniversitiesWork.observe(viewLifecycleOwner){error->
                contentVisibility(true)
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                log("$error errorUniversitiesWork")
            }
        }
    }

    private fun setUpUniversityWorkViewModel(){
        universityWorkViewModel = ViewModelProvider(
            this,
            UniversityWorkFactory(RetrofitBuilder().getApiInterface(requireContext()))
        )[UniversityWorkViewModel::class.java]
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}