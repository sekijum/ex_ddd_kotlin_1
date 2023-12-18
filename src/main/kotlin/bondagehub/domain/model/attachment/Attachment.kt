package bondagehub.domain.model.attachment

import java.time.Instant

class Attachment(
    val id: Long, // 主キー
    val bucket: String, // S3バケット名
    val key: String, // S3パス
    val isAttach: IsAttach, // 関係性を持っているか
    val createdAt: Instant, // 作成日時
    val updatedAt: Instant, // 更新日時
) {

    companion object {

        fun create(
            bucket: String,
            key: String,
        ): Attachment = with(Instant.now()) {
            Attachment(
                id = 0,
                bucket,
                key,
                isAttach = IsAttach.detach(),
                createdAt = this,
                updatedAt = this
            )
        }
    }
}