package bondagehub.presentation.controller.attachment.resource

import io.swagger.annotations.ApiModelProperty

data class GetS3PresignedUrlsForUploadResponse(
    @ApiModelProperty(
        value = "ファイルID", example = "1", required = true, position = 1
    )
    val attachmentId: Long,
    @ApiModelProperty(
        value = "著名付きURL", example = "https://example.com/hoge", required = true, position = 2
    )
    val url: String
)
