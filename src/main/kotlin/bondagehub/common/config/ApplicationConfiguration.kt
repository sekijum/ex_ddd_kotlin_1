package bondagehub.common.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.jetbrains.exposed.spring.SpringTransactionManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.data.domain.PageRequest
import org.springframework.data.web.PageableHandlerMethodArgumentResolver
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import javax.sql.DataSource

@Configuration
class EmployeeConfig : WebMvcConfigurer {
    // 設定を補完する情報のことをリゾルバ(resolver)と呼ぶ
    override fun addArgumentResolvers(argumentResolvers: MutableList<HandlerMethodArgumentResolver>) {

        // Pageableに対して設定を行うためのクラスであり、リゾルバ
        val resolver = PageableHandlerMethodArgumentResolver()

        // ページ単位に表示する件数を追加(第一引数：ページ番号、第二引数：1ページあたりの表示件数)
        resolver.setFallbackPageable(PageRequest.of(0, 10))

        // 具体的な設定をリゾルバに追加後、リストに追加
        argumentResolvers.add(resolver)
    }
}

@Configuration
@EnableTransactionManagement
class ExposedConfiguration(val dataSource: DataSource) {

    @Bean
    fun transactionManager(dataSource: DataSource): SpringTransactionManager =
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
