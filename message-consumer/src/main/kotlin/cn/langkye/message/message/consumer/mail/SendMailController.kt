package cn.langkye.message.message.consumer.mail

import cn.langkye.api.message.mail.model.SendMailRequest
import cn.langkye.api.message.mail.service.ISendMailService
import org.apache.dubbo.config.annotation.DubboReference
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class SendMailController {
    private val LOGGER: Logger = LoggerFactory.getLogger(SendMailController::class.java)

    @DubboReference(version = "1.0.0", group = "message", timeout = 10000)
    private lateinit var sendMailService: ISendMailService;

    @RequestMapping("/api/message/send/mail/simple")
    fun sendSimpleMail(){
        //build send mail request
        var request: SendMailRequest = SendMailRequest();
        request.mailSubject = "Consumer mail message.";
        request.mailContent = "This is mail content from message-consumer's service."
        request.sendTo = "878057221@qq.com";

        LOGGER.info("send request:{}",request);

        //call dubbo service
        sendMailService.sendSimpleMail(request);

    }
}