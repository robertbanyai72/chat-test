package hu.robert.banyai.lmitest.domain.di

import dagger.Module
import dagger.Provides
import hu.robert.banyai.lmitest.data.repository.SocketRepository
import hu.robert.banyai.lmitest.domain.usecase.SocketConnectUseCase
import hu.robert.banyai.lmitest.domain.usecase.SocketDisconnectUseCase
import hu.robert.banyai.lmitest.domain.usecase.SocketReconnectUseCase
import hu.robert.banyai.lmitest.domain.usecase.SocketSendMessageUseCase
import javax.inject.Singleton

@Module
class DomainModule {
    @Singleton
    @Provides
    fun provideSocketConnectUseCase(socketRepository: SocketRepository): SocketConnectUseCase {
        return SocketConnectUseCase(socketRepository)
    }

    @Singleton
    @Provides
    fun provideSocketDisconnectUseCase(socketRepository: SocketRepository): SocketDisconnectUseCase {
        return SocketDisconnectUseCase(socketRepository)
    }

    @Singleton
    @Provides
    fun provideSocketReconnectUseCase(socketRepository: SocketRepository): SocketReconnectUseCase {
        return SocketReconnectUseCase(socketRepository)
    }

    @Singleton
    @Provides
    fun provideSocketSendMessageUseCase(socketRepository: SocketRepository): SocketSendMessageUseCase {
        return SocketSendMessageUseCase(socketRepository)
    }
}