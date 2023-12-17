import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.1"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
}

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    // springDependencies
    implementation("org.springframework.boot:spring-boot-starter-web")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework:spring-tx")

    // sqlDependencies
    implementation("org.jetbrains.exposed:exposed-core:0.40.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.40.1")
    implementation("org.jetbrains.exposed:exposed-jodatime:0.40.1")
    implementation("org.jetbrains.exposed:spring-transaction:0.40.1")
    implementation("org.jetbrains.exposed:exposed-spring-boot-starter:0.40.1")
    runtimeOnly("com.h2database:h2")

    // springfoxDependencies
    implementation("io.springfox:springfox-boot-starter:3.0.0")

    // loggingDependency
    implementation("io.github.microutils:kotlin-logging:1.6.26")

    // jacksonDependencies
    implementation("com.fasterxml.jackson.module:jackson-modules-java8:2.12.7")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.7")
    implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:2.12.7")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.12.7")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.12.7")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("io.projectreactor:reactor-core")

    implementation("aws.sdk.kotlin:s3:1.0.0")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

//    withType<Test> {
//        testLogging {
//            events("skipped", "failed")
//            setExceptionFormat("full")
//        }
//        useJUnitPlatform()
//    }
}
