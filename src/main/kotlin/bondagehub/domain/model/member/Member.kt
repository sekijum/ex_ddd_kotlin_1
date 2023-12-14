package bondagehub.domain.model.member

import bondagehub.domain.model.Entity
import bondagehub.domain.model.member.detail.*
import bondagehub.domain.model.post.*
import java.time.Instant


/**
 * 会員ユーザーを表現する。
 */
class Member(
    val id: MemberId, // 主キー
    val name: Name, // 会員ユーザーの名前
    val pass: Pass, // 会員ユーザーのパスワード
    val status: MemberStatus, // 会員ユーザーステータス
    val email: Email? = null, // 会員ユーザーのメールアドレス
    val emailVerifiedAt: Instant? = null, // メール認証日時
    val deletedAt: Instant? = null, // 削除日時
    val createdAt: Instant, // 登録日時
    val updatedAt: Instant, // 更新日時

    val memberDetail: MemberDetail? = null,
    val posts: List<Post>? = ArrayList<Post>(),
): Entity<Member> {

    /**
     * この会員ユーザーが退会済みの場合に`true`を返す。
     */
    private val isWithdrawn: Boolean = status === MemberStatus.Withdrawn

    /**
     * この会員ユーザーが削除されている場合に`true`を返す。
     */
    val isDeleted: Boolean = deletedAt != null

    /**
     * 会員ユーザーを退会済みにする。
     *
     * `status`がWithdrawnが設定されことによって論理削除状態となる。
     * 削除日時([deletedAt])に現在日付を設定する。
     *
     * また、この会員ユーザーが退会済みの場合にはそのままこの会員ユーザーが返却される。
     *
     *  @return 削除された管理者
     */
    fun withdrawn(): Member = if (isWithdrawn) this else with(Instant.now()) {
        Member(
            id,
            name = name,
            email = email,
            pass = pass,
            status = MemberStatus.Withdrawn,
            deletedAt = this,
            createdAt = createdAt,
            updatedAt = this
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Member
        return sameIdentityAs(other)
    }

    override fun hashCode(): Int = MemberId.hashCode()

    override fun sameIdentityAs(other: Member): Boolean = id == other.id

    companion object {

        /**
         * 管理者を作成する。
         *
         * 名前、ユーザー名、パスワード、ユーザー詳細すべての項目が必須指定となる。
         *
         * @return 作成された会員
         */
        fun create(
            id: MemberId,
            name: Name,
            email: Email,
            pass: Pass,
            memberDetail: MemberDetail,
        ): Member = with(Instant.now()) {
            Member(
                id,
                name,
                pass,
                status = MemberStatus.Active,
                email = email,
                memberDetail = memberDetail,
                createdAt = this,
                updatedAt = this
            )
        }
    }
}