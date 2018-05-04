package hu.robert.banyai.lmitest.deivce.di

import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import hu.robert.banyai.lmitest.deivce.resource.ResourceHelperImpl
import hu.robert.banyai.lmitest.domain.resource.ResourceHelper

@Module
class DeviceModule {

    @Provides
    fun provideResources(context: Context): Resources {
        return context.resources
    }

    @Provides
    fun provideResourceHelper(resources: Resources): ResourceHelper {
        return ResourceHelperImpl(resources)
    }
}