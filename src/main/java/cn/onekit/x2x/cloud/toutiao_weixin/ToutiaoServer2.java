package cn.onekit.x2x.cloud.toutiao_weixin;

import cn.onekit.thekit.JSON;
import com.google.gson.JsonObject;
import com.qq.weixin.api.WeixinSDK;
import com.qq.weixin.api.entity.WeixinResponse;
import com.qq.weixin.api.entity.wxa__msg_sec_check_body;
import com.qq.weixin.api.entity.wxa__remove_user_storage_body;
import com.qq.weixin.api.entity.wxa__set_user_storage_body;
import com.toutiao.developer.ToutiaoAPI2;
import com.toutiao.developer.entity.ToutiaoError;
import com.toutiao.developer.entity.apps__remove_user_storage_response;
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

        //////////////
    try{
        JsonObject body = (JsonObject) JSON.object2json(tt_body);
        wxa__msg_sec_check_body wx_body = JSON.json2object(body, wxa__msg_sec_check_body.class);
        WeixinResponse wx_response = weixinSDK.wxa__msg_sec_check(tt_X_Token, wx_body);

        if (wx_response.getErrcode() != 0) {
            ToutiaoError2 tt_error = new ToutiaoError2();
            tt_error.setCode(wx_response.getErrcode());
            tt_error.setMessage(wx_response.getErrmsg());
            throw tt_error;
        }
        return new tags__text__antidirt_response();
    } catch (Exception e) {
        e.printStackTrace();
        ToutiaoError2 error = new ToutiaoError2();
        error.setCode(9527);
        error.setMessage(e.getMessage());
        throw error;
    }
    }

    @Override
    public tags__image_response tags__image(String tt_X_Token, tags__image_body tt_body) throws ToutiaoError2 {
    try{
        JsonObject body = (JsonObject) JSON.object2json(tt_body);
        byte[] wx_body = JSON.json2object(body, byte[].class);
        WeixinResponse wx_response = weixinSDK.wxa__img_sec_check(tt_X_Token, wx_body);

        if (wx_response.getErrcode() != 0) {
            ToutiaoError2 tt_error = new ToutiaoError2();
            tt_error.setCode(wx_response.getErrcode());
            tt_error.setMessage(wx_response.getErrmsg());
            throw tt_error;
        }
        return new tags__image_response();
    } catch (Exception e) {
        e.printStackTrace();
        ToutiaoError2 error = new ToutiaoError2();
        error.setCode(9527);
        error.setMessage(e.getMessage());
        throw error;
    }
}
}
