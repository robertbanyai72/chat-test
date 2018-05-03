package hu.robert.banyai.lmitest.presentation.screen

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import hu.robert.banyai.lmitest.data.model.SocketState
import hu.robert.banyai.lmitest.data.rx.SchedulersFacade
import hu.robert.banyai.lmitest.domain.model.SendMessageAction
import hu.robert.banyai.lmitest.domain.usecase.SocketConnectUseCase
import hu.robert.banyai.lmitest.domain.usecase.SocketDisconnectUseCase
import hu.robert.banyai.lmitest.domain.usecase.SocketReconnectUseCase
import hu.robert.banyai.lmitest.domain.usecase.SocketSendMessageUseCase
import hu.robert.banyai.lmitest.presentation.common.extension.addAllItem
import hu.robert.banyai.lmitest.presentation.common.extension.addItem
import hu.robert.banyai.lmitest.presentation.common.extension.findUrls
import hu.robert.banyai.lmitest.presentation.model.AdapterItemModel
import hu.robert.banyai.lmitest.presentation.model.MainUiModel
import java.util.*
import kotlin.collections.ArrayList

class MainViewModel(socketConnectUseCase: SocketConnectUseCase,
                    private val socketDisconnectUseCase: SocketDisconnectUseCase,
                    private val socketReconnectUseCase: SocketReconnectUseCase,
                    private val socketSendMessageUseCase: SocketSendMessageUseCase,
                    private var mainUiModel: MainUiModel,
                    private val schedulersFacade: SchedulersFacade) : ViewModel() {

    private val disposable = CompositeDisposable()

    val uiStateObserver = MutableLiveData<MainViewState>()

    override fun onCleared() {
        socketDisconnectUseCase.execute()
        disposable.clear()
        super.onCleared()
    }

    init {
        disposable.add(socketConnectUseCase.execute()
                .subscribeOn(schedulersFacade.io())
                .observeOn(schedulersFacade.ui())
                .subscribe({
                    when (it) {
                        is SocketState.SocketMessageEmitted -> {
                            mainUiModel = if (it.message.startsWith("\"")) {
                                val data = ArrayList<AdapterItemModel>()
                                data.add(AdapterItemModel(
                                        id = UUID.randomUUID().toString(),
                                        message = it.message.substring(1, it.message.length - 1),
                                        adapterViewState = AdapterViewState.Partner))

                                getUrls(it.message, data)

                                mainUiModel.copy(data = mainUiModel.data.addAllItem(data))
                            } else {
                                mainUiModel.copy(data = mainUiModel.data.addItem(
                                        AdapterItemModel(adapterViewState = AdapterViewState.Joined)))
                            }

                            uiStateObserver.value = MainViewState.DataUpdateState(mainUiModel)
                        }
                        is SocketState.SocketClosed -> {
                            uiStateObserver.value = MainViewState.DisconnectState()
                        }
                        is SocketState.SocketOpened -> {
                            uiStateObserver.value = MainViewState.ConnectState()
                        }
                    }
                }, {
                    uiStateObserver.value = MainViewState.ErrorState(it)
                }))
    }

    private fun getUrls(message: String, data: ArrayList<AdapterItemModel>) {
        for (i in message.findUrls()) {
            data.add(AdapterItemModel(
                    id = UUID.randomUUID().toString(),
                    imageUrl = i,
                    adapterViewState = AdapterViewState.Image
            ))
        }
    }

    fun sendMessage(message: String) {
        disposable.add(socketSendMessageUseCase.execute(SendMessageAction(message))
                .subscribeOn(schedulersFacade.io())
                .observeOn(schedulersFacade.ui())
                .subscribe({
                    val data = ArrayList<AdapterItemModel>()
                    data.add(AdapterItemModel(
                            id = UUID.randomUUID().toString(),
                            message = message,
                            adapterViewState = AdapterViewState.Owner))

                    getUrls(message, data)

                    mainUiModel = mainUiModel.copy(data = mainUiModel.data.addAllItem(data))

                    uiStateObserver.value = MainViewState.DataUpdateState(mainUiModel)
                }, {
                    uiStateObserver.value = MainViewState.ErrorState(it)
                }))
    }

    fun reconnect() {
        socketReconnectUseCase.execute()
    }
}