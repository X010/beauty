package com.dssmp.beauty.service;

import com.dssmp.beauty.model.User;

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
public interface UserService {

    /**
     * 根据用户名与密码获取用户信息
     *
     * @param username
     * @param password
     * @return
     */
    public User getUserByUserNameAndPassword(String username, String password);

    /**
     * 获取所有用户
     *
     * @return
     */
    public List<User> getAllUser();


    /**
     * 保存用户信息
     *
     * @param user
     */
    public void saveUser(User user);

    /**
     * 删除用户
     *
     * @param id
     */
    public void deleteUser(long id);

    /**
     * 根据ID获取用户
     * @param id
     * @return
     */
    public User getUserById(long id);

}
