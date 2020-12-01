import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.72"
    kotlin("plugin.spring") version "1.3.72"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.3.72"
    id("org.springframework.boot") version "2.3.1.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    id("com.gorylenko.gradle-git-properties") version "2.2.2"
    jacoco
}

group = "coffee.michel"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("io.github.microutils:kotlin-logging:1.7.9")
    implementation("com.google.code.gson:gson:2.8.6")

    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.assertj:assertj-core:3.18.1")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform { excludeTags = setOf("manual", "i-test") }
}


val coverage by tasks.registering {
    group = "verification"
    description = "Runs the unit tests with coverage."

    dependsOn("test", "jacocoTestReport", "jacocoTestCoverageVerification")
    val jacocoTestReport = tasks.findByName("jacocoTestReport")
    jacocoTestReport?.mustRunAfter(tasks.findByName("test"))
    tasks.findByName("jacocoTestCoverageVerification")?.mustRunAfter(jacocoTestReport)
}

val jacocoCoverage = "0.9".toBigDecimal()
val jacocoCounters = listOf(
        "LINE",
        "BRANCH",
        "METHOD"
)
val jacocoExcludes = listOf(
        "energy/uniper/salestrading/ApplicationKt.class",
        "energy/uniper/salestrading/logging/*",
        "energy/uniper/salestrading/exception/*",
        "energy/uniper/security/*"
)

tasks.jacocoTestReport {
    reports {
        csv.isEnabled = false
        xml.isEnabled = true
        xml.destination = file("${buildDir}/jacoco/xml/jacoco.xml")
        html.isEnabled = true
        html.destination = file("${buildDir}/jacoco/html")
    }
    classDirectories.setFrom(
            sourceSets.main.get().output.asFileTree.matching {
                exclude(jacocoExcludes)
            }
    )
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        jacocoCounters.map {
            rule {
                limit {
                    counter = it
                    value = "COVEREDRATIO"
                    minimum = jacocoCoverage
                }
            }
        }
    }
    classDirectories.setFrom(
            sourceSets.main.get().output.asFileTree.matching {
                exclude(jacocoExcludes)
            }
    )
}
