package cn.onekit.x2x.cloud.toutiao_weixin;

import cn.onekit.thekit.DB;
import cn.onekit.thekit.FileDB;
import com.qq.weixin.api.WeixinAuthSDK;
import com.qq.weixin.api.WeixinError;
import com.qq.weixin.api.auth.request.Code2SessionRequest;
import com.qq.weixin.api.auth.request.GetAccessTokenRequest;
import com.qq.weixin.api.auth.response.Code2SessionResponse;
import com.qq.weixin.api.auth.response.GetAccessTokenResponse;
import com.toutiao.developer.v1.ToutiaoError;
import com.toutiao.developer.v1.ToutiaoV1API;
import com.toutiao.developer.v1.request.*;
import com.toutiao.developer.v1.response.ApiAppsJscode2sessionResponse;
import com.toutiao.developer.v1.response.ApiAppsSubscribeNotificationDeveloperNotifyResponse;
import com.toutiao.developer.v1.response.ApiAppsTokenResponse;

import java.io.IOException;
import java.util.Date;


public  abstract class ToutiaoServer implements ToutiaoV1API {

    private final String wx_appid;
    private final String wx_secret;
    private WeixinAuthSDK weixinAuthSDK= new WeixinAuthSDK("https://api.weixin.qq.com");
    protected ToutiaoServer(
            String wx_appid, String wx_secret) {
        this.wx_appid = wx_appid;
        this.wx_secret = wx_secret;
    }

    //////////////////////////////////////
    abstract protected void setaccess_token(String access_token) throws IOException;
    abstract protected FileDB.Data getaccess_token();
    abstract protected void _code_openid(String wx_code, String wx_openid) throws IOException;
    abstract protected FileDB.Data _code_openid(String wx_code);
    abstract protected void _openid_sessionkey(String wx_openid, String wx_sessionkey) throws IOException;
    abstract protected FileDB.Data _openid_sessionkey(String wx_openid);

    public ApiAppsTokenResponse apiAppsToken(ApiAppsTokenRequset apiAppsTokenRequset) throws ToutiaoError {
        if(getaccess_token()!=null && (new Date().getTime()-getaccess_token().time)/1000 < 7200 ){
            String accesstoken = getaccess_token().value;
            ApiAppsTokenResponse tt_reponse = new ApiAppsTokenResponse();
            tt_reponse.setAccess_token(accesstoken);
            tt_reponse.setExpires_in(7200);
            return tt_reponse;
        }

        if(apiAppsTokenRequset.getAppid() == null || apiAppsTokenRequset.getSecret() == null || apiAppsTokenRequset.getGrant_type() == null) {
            ToutiaoError toutiaoError = new ToutiaoError();
            toutiaoError.setError(1);
            toutiaoError.setErrcode(40014);
            toutiaoError.setErrmsg("bad params");
            toutiaoError.setMessage("bad parameters");
            throw toutiaoError;
        }
        if(!apiAppsTokenRequset.getGrant_type().equals("client_credential")){
            ToutiaoError toutiaoError = new ToutiaoError();
            toutiaoError.setError(3);
            toutiaoError.setErrcode(40020);
            toutiaoError.setErrmsg("bad grant_type");
            toutiaoError.setMessage("grant_type is not client_credential");
            throw toutiaoError;
        }
        try {
            GetAccessTokenRequest getAccessTokenRequest = new GetAccessTokenRequest();
            getAccessTokenRequest.setAppid(wx_appid);
            getAccessTokenRequest.setSecret(wx_secret);
            GetAccessTokenResponse wx_response = weixinAuthSDK.getAccessToken(getAccessTokenRequest);
            /////////////////////
            ApiAppsTokenResponse tt_reponse = new ApiAppsTokenResponse();
            tt_reponse.setAccess_token(wx_response.getAccess_token());
            tt_reponse.setExpires_in(wx_response.getExpires_in());
            setaccess_token(wx_response.getAccess_token());
            return tt_reponse;
        } catch (WeixinError e) {
            ToutiaoError tt_error = new ToutiaoError();
            tt_error.setError(74077);
            tt_error.setErrcode(e.getErrcode());
            tt_error.setErrmsg(e.getMessage());
            throw tt_error;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public ApiAppsJscode2sessionResponse apiAppsJscode2session(ApiAppsJscode2sessionRequset apiAppsJscode2sessionRequset) throws ToutiaoError {
        if(apiAppsJscode2sessionRequset.getAppid() == null || apiAppsJscode2sessionRequset.getSecret() == null || (apiAppsJscode2sessionRequset.getCode() == null && apiAppsJscode2sessionRequset.getAnonymous_code() ==null)) {
            ToutiaoError toutiaoError = new ToutiaoError();
            toutiaoError.setError(1);
            toutiaoError.setErrcode(40014);
            toutiaoError.setErrmsg("bad params");
            toutiaoError.setMessage("bad parameters");
            throw toutiaoError;
        }
        Boolean iscode = apiAppsJscode2sessionRequset.getCode()!=null;
        final String code =  iscode ?apiAppsJscode2sessionRequset.getCode():apiAppsJscode2sessionRequset.getAnonymous_code();
        FileDB.Data wx_openid_data = _code_openid(code);
        FileDB.Data wx_session_key_data = null;
        if(wx_openid_data!=null){
            String wx_openid = wx_openid_data.value;
            wx_session_key_data = _openid_sessionkey(wx_openid);
            ApiAppsJscode2sessionResponse tt_response = new ApiAppsJscode2sessionResponse();
            tt_response.setAnonymous_openid(wx_openid);
            tt_response.setOpenid(wx_openid);
            tt_response.setSession_key(wx_session_key_data.value);
            return tt_response;
        }
        ///////////////////////////////////////////////////
        Code2SessionResponse wx_response = null;
        try {
            Code2SessionRequest code2SessionRequest = new Code2SessionRequest();
            code2SessionRequest.setAppid(apiAppsJscode2sessionRequset.getAppid());
            code2SessionRequest.setJs_code(apiAppsJscode2sessionRequset.getCode());
            code2SessionRequest.setSecret(apiAppsJscode2sessionRequset.getSecret());
            wx_response = weixinAuthSDK.code2Session(code2SessionRequest);
        } catch (WeixinError weixinError) {
            if (weixinError.getErrcode()!= 0) {
                ToutiaoError tt_error = new ToutiaoError();
                switch (weixinError.getErrcode()) {
                    case -1:
                        tt_error.setError(1);
                        tt_error.setErrcode(-1);
                        tt_error.setErrmsg(weixinError.getErrmsg());
                        tt_error.setMessage(weixinError.getErrmsg());
                        break;
                    case 40029:
                        tt_error.setError(3);
                        tt_error.setErrcode(40018);
                        tt_error.setErrmsg("bad code");
                        tt_error.setMessage("bad code");
                        break;

                    default:
                        tt_error.setError(74077);
                        tt_error.setErrcode(74077);
                        tt_error.setErrmsg(weixinError.getErrmsg());
                        tt_error.setMessage(weixinError.getErrmsg());
                        break;
                }
                throw tt_error;
            }
        }

        //////////

        assert false;
        wx_openid_data.value =wx_response.getOpenid();

        wx_session_key_data.value =wx_response.getSession_key();
        ///////////////
        try {
            _code_openid(code,wx_openid_data.value);
            _openid_sessionkey(wx_openid_data.value,wx_session_key_data.value);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ////////////
        ApiAppsJscode2sessionResponse tt_response = new ApiAppsJscode2sessionResponse();
        tt_response.setAnonymous_openid(wx_openid_data.value);
        tt_response.setOpenid(wx_openid_data.value);
        tt_response.setSession_key(wx_session_key_data.value);
        return tt_response;
    }

    public void apiAppsSetUserStorage(ApiAppsSetUserStorageRequest apiAppsSetUserStorageRequest) throws ToutiaoError {

    }

    public void apiAppsRemoveUserStorage(ApiAppsRemoveUserStorageRequest apiAppsRemoveUserStorageRequest) throws ToutiaoError {

    }

    public byte[] apiAppsQrcode(ApiAppsQrcodeRequest apiAppsQrcodeRequest) throws ToutiaoError {
        return new byte[0];
    }

    public ApiAppsSubscribeNotificationDeveloperNotifyResponse apiappsSubscribeNotificationDeveloperNotify(ApiAppsSubscribeNotificationDeveloperNotifyRequest apiAppsSubscribeNotificationDeveloperNotifyRequest) throws ToutiaoError {
        return null;
    }

    //////////////////////////////////////


}
