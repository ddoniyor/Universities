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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import tj.descwn.universities.data.retrofit.RetrofitBuilder
import tj.descwn.universities.databinding.FragmentFacultiesBinding
import tj.descwn.universities.view.adapter.GeneralListAdapter
import tj.descwn.universities.viewmodel.InstitutionDetailViewModel
import tj.descwn.universities.viewmodel.factory.InstitutionDetailFactory

class FacultiesFragment : Fragment() {
    private companion object{
        const val TAG = "Faculties Fragment"
    }
    private fun log(message: String) {
        Log.d(TAG, message)
    }

    private var _binding: FragmentFacultiesBinding? = null
    private val binding get() = _binding!!
    private lateinit var institutionDetailViewModel :InstitutionDetailViewModel
    private lateinit var generalListAdapter: GeneralListAdapter
    private var institutionId:Int?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        setUpInstitutionDetailViewModel()
        institutionId = arguments?.getInt("institutionId")
        log("$institutionId institutionId")
        _binding = FragmentFacultiesBinding.inflate(inflater, container, false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getInstitutionFaculties()
        getInstitutionFacultiesResponse()

        generalListAdapter = GeneralListAdapter()
        binding.fragmentFacultiesRecycler.adapter = generalListAdapter

        binding.fragmentFacultiesSwipeRefresh.setOnRefreshListener {
            getInstitutionFaculties()
        }
    }
    private fun getInstitutionFaculties(){
        contentVisibility(false)
        if (institutionId!=null){
            institutionDetailViewModel.getInstitutionFaculties(institutionId!!)
        }

    }

    private fun contentVisibility(visibility: Boolean){
        if (visibility){
            binding.fragmentFacultiesRecycler.visibility = View.VISIBLE
            binding.fragmentFacultiesSwipeRefresh.isRefreshing = false
        }else{
            binding.fragmentFacultiesRecycler.visibility = View.GONE
            binding.fragmentFacultiesSwipeRefresh.isRefreshing = true
        }
    }
    private fun getInstitutionFacultiesResponse(){
        with(institutionDetailViewModel){
            institutionFacultiesResponse.observe(viewLifecycleOwner){faculties->
                contentVisibility(true)

                if (faculties!=null){
                    generalListAdapter.setGeneralItems(faculties)
                }else{
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Упс!")
                        .setMessage("Факультеты не найдены :(  Вернуться назад?")
                        .setPositiveButton("Ок") { dialog, _ ->
                            findNavController().popBackStack()
                            dialog.dismiss()
                        }
                        .show()
                }

                log("$faculties institutionFacultiesResponse")
            }
            institutionFacultiesBadResponse.observe(viewLifecycleOwner){badResponse->
                contentVisibility(true)
                Toast.makeText(requireContext(), badResponse, Toast.LENGTH_SHORT).show()
                log("$badResponse institutionFacultiesBadResponse")
            }
            errorInstitutionFaculties.observe(viewLifecycleOwner){error->
                contentVisibility(true)
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                log("$error errorInstitutionFaculties")
            }
        }
    }
    private fun setUpInstitutionDetailViewModel() {
        institutionDetailViewModel = ViewModelProvider(
            this,
            InstitutionDetailFactory(RetrofitBuilder().getApiInterface(requireContext()))
        )[InstitutionDetailViewModel::class.java]
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}