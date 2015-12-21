MyBatis 使用总结:
1、使用<where>标签时
1)、第一个条件不加and,最后一个条件不加',';
<where>
  <if test="email != null">
    email = #{email},
  </if>

  <if test="phone != null">
    and phone = #{phone},
  </if>

  <if test="userName != null">
    and userName = #{userName} #就是这里不要加',',否则会报错
  </if>
</where>

2、

