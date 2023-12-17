package bondagehub.infrastructure.s3

import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.s3.model.PutObjectRequest
import aws.sdk.kotlin.services.s3.model.MultipartUpload
import aws.sdk.kotlin.services.s3.presigners.presignPutObject
import java.net.URL
import kotlin.time.Duration.Companion.hours
import bondagehub.domain.model.attachment.*

/**
 * `POST`および`PUT`リクエストの署名
 */
suspend fun getS3PresignedUrlForUpload(attachment: Attachment): String {
    val s3 = S3Client.fromEnvironment()
    val unsignedRequest = PutObjectRequest {
        bucket = attachment.bucket
        key = attachment.key
    }

    val presignedRequest = s3.presignPutObject(unsignedRequest, 1.hours)


    return URL(presignedRequest.url.toString()).readText()
}