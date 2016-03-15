package com.dssmp.beauty.model;

import java.util.Date;

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
public class RoleGroup {
    /**
     * 权限组
     */
    private long id;

    /**
     * 组名称
     */
    private String roleGroupName;


    /**
     * 创建时间
     */
    private Date createtime;


    /**
     * 项集合
     */
    private String roleItem;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleGroupName() {
        return roleGroupName;
    }

    public void setRoleGroupName(String roleGroupName) {
        this.roleGroupName = roleGroupName;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getRoleItem() {
        return roleItem;
    }

    public void setRoleItem(String roleItem) {
        this.roleItem = roleItem;
    }
}
