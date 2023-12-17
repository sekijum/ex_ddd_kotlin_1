package bondagehub.infrastructure.datasource.db.migration

import bondagehub.domain.model.attachment.*
import bondagehub.infrastructure.datasource.db.*

object AttachmentsTable : ExposedTable<Attachment>("attachments") {

    val id = AttachmentsTable.long("id").autoIncrement()
    val bucket = AttachmentsTable.varchar("bucket", length = 50)
    val key = AttachmentsTable.varchar("key", 50)
    val isAttach = AttachmentsTable.bool("is_attach").default(false)
    val createdAt = AttachmentsTable.instant("created_at")
    val updatedAt = AttachmentsTable.instant("updated_at")

    override val primaryKey = PrimaryKey(id)
}
