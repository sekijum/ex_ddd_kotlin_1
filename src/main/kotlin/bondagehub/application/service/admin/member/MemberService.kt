package bondagehub.application.service.admin.member

import bondagehub.domain.exception.*
import bondagehub.application.repository.admin.MemberRepository
import bondagehub.application.service.*
import bondagehub.application.service.admin.member.command.FindAllMemberCommand
import bondagehub.application.service.admin.member.dto.MemberDTO
import bondagehub.application.service.admin.member.dto.MemberPresenter
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val memberPresenter: MemberPresenter,
) {

    @Transactional(readOnly = true)
     fun findAll(command: FindAllMemberCommand): Mono<PaginationDTO<MemberDTO>> = runCatching {
        return Mono.just(memberRepository.count() to memberRepository.findAll(command.limit, command.offset))
            .map { (count, members) ->
                memberPresenter.toDTO(
                    members,
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
