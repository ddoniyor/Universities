package tj.descwn.universities.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tj.descwn.universities.data.retrofit.ApiInterface
import tj.descwn.universities.viewmodel.CollegeStudyViewModel

class CollegeStudyFactory(private val apiInterface: ApiInterface): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CollegeStudyViewModel::class.java)){
            CollegeStudyViewModel(this.apiInterface) as T
        }else{
            throw IllegalArgumentException("Unknown CollegeStudyViewModel Class")
        }
    }
}