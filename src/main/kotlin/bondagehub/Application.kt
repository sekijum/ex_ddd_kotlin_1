package bondagehub

import bondagehub.infrastructure.datasource.db.Migration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["bondagehub"])
@ConfigurationPropertiesScan(basePackages = ["bondagehub"])
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
    // https://zenn.dev/narikake/scraps/ad95efd3f0d4dc
    Migration()
}
