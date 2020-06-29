package com.yinmei.myd.common.routes;

import com.jfinal.config.Routes;
import com.yinmei.myd.controller.BookingDetailsController;
import com.yinmei.myd.controller.DetailController;
import com.yinmei.myd.controller.ExhibitionController;

public class AdminRoutes extends Routes {
    @Override
    public void config() {
        //展览
        this.add("exh", ExhibitionController.class);
        //报名预约
        this.add("det", BookingDetailsController.class);
        //详情页
        this.add("tai", DetailController.class);

    }
}
