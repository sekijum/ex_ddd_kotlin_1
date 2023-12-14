package bondagehub.application.service.admin.postcategory

import bondagehub.domain.exception.*
import bondagehub.application.repository.admin.PostCategoryRepository
import bondagehub.application.service.*
import bondagehub.application.service.admin.posttag.command.FindAllPostTagCommand
import bondagehub.application.service.admin.postcategory.dto.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
class PostCategoryService(
    private val postCategoryRepository: PostCategoryRepository,
    private val postCategoryPresenter: PostCategoryPresenter,
) {

    @Transactional(readOnly = true)
     fun findAll(command: FindAllPostTagCommand): Mono<PaginationDTO<PostCategoryDTO>> = runCatching {
        return Mono.just(postCategoryRepository.count() to postCategoryRepository.findAll(command.limit, command.offset))
            .map { (count, postCategories) ->
                postCategoryPresenter.toDTO(
                    postCategories,
                    count,
                    command.limit,
                    command.offset
                )
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
