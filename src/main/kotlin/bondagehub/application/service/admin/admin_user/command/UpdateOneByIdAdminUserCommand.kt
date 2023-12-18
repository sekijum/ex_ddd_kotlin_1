package bondagehub.application.service.admin.admin_user.command

/**
 * 管理者を更新する際のコマンド情報。
 */
data class UpdateOneByIdAdminUserCommand(
    val id: Long,
    val name: String?,
    val email: String?,
)
