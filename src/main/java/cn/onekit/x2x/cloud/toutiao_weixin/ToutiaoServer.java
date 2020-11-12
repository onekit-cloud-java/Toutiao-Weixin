package cn.onekit.x2x.cloud.toutiao_weixin;

import cn.onekit.thekit.JSON;
import com.google.gson.JsonObject;
import com.qq.weixin.api.entity.*;
import com.qq.weixin.api.sdk.WeixinSDK;
import com.toutiao.developer.ToutiaoAPI;
import com.toutiao.developer.entity.*;
import com.toutiao.developer.sdk.ToutiaoSDK;

public class ToutiaoServer implements ToutiaoAPI {

    private final String wx_secret;
    private final String wx_appid;

    public ToutiaoServer(String wx_appid, String wx_secret){
        this.wx_appid=wx_appid;
        this.wx_secret=wx_secret;
    }
    @Override
    public String _signBody(String sig_method, String session_key, String data) throws Exception {
        return new ToutiaoSDK(null)._signBody(sig_method,session_key,data);
    }

    @Override
    public String _signRaw(String rawData, String session_key) throws Exception {
        return new ToutiaoSDK(null)._signRaw( rawData,  session_key);
    }

    @Override
    public apps__token_response apps__token(
            String tt_appid,
            String tt_secret,
            String tt_grant_type
    ) throws ToutiaoError {
        try {
            //////////////////////
            cgi_bin__token_response wx_response = new WeixinSDK().cgi_bin__token(wx_appid, wx_secret, tt_grant_type);
            /////////////////////
            apps__token_response tt_reponse = new apps__token_response();
            tt_reponse.setAccess_token(wx_response.getAccess_token());
            tt_reponse.setExpires_in(wx_response.getExpires_in());
            return tt_reponse;
        } catch (WeixinError e) {
            ToutiaoError tt_error = new ToutiaoError();
            tt_error.setErrcode(9527);
            tt_error.setErrmsg(e.getMessage());
            throw tt_error;
        }
    }

    @Override
    public apps__jscode2session_response apps__jscode2session(
            String tt_appid,
            String tt_secret,
            String tt_code,
            String tt_anonymous_code
    ) throws ToutiaoError {
        //////////////////////
        snc__jscode2session_response wx_response = new WeixinSDK().snc__jscode2session(wx_appid,wx_secret, tt_code,"authorization_code");
        //////////
        if(wx_response.getErrcode()!=0){
            ToutiaoError tt_error = new ToutiaoError();
            tt_error.setErrcode(9527);
            tt_error.setErrmsg(wx_response.getErrmsg());
            throw tt_error;
        }
        ////////////
        apps__jscode2session_response tt_response = new apps__jscode2session_response();
        tt_response.setAnonymous_openid(wx_response.getOpenid());
        tt_response.setOpenid(wx_response.getOpenid());
        tt_response.setSession_key(wx_response.getSession_key());
        return tt_response;
    }

    @Override
    public apps__set_user_storage_response apps__set_user_storage(
            String tt_access_token,
            String tt_openid,
            String tt_signature,
            String tt_sig_method,
            apps__set_user_storage_body tt_body
    ) throws ToutiaoError {
        JsonObject body = (JsonObject) JSON.object2json(tt_body);
        wxa__set_user_storage_body wx_body = JSON.json2object(body,wxa__set_user_storage_body.class);
        //////////////
        WeixinResponse wx_response = new WeixinSDK().wxa__set_user_storage(tt_access_token, tt_openid, tt_signature, tt_sig_method, wx_body);
        ////////////
        if (wx_response.getErrcode() != 0) {
            ToutiaoError tt_error = new ToutiaoError();
            tt_error.setErrcode(9527);
            tt_error.setErrmsg(wx_response.getErrmsg());
            throw tt_error;
        }
        ////////////
        return new apps__set_user_storage_response();
    }

    @Override
    public apps__remove_user_storage_response apps__remove_user_storage(
            String tt_access_token,
            String tt_openid,
            String tt_signature,
            String tt_sig_method,
            apps__remove_user_storage_body tt_body
    )  {
        JsonObject body = (JsonObject) JSON.object2json(tt_body);
        wxa__remove_user_storage_body wx_body = JSON.json2object(body,wxa__remove_user_storage_body.class);
        //////////////
        WeixinResponse wx_response = new WeixinSDK().wxa__remove_user_storage(tt_access_token, tt_openid, tt_signature, tt_sig_method,wx_body);
        ////////////
        if(wx_response.getErrcode()!=0){
            apps__remove_user_storage_response tt_error=new apps__remove_user_storage_response();
            tt_error.setError(wx_response.getErrcode());
            return tt_error;
        }
        ////////////
        apps__remove_user_storage_response tt_response = new apps__remove_user_storage_response();
        tt_response.setError(0);
        return tt_response;
    }

    @Override
    public byte[] apps__qrcode(
            apps__qrcode_body tt_body
    ) throws ToutiaoError {
        return null;
    }


    @Override
    public apps__subscribe_notification__developer__notify_response apps__subscribe_notification__developer__notify(
            apps__subscribe_notification__developer__notify_body tt_body
    ) throws ToutiaoError {
        return null;
    }

    @Override
    public apps__game__template__send_response apps__game__template__send(
            apps__game__template__send_body tt_body
    ) throws ToutiaoError {
        return null;
    }
}
