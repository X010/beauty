package com.dssmp.beauty.service;

import com.dssmp.beauty.model.Page;

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
public interface PageService {

    /**
     * 保存Page对象
     *
     * @param page
     */
    public void savePage(Page page);

    /**
     * 根据URL获取Page对象
     *
     * @param url
     * @return
     */
    public Page getPageByUrl(String url);

    /**
     * 根据ID获取页面信息
     *
     * @param id
     * @return
     */
    public Page getPageById(long id);
}
