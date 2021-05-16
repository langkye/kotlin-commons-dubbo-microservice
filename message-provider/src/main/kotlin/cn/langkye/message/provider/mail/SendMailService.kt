package cn.langkye.message.provider.mail

import cn.langkye.api.message.mail.model.SendMailRequest
import cn.langkye.api.message.mail.service.ISendMailService
import org.apache.dubbo.config.annotation.DubboService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

//@Service
@DubboService(version = "1.0.0",group = "message")
class SendMailService: ISendMailService {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    private val mailSender: JavaMailSender? = null

    /**
     * 发件邮箱
     */
    @Value("\${spring.mail.username}")
    private val emailFrom: String? = null

    /**
     * 发送简单邮件
     *
     * @param request 接受人、邮件主题、邮件内容
     */
    override fun sendSimpleMail(request: SendMailRequest) {
        val message = SimpleMailMessage()
        message.setFrom(emailFrom!!)
        message.setTo(request.sendTo)
        message.setSubject(request.mailSubject)
        message.setText(request.mailContent)
        mailSender!!.send(message)
        logger.info("send mail: {}", request)
    }
}