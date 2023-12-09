package bondagehub.domain.model.member

enum class MemberStatus(private val value: Long) {

    /**
     * 有効
     */
    Active(1),

    /**
     * 退会済
     */
    Withdrawn(7),

    /**
     * 凍結(復活あり)
     */
    Suspended(8),

    /**
     * 無効
     */
    Inactive(9),
}