package com.dssmp.beauty.util;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * 模板分析工具
 */
public class TemplateAnlayserUntil {

    /**
     * 分析模板标签
     *
     * @param content
     * @return
     */
    public static List<String> getTemplateTag(String content) {
        List<String> tags = Lists.newArrayList();
        if (!Strings.isNullOrEmpty(content)) {
            Pattern pattern = Pattern.compile(CONST.COMPENT_PH_PATTERN);
            Matcher matcher = pattern.matcher(content);
            if (matcher != null) {
                while (matcher.find()) {
                    tags.add(matcher.group(0));
                }
            }
        }
        return tags;
    }
}
