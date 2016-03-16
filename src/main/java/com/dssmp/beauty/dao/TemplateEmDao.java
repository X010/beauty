package com.dssmp.beauty.dao;

import com.dssmp.beauty.model.TemplateEm;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
public interface TemplateEmDao {
    /**
     * 统计条数
     *
     * @return
     */
    @Select("select count(*) from beauty_template_em")
    public int countRows();

    /**
     * 添加数据
     *
     * @param templateEm
     */
    @Insert("insert into beauty_template_em(common_ref,page_header,page_footer,createtime)values(#{common_ref},#{page_header},#{page_footer},#{createtime})")
    public void insertTemplateEm(TemplateEm templateEm);

    /**
     * 更新数据
     *
     * @param templateEm
     */
    @Update("update beauty_template_em set common_ref=#{common_ref},page_header=#{page_header},page_footer=#{page_footer},updatetime=#{updatetime} where id=#{id}")
    public void updateTemplateEm(TemplateEm templateEm);

    /**
     * 获取唯一的数据
     *
     * @return
     */
    @Select("select * from beauty_template_em limit 1")
    public TemplateEm findTemplateEm();
}
