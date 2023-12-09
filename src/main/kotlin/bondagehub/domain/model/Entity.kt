package bondagehub.domain.model

/**
 * DDDにおけるエンティティの概念。
 */
interface Entity<T> {

    fun sameIdentityAs(other: T): Boolean
}
