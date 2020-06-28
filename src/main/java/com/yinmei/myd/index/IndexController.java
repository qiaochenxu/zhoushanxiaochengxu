package com.yinmei.myd.index;

import com.jfinal.core.Controller;

/**
 * @author 乔晨旭
 */
public class IndexController extends Controller {
    /**
     * 首页Action
     */
    public void index(){
        render("index.html");
    }
}
