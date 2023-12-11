package bondagehub.infrastructure.datasource.db.migration

import bondagehub.domain.model.post.image.PostImage
import bondagehub.infrastructure.datasource.db.*

object PostImagesTable : ExposedTable<PostImage>("post_images") {

    val id = PostImagesTable.long("id").autoIncrement()
    val postId = PostImagesTable.reference("post_id", PostsTable.id)
    val title = PostImagesTable.varchar("title", length = 64)
    val description = PostImagesTable.text("description")
    val imageId = PostImagesTable.reference("thumbnail_id", AttachmentsTable.id)
    val createdAt = PostImagesTable.instant("created_at")
    val updatedAt = PostImagesTable.instant("updated_at")

    override val primaryKey = PrimaryKey(id)
}
