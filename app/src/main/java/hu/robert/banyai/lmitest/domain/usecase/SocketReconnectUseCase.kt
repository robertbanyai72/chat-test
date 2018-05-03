package hu.robert.banyai.lmitest.domain.usecase

import hu.robert.banyai.lmitest.data.repository.SocketRepository
import javax.inject.Inject

class SocketReconnectUseCase @Inject constructor(private val socketRepository: SocketRepository) {

    fun execute() {
        return socketRepository.reconnect()
    }
}