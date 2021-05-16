package cn.langkye.api.message.mail.model

import java.io.Serializable


class SendMailRequest:Serializable {
    lateinit var mailSubject:String;
    lateinit var sendTo:String;
    lateinit var mailContent:String;
    lateinit var attachment:Any;
}
