package cn.langkye.api.message.sms.model

import java.io.Serializable

class SendSmsRequest:Serializable{
    //类型：SINGLE-发送单条短信，phone；MULTIPLE-发送多条短信,
    var type:SendType= SendType.SINGLE;
    private lateinit var phone:String;
    private var phoneSet:Array<String> = arrayOf();
    private var content:String="";

    fun setPhone(phone:String){
        this.phone = "+86$phone";
    }
    fun getPhone():String{
        return this.phone;
    }

    fun setPhoneSet(phoneSet:Array<String>){
        for (i in 0 until phoneSet.size){
            var it = phoneSet[i];
            phoneSet[i]="+86$it"
        }
        this.phoneSet = phoneSet;
    }

    fun getPhoneSet():Array<String>{
        return this.phoneSet;
    }

    fun setContent(content:String){
        this.content = content;
    }

    fun getContent():String{
        return this.content;
    }
}

enum class SendType{
    SINGLE,
    MULTIPLE;
}