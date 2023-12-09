package bondagehub.infrastructure.datasource

import bondagehub.domain.model.attachment.*
import bondagehub.application.repository.AttachmentRepository
import org.jetbrains.exposed.sql.*
import org.springframework.stereotype.Repository
import bondagehub.infrastructure.datasource.db.migration.AttachmentsTable

@Repository
class AttachmentDataSource : AttachmentRepository {

    override fun add(attachment: Attachment) {
        AttachmentsTable.insert {
            it[bucket] = attachment.bucket
            it[path] = attachment.path
            it[fileName] = attachment.fileName
            it[ext] = attachment.ext
            it[mimeType] = attachment.mimeType
            it[createdAt] = attachment.createdAt
            it[updatedAt] = attachment.updatedAt
        }
    }

    private fun ResultRow.rowToModel(): Attachment =
        Attachment(
            this[AttachmentsTable.id],
            this[AttachmentsTable.bucket],
            this[AttachmentsTable.size],
            this[AttachmentsTable.duration],
            this[AttachmentsTable.path],
            this[AttachmentsTable.fileName],
            this[AttachmentsTable.ext],
            this[AttachmentsTable.mimeType],
            this[AttachmentsTable.createdAt],
            this[AttachmentsTable.updatedAt]
        )
}
