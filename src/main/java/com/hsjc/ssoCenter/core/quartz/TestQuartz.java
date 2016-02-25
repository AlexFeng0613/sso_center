//package com.hsjc.ssoCenter.core.quartz;
//
//import com.hsjc.ssoCenter.core.util.DBUtil;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
///**
// * @author : zga
// * @date : 2016-02-25
// */
//@Component
//public class TestQuartz {
//
//    @Scheduled(cron="0/3 * * * * ?")
//    public void test(){
//        /**
//          * 发送Email
//          */
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
//                long sum = 0l;
//                for(int i = 1;i<10000000;i++){
//                    sum += i;
//                }
//                System.out.println(">>>1111111");
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        } finally {
//            DBUtil.freeConn(connection,resultSet,statement);
//        }
//    }
//}
