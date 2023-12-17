package bondagehub.application.service.admin.adminuser.command

/**
 * 管理者を更新する際のコマンド情報。
 */
data class UpdateOneByIdAdminUserCommand(
    val id: Long,
    val name: String?,
    val email: String?,
)