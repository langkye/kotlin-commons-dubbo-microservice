package cn.langkye.api.message.mail.service

import cn.langkye.api.message.mail.model.SendMailRequest

interface ISendMailService {
    /**
     * 发送简单邮件
     *
     * @param request 接受人、邮件主题、邮件内容
     */
    fun sendSimpleMail(request: SendMailRequest);

    /**
     * 发送待附件邮件
     * @param request <>
     */
    fun sendAttachmentsMail(request: SendMailRequest?) {}
}