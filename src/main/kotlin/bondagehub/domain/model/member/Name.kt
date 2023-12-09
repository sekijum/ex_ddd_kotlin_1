package bondagehub.domain.model.member

import bondagehub.domain.model.*
import bondagehub.domain.model.exception.*

/**
 * 会員ユーザーの氏名または会社名を表現する。
 *
 * 100桁までの文字列をもつ。
 */
class Name private constructor(value: String) : SomeValueObject<Name, String>(value) {

    companion object {

        private val LENGTH_RANGE = (1..100)

        /**
         * [value]に指定された値を管理者の氏名または会社名に変換する。
         *
         * 値には、100桁までの文字列を指定することが可能である。
         *
         * @throws InvalidRequestException 条件に違反した値を指定した場合
         * @return 指定された値を持つ管理者の氏名または会社名
         */
        fun valueOf(value: String): Name = value
            .takeIf { LENGTH_RANGE.contains(it.length) }
            ?.let { Name(it) }
            ?: throw InvalidRequestException("Name must be 100 characters or less.")
    }
}
