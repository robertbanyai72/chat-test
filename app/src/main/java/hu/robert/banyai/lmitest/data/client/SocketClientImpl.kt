package hu.robert.banyai.lmitest.data.client

import android.system.Os.close
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import hu.robert.banyai.lmitest.data.model.SocketState
import hu.robert.banyai.lmitest.domain.resource.ResourceHelper
import lmitest.banyai.robert.com.logmeintest.R
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import timber.log.Timber
import java.lang.Exception
import java.net.URI
import javax.inject.Inject

class SocketClientImpl @Inject constructor(
        uri: URI,
        val resourceHelper: ResourceHelper) : WebSocketClient(uri), SocketClient {

    private val subject = PublishSubject.create<SocketState>()

    override fun openSocket(): Observable<SocketState> {
        return subject.doOnSubscribe {
            connect()
        }
    }

    override fun closeSocket() {
        close()
    }

    override fun reconnectSocket() {
        reconnect()
    }

    override fun onOpen(handshakedata: ServerHandshake?) {
        Timber.d("Socket : onOpen")
        subject.onNext(SocketState.SocketOpened())
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
        Timber.d("Socket : onClose")
        subject.onNext(SocketState.SocketClosed())
    }

    override fun onMessage(message: String?) {
        Timber.d("Socket : onMessage $message")
        message?.let { subject.onNext(SocketState.SocketMessageEmitted(it)) }
    }

    override fun onError(ex: Exception?) {
        Timber.d("Socket : onError ${ex.toString()}")
        ex?.let { subject.onError(it) }
    }

    override fun sendMessage(message: String): Completable {
        return Completable.create({
            if (isOpen) {
                try {
                    send("\"$message\"")
                    it.onComplete()
                } catch (ex: Exception) {
                    it.onError(ex)
                }
            } else {
                it.onError(Throwable(resourceHelper.getString(R.string.offline)))
            }
        })
    }
}