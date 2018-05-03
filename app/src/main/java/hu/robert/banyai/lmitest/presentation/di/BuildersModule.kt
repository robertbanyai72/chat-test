package hu.robert.banyai.lmitest.presentation.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import hu.robert.banyai.lmitest.presentation.screen.MainActivity

@Module
interface BuildersModule {

    @ContributesAndroidInjector(modules = [(MainModule::class)])
    fun bindMainActivity(): MainActivity
}
