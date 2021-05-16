package cn.langkye.message.provider

import cn.langkye.api.message.mail.model.SendMailRequest
import cn.langkye.api.message.mail.service.ISendMailService
import cn.langkye.api.message.sms.service.ISendSmsService
import org.apache.dubbo.config.annotation.DubboReference
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class MessageProviderApplicationTests {
    private val logger = LoggerFactory.getLogger(MessageProviderApplicationTests::class.java)

    @DubboReference(version = "1.0.0")
    //@Autowired
    private lateinit var smsService: ISendSmsService;

    @DubboReference(version = "1.0.0", group = "message", timeout = 10000)
    //@Autowired
    private lateinit var mailService: ISendMailService;

    @Test
    fun testSendMail() {
        val req = SendMailRequest();
        req.sendTo = "langkye@gmail.com";
        req.mailContent="mail content";
        req.mailSubject = "message provider"
        logger.info("call smsService, req: {}", req)
        mailService.sendSimpleMail(req)
    }

    @Test
    fun testSendSms() {
        val req = cn.langkye.api.message.sms.model.SendSmsRequest();
        req.setPhone("1758517xxxx");
        logger.info("call smsService, req: {}", req)
        smsService.sendSms(req)
    }
}
