package bondagehub.infrastructure.datasource.db.migration

import bondagehub.domain.model.post.video.*
import bondagehub.infrastructure.datasource.db.*

object PostVideosTable : ExposedTable<PostVideo>("post_videos") {

    val id = PostVideosTable.long("id").autoIncrement()
    val postId = PostVideosTable.reference("post_id", PostsTable.id)
    val title = PostVideosTable.varchar("title", length = 64)
    val description = PostVideosTable.text("description")
    val thumbnailId = PostVideosTable.reference("thumbnail_id", AttachmentsTable.id)
    val videoId = PostVideosTable.reference("video_id", AttachmentsTable.id)
    val createdAt = PostVideosTable.instant("created_at")
    val updatedAt = PostVideosTable.instant("updated_at")

    override val primaryKey = PrimaryKey(id)
}
