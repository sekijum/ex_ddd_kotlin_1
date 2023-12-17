package bondagehub.application.shared.attachment

import bondagehub.application.repository.AttachmentRepository
import bondagehub.application.service.ApplicationException
import bondagehub.application.shared.attachment.command.GetS3PresignedUrlsForUploadCommand
import bondagehub.domain.exception.InvalidDataStateException
import bondagehub.domain.exception.InvalidRequestException
import bondagehub.domain.exception.NotFoundException
import bondagehub.domain.exception.UpdateFailedException
import bondagehub.infrastructure.s3.*
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono
import bondagehub.domain.model.attachment.*
import org.springframework.stereotype.Service

@Service
class AttachmentService(private val attachmentRepository: AttachmentRepository, ) {

    @Transactional(timeout = AttachmentService.TRANSACTIONAL_TIMEOUT_SECONDS, rollbackFor = [Exception::class])
    suspend fun getS3PresignedUrlsForUpload(
        commands: List<GetS3PresignedUrlsForUploadCommand>
    ): Mono<List<Pair<Long, String>>> = runCatching {
        val attachments = commands.map {
            Attachment.create(bucket = "", key = it.key)
        }
        Mono.just(
            attachmentRepository.createAll(attachments).map {
                Pair(it.id, getS3PresignedUrlForUpload(it))
            }
        )
    }
        .getOrElse { Mono.error(it.error()) }

    private fun Throwable.error(): Throwable =
        when (this) {
            is InvalidRequestException -> ApplicationException(type, 400, message, this)
            is NotFoundException -> ApplicationException(type, 404, message, this)
            is InvalidDataStateException -> ApplicationException(type, 409, message, this)
            is UpdateFailedException -> ApplicationException(type, 500, message, this)
            else -> ApplicationException(message, this)
        }


    private companion object {

        const val TRANSACTIONAL_TIMEOUT_SECONDS: Int = 10
    }
}