package bondagehub.application.service.admin.post_tag

import bondagehub.domain.exception.*
import bondagehub.application.repository.admin.PostTagRepository
import bondagehub.application.service.*
import bondagehub.application.service.admin.post_tag.dto.PostTagDTO
import bondagehub.application.service.admin.post_tag.dto.PostTagPresenter
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
class PostTagService(
    private val postTagRepository: PostTagRepository,
    private val postTagPresenter: PostTagPresenter,
) {

    @Transactional(readOnly = true)
     fun findPageByQuery(pageable: Pageable): Mono<Pair<List<PostTagDTO>, Int>> = runCatching {
         Mono.just(postTagRepository.count() to postTagRepository.findPageByQuery(pageable))
            .map { (count, postTags) ->
                postTagPresenter.toDTO(postTags, count)
            }
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
}
