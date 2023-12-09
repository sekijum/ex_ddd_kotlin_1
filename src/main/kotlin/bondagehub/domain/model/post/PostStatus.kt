package bondagehub.domain.model.post

enum class PostStatus(private val value: Long) {

    /**
     * 公開済み(デフォルト)
     */
    Publish(1),

    /**
     * 予約済み
     *
     * 未来の日付に公開を予定されている場合。
     */
    Future(2),

    /**
     * 下書き
     *
     * 未完成の投稿。
     */
    Draft(3),

    /**
     * 承認待ち
     *
     * 多数のレポートが寄せられた場合`Pending`ステータスが割り当てられる。
     */
    Pending(4),

    /**
     * 非公開
     *
     * 管理者レベルのユーザーだけが見ることができる。
     */
    Private(5),

    /**
     * ゴミ箱
     *
     * ゴミ箱の中にある投稿は`Trash` ステータスを割り当てられる。
     */
    Trash(6),
}