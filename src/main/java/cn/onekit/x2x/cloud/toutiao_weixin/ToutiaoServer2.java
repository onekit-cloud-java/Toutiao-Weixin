package cn.onekit.x2x.cloud.toutiao_weixin;

import com.qq.weixin.api.WeixinSDK;
import com.toutiao.developer.ToutiaoAPI2;
import com.toutiao.developer.entity.v2.*;

public abstract class ToutiaoServer2 implements ToutiaoAPI2 {

    private final String wx_appid;
    private final String wx_secret;
    WeixinSDK weixinSDK= new WeixinSDK("https://api.weixin.qq.com");
    public ToutiaoServer2(
            String wx_appid, String wx_secret) {
        this.wx_appid = wx_appid;
        this.wx_secret = wx_secret;
    }


    //////////////////////////////////////
    abstract protected void _jscode_openid(String wx_jscode, String wx_openid);
    abstract protected String _jscode_openid(String wx_jscode);
    abstract protected void _openid_sessionkey(String wx_openid, String wx_sessionkey);

    abstract protected String _openid_sessionkey(String wx_openid);

    @Override
    public tags__text__antidirt_response tags__text__antidirt(String tt_X_Token, tags__text__antidirt_body tt_body) throws ToutiaoError2 {
       return null;
    }

    @Override
    public tags__image_response tags__image(String X_Token, tags__image_body body) throws ToutiaoError2 {
        return null;
    }
}
