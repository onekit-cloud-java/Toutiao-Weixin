package cn.onekit.x2x.cloud.toutiao_weixin;

import com.qq.weixin.api.entity.*;
import com.qq.weixin.api.sdk.*;
import com.toutiao.developer.ToutiaoAPI;
import com.toutiao.developer.entity.*;
import com.toutiao.developer.sdk.ToutiaoSDK;

public class ToutiaoServer implements ToutiaoAPI {

    @Override
    public String _signBody(String sig_method, String session_key, String data) throws Exception {
        return new ToutiaoSDK()._signBody(sig_method,session_key,data);
    }

    @Override
    public String _signRaw(String rawData, String session_key) throws Exception {
        return new ToutiaoSDK()._signRaw( rawData,  session_key);
    }

    @Override
    public apps__token_response apps__token(String appid, String secret, String grant_type) throws ToutiaoError {
        try {
            cgi_bin__token_response wx_response = new WeixinSDK().cgi_bin__token(appid, secret, grant_type);
            ////////////
            apps__token_response tt_reponse = new apps__token_response();
            tt_reponse.setAccess_token(wx_response.getAccess_token());
            tt_reponse.setExpires_in(wx_response.getExpires_in());
            return tt_reponse;
        } catch (WeixinError e) {
            ToutiaoError error = new ToutiaoError();
            error.setErrcode(9527);
            error.setErrmsg(e.getMessage());
            throw error;
        }
    }

    @Override
    public apps__jscode2session_response apps__jscode2session(String appid, String secret, String code, String anonymous_code) throws ToutiaoError {
        snc__jscode2session_response wx_response = new WeixinSDK().snc__jscode2session(appid,secret,code,anonymous_code);
        //////////
        if(wx_response.getErrcode()!=0){
            ToutiaoError error=new ToutiaoError();
            error.setErrcode(wx_response.getErrcode());
            error.setErrmsg(wx_response.getErrmsg());
            throw error;
        }
        ////////////
        apps__jscode2session_response tt_response = new apps__jscode2session_response();
        tt_response.setAnonymous_openid(wx_response.getOpenid());
        tt_response.setOpenid(wx_response.getOpenid());
        tt_response.setSession_key(wx_response.getSession_key());
        return tt_response;
    }

    @Override
    public apps__set_user_storage_response apps__set_user_storage(String access_token, String openid, String signature, String sig_method, apps__set_user_storage_body body) throws ToutiaoError {
        wxa__set_user_storage_body wx_body = new wxa__set_user_storage_body();
      //////////////
        WeixinResponse wx_response = new WeixinSDK().wxa__set_user_storage(access_token,openid,signature,sig_method,wx_body);
        ////////////
        if(wx_response.getErrcode()!=0){
            ToutiaoError error=new ToutiaoError();
            error.setErrcode(wx_response.getErrcode());
            error.setErrmsg(wx_response.getErrmsg());
            throw error;
        }
        ////////////
        apps__set_user_storage_response tt_response = new apps__set_user_storage_response();
        return tt_response;
    }

    @Override
    public apps__remove_user_storage_response apps__remove_user_storage(String access_token, String openid, String signature, String sig_method, apps__remove_user_storage_body body)  {
        wxa__remove_user_storage_body wx_body = new wxa__remove_user_storage_body();
        //////////////
        WeixinResponse wx_response = new WeixinSDK().wxa__remove_user_storage(access_token,openid,signature,sig_method,wx_body);
        ////////////
        if(wx_response.getErrcode()!=0){
            apps__remove_user_storage_response error=new apps__remove_user_storage_response();
            error.setError(wx_response.getErrcode());
            return error;
        }
        ////////////
        apps__remove_user_storage_response tt_response = new apps__remove_user_storage_response();
        tt_response.setError(0);
        return tt_response;
    }

    @Override
    public byte[] apps__qrcode(apps__qrcode_body body) throws ToutiaoError {
        return new byte[0];
    }


    @Override
    public apps__subscribe_notification__developer__notify_response apps__subscribe_notification__developer__notify(apps__subscribe_notification__developer__notify_body request) throws ToutiaoError {
        return null;
    }

    @Override
    public apps__game__template__send_response apps__game__template__send(apps__game__template__send_body body) throws ToutiaoError {
        return null;
    }
}
