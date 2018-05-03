package hu.robert.banyai.lmitest.presentation.screen

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import hu.robert.banyai.lmitest.data.rx.SchedulersFacade
import hu.robert.banyai.lmitest.domain.usecase.SocketConnectUseCase
import hu.robert.banyai.lmitest.domain.usecase.SocketDisconnectUseCase
import hu.robert.banyai.lmitest.domain.usecase.SocketReconnectUseCase
import hu.robert.banyai.lmitest.domain.usecase.SocketSendMessageUseCase
import hu.robert.banyai.lmitest.presentation.model.MainUiModel

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
        private val socketConnectUseCase: SocketConnectUseCase,
        private val socketDisconnectUseCase: SocketDisconnectUseCase,
        private val socketReconnectUseCase: SocketReconnectUseCase,
        private val socketSendMessageUseCase: SocketSendMessageUseCase,
        private val mainUiModel: MainUiModel,
        private val schedulersFacade: SchedulersFacade) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                    socketConnectUseCase = socketConnectUseCase,
                    socketDisconnectUseCase = socketDisconnectUseCase,
                    socketReconnectUseCase = socketReconnectUseCase,
                    socketSendMessageUseCase = socketSendMessageUseCase,
                    mainUiModel = mainUiModel,
                    schedulersFacade = schedulersFacade) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}