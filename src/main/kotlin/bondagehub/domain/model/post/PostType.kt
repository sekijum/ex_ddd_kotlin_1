package bondagehub.domain.model.post

enum class PostType(private val value: Long) {

    /**
     * アルバム
     */
    Album(1),

    /**
     * 動画
     *
     * 未来の日付に公開を予定されている場合。
     */
    Video(2),

    /**
     * 単体画像
     *
     * 未完成の投稿。
     */
    Image(3),
}