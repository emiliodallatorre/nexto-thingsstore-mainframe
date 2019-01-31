plugins {
    kotlin("jvm") version "1.3.20"
    application
    `build-scan`
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    // Test dependencies.
    testCompile("org.junit.jupiter:junit-jupiter-api:5.3.2")
    testCompile("org.junit.jupiter:junit-jupiter-params:5.3.2")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.3.2")

    implementation(kotlin("stdlib"))

    // Aggiunge le dipendenze in formato .jar che stanno in ./lib.
    implementation(fileTree(mapOf("dir" to "lib", "include" to listOf("*.jar"))))
}

buildScan {
    setTermsOfServiceUrl("https://gradle.com/terms-of-service")
    setTermsOfServiceAgree("yes")

    publishAlways()
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<Wrapper> {
    gradleVersion = "4.8"
}

allprojects {
    group = "com.nextocompany.thingsstore"
    version = "0.1"
}

application {
    mainClassName = "com.nextocompany.thingsstore.ServerKt"
}

val run by tasks.getting(JavaExec::class) {
    standardInput = System.`in`
}

defaultTasks("run")