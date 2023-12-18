package bondagehub.common.database.table

import org.jetbrains.exposed.sql.Table
import bondagehub.common.database.*

object PostAlbumImagesTable : Table("post_album_images") {

    val order = integer("order")
    val postAlbumId = reference("post_album_id", PostAlbumsTable.id)
    val imageId = reference("image_id", AttachmentsTable.id)

    override val primaryKey = PrimaryKey(postAlbumId, imageId)
}
