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
        List<Record> list = Db.find("select  title,thumb,IFNULL(description,' ') description,FROM_UNIXTIME(inputtime) exhibitionTime from v9_jibenchenglie where status='99' and id=?", id);
        set("list",list);
        renderJson();
    }
    /**
     * 临展预告详情页
     */
    public void foreShowDetail(){
        String id = getPara("id","");
        List<Record> list = Db.find("select  title,thumb,IFNULL(description,' ') description,FROM_UNIXTIME(inputtime) exhibitionTime from v9_linshizhanlan where status='99' and id=?", id);
        set("list",list);
        renderJson();

    }
    /**
     * 网上展厅详情页
     */
    public void  networkDetail(){
        String id = getPara("id","");
        List<Record> list = Db.find("select  title,thumb,IFNULL(description,' ') description,FROM_UNIXTIME(inputtime) exhibitionTime from v9_wangshangzhanting where status='99' and id=?", id);
        set("list",list);
        renderJson();

    }

}
