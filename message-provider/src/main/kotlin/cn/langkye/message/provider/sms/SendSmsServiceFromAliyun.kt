package cn.langkye.message.provider.sms

import cn.langkye.api.message.sms.model.SendType
import cn.langkye.api.message.sms.service.ISendSmsService
import com.alibaba.fastjson.JSONObject
import com.aliyun.dysmsapi20170525.*
import com.aliyun.dysmsapi20170525.Client
import com.aliyun.dysmsapi20170525.models.*
import com.aliyun.tea.*
import com.aliyun.teaopenapi.*
import com.aliyun.teaopenapi.models.*
import org.apache.dubbo.config.annotation.DubboService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import kotlin.math.ceil

/**
 * 发送阿里云短信
 * 在线文档：https://help.aliyun.com/document_detail/215759.html?spm=a2c4g.11186623.6.658.34932e84yWIeKW
 * @author langkye
 */
//@Service
@DubboService(version = "1.0.0", group = "aliyun-message")
class SendSmsServiceFromAliyun: ISendSmsService {
    val logger:Logger = LoggerFactory.getLogger(javaClass);

    @Value("\${aliyun.sms.sign}")
    private lateinit var sign:String;

    @Value("\${aliyun.sms.templateCode}")
    private lateinit var templateCode:String;

    @Value("\${aliyun.sms.accessKeyId}")
    private lateinit var accessKeyId:String;

    @Value("\${aliyun.sms.accessKeySecret}")
    private lateinit var accessKeySecret:String;

    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    @Throws(Exception::class)
    fun createClient(accessKeyId: String?, accessKeySecret: String?): Client {
        val config: Config = Config() // 您的AccessKey ID
            .setAccessKeyId(accessKeyId) // 您的AccessKey Secret
            .setAccessKeySecret(accessKeySecret)
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com"
        return Client(config)
    }

    override fun sendSms(request: cn.langkye.api.message.sms.model.SendSmsRequest) {
        logger.info("aliyun sms request:${JSONObject.toJSONString(request)}")

        if (request.type.equals(SendType.MULTIPLE)){
            logger.error("Not support multiple phones！")
            return;
        }

        var randomInt = ceil((Math.random()*9+1)*100000).toInt();
        var code = "$randomInt"

        val client: Client = createClient(accessKeyId, accessKeySecret);
        val sendSmsRequest = SendSmsRequest()
            .setPhoneNumbers(request.getPhone())
            .setSignName(sign)
            .setTemplateCode(templateCode)
            .setTemplateParam("{\"code\":\"$code\"}");

        var response = client.sendSms(sendSmsRequest);

        logger.info("aliyun sms response:${JSONObject.toJSONString(response)}");
    }
}