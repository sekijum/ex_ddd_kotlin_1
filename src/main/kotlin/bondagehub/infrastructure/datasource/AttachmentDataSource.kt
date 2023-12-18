package bondagehub.infrastructure.datasource

import bondagehub.domain.model.attachment.*
import bondagehub.application.repository.AttachmentRepository
import org.jetbrains.exposed.sql.*
import org.springframework.stereotype.Repository
import bondagehub.common.database.table.*

@Repository
class AttachmentDataSource : AttachmentRepository {

    override fun createOne(attachment: Attachment): Attachment {
         return AttachmentsTable.insert {
            it[bucket] = attachment.bucket
            it[key] = attachment.key
            it[isAttach] = attachment.isAttach.value()
            it[createdAt] = attachment.createdAt
            it[updatedAt] = attachment.updatedAt
        }
            .resultedValues!!.first()
             .rowToModel()
    }

    override fun createAll(attachments: List<Attachment>): List<Attachment> {
        return AttachmentsTable.batchInsert(attachments) {
            this[AttachmentsTable.bucket] = it.bucket
            this[AttachmentsTable.key] = it.key
            this[AttachmentsTable.isAttach] = it.isAttach.value()
            this[AttachmentsTable.createdAt] = it.createdAt
            this[AttachmentsTable.updatedAt] = it.updatedAt
        }
            .map { it.rowToModel() }
    }

    private fun ResultRow.rowToModel(): Attachment =
        Attachment(
            this[AttachmentsTable.id],
            this[AttachmentsTable.bucket],
            this[AttachmentsTable.key],
            IsAttach(this[AttachmentsTable.isAttach]),
            this[AttachmentsTable.createdAt],
            this[AttachmentsTable.updatedAt]
        )
}
