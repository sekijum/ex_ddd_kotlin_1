package bondagehub.application.service.admin.adminuser

import bondagehub.domain.model.adminuser.*
import bondagehub.domain.exception.*
import bondagehub.application.repository.admin.AdminUserRepository
import bondagehub.application.service.admin.adminuser.command.*
import bondagehub.application.service.admin.adminuser.dto.*
import bondagehub.application.service.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

/**
 * 管理者([AdminUser])ドメインの操作を提供するアプリケーションサービス。
 */
@Service
class AdminUserService(
    private val adminUserRepository: AdminUserRepository,
    private val adminUserPresenter: AdminUserPresenter,
) {

    @Transactional(readOnly = true)
     fun findById(command: FindAdminUserCommand): Mono<AdminUserDTO> = runCatching {

        return Mono.just(adminUserRepository.findById(command.id))
            .onErrorResume { Mono.error(it.error()) }
        .map { adminUserPresenter.toDTO(it) }
    }
        .getOrElse { Mono.error(it.error()) }

    @Transactional(readOnly = true)
     fun findAll(command: FindAllAdminUserCommand): Mono<PaginationDTO<AdminUserDTO>> = runCatching {
        return Mono.just(adminUserRepository.count() to adminUserRepository.findAll(command.limit, command.offset))
            .map { (count, adminUsers) ->
                adminUserPresenter.toDTO(
                    adminUsers,
                    count,
                    command.limit,
                    command.offset
                )
            }
    }
        .getOrElse { Mono.error(it.error()) }

    @Transactional(timeout = TRANSACTIONAL_TIMEOUT_SECONDS, rollbackFor = [Exception::class])
     fun create(command: CreateAdminUserCommand): Mono<AdminUserDTO> = runCatching {
        val name = Name.valueOf(command.name)
        val email = Email.valueOf(command.email)
        val pass = Pass.valueOf(command.pass, email)

        val created = AdminUser
            .create(name, email, pass)
            .also { adminUserRepository.create(it) }

        return Mono.just(created)
            .onErrorResume { Mono.error(it.error()) }
        .map { adminUserPresenter.toDTO(it) }
    }
        .getOrElse { Mono.error(it.error()) }

    @Transactional(timeout = TRANSACTIONAL_TIMEOUT_SECONDS, rollbackFor = [Exception::class])
     fun update(command: UpdateAdminUserCommand): Mono<AdminUserDTO> = runCatching {
        val id = command.id
        val name = command.name?.let { Name.valueOf(it) }
        val email = command.email?.let { Email.valueOf(it) }

        val updated = adminUserRepository
            .findById(id, lock = true)
            .update(name, email)
            .also { adminUserRepository.update(it) }

        return Mono.just(updated)
            .onErrorResume { Mono.error(it.error()) }
        .map { adminUserPresenter.toDTO(it) }
    }
        .getOrElse { Mono.error(it.error()) }

    @Transactional(timeout = TRANSACTIONAL_TIMEOUT_SECONDS, rollbackFor = [Exception::class])
     fun delete(command: DeleteAdminUserCommand): Mono<AdminUserDTO> = runCatching {
        val id = command.id

        val deleted = adminUserRepository
            .findById(id, lock = true)
            .also { adminUserRepository.delete(it) }

        return Mono.just(deleted)
            .onErrorResume { Mono.error(it.error()) }
        .map { adminUserPresenter.toDTO(it) }
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
