package com.hubuteam.ordersystem.mappers;

import com.hubuteam.ordersystem.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 云天泽 Steven
 * @version 1.0
 * Date: 2024-07-11
 * QQ：2311170320
 * 功能实现: 用户类Mapper
 */
@Mapper
public interface UserMapper {
    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 用户信息
     */
    @Select("SELECT * FROM ordersys.users WHERE username = #{username}")
    User findByUsername(String username);


    /**
     * 查询所有用户的信息
     * @return 所有用户信息
     */
    @Select("SELECT * FROM ordersys.users")
    List<User> selectAllUsers();

    /**
     * 更新用户信息
     * @param user 新用户信息
     * @return 成功返回1 失败返回0
     */
    int updateUserInfo(@Param("m_user") User user);

    /**
     * 更新用户密码
     * @param userId 用户id
     * @param newPassword 新密码
     * @return 成功返回1 失败返回0
     */
    @Update("UPDATE ordersys.users SET Password = #{newPassword} WHERE UserID = #{userId}")
    int updateUserPassword(@Param("userId") int userId, @Param("newPassword") String newPassword);

    /**
     * 注册用户
     * @param user 用户信息
     * @return 成功返回1 失败返回0
     */
    @Insert("INSERT INTO ordersys.users(Username, Password, Phone, Address) VALUES(#{m_user.username}, #{m_user.password}, #{m_user.phone}, #{m_user.address})")
    int insertUser(@Param("m_user") User user);
}
