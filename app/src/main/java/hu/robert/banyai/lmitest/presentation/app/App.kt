package hu.robert.banyai.lmitest.presentation.app

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import hu.robert.banyai.lmitest.presentation.di.DaggerAppComponent
import timber.log.BuildConfig

import timber.log.Timber

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent
    }

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}