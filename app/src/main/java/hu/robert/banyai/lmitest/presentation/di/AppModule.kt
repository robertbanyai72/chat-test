package hu.robert.banyai.lmitest.presentation.di

import android.content.Context
import dagger.Module
import dagger.Provides
import hu.robert.banyai.lmitest.data.rx.SchedulersFacade
import hu.robert.banyai.lmitest.presentation.app.App

@Module
class AppModule {

    @Provides
    fun provideContext(application: App): Context {
        return application.applicationContext
    }

    @Provides
    fun provideSchedulerFacade(): SchedulersFacade {
        return SchedulersFacade()
    }
}
