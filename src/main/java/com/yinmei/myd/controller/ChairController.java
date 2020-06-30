package com.yinmei.myd.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.yinmei.myd.common.interceptor.CrossDomainInterceptor;

import java.util.List;

/**
 * 活动讲座页面
 * @author 乔晨旭
 */
@Before(CrossDomainInterceptor.class)
public class ChairController extends Controller {
    /**
     * 文博讲座列表
     */
    public void chair(){
        //第几页，默认第一页
        int pageNumber=getParaToInt(1,1);
        //每页显示数目，默认每页10条
        int pageSize = getParaToInt(2,10);
        Page<Record> paginate = Db.paginate(pageNumber,pageSize,"select w.id,w.title,w.thumb,d.zhengwen,FROM_UNIXTIME(w.inputtime) releaseTime,d.edit,d.address"," FROM v9_wenbojiangzuo w,v9_wenbojiangzuo_data d WHERE w.id=d.id and status='99' ORDER BY startdate desc");
        set("paginate",paginate);
        renderJson();
    }
    /**
     * 活动预告列表
     */
    public void activity(){
        //第几页，默认第一页
        int pageNumber=getParaToInt(1,1);
        //每页显示数目，默认每页10条
        int pageSize = getParaToInt(2,10);
        Page<Record> paginate = Db.paginate(pageNumber,pageSize,"select h.id,h.title,h.thumb,d.zhengwen,FROM_UNIXTIME(h.inputtime) releaseTime"," from v9_huodongyugao h,v9_huodongyugao_data d where h.id=d.id and status='99' ORDER BY startdate desc");
        set("paginate",paginate);
        renderJson();
    }


}
