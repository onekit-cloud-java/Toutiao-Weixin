package cn.onekit.x2x.cloud.toutiao_weixin.web;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = {"classpath:config.properties"},ignoreResourceNotFound = true)
public class ToutiaoAccount implements ApplicationContextAware {


    static String tt_appid;
    static String tt_secret;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        tt_appid = applicationContext.getEnvironment().getProperty("toutiao.appid");
        tt_secret = applicationContext.getEnvironment().getProperty("toutiao.secret");
    }
}
