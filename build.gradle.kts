import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    id("java")
    id("maven")
    id("idea")
    id("eclipse")
    id("jacoco")
    id("org.springframework.boot") version "2.2.2.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version "1.3.72"
    kotlin("plugin.spring") version "1.3.72"
    kotlin("plugin.jpa") version "1.3.72"
    kotlin("kapt") version "1.3.72"
    base
}

group = "cn.langkye"
version = "0.0.1-SNAPSHOT"

extra["springBootVersion"] = "2.2.2.RELEASE"

//仓库配置
repositories {
    mavenLocal { setUrl("file://${project.rootDir}/lib") }
    //首先去本地仓库找
    mavenLocal()
    //然后去阿里仓库找
    // build.gradle.bak:
    // maven { url "http://maven.aliyun.com/nexus/content/groups/public/" }
    // build.gradle.bak.kts:
    maven { setUrl("http://maven.aliyun.com/nexus/content/groups/public/") }
    maven { url = uri("https://maven.aliyun.com/repository/public") }
    maven { url = uri("https://maven.aliyun.com/repository/google") }
    maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
    maven { url = uri("https://maven.aliyun.com/repository/spring-plugin") }
    maven { url = uri("https://maven.aliyun.com/repository/apache-snapshots") }
    maven { url = uri("http://oss.jfrog.org/artifactory/oss-snapshot-local/") }
    //最后从maven中央仓库找
    mavenCentral()
}

//禁止根项目一切行为（不影响模块）
tasks.forEach {
    //禁用以后执行build的时候不会构建parent项目
    it.enabled = false
}

allprojects {
    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }
}




dependencyManagement {
}

subprojects {
    //使用kotlin插件
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring")
    apply(plugin = "java")
    apply(plugin = "idea")
    apply(plugin = "maven")
    apply(plugin = "eclipse")
    apply(plugin = "jacoco")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.jvm")


    java.sourceCompatibility = JavaVersion.VERSION_1_8

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    sourceSets {
        main {
            kotlin {
            }
        }
    }

    task("createDirs") {
        mkdir("src/main/kotlin")
        mkdir("src/main/resources")
        mkdir("src/test/kotlin")
        mkdir("src/test/resources")
        delete("src/main/java")
        delete("src/test/java")
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        // https://mvnrepository.com/artifact/cn.hutool/hutool-all
        implementation("cn.hutool:hutool-all:5.6.5")
        runtimeOnly("mysql:mysql-connector-java")
        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        }
    }


    //仓库配置
    repositories {
        mavenLocal { setUrl("file://${project.rootDir}/lib") }
        //首先去本地仓库找
        mavenLocal()
        //然后去阿里仓库找
        // build.gradle.bak:
        // maven { url "http://maven.aliyun.com/nexus/content/groups/public/" }
        // build.gradle.bak.kts:
        maven { setUrl("http://maven.aliyun.com/nexus/content/groups/public/") }
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven { url = uri("https://maven.aliyun.com/repository/google") }
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
        maven { url = uri("https://maven.aliyun.com/repository/spring-plugin") }
        maven { url = uri("https://maven.aliyun.com/repository/apache-snapshots") }
        maven { url = uri("http://oss.jfrog.org/artifactory/oss-snapshot-local/") }
        //最后从maven中央仓库找
        mavenCentral()
    }
}