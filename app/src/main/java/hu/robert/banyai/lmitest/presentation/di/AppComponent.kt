package hu.robert.banyai.lmitest.presentation.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import hu.robert.banyai.lmitest.data.di.DataModule
import hu.robert.banyai.lmitest.domain.di.DomainModule
import hu.robert.banyai.lmitest.presentation.app.App
import javax.inject.Singleton

@Singleton
@Component(modules = [(
        AndroidSupportInjectionModule::class), (MainModule::class), (AppModule::class), (DataModule::class), (DomainModule::class), (BuildersModule::class)])
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: App)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent

    }
}
