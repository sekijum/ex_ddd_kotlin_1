package bondagehub.application.service.admin.admin_user.command

/**
 * すべての管理者を取得する際のコマンド情報。
 */
data class FindAllAdminUserCommand(
    val limit: Int,
    val offset: Int
)
