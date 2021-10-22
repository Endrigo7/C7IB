plugins {
    id("org.jetbrains.kotlin.jvm") version "1.5.10"
    id("jacoco")
}

group = "org.example"
version = "1.0-SNAPSHOT"

sourceSets {
    create("componentTest") {
        compileClasspath += sourceSets.main.get().output
        runtimeClasspath += sourceSets.main.get().output
    }
}

val componentTestImplementation: Configuration by configurations.getting {
    extendsFrom(configurations.implementation.get())
}

configurations["componentTestRuntimeOnly"].extendsFrom(configurations.runtimeOnly.get())

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.5.10")

    //json
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.11.2")

    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
    testImplementation("io.mockk:mockk:1.10.0")

    componentTestImplementation(sourceSets["test"].output)
    componentTestImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
}

val mainClassQualifiedName = "school.cesar.c7ib.C7IBMain"

val componentTestTask = tasks.create("componentTest", Test::class) {
    description = "Runs the component testes."
    group = "verification"

    testClassesDirs = sourceSets["componentTest"].output.classesDirs
    classpath = sourceSets["componentTest"].runtimeClasspath

    useJUnitPlatform()
}

tasks.jar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    manifest {
        attributes("Main-Class" to mainClassQualifiedName)
    }

    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<JacocoReport> {
    reports {
        xml.required
        html.required
    }

    afterEvaluate {
        classDirectories.setFrom(files(classDirectories.files.map {
            fileTree(it).apply {
                exclude(
                    "school/cesar/c7ib/C7IBMain.class"
                )
            }
        }))
    }
}

tasks.withType<JacocoCoverageVerification> {
    violationRules {
        rule {
            limit {
                minimum = "0.0".toBigDecimal()
                counter = "LINE"
            }
            limit {
                minimum = "0.0".toBigDecimal()
                counter = "BRANCH"
            }
        }
    }

    afterEvaluate {
        classDirectories.setFrom(files(classDirectories.files.map {
            fileTree(it).apply {
                exclude(
                    "school/cesar/c7ib/C7IBMain.class"
                )
            }
        }))
    }
}

tasks.test {
    finalizedBy(
        "jacocoTestReport",
        "jacocoTestCoverageVerification",
        "componentTest"
    )
}