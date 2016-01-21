package com.hsjc.ssoCenter.core.quartz;

import com.hsjc.ssoCenter.core.constant.SMSConstant;
import com.hsjc.ssoCenter.core.domain.EmailSend;
import com.hsjc.ssoCenter.core.domain.SmsSend;
import com.hsjc.ssoCenter.core.service.EmailService;
import com.hsjc.ssoCenter.core.service.SmsService;
import com.hsjc.ssoCenter.core.util.MailUtil;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author : zga
 * @date : 2016-01-20
 *
 * 短信定时器类
 *
 */
@Component
public class SMSAndEmailQuartz {
    @Autowired
    private SmsService smsService;

    @Autowired
    private EmailService emailService;

    /**
     * @author : zga
     * @date : 2016-1-20
     *
     * 定时器,定时短信.
     *
     */
    @Scheduled(cron="0/3 * * * * ?")
    public synchronized void snedSmsAndEmail() {
        List<SmsSend> list = smsService.selectSmsSendBySendFlag();
        if(list != null && list.size() > 0){
            TaobaoClient client = new DefaultTaobaoClient(SMSConstant.URL, SMSConstant.APPKEY, SMSConstant.APPSECRET);
            AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();

            for(int i = 0;i < list.size();i ++){
                SmsSend smsSend = list.get(i);

                req.setSmsType(smsSend.getSmsType());
                req.setSmsFreeSignName(smsSend.getSmsSignName());
                req.setSmsParam(smsSend.getSmsParam());
                req.setRecNum(smsSend.getPhoneNum());
                req.setSmsTemplateCode(smsSend.getSmsTemplateCode());
                try {
                    AlibabaAliqinFcSmsNumSendResponse response = client.execute(req);
                    String errorCode = response.getErrorCode();
                    if(!StringUtils.isEmpty(errorCode)){
                        //说明发送失败
                    }

                    smsSend.setSendTime(new Date());
                    smsService.updateSendFlagById(smsSend);
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @author : zga
     * @date : 2016-1-20
     *
     * 定时器,定时发送Email
     *
     */
    @Scheduled(cron="0/3 * * * * ?")
    public synchronized void sendEmail(){
        /**
         * 发送Email
         */
        try {
            List<EmailSend> list = emailService.selectEmailSendBySendFlag();
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    EmailSend emailSend = list.get(i);
                    MailUtil.sendMail(emailSend.getSubject(), emailSend.getContent(), emailSend.getEmail());

                    emailSend.setSendTime(new Date());
                    emailService.updateSendFlagById(emailSend);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
