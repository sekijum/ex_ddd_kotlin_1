package bondagehub.infrastructure.datasource.db.migration

import bondagehub.domain.model.attachment.*
import bondagehub.infrastructure.datasource.db.*

object AttachmentsTable : ExposedTable<Attachment>("attachments") {

    val id = AttachmentsTable.long("id").autoIncrement()
    val bucket = AttachmentsTable.varchar("bucket", length = 100)
    val size = AttachmentsTable.long("size")
    val duration= AttachmentsTable.varchar("duration", length = 100)
    val path = AttachmentsTable.varchar("path", length = 100)
    val fileName = AttachmentsTable.varchar("file_name", length = 100)
    val ext = AttachmentsTable.varchar("ext", length = 5)
    val mimeType = AttachmentsTable.varchar("mime_type", length = 64)
    val createdAt = AttachmentsTable.instant("created_at")
    val updatedAt = AttachmentsTable.instant("updated_at")

    override val primaryKey = PrimaryKey(id)
}
