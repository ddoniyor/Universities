package tj.descwn.universities.view.fragment.study

import android.content.Intent
import android.net.Uri
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
import tj.descwn.universities.data.model.Institutions
import tj.descwn.universities.data.retrofit.RetrofitBuilder
import tj.descwn.universities.databinding.FragmentDetailInstitutionBinding
import tj.descwn.universities.viewmodel.InstitutionDetailViewModel
import tj.descwn.universities.viewmodel.factory.InstitutionDetailFactory


class InstitutionDetailFragment : Fragment() {

    private companion object{
        const val TAG = "Institution Detail Fragment"
    }
    private fun log(message: String) {
        Log.d(TAG, message)
    }
    private var _binding: FragmentDetailInstitutionBinding? = null
    private val binding get() = _binding!!
    private lateinit var institutionDetailViewModel :InstitutionDetailViewModel
    private var _institutionDetail = Institutions.InstitutionDetail()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setUpInstitutionDetailViewModel()
        val institutionId = arguments?.getInt("institutionId")
        log("$institutionId institutionId")
        institutionDetailViewModel.getInstitutionDetail(institutionId!!)
        _binding = FragmentDetailInstitutionBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contentVisibility(false)
        getInstitutionDetailResponse()

        binding.fragmentDetailInstitutionFaculties.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("institutionId", _institutionDetail.id!!)
            //_institutionDetail.let { bundle.putInt("institutionId", it.id)}
            if (findNavController().currentDestination?.id==R.id.institutionDetailFragment){
                findNavController().navigate(R.id.action_institutionDetailFragment_to_facultiesFragment,bundle)
            }
        }

        binding.fragmentDetailInstitutionCoordinates.setOnClickListener {
            showInstitutionOnMap(_institutionDetail.latitude,_institutionDetail.longitude)
        }
        binding.fragmentDetailInstitutionPhoneNumber.setOnClickListener {
            callToInstitution(_institutionDetail.phoneNumber)
        }
        binding.fragmentDetailInstitutionEmail.setOnClickListener {
            emailToInstitution(_institutionDetail.email)
        }
        binding.fragmentDetailInstitutionWebSite.setOnClickListener {
            openInstitutionWebSite(_institutionDetail.webSite)
        }

    }
    private fun contentVisibility(visibility: Boolean){
        if (visibility){
            binding.fragmentDetailInstitutionNestedScrollView.visibility = View.VISIBLE
            binding.fragmentDetailInstitutionProgressBar.visibility = View.GONE
        }else{
            binding.fragmentDetailInstitutionNestedScrollView.visibility = View.GONE
            binding.fragmentDetailInstitutionProgressBar.visibility = View.VISIBLE
        }
    }
    private fun getInstitutionDetailResponse(){
        with(institutionDetailViewModel){
            institutionDetailResponse.observe(viewLifecycleOwner){ institutionDetail->
                contentVisibility(true)
                _institutionDetail = institutionDetail
                binding.fragmentDetailInstitutionName.text = institutionDetail.name
                binding.fragmentDetailInstitutionDescription.text = institutionDetail.description

                log("$institutionDetail institutionDetailResponse")
            }

            institutionDetailBadResponse.observe(viewLifecycleOwner){ badResponse->
                Toast.makeText(requireContext(), badResponse,Toast.LENGTH_SHORT).show()
                log("$badResponse institutionDetailBadResponse")
            }

            errorInstitutionDetail.observe(viewLifecycleOwner){error->
                Toast.makeText(requireContext(), error,Toast.LENGTH_SHORT).show()
                log("$error errorInstitutionDetail")
            }
        }
    }
    private fun showInstitutionOnMap(latitude: String?,longitude:String?){
        val locationUri = Uri.parse("geo:${latitude},${longitude}")
        if (latitude !=null && longitude !=null){
            activity?.let {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = locationUri
                it.startActivity(intent)
            }
        }else{
            Toast.makeText(
                requireContext(),
                getString(R.string.unknown_message),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    private fun callToInstitution(phoneNumber:String?){
        if (phoneNumber!=null){
            activity?.let {
                val dialIntent = Intent(Intent.ACTION_VIEW)
                dialIntent.data = Uri.parse("tel:$phoneNumber")
                it.startActivity(dialIntent)
            }
        }else{
            Toast.makeText(
                requireContext(),
                getString(R.string.unknown_message),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    private fun emailToInstitution(email:String?){
        if (email!=null){
            activity?.let {
                val dialIntent = Intent(Intent.ACTION_SENDTO)
                dialIntent.data = Uri.parse("mailto: $email")
                try {
                    it.startActivity(Intent.createChooser(dialIntent,getString(R.string.settings_fragment_choose_method)))
                }catch (e: Exception){
                    Toast.makeText(requireContext(),"${e.message}",Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            Toast.makeText(
                requireContext(),
                getString(R.string.unknown_message),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    private fun openInstitutionWebSite(link:String?){
        if (link!=null){
            activity?.let {
                val browserIntent = Intent(Intent.ACTION_VIEW)
                browserIntent.data = Uri.parse(link)
                it.startActivity(browserIntent)
            }
        }else{
            Toast.makeText(
                requireContext(),
                getString(R.string.unknown_message),
                Toast.LENGTH_SHORT
            ).show()
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