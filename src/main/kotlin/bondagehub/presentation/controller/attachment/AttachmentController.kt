package bondagehub.presentation.controller.attachment

import bondagehub.application.service.admin.adminuser.*
import bondagehub.application.service.admin.adminuser.command.*
import bondagehub.application.service.admin.adminuser.dto.*
import bondagehub.application.shared.attachment.AttachmentService
import bondagehub.application.shared.attachment.command.GetS3PresignedUrlsForUploadCommand
import bondagehub.presentation.controller.admin.adminuser.resource.*
import bondagehub.presentation.controller.attachment.resource.GetS3PresignedUrlsForUploadRequest
import bondagehub.presentation.controller.attachment.resource.GetS3PresignedUrlsForUploadResponse
import io.swagger.annotations.*
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@Api("ファイルを管理するAPI", tags = ["attachment"])
@RestController
@RequestMapping("/attachment")
class AttachmentController(private val attachmentService: AttachmentService) {

    @ApiOperation("アップロードリクエストの署名付きURLの発行")
    @PostMapping("/urls-for-upload")
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun getS3PresignedUrlsForUpload(
        @RequestBody request: List<GetS3PresignedUrlsForUploadRequest>
    ): Mono<List<GetS3PresignedUrlsForUploadResponse>> {
        val commands = request.map { GetS3PresignedUrlsForUploadCommand(key = it.key) }
        return attachmentService.getS3PresignedUrlsForUpload(commands)
            .map { it ->
                it.map {
                    GetS3PresignedUrlsForUploadResponse(
                        attachmentId = it.first,
                        url = it.second,
                    )
                }
            }
    }
}
