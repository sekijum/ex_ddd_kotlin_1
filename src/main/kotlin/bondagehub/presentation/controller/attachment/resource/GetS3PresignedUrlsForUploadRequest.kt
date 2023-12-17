package bondagehub.presentation.controller.attachment.resource

import io.swagger.annotations.ApiModelProperty

data class GetS3PresignedUrlsForUploadRequest(
    @ApiModelProperty(
        value = "S3キー", example = "2000/1/1/example", required = true, position = 1
    )
    val key: String
)
