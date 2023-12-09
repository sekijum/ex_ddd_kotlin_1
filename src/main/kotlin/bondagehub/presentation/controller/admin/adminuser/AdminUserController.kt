package bondagehub.presentation.controller.admin.adminuser

import bondagehub.application.service.admin.adminuser.*
import bondagehub.application.service.admin.adminuser.command.*
import bondagehub.application.service.admin.adminuser.dto.*
import bondagehub.presentation.controller.admin.adminuser.resource.*
import io.swagger.annotations.*
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@Api("管理者を管理するAPI", tags = ["admin-users"])
@RestController
@RequestMapping("/admin/admin-users")
class AdminUserController(private val adminUserService: AdminUserService) {

    @ApiOperation("管理者を取得する")
    @GetMapping("/{id}")
    fun findById(
        @ApiParam(value = "管理者のID", required = true, example = "AC_c5fb2cec-a77c-4886-b997-ffc2ef060e78")
        @PathVariable id: Long
    ): Mono<AdminUserResponse> {
        val command = FindAdminUserCommand(id)
        return adminUserService.findById(command)
            .map { it.toResponse() }
    }

    @ApiOperation("すべての管理者を取得する")
    @GetMapping("")
    fun findAll(
        @ModelAttribute request: AdminUserFindAllRequest
    ): Mono<AdminUserResponses> {
        val command = FindAllAdminUserCommand(
            request.limit, 
            request.offset
        )
        return adminUserService.findAll(command)
            .map { dto ->
                AdminUserResponses(
                    dto.count,
                    dto.hasMore,
                    dto.data.map { it.toResponse() }
                )
            }
    }

    @ApiOperation("管理者を作成する")
    @PostMapping("", consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @RequestBody request: AdminUserCreateRequest
    ): Mono<AdminUserResponse> {
        val command = CreateAdminUserCommand(
            request.name,
            request.email,
            request.pass
        )
        return adminUserService.create(command)
            .map { it.toResponse() }
    }

    @ApiOperation("管理者を更新する")
    @PutMapping("/{id}", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun update(
        @ApiParam(value = "管理者のID", required = true, example = "AC_c5fb2cec-a77c-4886-b997-ffc2ef060e78")
        @PathVariable id: Long,
        @RequestBody request: AdminUserUpdateRequest
    ): Mono<AdminUserResponse> {
        val command = UpdateAdminUserCommand(
            id,
            request.name,
            request.email,
        )
        return adminUserService.update(command)
            .map { it.toResponse() }
    }

    @ApiOperation("管理者を削除する")
    @DeleteMapping("/{id}")
    fun delete(
        @ApiParam(value = "管理者のID", required = true, example = "AC_c5fb2cec-a77c-4886-b997-ffc2ef060e78")
        @PathVariable id: Long
    ): Mono<AdminUserResponse> {
        val command = DeleteAdminUserCommand(id)
        return adminUserService.delete(command)
            .map { it.toResponse() }
    }

    private fun AdminUserDTO.toResponse() =
        AdminUserResponse.from(this)
}
