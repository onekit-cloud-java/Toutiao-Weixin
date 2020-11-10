package cn.onekit.x2x.cloud.toutiao.weixin;

import com.qq.weixin.api.entity.cgi_bin__token_response;
import com.qq.weixin.api.sdk.WeixinSDK;
import com.toutiao.developer.ToutiaoAPI;
import com.toutiao.developer.entity.*;
import com.toutiao.developer.sdk.ToutiaoSDK;

public class ToutiaoServer implements ToutiaoAPI {
    @Override
    public String _crypto(String sig_method, String session_key, String data) throws Exception {
        return new ToutiaoSDK()._crypto(sig_method,session_key,data);
    }

    @Override
    public apps__token_response apps__token(String appid, String secret, String grant_type) throws ToutiaoError {
        cgi_bin__token_response response = new WeixinSDK().cgi_bin__token(appid,secret,grant_type);
        //
        //if(response.)
        //
        apps__token_response result = new apps__token_response();
        return result;
    }

    @Override
    public apps__jscode2session_response apps__jscode2session(String appid, String secret, String code, String anonymous_code) throws ToutiaoError {
        apps__jscode2session_response wx_response = new ToutiaoSDK().apps__jscode2session(appid,secret,code,anonymous_code);
        //////////
        apps__jscode2session_response tt_response = new apps__jscode2session_response();
        tt_response.setAnonymous_openid(wx_response.getOpenid());
        tt_response.setOpenid(wx_response.getOpenid());
        tt_response.setSession_key(wx_response.getSession_key());
        return tt_response;
    }

    @Override
    public apps__set_user_storage_response apps__set_user_storage(String access_token, String openid, String signature, String sig_method, apps__set_user_storage_body body) throws ToutiaoError {
        return null;
    }

    @Override
    public apps__remove_user_storage_response apps__remove_user_storage(String access_token, String openid, String signature, String sig_method, apps__remove_user_storage_body body) throws ToutiaoError {
        return null;
    }

    @Override
    public byte[] apps__qrcode(apps__qrcode_body body) throws ToutiaoError {
        return new byte[0];
    }

    @Override
    public apps__game__template__send_response apps__game__template__send(apps__game__template__send_body body) throws ToutiaoError {
        return null;
    }

    @Override
    public apps__subscribe_notification__developer__notify_response apps__subscribe_notification__developer__notify(apps__subscribe_notification__developer__notify_body request) throws ToutiaoError {
        return null;
    }
}
