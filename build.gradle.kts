plugins {
    kotlin("jvm") version "1.9.23"
}

group = "fr.thibaut.chess"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.exposed", "exposed-core", "0.50.0")
    implementation("org.jetbrains.exposed", "exposed-dao", "0.50.0")
    implementation("org.jetbrains.exposed", "exposed-jdbc", "0.50.0")
    implementation("org.xerial:sqlite-jdbc:3.44.1.0")
    implementation("io.github.oshai:kotlin-logging-jvm:5.1.1")
    implementation("org.slf4j:slf4j-api:2.0.9")
    implementation("ch.qos.logback:logback-classic:1.5.6")
}

tasks.register<JavaExec>("gameLaunch") {
    group = "application"
    classpath = sourceSets["main"].runtimeClasspath
    args = listOf("dev")
    description = "Launch the chess game"
    mainClass.set("fr.thibaut.chess.GameLaunch")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(20)
}