package bondagehub.common.database.migration

import bondagehub.common.database.seeder.AdminUserSeeder
import bondagehub.common.database.seeder.MemberSeeder
import bondagehub.common.database.table.*
import org.flywaydb.core.api.migration.BaseJavaMigration
import org.flywaydb.core.api.migration.Context
import org.jetbrains.exposed.sql.Schema
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component

@Component
class V1__init : BaseJavaMigration() {
	override fun migrate(context: Context?) {

		val schema = Schema("bondagehub").also {
			transaction {
				SchemaUtils.createSchema(it)
			}
		}

		val tables = arrayOf(
			AdminUsersTable,
			AttachmentsTable,
			PostCategoriesTable,
			PostTagsTable,
			MembersTable,
			MemberDetailsTable,
			PostsTable,
			PostVideosTable,
			PostImagesTable,
			PostAlbumsTable,
			PostAlbumImagesTable,
			PostCategorizationTable,
			PostTaggingTable,
		).also {
			transaction {
				SchemaUtils.create(*it)
				AdminUserSeeder.run()
				MemberSeeder.run()
			}
		}
	}
}
