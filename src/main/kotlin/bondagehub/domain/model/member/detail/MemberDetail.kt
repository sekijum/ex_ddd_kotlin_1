package bondagehub.domain.model.member.detail

import bondagehub.domain.model.Entity
import java.time.Instant
import bondagehub.domain.model.attachment.*
import bondagehub.domain.model.member.*

/**
 * 会員詳細を表現する。
 * 会員に付随する詳細の情報がここに登録される。
 */
class MemberDetail(
    val id: Long, // 主キー
    val memberId: MemberId, // 会員ID
    val birthday: Instant? = null, // 生年月日
    val gender: Gender?, // 性別
    val iconId: Long? = null, // アイコンID
    val displayName: String? = null, // 表示名
    val createdAt: Instant, // 作成日時
    val updatedAt: Instant, // 更新日時

    val icon: Attachment? = null,
) {

    companion object {

        fun create(
            memberId: MemberId,
            birthday: Instant? = null,
            gender: Gender? = null,
            iconId: Long? = null,
            displayName: String? = null,
        ): MemberDetail = with(Instant.now()) {
            MemberDetail(
                id = 0,
                memberId,
                birthday,
                gender,
                iconId,
                displayName,
                createdAt = this,
                updatedAt = this
            )
        }
    }
}