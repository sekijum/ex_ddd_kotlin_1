package bondagehub.presentation.controller.admin.member.resource

import bondagehub.application.service.admin.member.dto.MemberDTO
import io.swagger.annotations.ApiModelProperty

data class MemberResponse(
        @ApiModelProperty(
                value = "会員ID", example = "c5fb2cec-a77c-4886-b997-ffc2ef060e78", required = true, position = 1
        )
        val id: String,
        @ApiModelProperty(
                value = "会員名", example = "ほげ", required = true, position = 2
        )
        val name: String,
        @ApiModelProperty(
                value = "会員パスワード", required = true, position = 5
        )
        val pass: String,
        @ApiModelProperty(
            value = "会員メールアドレス", example = "example@example.com", required = true, position = 4
        )
        val email: String?,
        @ApiModelProperty(
                value = "ステータス種別", example = "公開済み", required = true, position = 5
        )
        val statusLabel: String,
        @ApiModelProperty(
                value = "ステータスのキー", example = "Publish", required = true, position = 6
        )
        val statusName: String,
        @ApiModelProperty(
                value = "会員のメールアドレス認証日時", example = "1576120910973", required = true, position = 6
        )
        val emailVerifiedAt: Long?,
        @ApiModelProperty(
                value = "会員の削除日時", example = "1576120910973", required = true, position = 6
        )
        val deletedAt: Long?,
        @ApiModelProperty(
                value = "管理者の作成日時", example = "1576120910973", required = true, position = 6
        )
        val createdAt: Long,
        @ApiModelProperty(
                value = "管理者の削除日時", example = "1576120910973", required = false, position = 7
        )
        val updatedAt: Long
) {

    companion object {

        fun from(dto: MemberDTO): MemberResponse =
                MemberResponse(
                    dto.id,
                    dto.name,
                    dto.pass,
                    dto.email,
                    dto.statusLabel,
                    dto.statusName,
                    dto.emailVerifiedAt,
                    dto.deletedAt,
                    dto.createdAt,
                    dto.updatedAt
                )
    }
}
