package com.yinmei.myd.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * 预约详情
 * @author 乔晨旭
 */
public class BookingDetailsController extends Controller {
    public void detail(){
        //第几页，默认第一页
        int pageNumber=getParaToInt(1,1);
        //每页显示数目，默认每页10条
        int pageSize = getParaToInt(2,5);
        Page<Record> paginate = Db.paginate(pageNumber, pageSize, "select s.number,s.create_time,s.visiting_time,s.attendance,s.status,s.operation", " from v9_s-gerenyuyue s,v9_admin a where s.user_id=a.user_id;");
        set("paginate",paginate);
        renderJson();
    }
}
