package com.yinmei.myd.common.routes;

import com.jfinal.config.Routes;
import com.yinmei.myd.controller.ExhibitionController;

public class AdminRoutes extends Routes {
    @Override
    public void config() {
        this.add("exh", ExhibitionController.class);

    }
}
