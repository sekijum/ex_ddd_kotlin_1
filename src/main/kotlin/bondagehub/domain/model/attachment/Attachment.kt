package bondagehub.domain.model.attachment

import org.apache.juli.logging.Log
import java.time.Instant

class Attachment(
    val id: Long, // 主キー
    val bucket: String, // S3バケット名
    val size: Long, // 合計サイズ
    val duration: String, // 再生時間
    val path: String, // S3パス
    val fileName: String, // ファイル名
    val ext: String, // ファイル拡張子
    val mimeType: String, // メディアタイプ
    val createdAt: Instant, // 作成日時
    val updatedAt: Instant, // 更新日時
) {

    companion object {

        fun create(
            bucket: String,
            size: Long,
            duration: String,
            path: String,
            fileName: String,
            ext: String,
            mimeType: String
        ): Attachment = with(Instant.now()) {
            Attachment(
                id = 0,
                bucket,
                size,
                duration,
                path,
                fileName,
                ext,
                mimeType,
                createdAt = this,
                updatedAt = this
            )
        }
    }
}