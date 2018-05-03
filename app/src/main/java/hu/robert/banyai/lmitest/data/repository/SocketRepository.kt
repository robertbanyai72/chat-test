package hu.robert.banyai.lmitest.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import hu.robert.banyai.lmitest.data.client.SocketClient
import hu.robert.banyai.lmitest.data.model.SocketState
import javax.inject.Inject

class SocketRepository @Inject constructor(private val socketClient: SocketClient) {

    fun connect(): Observable<SocketState> {
        return socketClient.openSocket()
    }

    fun disconnect() {
        return socketClient.closeSocket()
    }

    fun reconnect() {
        return socketClient.reconnectSocket()
    }

    fun sendMessage(message: String): Completable {
        return socketClient.sendMessage(message)
    }
}