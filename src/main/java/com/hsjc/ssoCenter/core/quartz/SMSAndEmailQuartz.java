//package com.hsjc.ssoCenter.core.quartz;
//
//import com.hsjc.ssoCenter.core.constant.SMSConstant;
//import com.hsjc.ssoCenter.core.domain.EmailSend;
//import com.hsjc.ssoCenter.core.domain.SmsSend;
//import com.hsjc.ssoCenter.core.service.EmailService;
//import com.hsjc.ssoCenter.core.service.SmsService;
//import com.hsjc.ssoCenter.core.util.DBUtil;
//import com.hsjc.ssoCenter.core.util.MailUtil;
//import com.taobao.api.DefaultTaobaoClient;
//import com.taobao.api.TaobaoClient;
//import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
//import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.Date;
//
///**
// * @author : zga
// * @date : 2016-01-20
// *
// * 短信定时器类
// *
// */
//@Component
//public class SMSAndEmailQuartz {
//
//    private final static Logger logger = Logger.getLogger(SMSAndEmailQuartz.class);
//
//    @Autowired
//    private SmsService smsService;
//
//    @Autowired
//    private EmailService emailService;
//
//    /**
//     * @author : zga
//     * @date : 2016-1-20
//     *
//     * 定时器,定时短信.
//     *
//     */
//    @Scheduled(cron="0/6 * * * * ?")
//    public void snedSmsAndEmail() {
//        Connection connection = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = DBUtil.getConn();
//            statement = connection.createStatement();
//            String sql = "select * FROM tbsmssend WHERE sendFlag = 0";
//            resultSet = statement.executeQuery(sql);
//
//            TaobaoClient client = new DefaultTaobaoClient(SMSConstant.URL, SMSConstant.APPKEY, SMSConstant.APPSECRET);
//            AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
//            while (resultSet.next()){
//                SmsSend smsSend = new SmsSend();
//                smsSend.setSendTime(new Date());
//                smsSend.setId(resultSet.getLong("id"));
//
//                smsService.updateSendFlagById(smsSend);
//
//                req.setSmsType(resultSet.getString("smsType"));
//                req.setSmsFreeSignName(resultSet.getString("smsSignName"));
//                req.setSmsParam(resultSet.getString("smsParam"));
//                req.setRecNum(resultSet.getString("phoneNum"));
//                req.setSmsTemplateCode(resultSet.getString("smsTemplateCode"));
//
//                AlibabaAliqinFcSmsNumSendResponse response = client.execute(req);
//                String errorCode = response.getErrorCode();
//                if(!StringUtils.isEmpty(errorCode)){
//                    //说明发送失败
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        } finally {
//            DBUtil.freeConn(connection,resultSet,statement);
//        }
//    }
//
//    /**
//     * @author : zga
//     * @date : 2016-1-20
//     *
//     * 定时器,定时发送Email
//     *
//     */
//    @Scheduled(cron="0/6 * * * * ?")
//    public void sendEmail(){
//        /**
//         * 发送Email
//         */
//        Connection connection = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = DBUtil.getConn();
//            statement = connection.createStatement();
//
//            String sql  = "SELECT * FROM tbemailsend WHERE sendFlag = 0";
//            resultSet = statement.executeQuery(sql);
//
//            while (resultSet.next()){
//                logger.debug("Mail send .....");
//                EmailSend emailSend = new EmailSend();
//                emailSend.setSendTime(new Date());
//                emailSend.setId(resultSet.getLong("id"));
//                emailService.updateSendFlagById(emailSend);
//
//                MailUtil.sendMail(resultSet.getString("subject"), resultSet.getString("content"), resultSet.getString("email"));
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        } finally {
//            DBUtil.freeConn(connection,resultSet,statement);
//        }
//    }
//}
