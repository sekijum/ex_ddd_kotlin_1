package bondagehub.presentation.configuration

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.spring.SpringTransactionManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.transaction.annotation.EnableTransactionManagement
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.builders.PathSelectors
import javax.sql.DataSource

private typealias PlatformDataSource = HikariDataSource

@Configuration
@EnableTransactionManagement
class ExposedConfiguration(val dataSource: DataSource) {

    @Bean
    fun transactionManager(dataSource: PlatformDataSource): SpringTransactionManager =
        SpringTransactionManager(dataSource)

    @Bean
    fun persistenceExceptionTranslationPostProcessor(): PersistenceExceptionTranslationPostProcessor =
        PersistenceExceptionTranslationPostProcessor()
}

@Configuration
class JacksonConfiguration {

    @Bean
    fun objectMapper(): ObjectMapper = jacksonObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
}

@Configuration
class SpringfoxConfiguration {

    @Bean
    fun customDocket(): Docket = Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("bondagehub.presentation.controller"))
        .paths(PathSelectors.any())
        .build()
        .useDefaultResponseMessages(false)
        .apiInfo(apiInfo())

    private fun apiInfo(): ApiInfo = ApiInfoBuilder()
        .title("API仕様書")
        .contact(Contact("sekijum", "https://github.com/sekijum", "jumpei0910@icloud.com"))
        .version("1.0.0")
        .build()
}
