package bondagehub.application.service.admin.post

import bondagehub.domain.exception.*
import bondagehub.application.repository.admin.PostRepository
import bondagehub.application.service.*
import bondagehub.application.service.admin.post.dto.PostDTO
import bondagehub.application.service.admin.post.dto.PostPresenter
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
class PostService(
    private val postRepository: PostRepository,
    private val postPresenter: PostPresenter,
) {

    @Transactional(readOnly = true)
     fun findPageByQuery(pageable: Pageable): Mono<Pair<List<PostDTO>, Int>> = runCatching {
        return Mono.just(postRepository.count() to postRepository.findPageByQuery(pageable))
            .map { (count, posts) ->
                postPresenter.toDTO(posts, count, )
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
