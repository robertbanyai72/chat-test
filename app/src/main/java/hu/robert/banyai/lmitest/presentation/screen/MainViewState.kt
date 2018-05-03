package hu.robert.banyai.lmitest.presentation.screen

import hu.robert.banyai.lmitest.presentation.model.MainUiModel

sealed class MainViewState {

    data class DataUpdateState(val mainUiModel: MainUiModel) : MainViewState()
    data class ErrorState(val throwable: Throwable) : MainViewState()
    class DisconnectState : MainViewState()
    class ConnectState : MainViewState()
}