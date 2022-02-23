package com.example.universities.view.fragment.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.universities.R
import com.example.universities.data.retrofit.RetrofitBuilder
import com.example.universities.databinding.FragmentSearchBinding
import com.example.universities.view.adapter.InstitutionsListAdapter
import com.example.universities.viewmodel.SearchViewModel
import com.example.universities.viewmodel.factory.SearchFactory


class SearchFragment : Fragment(), InstitutionsListAdapter.CallBackInterface {
    private companion object{
        const val TAG = "Search Fragment"
    }
    private fun log(message: String) {
        Log.d(TAG, message)
    }
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var searchViewModel :SearchViewModel
    private lateinit var institutionsListAdapter: InstitutionsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setUpSearchViewModel()
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchInstitutionsResponse()
        binding.searchFragmentMessageView.text = getString(R.string.search_fragment_two_digits)

        institutionsListAdapter = InstitutionsListAdapter(this)
        binding.fragmentSearchRecycler.adapter = institutionsListAdapter

        binding.fragmentSearchEditText.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText?.length!!>=2){
                    binding.fragmentSearchProgressBar.visibility = View.VISIBLE
                    binding.searchFragmentMessageView.visibility =View.GONE
                    searchViewModel.searchInstitutions(newText)
                }else if (newText.length<2){
                    institutionsListAdapter.setInstitutions(emptyList())
                    binding.fragmentSearchProgressBar.visibility = View.GONE
                    binding.searchFragmentMessageView.visibility =View.VISIBLE
                    binding.searchFragmentMessageView.text = getString(R.string.search_fragment_two_digits)

                }
                return false
            }
        })
    }
    private fun searchInstitutionsResponse(){
        with(searchViewModel){
            institutionSearchResponse.observe(viewLifecycleOwner){institutionsSearch->
                binding.fragmentSearchProgressBar.visibility = View.GONE
                if (institutionsSearch.data!=null){
                    institutionsListAdapter.setInstitutions(institutionsSearch.data!!)
                }else{
                    institutionsListAdapter.setInstitutions(emptyList())
                    binding.searchFragmentMessageView.visibility =View.VISIBLE
                    binding.searchFragmentMessageView.text =
                        getString(R.string.search_fragment_nothing_find)
                }
                log("$institutionsSearch institutionSearchResponse")
            }
            institutionSearchBadResponse.observe(viewLifecycleOwner){badResponse->
                binding.fragmentSearchProgressBar.visibility = View.GONE
                binding.searchFragmentMessageView.visibility =View.VISIBLE
                binding.searchFragmentMessageView.text = badResponse
                log("$badResponse institutionSearchBadResponse")
            }
            errorInstitutionSearch.observe(viewLifecycleOwner){error->
                binding.fragmentSearchProgressBar.visibility = View.GONE
                binding.searchFragmentMessageView.visibility =View.VISIBLE
                binding.searchFragmentMessageView.text = error
                log("$error errorInstitutionSearch")
            }
        }
    }
    private fun setUpSearchViewModel(){
        searchViewModel = ViewModelProvider(
            this,
            SearchFactory(RetrofitBuilder().getApiInterface(requireContext()))
        )[SearchViewModel::class.java]
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun goToInstitutionsDetail(id: Int) {
        log("$id goToInstitutionsDetail")
        val bundle = Bundle()
        id.let { bundle.putInt("institutionId", it)}
        if (findNavController().currentDestination?.id == R.id.searchFragment) {
            findNavController().navigate(R.id.action_searchFragment_to_institutions_detail, bundle)
        }
    }

}