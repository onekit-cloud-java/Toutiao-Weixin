package cn.onekit.x2x.cloud.toutiao_weixin;

import com.toutiao.developer.ToutiaoAPI2;
import com.toutiao.developer.entity.v2.*;

public class ToutiaoServer2 implements ToutiaoAPI2 {

    private final String wx_secret;
    private final String wx_appid;

    public ToutiaoServer2(String wx_appid, String wx_secret){
        this.wx_appid=wx_appid;
        this.wx_secret=wx_secret;
    }

    @Override
    public tags__text__antidirt_response tags__text__antidirt(String tt_X_Token, tags__text__antidirt_body tt_body) throws ToutiaoError {
       return null;
    }

    @Override
    public tags__image_response tags__image(String X_Token, tags__image_body body) throws ToutiaoError {
        return null;
    }
}
