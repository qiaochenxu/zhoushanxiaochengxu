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
        Page<Record> paginate = Db.paginate(pageNumber, pageSize, "select id,title,thumb,description,FROM_UNIXTIME(inputtime) exhibitionTime", " from v9_jibenchenglie WHERE status ='99' ORDER BY exhibitionTime desc");
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
        Page<Record> into = Db.paginate(pageNumber,pageSize,"select id,title,thumb,description,FROM_UNIXTIME(inputtime) exhibitionTime"," from v9_linshizhanlan WHERE status='99' ORDER BY exhibitionTime desc");
        set("into",into);
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
        Page<Record> paginate = Db.paginate(pageNumber, pageSize, "select id,title,thumb,description,FROM_UNIXTIME(inputtime) exhibitionTime", " from v9_wangshangzhanting WHERE status='99' ORDER BY exhibitionTime desc");
        set("paginate",paginate);
        renderJson();
    }

}
