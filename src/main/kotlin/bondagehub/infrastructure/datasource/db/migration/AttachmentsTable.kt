package bondagehub.infrastructure.datasource.db.migration

import bondagehub.domain.model.attachment.*
import bondagehub.infrastructure.datasource.db.*
import org.jetbrains.exposed.sql.*
import java.time.Instant

object AttachmentsTable : ExposedTable<Attachment>("attachments") {

    val id: Column<Long> = AttachmentsTable.long("id").autoIncrement()
    val bucket: Column<String> = AttachmentsTable.varchar("bucket", length = 100)
    val size: Column<Long> = AttachmentsTable.long("size")
    val duration: Column<String> = AttachmentsTable.varchar("duration", length = 100)
    val path: Column<String> = AttachmentsTable.varchar("path", length = 100)
    val fileName: Column<String> = AttachmentsTable.varchar("file_name", length = 100)
    val ext: Column<String> = AttachmentsTable.varchar("ext", length = 5)
    val mimeType: Column<String> = AttachmentsTable.varchar("mime_type", length = 64)
    val createdAt: Column<Instant> = AttachmentsTable.instant("created_at")
    val updatedAt: Column<Instant> = AttachmentsTable.instant("updated_at")

    override val primaryKey = PrimaryKey(id)
}
