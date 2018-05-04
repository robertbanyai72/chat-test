package hu.robert.banyai.lmitest.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import hu.robert.banyai.lmitest.data.client.ImageLoader
import hu.robert.banyai.lmitest.data.client.ImageLoaderImpl
import hu.robert.banyai.lmitest.data.client.SocketClient
import hu.robert.banyai.lmitest.data.client.SocketClientImpl
import hu.robert.banyai.lmitest.data.repository.SocketRepository
import hu.robert.banyai.lmitest.domain.resource.ResourceHelper
import java.net.URI
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideImageLoader(context: Context): ImageLoader {
        return ImageLoaderImpl(context)
    }

    @Singleton
    @Provides
    fun provideSocketRepository(socketClient: SocketClient): SocketRepository {
        return SocketRepository(socketClient)
    }

    @Singleton
    @Provides
    @Named("socketURI")
    fun provideSocketURI(): URI {
        return URI("wss://remy-ws.glitch.me/")
    }

    @Singleton
    @Provides
    fun provideSocketClient(@Named("socketURI") socketURI: URI, resourceHelper: ResourceHelper): SocketClient {
        return SocketClientImpl(socketURI, resourceHelper)
    }
}