package com.yinmei.myd.common.routes;

import com.jfinal.config.Routes;
import com.yinmei.myd.controller.*;

public class AdminRoutes extends Routes {
    @Override
    public void config() {
        //展览
        this.add("exh", ExhibitionController.class);
        //报名预约
        this.add("det", BookingDetailsController.class);
        //详情页
        this.add("tai", DetailController.class);
        //活动讲座
        this.add("cha", ChairController.class);
        //语音导览
        this.add("voi", VoiceController.class);

    }
}
