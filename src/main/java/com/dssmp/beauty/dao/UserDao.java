package com.dssmp.beauty.dao;

import com.dssmp.beauty.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public interface UserDao {

    /**
     * 登陆
     *
     * @param username
     * @param password
     * @return
     */
    @Select("select * from beauty_user where username=#{username} and password=#{password} limit 1")
    public User findUserByUserNameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 根据用户获取用户
     * @param username
     * @return
     */
    @Select("select * from beauty_user where username=#{username}")
    public User findUserByUserName(@Param("username") String username);

    /**
     * 获取所有的用户信息
     *
     * @return
     */
    @Select("select * from beauty_user order by id desc")
    public List<User> findAllUser();


    /**
     * 删除用户
     *
     * @param id
     */
    @Delete("delete from beauty_user where id=#{id}")
    public void deleteUser(@Param("id") long id);

    /**
     * 添加一个用户
     *
     * @param user
     */
    @Insert("insert into beauty_user(username,password)values(#{username},#{password})")
    public void insertUserByUser(User user);
}
