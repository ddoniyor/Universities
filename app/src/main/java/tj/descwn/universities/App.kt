package tj.descwn.universities

import android.app.Application
import android.content.res.Resources

class App:Application() {
    companion object {
        private const val TAG = "Application Class"
        lateinit var instance: Application
        lateinit var resourses: Resources
    }

    override fun onCreate() {
        super.onCreate()
        tj.descwn.universities.App.Companion.instance = this
        tj.descwn.universities.App.Companion.resourses = resources
    }
}