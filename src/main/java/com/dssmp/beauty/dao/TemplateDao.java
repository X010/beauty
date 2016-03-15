package com.dssmp.beauty.dao;

import com.dssmp.beauty.model.Template;
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
 * 模板布局控制
 */
public interface TemplateDao {

    /**
     * 保存保模板信息
     *
     * @param template
     */
    @Insert("insert into beauty_template(title,description,createtime,header,status,content,tagNum,tagMap)values(#{title},#{description},#{createtime},#{header}" +
            ",#{status},#{content},#{tagNum},#{tagMap})")
    public void insertTemplate(Template template);

    /**
     * 获取模板列表
     *
     * @return
     */
    @Select("select * from beauty_template")
    public List<Template> getAllTemplate();

    /**
     * 删除模板
     *
     * @param id
     */
    @Delete("delete from beauty_template where id=#{id}")
    public void deleteTemplate(@Param("id") long id);
}
