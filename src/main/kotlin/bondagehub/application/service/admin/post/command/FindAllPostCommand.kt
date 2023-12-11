package bondagehub.application.service.admin.post.command

/**
 * すべての投稿を取得する際のコマンド情報。
 */
data class FindAllPostCommand(
    val limit: Int,
    val offset: Int
)
