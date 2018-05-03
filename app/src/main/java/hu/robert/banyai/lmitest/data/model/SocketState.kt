package hu.robert.banyai.lmitest.data.model

sealed class SocketState {
    class SocketOpened : SocketState()
    class SocketClosed : SocketState()
    data class SocketMessageEmitted(val message: String) : SocketState()
}