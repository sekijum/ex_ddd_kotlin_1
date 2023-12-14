package bondagehub.domain.model.member

import bondagehub.domain.exception.InvalidRequestException
import bondagehub.domain.model.post.PostType

enum class MemberStatus(val status: Int, val label: String) {

    /**
     * 有効
     */
    Active(1, "有効"),

    /**
     * 退会済
     */
    Withdrawn(7, "退会済"),

    /**
     * 凍結(復活あり)
     */
    Suspended(8, "凍結"),

    /**
     * 無効
     */
    Inactive(9, "無効");


    companion object {

        operator fun invoke(type: Int) = MemberStatus.values().find { it.status == type }
            ?: throw InvalidRequestException("無効なステータスです。")

        operator fun invoke(type: String) = MemberStatus.values().find { it.name == type }
            ?: throw InvalidRequestException("無効なステータスです。")
    }
}