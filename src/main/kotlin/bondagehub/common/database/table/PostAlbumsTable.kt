package bondagehub.common.database.table

import bondagehub.domain.model.post.album.PostAlbum
import bondagehub.common.database.*

object PostAlbumsTable : ExposedTable<PostAlbum>("post_albums") {

    val id = PostAlbumsTable.long("id").autoIncrement()
    val postId = PostAlbumsTable.reference("post_id", PostsTable.id)
    val title = PostAlbumsTable.varchar("title", length = 64)
    val description = PostAlbumsTable.text("description")
    val thumbnailId = PostAlbumsTable.reference("thumbnail_id", AttachmentsTable.id)
    val createdAt = PostAlbumsTable.instant("created_at")
    val updatedAt = PostAlbumsTable.instant("updated_at")

    override val primaryKey = PrimaryKey(id)
}
