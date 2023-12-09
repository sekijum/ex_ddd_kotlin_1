package bondagehub.presentation.controller.admin.adminuser.resource

import io.swagger.annotations.ApiModelProperty

/**
 * 複数の管理者のレスポンス情報。
 *
 * 複数の管理者のレスポンス情報には以下の情報が含まれる。
 * - count
 *   管理者の件数
 * - hasMore
 *   取得できていない管理者があるかどうか
 * - data
 *   管理者のレスポンス情報([AdminUserResponse])のリスト
 */
data class AdminUserResponses(
    @ApiModelProperty(
        value = "管理者の件数", required = true, position = 1
    )
    val count: Int,
    @ApiModelProperty(
        value = "取得できていない管理者があるかどうか", required = true, position = 2
    )
    val hasMore: Boolean,
    @ApiModelProperty(
        value = "管理者のレスポンス情報のリスト", required = true, position = 3
    )
    val data: List<AdminUserResponse>
)
