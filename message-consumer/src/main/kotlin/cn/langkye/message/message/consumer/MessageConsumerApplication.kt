package cn.langkye.message.message.consumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MessageConsumerApplication

fun main(args: Array<String>) {
    runApplication<MessageConsumerApplication>(*args)
}
