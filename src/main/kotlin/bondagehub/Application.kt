package bondagehub

import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.AnnotationBeanNameGenerator
import org.springframework.context.annotation.ComponentScan
import bondagehub.common.database.DatabaseManager

class FQCNBeanNameGenerator : AnnotationBeanNameGenerator() {
    override fun buildDefaultBeanName(definition: BeanDefinition): String {
        return definition.beanClassName!!
    }
}

@SpringBootApplication(scanBasePackages = ["bondagehub"])
@ConfigurationPropertiesScan(basePackages = ["bondagehub"])
@ComponentScan(nameGenerator = FQCNBeanNameGenerator::class)
class Application

fun main(args: Array<String>) {
    DatabaseManager().connectAndMigrate()
    runApplication<Application>(*args)
}