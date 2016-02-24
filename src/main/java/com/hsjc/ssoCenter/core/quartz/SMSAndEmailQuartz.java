package com.hsjc.ssoCenter.core.quartz;

import com.hsjc.ssoCenter.core.constant.SMSConstant;
import com.hsjc.ssoCenter.core.domain.SmsSend;
import com.hsjc.ssoCenter.core.service.EmailService;
import com.hsjc.ssoCenter.core.service.SmsService;
import com.hsjc.ssoCenter.core.util.DBUtil;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

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
    @Scheduled(cron="0/1 * * * * ?")
    public synchronized void snedSmsAndEmail() {
        /*List<SmsSend> list = smsService.selectSmsSendBySendFlag();
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
                    smsSend.setSendTime(new Date());
                    smsService.updateSendFlagById(smsSend);

                    AlibabaAliqinFcSmsNumSendResponse response = client.execute(req);
                    String errorCode = response.getErrorCode();
                    if(!StringUtils.isEmpty(errorCode)){
                        //说明发送失败
                    }
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        }*/

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConn();
            statement = connection.createStatement();
            String sql = "select * FROM tbsmssend WHERE sendFlag = 0";
            resultSet = statement.executeQuery(sql);

            TaobaoClient client = new DefaultTaobaoClient(SMSConstant.URL, SMSConstant.APPKEY, SMSConstant.APPSECRET);
            AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
            while (resultSet.next()){
                SmsSend smsSend = new SmsSend();
                smsSend.setSendTime(new Date());
                smsSend.setId(resultSet.getLong("id"));

                smsService.updateSendFlagById(smsSend);

                req.setSmsType(resultSet.getString("smsType"));
                req.setSmsFreeSignName(resultSet.getString("smsSignName"));
                req.setSmsParam(resultSet.getString("smsParam"));
                req.setRecNum(resultSet.getString("phoneNum"));
                req.setSmsTemplateCode(resultSet.getString("smsTemplateCode"));

                AlibabaAliqinFcSmsNumSendResponse response = client.execute(req);
                String errorCode = response.getErrorCode();
                if(!StringUtils.isEmpty(errorCode)){
                    //说明发送失败
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            DBUtil.freeConn(connection,resultSet,statement);
        }
    }

    /**
     * @author : zga
     * @date : 2016-1-20
     *
     * 定时器,定时发送Email
     *
     */
    //@Scheduled(cron="0/3 * * * * ?")
    /*public synchronized void sendEmail(){
        *//**
         * 发送Email
         *//*
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {

            connection = DBUtil.getConn();

            statement = connection.createStatement();
            String sql  = "SELECT * FROM tbemailsend WHERE sendFlag = 0";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                EmailSend emailSend = new EmailSend();
                emailSend.setSendTime(new Date());
                emailSend.setId(resultSet.getLong("id"));
                emailService.updateSendFlagById(emailSend);

                MailUtil.sendMail(resultSet.getString("subject"), resultSet.getString("content"), resultSet.getString("email"));
            }

            *//*List<EmailSend> list = emailService.selectEmailSendBySendFlag();
             if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    EmailSend emailSend = list.get(i);
                    emailSend.setSendTime(new Date());
                    emailService.updateSendFlagById(emailSend);

                    MailUtil.sendMail(emailSend.getSubject(), emailSend.getContent(), emailSend.getEmail());
                }
            }*//*
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            DBUtil.freeConn(connection,resultSet,statement);
        }
    }*/
}
