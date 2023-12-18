package bondagehub.common.database.seeder


import bondagehub.common.database.table.*
import org.jetbrains.exposed.sql.batchInsert
import bondagehub.domain.model.admin_user.*

class AdminUserSeeder {

	companion object {

		fun run() {
			val result = AdminUsersTable.batchInsert(
				listOf(
					AdminUser.create(
						Name.valueOf("管理者01"),
						Email.valueOf("admin01@example.com"),
						Pass.valueOf("pass1234", Email.valueOf("admin01@example.com"))
					),
				)
			) {
				this[AdminUsersTable.name] = it.name.value()
				this[AdminUsersTable.email] = it.email.value()
				this[AdminUsersTable.pass] = it.pass.value()
				this[AdminUsersTable.updatedAt] = it.updatedAt
				this[AdminUsersTable.createdAt] = it.createdAt
			}
		}
	}
}
