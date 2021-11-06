import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.6"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.31"
	kotlin("plugin.spring") version "1.5.31"
	kotlin("plugin.jpa") version "1.5.31"
	java
	application
}

apply(plugin = "io.spring.dependency-management")


group = "com.ergolui"
version = "0.1"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
	maven {
		url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
	}
}

java {
	sourceCompatibility = JavaVersion.VERSION_11
	targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.ergoplatform:ergo-appkit_2.12:develop-1b7347ca-SNAPSHOT")
	implementation("org.ergoplatform:ergo-wallet_2.12:4.0.15")
	implementation(platform("org.http4k:http4k-bom:4.16.2.0"))
	implementation("org.http4k:http4k-core")
	implementation("org.http4k:http4k-client-apache")
	implementation(group= "org.http4k", name= "http4k-format-jackson", version= "4.16.2.0")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

springBoot {
	mainClass.set("com.ergolui.ergotipperbackend.ErgoTipperBackendApplicationKt")
}

application {
	mainClass.set("com.ergolui.ergotipperbackend.ErgoTipperBackendApplicationKt")
}
