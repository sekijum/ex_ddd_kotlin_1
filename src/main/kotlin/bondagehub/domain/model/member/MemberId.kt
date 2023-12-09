package bondagehub.domain.model.member

import bondagehub.domain.model.SomeIdentity
import java.util.UUID
import bondagehub.domain.model.exception.*

/**
 * 会員のIDを表現する。
 *
 * 64桁までの一意な文字列をもつ。
 */
class MemberId private constructor(id: String) : SomeIdentity<MemberId>(id) {

    companion object {

        /**
         * [UUID]を用いて会員のIDを生成する。
         *
         * @return 生成した値を持つ会員のID
         */
        fun generate(): MemberId = MemberId("AC_${UUID.randomUUID()}")

        /**
         * [id]に指定された値を会員のIDに変換する。
         *
         * 値には、64桁までの一意な文字列を指定することが可能で、
         * 指定可能な値は、英数字、ハイフン、アンダースコアとなる。
         * この条件に違反した値を指定した場合には例外となる。
         *
         * @throws InvalidRequestException 条件に違反した値を指定した場合
         * @return 指定された値を持つ会員のID
         */
        fun valueOf(id: String): MemberId = id
            .takeIf { LENGTH_RANGE.contains(it.length) && PATTERN.matches(it) }
            ?.let { MemberId(it) }
            ?: throw InvalidRequestException(
                "Account id must be 64 characters or less and alphanumeric, hyphen, underscore."
            )
    }
}
