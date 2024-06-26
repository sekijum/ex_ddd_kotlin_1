package bondagehub.domain.model

import java.io.Serializable

/**
 * DDDにおける値オブジェクトの概念。
 *
 * @param T 値オブジェクトの型
 */
interface ValueObject<T> : Serializable {

    fun sameValueAs(other: T): Boolean
}
