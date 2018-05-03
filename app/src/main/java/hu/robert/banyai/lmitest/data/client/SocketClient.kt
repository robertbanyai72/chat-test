package hu.robert.banyai.lmitest.data.client

import io.reactivex.Completable
import io.reactivex.Observable
import hu.robert.banyai.lmitest.data.model.SocketState

interface SocketClient {
    fun openSocket(): Observable<SocketState>
    fun closeSocket()
    fun reconnectSocket()
    fun sendMessage(message: String): Completable
}