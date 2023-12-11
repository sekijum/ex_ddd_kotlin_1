package bondagehub.domain.model.post

import bondagehub.domain.exception.InvalidRequestException

enum class PostStatus(val status: Int, val label: String) {

    /**
     * デフォルト
     */
    Publish(1, "公開済み"),

    /**
     * 未来の日付に公開を予定されている場合。
     */
    Future(2, "予約済み"),

    /**
     * 未完成の投稿。
     */
    Draft(3, "下書き"),

    /**
     * 多数のレポートが寄せられた場合`Pending`ステータスが割り当てられる。
     */
    Pending(4, "承認待ち"),

    /**
     * 管理者レベルのユーザーだけが見ることができる。
     */
    Private(5, "非公開"),

    /**
     * ゴミ箱の中にある投稿は`Trash` ステータスを割り当てられる。
     */
    Trash(6, "ゴミ箱");

    companion object {

        operator fun invoke(status: Int) = PostStatus.values().find { it.status == status }
            ?: throw InvalidRequestException("無効なステータスです。")

        operator fun invoke(status: String) = PostStatus.values().find { it.name == status }
            ?: throw InvalidRequestException("無効なステータスです。")
    }
}