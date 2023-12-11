package bondagehub.domain.model.member.detail

import bondagehub.domain.exception.InvalidRequestException
import bondagehub.domain.model.member.MemberStatus

enum class Gender(private val gender: Int) {

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
    Unanswered(3);


    companion object {

        operator fun invoke(gender: Int) = Gender.values().find { it.gender == gender }
            ?: throw InvalidRequestException("無効な性別です。")

        operator fun invoke(type: String) = Gender.values().find { it.name == type }
            ?: throw InvalidRequestException("無効なステータスです。")
    }
}