package bondagehub.domain.model.post

import bondagehub.domain.model.*
import java.util.*
import bondagehub.domain.exception.*

/**
 * 管理者のIDを表現する。
 *
 * 64桁までの一意な文字列をもつ。
 */
class PostId private constructor(id: String) : SomeIdentity<PostId>(id) {

    companion object {

        /**
         * [UUID]を用いて管理者のIDを生成する。
         *
         * @return 生成した値を持つ管理者のID
         */
        fun generate(): PostId = PostId("AC_${UUID.randomUUID()}")

        /**
         * [id]に指定された値を管理者のIDに変換する。
         *
         * 値には、64桁までの一意な文字列を指定することが可能で、
         * 指定可能な値は、英数字、ハイフン、アンダースコアとなる。
         * この条件に違反した値を指定した場合には例外となる。
         *
         * @throws InvalidRequestException 条件に違反した値を指定した場合
         * @return 指定された値を持つ管理者のID
         */
        fun valueOf(id: String): PostId = id
            .takeIf { LENGTH_RANGE.contains(it.length) && PATTERN.matches(it) }
            ?.let { PostId(it) }
            ?: throw InvalidRequestException(
                "AdminUser id must be 64 characters or less and alphanumeric, hyphen, underscore."
            )
    }
}
