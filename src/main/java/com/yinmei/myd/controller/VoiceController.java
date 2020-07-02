package com.yinmei.myd.controller;

import cn.hutool.http.HttpRequest;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.yinmei.myd.common.interceptor.CrossDomainInterceptor;

/**
 * @author 乔晨旭
 */
@Before(CrossDomainInterceptor.class)
public class VoiceController extends Controller {
    /**
     * 语音导览接口,参数传递页码
     */
    public void voice(){
        String page = getPara("page","");
//        String limit = getPara("limit","");
        String domain = "http://47.99.59.109/zsmanager/yuyindaolan/page?page="+ page+"&limit=10";
        String res = HttpRequest.post(domain).timeout(20000).execute().body();
        renderJson(res);
    }
}
