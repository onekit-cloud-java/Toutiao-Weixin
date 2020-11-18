package cn.onekit.x2x.cloud.toutiao_weixin.web;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = {"file:config.properties"},ignoreResourceNotFound = true)
public class WeixinAccount implements ApplicationContextAware {

    static String appid;
    static String secret;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
         appid = applicationContext.getEnvironment().getProperty("weixin.appid");
         secret = applicationContext.getEnvironment().getProperty("weixin.secret");
    }
}
