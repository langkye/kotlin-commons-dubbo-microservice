//tasks.getByName<Jar>("jar") {
//    enabled = true
//}

tasks.getByName<Jar>("jar") {
    enabled = true
}

//tasks.getByName<Jar>("bootJar") {
//    enabled = true
//}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}