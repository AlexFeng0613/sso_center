package com.hsjc.central.mapper;

import com.hsjc.central.domain.UserTeacher;

public interface UserTeacherMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userteacher
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userteacher
     *
     * @mbggenerated
     */
    int insert(UserTeacher record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userteacher
     *
     * @mbggenerated
     */
    int insertSelective(UserTeacher record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userteacher
     *
     * @mbggenerated
     */
    UserTeacher selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userteacher
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(UserTeacher record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userteacher
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(UserTeacher record);
}