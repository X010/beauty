package com.dssmp.beauty.dao;

import com.dssmp.beauty.model.Compent;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
public interface CompentDao {

    /**
     * 根据页面ID获取组件列表
     *
     * @param pid
     * @return
     */
    @Select("select * from beauty_compent where pid=#{pid}")
    public List<Compent> findCompentsByPid(@Param("pid") long pid);


    /**
     * 添加组件
     *
     * @param compent
     */
    @Insert("insert into beauty_compent(pid,flag,content,createtime,updatetime)values(#{pid},#{flag},#{content},#{createtime},#{updatetime})")
    public void insertCompent(Compent compent);


    /**
     * 更新数据库中的内容
     *
     * @param compent
     */
    @Update("update beauty_compent set content=#{content} where id=#{id}")
    public void updateCompent(Compent compent);
}
