package bondagehub.common.database

import bondagehub.common.database.seeder.AdminUserSeeder
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory
import javax.sql.DataSource


class DatabaseManager {
	private val log = LoggerFactory.getLogger(this::class.java)


	/**
	 * Establishes a database connection using HikariCP connection pool and runs Flyway migrations.
	 */
	fun connectAndMigrate() {
		log.info("Attempting to migrate")
		val pool = createConnectionPool()
		Database.connect(pool)
		runFlyway("bondagehub/common/database/migration", pool)
	}

	/**
	 * Creates and configures a HikariCP connection pool based on application configuration.
	 *
	 * @return HikariCP data source.
	 */
	private fun createConnectionPool(): HikariDataSource {
		val config = HikariConfig().apply {
			driverClassName = "org.h2.Driver"
			jdbcUrl = "jdbc:h2:mem:bondagehub"
			username = "Ninja"
			password = "Ninja01"
			maximumPoolSize = 3
			isAutoCommit = false
			transactionIsolation = "TRANSACTION_REPEATABLE_READ"
			validate()
		}
		return HikariDataSource(config)
	}

	/**
	 * Runs database migrations using Flyway based on the specified migration locations.
	 *
	 * @param datasource The data source for the database.
	 */
	private fun runFlyway(flywayMigrationPath: String, datasource: DataSource) {
		val flyway = Flyway.configure()
			.dataSource(datasource)
			.locations(flywayMigrationPath)
			.load()
		try {
			flyway.info()
			flyway.migrate()
		} catch (e: Exception) {
			log.error("Exception running Flyway migration", e)
			throw e
		}
		log.info("Flyway migration has finished")
	}
}

/**
 * Executes a database transaction within a coroutine scope.
 *
 * @param block A lambda that contains the database transaction logic.
 * @return The result of the transaction.
 */
suspend fun <T> executeInTransaction(
	block: () -> T
): T = withContext(Dispatchers.IO) {
	transaction { block() }
}
