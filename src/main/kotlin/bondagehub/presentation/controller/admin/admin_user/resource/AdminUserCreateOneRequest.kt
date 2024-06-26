package bondagehub.presentation.controller.admin.admin_user.resource

import io.swagger.annotations.ApiModelProperty

/**
 * 管理者作成時のリクエスト情報。
 */
data class AdminUserCreateOneRequest(
    @ApiModelProperty(
        value = "管理者の氏名または会社名", example = "あいうえお", required = true, position = 1
    )
    val name: String,
    @ApiModelProperty(
        value = "管理者のメールアドレス", example = "アイウエオ", required = true, position = 2
    )
    val email: String,
    @ApiModelProperty(
        value = "管理者のパスワード", required = true, position = 4
    )
    val pass: String
)
