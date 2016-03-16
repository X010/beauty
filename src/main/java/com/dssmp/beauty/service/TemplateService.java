package com.dssmp.beauty.service;

import com.dssmp.beauty.model.Template;

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
public interface TemplateService {


    /**
     * 获取所有模板
     *
     * @return
     */
    public List<Template> getAllTemplate();

    /**
     * 获取简单的模板字段
     *
     * @return
     */
    public List<Template> getAllSimpleTemplate();


    /**
     * 保存模板
     *
     * @param template
     */
    public void saveTemplate(Template template);

    /**
     * 删除模板
     *
     * @param id
     */
    public void deleteTemplate(long id);


    /**
     * 根据ID获取模板信息
     *
     * @param id
     * @return
     */
    public Template findTemplateById(long id);
}
