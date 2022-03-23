package tj.descwn.universities.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tj.descwn.universities.data.retrofit.ApiInterface
import tj.descwn.universities.viewmodel.UniversityWorkViewModel

class UniversityWorkFactory (private val apiInterface: ApiInterface): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UniversityWorkViewModel::class.java)){
            UniversityWorkViewModel(this.apiInterface) as T
        }else{
            throw IllegalArgumentException("Unknown UniversityWorkViewModel Class")
        }
    }
}