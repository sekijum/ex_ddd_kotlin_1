package bondagehub.application.service.admin.adminuser.command

/**
 * 管理者を作成する際のコマンド情報。
 */
data class CreateOneAdminUserCommand(
    val name: String,
    val email: String,
    val pass: String
)
