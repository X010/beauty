package com.dssmp.beauty.dao;

import com.dssmp.beauty.model.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
public interface PageDao {

    /**
     * 保存页面信息
     *
     * @param page
     */
    @Insert("insert into beauty_page(url,createtime,status,tid,rgids)values(#{url},#{createtime},#{status},#{tid},#{rgids})")
    public void insertPage(Page page);


    /**
     * 根据URL查询页面信息
     *
     * @param url
     * @return
     */
    @Select("select * from beauty_page where url=#{url}")
    public Page findPageByUrl(@Param("url") String url);

    /**
     * 根据ID获取PAGE对象
     *
     * @param id
     * @return
     */
    @Select("select * from beauty_page where id=#{id}")
    public Page findPageById(@Param("id") long id);

    /**
     * 根据ID删除Page对象
     *
     * @param id
     */
    @Delete("delete from beauty_page where id=#{id}")
    public void deletePageById(@Param("id") long id);
}
