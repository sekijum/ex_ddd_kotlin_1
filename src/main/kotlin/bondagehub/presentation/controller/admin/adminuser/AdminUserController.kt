package bondagehub.presentation.controller.admin.adminuser

import bondagehub.application.service.admin.adminuser.*
import bondagehub.application.service.admin.adminuser.command.*
import bondagehub.application.service.admin.adminuser.dto.*
import bondagehub.presentation.controller.admin.adminuser.resource.*
import io.swagger.annotations.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.support.PageableExecutionUtils
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.util.function.LongSupplier

@Api("管理者を管理するAPI", tags = ["admin-users"])
@RestController
@RequestMapping("/admin/admin-users")
class AdminUserController(private val adminUserService: AdminUserService) {

    @ApiOperation("管理者を取得する")
    @GetMapping("/id/{id}")
    fun findOneById(
        @ApiParam(value = "管理者のID", required = true, example = "AC_c5fb2cec-a77c-4886-b997-ffc2ef060e78")
        @PathVariable id: Long
    ): Mono<AdminUserResponse> {
        val command = FindOneByIdAdminUserCommand(id)
        return adminUserService.findOneById(command)
            .map { it.toResponse() }
    }

    @ApiOperation("すべての管理者を取得する")
    @GetMapping("")
    fun findAllByQuery(
        @ModelAttribute request: AdminUserFindAllRequest
    ): ResponseEntity<Mono<List<AdminUserResponse>>> {
        return ResponseEntity.ok(adminUserService.findAllByQuery()
            .map { listDTO -> listDTO.map { it.toResponse() } }
        )
    }

    @ApiOperation("ページネーション付きのすべての管理者を取得する")
    @GetMapping("/paginate")
    fun findPageByQuery(pageable: Pageable): ResponseEntity<Mono<Page<AdminUserResponse>>> {
        return ResponseEntity.ok(adminUserService.findPageByQuery(pageable)
            .map { (count, listDTO) -> Pair(count, listDTO.map { it.toResponse() }) }
            .map { PageableExecutionUtils.getPage(it.second, pageable, LongSupplier { it.first.toLong() }) }
        )
    }

    @ApiOperation("管理者を作成する")
    @PostMapping("", consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun createOne(
        @RequestBody request: AdminUserCreateOneRequest
    ): Mono<AdminUserResponse> {
        val command = CreateOneAdminUserCommand(
            request.name,
            request.email,
            request.pass
        )
        return adminUserService.createOne(command)
            .map { it.toResponse() }
    }

    @ApiOperation("管理者を更新する")
    @PutMapping("/{id}", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun updateOneById(
        @ApiParam(value = "管理者のID", required = true, example = "AC_c5fb2cec-a77c-4886-b997-ffc2ef060e78")
        @PathVariable id: Long,
        @RequestBody request: AdminUserUpdateOneByIdRequest
    ): Mono<AdminUserResponse> {
        val command = UpdateOneByIdAdminUserCommand(
            id,
            request.name,
            request.email,
        )
        return adminUserService.updateOneById(command)
            .map { it.toResponse() }
    }

    @ApiOperation("管理者を削除する")
    @DeleteMapping("/{id}")
    fun deleteOneById(
        @ApiParam(value = "管理者のID", required = true, example = "AC_c5fb2cec-a77c-4886-b997-ffc2ef060e78")
        @PathVariable id: Long
    ): Mono<AdminUserResponse> {
        val command = DeleteOneByIdAdminUserCommand(id)
        return adminUserService.deleteOneById(command)
            .map { it.toResponse() }
    }

    private fun AdminUserDTO.toResponse() =
        AdminUserResponse.from(this)
}
