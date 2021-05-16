package cn.langkye.api.message.sms.service

import cn.langkye.api.message.sms.model.SendSmsRequest

interface ISendSmsService {
    fun sendSms(request: SendSmsRequest);
}