package bondagehub

import bondagehub.infrastructure.datasource.db.Migration
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.AnnotationBeanNameGenerator
import org.springframework.context.annotation.ComponentScan

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
    runApplication<Application>(*args)
    // https://zenn.dev/narikake/scraps/ad95efd3f0d4dc
    Migration()
}