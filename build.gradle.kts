plugins {
	java
	id("org.springframework.boot") version "3.0.12"
	id("io.spring.dependency-management") version "1.1.3"
}

group = "com.perfume-pedia"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.projectlombok:lombok:1.18.22")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
//	runtimeOnly("com.h2database:h2")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation ("com.fasterxml.jackson.core:jackson-databind")
	runtimeOnly("com.mysql:mysql-connector-j")
//	runtimeOnly("mysql:mysql-connector-java:8.0.33")


	//JUnit4 추가
	testImplementation("org.junit.vintage:junit-vintage-engine") {
		exclude("org.hamcrest", "hamcrest-core")

	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
