package hu.robert.banyai.lmitest.presentation.di

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import hu.robert.banyai.lmitest.data.client.ImageLoader
import hu.robert.banyai.lmitest.data.rx.SchedulersFacade
import hu.robert.banyai.lmitest.domain.resource.ResourceHelper
import hu.robert.banyai.lmitest.domain.usecase.SocketConnectUseCase
import hu.robert.banyai.lmitest.domain.usecase.SocketDisconnectUseCase
import hu.robert.banyai.lmitest.domain.usecase.SocketReconnectUseCase
import hu.robert.banyai.lmitest.domain.usecase.SocketSendMessageUseCase
import hu.robert.banyai.lmitest.presentation.common.SpaceItemDecoration
import hu.robert.banyai.lmitest.presentation.model.MainUiModel
import hu.robert.banyai.lmitest.presentation.screen.MainAdapter
import hu.robert.banyai.lmitest.presentation.screen.MainViewModelFactory

@Module
class MainModule {

    @Provides
    fun provideMainAdapter(imageLoader: ImageLoader): MainAdapter {
        return MainAdapter(imageLoader)
    }

    @Provides
    fun provideSpaceItemDecoration(resourceHelper: ResourceHelper): SpaceItemDecoration {
        return SpaceItemDecoration(resourceHelper)
    }

    @Provides
    fun provideUiDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    fun provideLinearLayoutManager(context: Context): LinearLayoutManager {
        return LinearLayoutManager(context)
    }

    @Provides
    fun provideMainViewModelFactory(socketConnectUseCase: SocketConnectUseCase,
                                    socketDisconnectUseCase: SocketDisconnectUseCase,
                                    socketReconnectUseCase: SocketReconnectUseCase,
                                    socketSendMessageUseCase: SocketSendMessageUseCase,
                                    mainUiModel: MainUiModel,
                                    schedulersFacade: SchedulersFacade): MainViewModelFactory {
        return MainViewModelFactory(socketConnectUseCase, socketDisconnectUseCase, socketReconnectUseCase, socketSendMessageUseCase, mainUiModel, schedulersFacade)
    }

    @Provides
    fun provideMainUiModel(): MainUiModel {
        return MainUiModel()
    }
}