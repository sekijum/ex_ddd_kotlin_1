package bondagehub.domain.model.adminuser

import bondagehub.domain.model.*
import java.time.Instant

/**
 * 管理者を表現する。
 *
 * 氏名または会社名、発音などの情報を指定して作成することが可能である。
 * また、管理者の作成後は更新、削除が可能である。
 */
class AdminUser(
    val id: Long, // 主キー
    val name: Name, // 管理者の名前
    val email: Email, // 管理者メールアドレス
    val pass: Pass, // 管理者パスワード
    val createdAt: Instant, // 作成日時
    val updatedAt: Instant, // 更新日時
) : Entity<AdminUser> {

    /**
     * 管理者を更新する。
     *
     * 氏名またはメールアドレスを更新可能で、すべて任意指定が可能であり指定しなかった場合は現在の値のままとなる。
     *
     * @return 更新された管理者
     */
    fun update(
        name: Name?,
        email: Email?,
    ): AdminUser {

        return AdminUser(
            id,
            name = name ?: this.name,
            email = email ?: this.email,
            pass,
            createdAt,
            updatedAt = Instant.now(),
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as AdminUser
        return sameIdentityAs(other)
    }

    override fun sameIdentityAs(other: AdminUser): Boolean =
        id == other.id

    companion object {

        /**
         * 管理者を新規作成する。
         *
         * 名前、メールアドレス、パスワードすべての項目が必須指定となる。
         *
         * @return 作成された管理者
         */
        fun create(
            name: Name,
            email: Email,
            pass: Pass
        ): AdminUser = with(Instant.now()) {
            AdminUser(
                id = 0,
                name = name,
                email = email,
                pass = pass,
                createdAt = this,
                updatedAt = this,
            )
        }
    }
}
