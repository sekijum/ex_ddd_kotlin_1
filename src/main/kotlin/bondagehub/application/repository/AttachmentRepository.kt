package bondagehub.application.repository

import bondagehub.domain.model.attachment.Attachment

interface AttachmentRepository {

    fun createOne(attachment: Attachment): Attachment
    fun createAll(attachments: List<Attachment>): List<Attachment>
}
