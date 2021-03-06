package com.yinmei.myd.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.yinmei.myd.common.interceptor.CrossDomainInterceptor;

import java.util.List;

/**
 * 预约详情
 * @author 乔晨旭
 */
@Before(CrossDomainInterceptor.class)
public class BookingDetailsController extends Controller {
    public void detail(){
//        //第几页，默认第一页
//        int pageNumber=getParaToInt(1,1);
//        //每页显示数目，默认每页10条
//        int pageSize = getParaToInt(2,10);
//        Page<Record> paginate = Db.paginate(pageNumber, pageSize, "select number,create_time,visiting_time,attendance,status,operation", " from v9_s_gerenyuyue;");
//        set("paginate",paginate);
        //status 预约状态 2-成功 4-失败 6-过期 8-取消
        String mobile = getPara("mobile","");
        List<Record> list = Db.find("select s.number,s.create_time,s.status from v9_s_gerenyuyue s,v9_s_user u WHERE s.user_id=u.id AND u.mobile='"+mobile+"';");
        set("list",list);
        renderJson();
    }

    /**
     * 取消预约
     */
    public void cancel(){
        String id = getPara("id","");
        int update = Db.update("update v9_s_gerenyuyue set status='8' where id=?", id);
        set("update",update);
        renderJson();
    }
}
