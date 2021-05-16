package cn.langkye.message.provider.sms

import cn.hutool.core.date.DateUtil
import cn.langkye.api.message.sms.model.SendSmsRequest
import cn.langkye.api.message.sms.model.SendType
import cn.langkye.api.message.sms.service.ISendSmsService
import com.alibaba.fastjson.JSONObject
import com.tencentcloudapi.common.Credential
import com.tencentcloudapi.common.profile.ClientProfile
import com.tencentcloudapi.common.profile.HttpProfile
import com.tencentcloudapi.sms.v20190711.SmsClient
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse
import org.apache.commons.lang3.time.DateUtils
import org.apache.dubbo.config.annotation.DubboService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*
import javax.annotation.PostConstruct
import kotlin.math.ceil

/**
 * 短信发送服务
 * 在线文档：@docs https://cloud.tencent.com/document/product/382/43194
 */
//@Service
@DubboService(version = "1.0.0", group = "tencentcloud-message")
class SendSmsServiceFromTencentclound : ISendSmsService {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Value("\${tencent.sms.secretId}")
    private lateinit var secretId: String;

    @Value("\${tencent.sms.secretKey}")
    private lateinit var secretKey: String;

    @Value("\${tencent.sms.port}")
    private var port: Int? = 0;

    @Value("\${tencent.sms.appid}")
    private lateinit var appid: String;

    @Value("\${tencent.sms.templateId}")
    private lateinit var templateId: String;

    @Value("\${tencent.sms.sign}")
    private lateinit var sign: String;

    @Value("\${tencent.sms.port}")
    private fun setPort(port: String) {
        this.port = port.toInt()
    }

    @PostConstruct
    private fun init() {

    }

    /**
     * 发送短信
     */
    override fun sendSms(request: SendSmsRequest) {
        logger.info("Tencentclound request: ${DateUtil.format(Date(), "yyyy-MM-dd HH:mm:ss")}request:${JSONObject.toJSONString(request)}")
        val cred = Credential(secretId, secretKey)

        val httpProfile = HttpProfile()
        httpProfile.endpoint = "sms.tencentcloudapi.com"

        val clientProfile = ClientProfile()
        clientProfile.httpProfile = httpProfile

        val client = SmsClient(cred, "", clientProfile)

        val req = com.tencentcloudapi.sms.v20190711.models.SendSmsRequest()
        if (SendType.SINGLE.equals(request.type)) {
            req.phoneNumberSet = arrayOf(request.getPhone())
        } else {
            req.phoneNumberSet = request.getPhoneSet();
        }
        //val phoneNumberSet1 = arrayOf("+861758517***")
        //req.setPhoneNumberSet(phoneNumberSet1)

        req.setTemplateID(templateId)
        req.setSign(sign)

        var randomInt = ceil((Math.random()*9+1)*100000).toInt();
        var code = "$randomInt"

        var timeout: Int = 1000 * 60 * 5;
        var codeTimeout: String = "$timeout";

        val templateParamSet1 = arrayOf(code, codeTimeout)
        req.setTemplateParamSet(templateParamSet1)

        req.setSmsSdkAppid(appid)

        val resp = client.SendSms(req)

        logger.info("TencentCloud sms response:${SendSmsResponse.toJsonString(resp)}")
    }
}
