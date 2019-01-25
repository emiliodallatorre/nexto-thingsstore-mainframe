apply plugin: 'kotlin'
apply plugin: 'application'

buildscript {
    ext.kotlin_version = "1.3.20"

    repositories {
        jcenter()
    }

    dependencies {
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    }

}

mainClassName = 'com.nextocompany.thingsstore.ServerKt'
defaultTasks 'run'

run {
    standardInput = System.in
}

jar {
    baseName = 'things-store'
    version = '0.1'
}

repositories {
    jcenter()
}

dependencies {
    compile "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    compile files('lib/mysql-connector-java-8.0.13.jar')

    // Test dependencies.
    testCompile("org.junit.jupiter:junit-jupiter-api:5.3.2")
    testCompile("org.junit.jupiter:junit-jupiter-params:5.3.2")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.3.2")
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