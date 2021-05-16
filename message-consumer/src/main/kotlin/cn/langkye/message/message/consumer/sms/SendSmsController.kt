package cn.langkye.message.message.consumer.sms

import cn.langkye.api.message.mail.model.SendMailRequest
import cn.langkye.api.message.sms.model.SendSmsRequest
import cn.langkye.api.message.sms.model.SendType
import cn.langkye.api.message.sms.service.ISendSmsService
import cn.langkye.message.message.consumer.mail.SendMailController
import org.apache.dubbo.config.annotation.DubboReference
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("")
class SendSmsController {
    private val LOGGER: Logger = LoggerFactory.getLogger(SendMailController::class.java)
    @DubboReference(version = "1.0.0", group = "message", timeout = 10000)
    private lateinit var sendSmsService: ISendSmsService;

    @RequestMapping("/api/message/send/sms/simple")
    fun sendSimpleMail(){
        //build send sms request
        var request: SendSmsRequest = SendSmsRequest();
        request.setPhone("17585178571");
        request.content = "This is sms content from message-consumer's service."
        request.type = SendType.SINGLE;

        LOGGER.info("send request:{}",request);

        //call dubbo service
        sendSmsService.sendSms(request);
    }
}