package hu.robert.banyai.lmitest.domain.usecase

import io.reactivex.Completable
import hu.robert.banyai.lmitest.data.repository.SocketRepository
import hu.robert.banyai.lmitest.domain.model.SendMessageAction
import javax.inject.Inject

class SocketSendMessageUseCase @Inject constructor(private val socketRepository: SocketRepository) {

    fun execute(sendMessageAction: SendMessageAction): Completable {
        return socketRepository.sendMessage(sendMessageAction.message)
    }
}