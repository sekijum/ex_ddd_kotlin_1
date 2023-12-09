package bondagehub.infrastructure.datasource.db

import bondagehub.infrastructure.datasource.db.migration.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Schema
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class Migration {
    companion object {
//        val database = Database.connect(
//            url = "jdbc:h2:mem:bondagehub;MODE=MySQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
//            driver = "org.h2.Driver",
//            user = "Ninja",
//            password = "Ninja01"
//        )

        val schema = Schema("bondagehub").also {
            transaction {
                SchemaUtils.createSchema(it)
            }
        }

        val tables = arrayOf(
            AdminUsersTable,
            AttachmentsTable,
        ).also {
            transaction {
                SchemaUtils.create(*it)
            }
        }
    }
}