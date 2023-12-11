package bondagehub.domain.model.member

import bondagehub.domain.model.*
import bondagehub.domain.exception.*

/**
 * 会員ユーザーのメールアドレスを表現する。
 *
 * 100桁までの文字列をもつ。
 */
class Email private constructor(value: String) : SomeValueObject<Email, String>(value) {

    companion object {

        private val LENGTH_RANGE = (1..100)

        /**
         * [value]に指定された値を管理者のメールアドレスに変換する。
         *
         * 値には、100桁までの文字列を指定することが可能である。
         *
         * @throws InvalidRequestException 条件に違反した値を指定した場合
         * @return 指定された値を持つ会員ユーザーのメールアドレス
         */
        fun valueOf(value: String): Email = value
            .takeIf { LENGTH_RANGE.contains(it.length) }
            ?.let { Email(it) }
            ?: throw InvalidRequestException("Email must be 100 characters or less.")


        fun from(value: String): Email = Email(value)
    }
}
