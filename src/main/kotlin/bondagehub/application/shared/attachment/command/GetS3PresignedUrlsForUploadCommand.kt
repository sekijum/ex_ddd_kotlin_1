package bondagehub.application.shared.attachment.command

data class GetS3PresignedUrlsForUploadCommand(
    val key: String,
)
