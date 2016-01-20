package com.hsjc.ssoCenter.core.quartz;

import com.hsjc.ssoCenter.core.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author : zga
 * @date : 2016-01-20
 *
 * 短信定时器类
 *
 */
@Component
public class SMSQuartz {
    @Autowired
    private SmsService smsService;



    @Scheduled(cron="0/1 * * * * ?")
    public void snedSmsAndEmail() {
        long ms = System.currentTimeMillis();
        System.out.println(ms);
    }

}
