package bondagehub.domain.model.member.detail

enum class Gender(private val value: Long) {

    /**
     * 男性
     */
    Male(1),

    /**
     * 女性
     */
    Female(2),

    /**
     * 未回答(デフォルト)
     */
    Unanswered(3),
}