package bondagehub.infrastructure.datasource.db.migration

import org.jetbrains.exposed.sql.Table

object PostAlbumImagesTable : Table("post_album_images") {

    val id = long("id")
    val order = integer("order")
    val postAlbumId = reference("post_album_id", PostAlbumsTable.id)
    val imageId = reference("image_id", AttachmentsTable.id)

    override val primaryKey = PrimaryKey(id)
}
