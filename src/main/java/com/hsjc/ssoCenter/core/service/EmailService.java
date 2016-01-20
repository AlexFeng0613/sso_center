package com.hsjc.ssoCenter.core.service;

import com.hsjc.ssoCenter.core.domain.EmailSend;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : zga
 * @date : 2016-01-20
 */
@Service
public class EmailService extends ApiBaseService{

    /**
     * @author : zga
     * @date : 2016-1-20
     *
     * 查询Email发送表中的sendFlag为0的数据
     *
     * @return
     */
    public List<EmailSend> selectEmailSendBySendFlag(){
        List<EmailSend> list = emailSendMapper.selectEmailSendBySendFlag();
        return list;
    }

    /**
     * @author : zga
     * @date : 2016-1-20
     *
     * 更新Email发送表的状态为1
     *
     * @param emailSend
     * @return
     */
    public int updateSendFlagById(EmailSend emailSend){
        return emailSendMapper.updateSendFlagById(emailSend);
    }
}
