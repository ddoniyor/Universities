package tj.descwn.universities.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tj.descwn.universities.data.retrofit.ApiInterface
import tj.descwn.universities.viewmodel.UniversityStudyViewModel

class UniversityStudyFactory (private val apiInterface: ApiInterface): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UniversityStudyViewModel::class.java)){
            UniversityStudyViewModel(this.apiInterface) as T
        }else{
            throw IllegalArgumentException("Unknown UniversityStudyViewModel Class")
        }
    }
}