package com.yinmei.myd.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.yinmei.myd.common.interceptor.CrossDomainInterceptor;

import java.util.List;

/**
 * 详情页
 * @author 乔晨旭
 */
@Before(CrossDomainInterceptor.class)
public class DetailController extends Controller {
    /**
     * 基本陈列详情页
     */
    public void basicDetail(){
        String id = getPara("id","");
        List<Record> list = Db.find("select  j.title,j.thumb,d.zhengwen,FROM_UNIXTIME(j.inputtime) exhibitionTime from v9_jibenchenglie j,v9_jibenchenglie_data d where j.id=d.id and status='99' and j.id=?", id);
        set("list",list);
        renderJson();
    }
    /**
     * 临展预告详情页
     */
    public void foreShowDetail(){
        String id = getPara("id","");
        List<Record> list = Db.find("select  l.title,l.thumb,d.zhengwen,FROM_UNIXTIME(l.inputtime) exhibitionTime from v9_linshizhanlan l,v9_linshizhanlan_data d where l.id=d.id and status='99' and l.id=?", id);
        set("list",list);
        renderJson();

    }
    /**
     * 网上展厅详情页
     */
    public void  networkDetail(){
        String id = getPara("id","");
        List<Record> list = Db.find("select  w.title,w.thumb,d.zhengwen,FROM_UNIXTIME(w.inputtime) exhibitionTime from v9_wangshangzhanting w,v9_wangshangzhanting_data d where w.id=d.id and status='99' and w.id=?", id);
        set("list",list);
        renderJson();

    }

}
