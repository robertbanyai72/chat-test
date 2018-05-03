package hu.robert.banyai.lmitest.domain.usecase

import io.reactivex.Observable
import hu.robert.banyai.lmitest.data.model.SocketState
import hu.robert.banyai.lmitest.data.repository.SocketRepository
import javax.inject.Inject

class SocketConnectUseCase @Inject constructor(private val socketRepository: SocketRepository) {

    fun execute(): Observable<SocketState> {
        return socketRepository.connect()
    }
}