package com.yinmei.myd.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.yinmei.myd.common.interceptor.CrossDomainInterceptor;

import java.util.List;

/**
 * @author 乔晨旭
 */
@Before(CrossDomainInterceptor.class)
public class ExhibitionController extends Controller {
    /**
     * 基本陈列
     */
    public void basic(){
        //第几页，默认第一页
        int pageNumber=getParaToInt(1,1);
        //每页显示数目，默认每页10条
        int pageSize = getParaToInt(2,10);
        Page<Record> paginate = Db.paginate(pageNumber, pageSize, "select j.id,j.title,j.thumb,d.zhengwen,FROM_UNIXTIME(j.inputtime) exhibitionTime", " from v9_jibenchenglie j,v9_jibenchenglie_data d WHERE j.id=d.id and status ='99' ORDER BY exhibitionTime desc");
        set("paginate",paginate);
        renderJson();

    }
    /**
     * 临展预告
     */
    public void foreShow(){
        //第几页，默认第一页
        int pageNumber=getParaToInt(1,1);
        //每页显示数目，默认每页10条
        int pageSize = getParaToInt(2,10);
        Page<Record> paginate = Db.paginate(pageNumber,pageSize,"select l.id,l.title,l.thumb,d.zhengwen,FROM_UNIXTIME(l.inputtime) exhibitionTime"," from v9_linshizhanlan l,v9_linshizhanlan_data d WHERE l.id=d.id and status='99' ORDER BY exhibitionTime desc");
        set("paginate",paginate);
        renderJson();
    }

    /**
     * 网上展厅
     */
    public void  network(){
        //第几页，默认第一页
        int pageNumber=getParaToInt(1,1);
        //每页显示数目，默认每页10条
        int pageSize = getParaToInt(2,10);
        Page<Record> paginate = Db.paginate(pageNumber, pageSize, "select w.id,w.title,w.thumb,d.zhengwen,FROM_UNIXTIME(w.inputtime) exhibitionTime", " from v9_wangshangzhanting w,v9_wangshangzhanting_data d WHERE w.id=d.id and status='99' ORDER BY exhibitionTime desc");
        set("paginate",paginate);
        renderJson();
    }

}
