package tj.descwn.universities.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tj.descwn.universities.data.retrofit.ApiInterface
import tj.descwn.universities.viewmodel.InstitutionDetailViewModel

class InstitutionDetailFactory (private val apiInterface: ApiInterface): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(InstitutionDetailViewModel::class.java)){
            InstitutionDetailViewModel(this.apiInterface) as T
        }else{
            throw IllegalArgumentException("Unknown InstitutionDetailViewModel Class")
        }
    }
}