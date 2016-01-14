#hsjc 项目SSO_center</h2>
    该项目旨在第三方无缝接轨,第三方及时与SSO进行数据同步;CAS登录.

#部署环境前提
*Intellij Idea
*jdk8
*git
*maven
*tomcat
*mysql
*redis

#目录说明:
##modify_record
    1)、该目录旨在记录开发人员每天的开发内容,尤其是修改了sql,需要进行相应的记录,方便运维人员直接进行线上数据库的维护与同步.

    2)、目录命名格式:yyyy-MM-dd>username>modify.txt/modify.sql(".sql"文件必须要有,".txt"文件可以不要)
        a、modify.txt记录修改的内容
        b、modify.sql记录当天修改的sql脚本

##src
###main
####java
 *开发的Java源代码文件

###resources
*配置文件目录

####web
#####public
*公共的html文件

#####static
*静态资源文件(css/js/image)

#####WEB-INF
*页面文件

####test
*测试用例目录

