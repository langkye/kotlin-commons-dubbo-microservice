dependencies {
    //本地api依赖
    implementation(project(":message-api"))

    //邮件依赖
    implementation("org.springframework.boot:spring-boot-starter-mail")

    //腾讯云短信SDK
    // https://mvnrepository.com/artifact/com.tencentcloudapi/tencentcloud-sdk-java
    implementation("com.tencentcloudapi:tencentcloud-sdk-java:4.0.11")

    //阿里云短信SDK
    implementation("com.tencentcloudapi:tencentcloud-sdk-java:4.0.11")

    //lombok依赖
    // https://mvnrepository.com/artifact/org.projectlombok/lombok
    implementation("com.aliyun:dysmsapi20170525:2.0.4")

    //nacos依赖
    implementation("org.apache.dubbo:dubbo-registry-nacos:2.7.10")
    //dubbo依赖
    implementation("org.apache.dubbo:dubbo-spring-boot-starter:2.7.10")

    //hutool工具类依赖
    // https://mvnrepository.com/artifact/cn.hutool/hutool-all
    implementation("cn.hutool:hutool-all:5.6.5")

    //热部署devtools依赖
    implementation("org.springframework.boot:spring-boot-devtools")
}