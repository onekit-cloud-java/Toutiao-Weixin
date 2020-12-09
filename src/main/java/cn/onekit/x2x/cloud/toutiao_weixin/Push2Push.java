package cn.onekit.x2x.cloud.toutiao_weixin;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@SuppressWarnings("unused")
@Component
@PropertySource(value = {"classpath:push2push.properties"},ignoreResourceNotFound = true)
public class Push2Push implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    private HashMap<String,HashMap<String,String>> keys2keys = new HashMap<String,HashMap<String,String>>(){{

        put("支付通知", new HashMap<String,String>(){{
            put("时间","付款时间");
            put("金额","付款金额");
            put("商家名称","商品名称");
        }});
    }};

    public  HashMap<String,String> id2keys(String tt_template_id){
        String tt_template_name = applicationContext.getEnvironment().getProperty(String.format("%s_name",tt_template_id));
    return keys2keys.get(tt_template_name);
    }

    public  String id2id(String tt_template_id){
        return applicationContext.getEnvironment().getProperty(tt_template_id);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Push2Push.applicationContext =applicationContext;
    }
}
