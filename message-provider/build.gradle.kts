dependencies {
    implementation(project(":message-api"))
    implementation("org.springframework.boot:spring-boot-starter-mail")
    // https://mvnrepository.com/artifact/com.tencentcloudapi/tencentcloud-sdk-java
    implementation("com.tencentcloudapi:tencentcloud-sdk-java:4.0.11")
    // https://mvnrepository.com/artifact/org.projectlombok/lombok
    implementation("org.projectlombok:lombok:1.18.20")
    implementation("org.apache.dubbo:dubbo-registry-nacos:2.7.10")
    implementation("org.apache.dubbo:dubbo-spring-boot-starter:2.7.10")
    implementation("org.springframework.boot:spring-boot-devtools")
    //implementation("log4j:log4j:1.2.16")
    // https://mvnrepository.com/artifact/cn.hutool/hutool-all
    implementation("cn.hutool:hutool-all:5.6.5")
}