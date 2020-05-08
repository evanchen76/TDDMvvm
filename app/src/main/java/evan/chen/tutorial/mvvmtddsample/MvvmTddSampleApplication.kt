package evan.chen.tutorial.mvvmtddsample

import android.app.Application
import evan.chen.tutorial.mvvmtddsample.di.appModule
import org.koin.core.context.startKoin

class MvvmTddSampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin { modules(listOf(appModule)) }
    }
}