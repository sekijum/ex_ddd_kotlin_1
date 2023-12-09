package bondagehub.application.repository

import bondagehub.domain.model.attachment.Attachment

interface AttachmentRepository {

    fun add(attachment: Attachment)
}
