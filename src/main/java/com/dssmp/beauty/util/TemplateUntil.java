package com.dssmp.beauty.util;

import com.dssmp.beauty.model.Compent;
import com.dssmp.beauty.model.Page;
import com.dssmp.beauty.model.Template;
import com.dssmp.beauty.model.TemplateEm;
import com.google.common.base.Strings;

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
 * 模板工具
 */
public class TemplateUntil {
    private static String HTML_EM = "<!DOCTYPE html>";
    private static String HTML_BEGIN = "<html <html lang=\"en\">";
    private static String HTML_END = "</html>";
    private static String HEADER_BEGIN = "<head>";
    private static String HEADER_END = "</head>";
    private static String HEADER_UTF8 = "<meta charset=\"UTF-8\">";
    private static String TITLE = "<title>系统生成</title>";
    private static String BODY_BEGIN = "<body>";
    private static String BODY_END = "</body>";
    private static String AB_DIV_BEGIN = "<div class=\"content\">";
    private static String DIV_END = "</div>";
    private static String PAGE_CONTENT_HEADER_BEGIN = "<section class=\"content-header\">";
    private static String SECTION_END = "</section>";
    private static String PAGE_CONTENT_CONTENT_BEIGN = "<section class=\"content\">";

    /**
     * 生成页面内容
     *
     * @param page
     * @param template
     * @param templateEm
     * @return
     */
    public static String createPageContent(Page page, Template template, TemplateEm templateEm, List<Compent> compentList) {
        if (page != null && template != null) {
            StringBuilder pageContent = new StringBuilder();
            pageContent.append(HTML_EM);
            pageContent.append(HTML_BEGIN);
            pageContent.append(HEADER_BEGIN);
            pageContent.append(HEADER_UTF8);
            pageContent.append(TITLE);
            //些处添加模板引用头部和公共引用的头部信息
            if (templateEm != null && !Strings.isNullOrEmpty(templateEm.getCommon_ref())) {
                pageContent.append(templateEm.getCommon_ref());
            }
            pageContent.append(template.getHeader());
            pageContent.append(HEADER_END);
            //内容信息
            pageContent.append(BODY_BEGIN);
            pageContent.append(AB_DIV_BEGIN);
            pageContent.append(PAGE_CONTENT_HEADER_BEGIN);
            //些处添加公共头部信息
            if (templateEm != null && !Strings.isNullOrEmpty(templateEm.getPage_header())) {
                //判断头部是否有占位符,如果有占位符则替换成页面编辑页面的链接地址
                String common_header = templateEm.getPage_header();
                if (common_header.indexOf(CONST.EDITPAGE) > 0) {
                    common_header = common_header.replace(CONST.EDITPAGE, CONST.EDITPAGEURL + page.getId());
                }
                pageContent.append(common_header);
            }
            pageContent.append(SECTION_END);


            pageContent.append(PAGE_CONTENT_CONTENT_BEIGN);
            //些处添加内容信息
            //些处需要对Content内容进行处理
            pageContent.append(template.getContent());


            pageContent.append(SECTION_END);
            pageContent.append(DIV_END);
            pageContent.append(BODY_END);
            pageContent.append(HTML_END);
            return pageContent.toString();
        }
        return null;
    }


}
