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
 * 页面要素管理
 */
public class TemplateEm {

    private long id;

    /**
     * 公共引用
     */
    private String common_ref;

    /**
     * 页面头部信息
     */
    private String page_header;

    /**
     * 页面尾部信息
     */
    private String page_footer;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getCommon_ref() {
        return common_ref;
    }

    public void setCommon_ref(String common_ref) {
        this.common_ref = common_ref;
    }

    public String getPage_header() {
        return page_header;
    }

    public void setPage_header(String page_header) {
        this.page_header = page_header;
    }

    public String getPage_footer() {
        return page_footer;
    }

    public void setPage_footer(String page_footer) {
        this.page_footer = page_footer;
    }
}
