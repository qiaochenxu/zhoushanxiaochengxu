package com.yinmei.myd.common.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.*;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;
import com.yinmei.myd.common.routes.AdminRoutes;
import com.yinmei.myd.index.IndexController;

public class MainConfig extends JFinalConfig {
    private static Prop p;
    private WallFilter wallFilter;
    static void loadConfig(){
        p = PropKit.use("config.properties").appendIfExists("config-pro.properties");
    }
    @Override
    public void configConstant(Constants me) {
        //获取配置文件
        loadConfig();
        //配置开发模式
        me.setDevMode(p.getBoolean("devMode"));
        //配置上传路径
        me.setBaseUploadPath("upload");
        //配置下载路径
        me.setBaseDownloadPath("download");
        //设置默认视图类型
        me.setViewType(ViewType.JFINAL_TEMPLATE);
        //设置404渲染视图
        //me.setError404View("404.html");
        //设置启用依赖注入
        me.setInjectDependency(true);
    }

    @Override
    public void configRoute(Routes me) {
        //配置后台管理系统路由
        me.add(new AdminRoutes());

        me.add("/", IndexController.class);

    }

    @Override
    public void configEngine(Engine me) {
        //配置模板支持热加载
        me.setDevMode(p.getBoolean("engineDevMode",false));

    }

    public static DruidPlugin getDruidPlugin(){
        loadConfig();
        return new DruidPlugin(p.get("jdbcUrl"),p.get("user"),p.get("password"));
    }

    /**
     * 配置JFinal插件
     * 数据库连接池
     * @param me
     */
    @Override
    public void configPlugin(Plugins me) {
        //读取数据库配置文件
        loadConfig();
        //配置druid数据库插件
        DruidPlugin druidPlugin = getDruidPlugin();
        //添加statFilter,才有统计数据
        druidPlugin.addFilter(new StatFilter());
        //加强数据库安全
        wallFilter = new WallFilter();
        wallFilter.setDbType("mysql");
        druidPlugin.addFilter(wallFilter);
        //数据映射
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        arp.setShowSql(p.getBoolean("devMode"));
        arp.setDialect(new MysqlDialect());
        druidPlugin.setDriverClass("com.mysql.jdbc.Driver");

        //开发模式是否在控制台打印显示
        arp.setShowSql(false);

        //添加插件列表
        me.add(druidPlugin);
        me.add(arp);
        //配置ehcache插件 配置文件是ehcache.xml
        //me.add(new EhCachePlugin());


    }

    /**
     * 配置全局拦截器
     * @param me
     */
    @Override
    public void configInterceptor(Interceptors me) {
        me.addGlobalActionInterceptor(new SessionInViewInterceptor());

    }

    /**
     * 配置全局处理器
     * @param me
     */
    @Override
    public void configHandler(Handlers me) {

    }
    @Override
    public void onStart(){
        wallFilter.getConfig().setSelectUnionCheck(false);
    }

    public static void main(String[] args) {
        UndertowServer.start(MainConfig.class,83,true);
    }
}
