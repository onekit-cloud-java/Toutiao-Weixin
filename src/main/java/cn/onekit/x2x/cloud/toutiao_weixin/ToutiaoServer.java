package cn.onekit.x2x.cloud.toutiao_weixin;

import cn.onekit.thekit.JSON;
import com.google.gson.JsonObject;
import com.qq.weixin.api.WeixinSDK;
import com.qq.weixin.api.entity.*;
import com.toutiao.developer.ToutiaoAPI;
import com.toutiao.developer.entity.*;

import java.util.HashMap;
import java.util.Map;

public abstract class ToutiaoServer implements ToutiaoAPI {

    private final String wx_appid;
    private final String wx_secret;
    private WeixinSDK weixinSDK= new WeixinSDK("https://api.weixin.qq.com");
    public ToutiaoServer(
            String wx_appid, String wx_secret) {
        this.wx_appid = wx_appid;
        this.wx_secret = wx_secret;
    }

    private final String wx_sig_method = "hmac_sha256";

    //////////////////////////////////////
    abstract protected void _jscode_openid(String wx_jscode, String wx_openid);
    abstract protected String _jscode_openid(String wx_jscode);
    abstract protected void _openid_sessionkey(String wx_openid, String wx_sessionkey);
    abstract protected String _openid_sessionkey(String wx_openid);

    //////////////////////////////////////

    @Override
    public apps__token_response apps__token(
            String tt_appid,
            String tt_secret,
            String tt_grant_type
    ) throws ToutiaoError {
        if(tt_appid == null || tt_secret == null || tt_grant_type == null) {
            ToutiaoError toutiaoError = new ToutiaoError();
            toutiaoError.setError(1);
            toutiaoError.setErrcode(40014);
            toutiaoError.setErrmsg("bad params");
            toutiaoError.setMessage("bad parameters");
            throw toutiaoError;
        }
        if(!tt_grant_type.equals("client_credential")){
            ToutiaoError toutiaoError = new ToutiaoError();
            toutiaoError.setError(3);
            toutiaoError.setErrcode(40020);
            toutiaoError.setErrmsg("bad grant_type");
            toutiaoError.setMessage("grant_type is not client_credential");
            throw toutiaoError;
        }
        try {

            final String wx_grant_type = "client_credential";
            cgi_bin__token_response wx_response = weixinSDK.cgi_bin__token(wx_appid, wx_secret, wx_grant_type);
            /////////////////////
            apps__token_response tt_reponse = new apps__token_response();
            tt_reponse.setAccess_token(wx_response.getAccess_token());
            tt_reponse.setExpires_in(wx_response.getExpires_in());
            return tt_reponse;
        } catch (WeixinError e) {
            ToutiaoError tt_error = new ToutiaoError();
            tt_error.setError(74077);
            tt_error.setErrcode(e.getErrcode());
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
        if(tt_appid == null || tt_secret == null || (tt_code == null && tt_anonymous_code ==null)) {
            ToutiaoError toutiaoError = new ToutiaoError();
            toutiaoError.setError(1);
            toutiaoError.setErrcode(40014);
            toutiaoError.setErrmsg("bad params");
            toutiaoError.setMessage("bad parameters");
            throw toutiaoError;
        }
        boolean isCode =  tt_code!=null;
        final String code = isCode?tt_code:tt_anonymous_code;
        String wx_openid = _jscode_openid(code);
        String wx_session_key;
        if(wx_openid!=null){
            wx_session_key = _openid_sessionkey(wx_openid);
            apps__jscode2session_response tt_response = new apps__jscode2session_response();
            tt_response.setAnonymous_openid(wx_openid);
            tt_response.setOpenid(wx_openid);
            tt_response.setSession_key(wx_session_key);
            return tt_response;
        }
        ///////////////////////////////////////////////////
        final String wx_grant_type = "authorization_code";
        snc__jscode2session_response wx_response = weixinSDK.snc__jscode2session(wx_appid, wx_secret, code, wx_grant_type);
        //////////
        if (wx_response.getErrcode() != 0) {
            ToutiaoError tt_error = new ToutiaoError();
            switch (wx_response.getErrcode()) {
                case -1:
                    tt_error.setError(1);
                    tt_error.setErrcode(-1);
                    tt_error.setErrmsg(wx_response.getErrmsg());
                    tt_error.setMessage(wx_response.getErrmsg());
                    break;
                case 40029:
                    tt_error.setError(3);
                    if (isCode) {
                        tt_error.setErrcode(40018);
                        tt_error.setErrmsg("bad code");
                        tt_error.setMessage("bad code");
                    } else {
                        tt_error.setErrcode(40019);
                        tt_error.setErrmsg("bad anonymous code");
                        tt_error.setMessage("bad anonymous code");
                    }
                    break;
                case 45011:
                    tt_error.setError(1);
                    tt_error.setErrcode(-1);
                    tt_error.setErrmsg(wx_response.getErrmsg());
                    tt_error.setMessage(wx_response.getErrmsg());
                    break;
                default:
                    tt_error.setError(74077);
                    tt_error.setErrcode(74077);
                    tt_error.setErrmsg(wx_response.getErrmsg());
                    tt_error.setMessage(wx_response.getErrmsg());
                    break;
            }
            throw tt_error;
        }
         wx_openid = wx_response.getOpenid();
         wx_session_key = wx_response.getSession_key();
         ///////////////
        _jscode_openid(code,wx_openid);
        _openid_sessionkey(wx_openid,wx_session_key);
        ////////////
        apps__jscode2session_response tt_response = new apps__jscode2session_response();
        tt_response.setAnonymous_openid(wx_openid);
        tt_response.setOpenid(wx_openid);
        tt_response.setSession_key(wx_session_key);
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
        if(tt_access_token == null || tt_openid == null || tt_signature == null || tt_sig_method ==null || tt_body == null) {
            ToutiaoError toutiaoError = new ToutiaoError();
            toutiaoError.setError(1);
            toutiaoError.setErrcode(40014);
            toutiaoError.setErrmsg("bad params");
            toutiaoError.setMessage("bad parameters");
            throw toutiaoError;
        }

        try {
            String wx_session_key = _openid_sessionkey(tt_openid);
            if (!_signBody(tt_sig_method, wx_session_key, JSON.object2string(tt_body)).equals(tt_signature)) {
                throw new Exception("bad sign!");
            }
            //////////////
            JsonObject body = (JsonObject) JSON.object2json(tt_body);
            wxa__set_user_storage_body wx_body = JSON.json2object(body, wxa__set_user_storage_body.class);
            String wx_signature = _signBody(wx_sig_method, wx_session_key, JSON.object2string(wx_body));
            //////////////
            WeixinResponse wx_response = weixinSDK.wxa__set_user_storage(tt_access_token, tt_openid, wx_signature, wx_sig_method, wx_body);
            ////////////
            if (wx_response.getErrcode() != 0) {
                ToutiaoError tt_error = new ToutiaoError();
                switch (wx_response.getErrcode()) {
                    case -1:
                        tt_error.setError(1);
                        tt_error.setErrcode(-1);
                        tt_error.setErrmsg(wx_response.getErrmsg());
                        tt_error.setMessage(wx_response.getErrmsg());
                        break;
                    case 87016:
                        tt_error.setError(1);
                        tt_error.setErrcode(40010);
                        tt_error.setErrmsg(wx_response.getErrmsg());
                        tt_error.setMessage(wx_response.getErrmsg());
                        break;
                    case 87017:
                        tt_error.setError(1);
                        tt_error.setErrcode(60001);
                        tt_error.setErrmsg(wx_response.getErrmsg());
                        tt_error.setMessage(wx_response.getErrmsg());
                        break;
                    case 87018:
                        tt_error.setError(1);
                        tt_error.setErrcode(-1);
                        tt_error.setErrmsg(wx_response.getErrmsg());
                        tt_error.setMessage(wx_response.getErrmsg());
                        break;
                    case 87019:
                        tt_error.setError(1);
                        tt_error.setErrcode(40009);
                        tt_error.setErrmsg(wx_response.getErrmsg());
                        tt_error.setMessage(wx_response.getErrmsg());
                        break;
                    case 40001:
                        tt_error.setError(1);
                        tt_error.setErrcode(40002);
                        tt_error.setErrmsg("bad access_token");
                        tt_error.setMessage("bad access_token");
                        break;
                    default:
                        tt_error.setError(74077);
                        tt_error.setErrcode(74077);
                        tt_error.setErrmsg(wx_response.getErrmsg());
                        tt_error.setMessage(wx_response.getErrmsg());
                        break;
                }
                throw tt_error;
            }
            ////////////
            return new apps__set_user_storage_response();
        } catch (Exception e) {
            e.printStackTrace();
            ToutiaoError error = new ToutiaoError();
            error.setError(74077);
            error.setErrcode(74077);
            error.setErrmsg(e.getMessage());
            throw error;
        }
    }

    @Override
    public apps__remove_user_storage_response apps__remove_user_storage(
            String tt_access_token,
            String tt_openid,
            String tt_signature,
            String tt_sig_method,
            apps__remove_user_storage_body tt_body
    ) throws ToutiaoError {
        if(tt_access_token == null || tt_openid == null || tt_signature == null || tt_sig_method == null || tt_body == null) {
            ToutiaoError toutiaoError = new ToutiaoError();
            toutiaoError.setError(1);
            toutiaoError.setErrcode(40014);
            toutiaoError.setErrmsg("bad params");
            toutiaoError.setMessage("bad parameters");
            throw toutiaoError;
        }
        try {
            String wx_session_key = _openid_sessionkey(tt_openid);
            if (!_signBody(tt_sig_method, wx_session_key, JSON.object2string(tt_body)).equals(tt_signature)) {
                throw new Exception("bad sign!");
            }
            //////////////
            wxa__remove_user_storage_body wx_body = new wxa__remove_user_storage_body();
            wx_body.setKey(tt_body.getKey());
            String wx_signature = _signBody(wx_sig_method, wx_session_key, JSON.object2string(wx_body));
            //////////////
            WeixinResponse wx_response = weixinSDK.wxa__remove_user_storage(tt_access_token, tt_openid, wx_signature, wx_sig_method, wx_body);
            ////////////
            if (wx_response.getErrcode() != 0) {
                ToutiaoError tt_error = new ToutiaoError();
                switch (wx_response.getErrcode()){
                    case -1:
                        tt_error.setError(1);
                        tt_error.setErrcode(-1);
                        tt_error.setErrmsg(wx_response.getErrmsg());
                        tt_error.setMessage(wx_response.getErrmsg());
                    case 41001:
                        tt_error.setError(1);
                        tt_error.setErrcode(40002);
                        tt_error.setErrmsg("bad access_token");
                        tt_error.setMessage("bad access_token");
                        break;
                    default:
                        tt_error.setError(74077);
                        tt_error.setErrcode(74077);
                        tt_error.setErrmsg(wx_response.getErrmsg());
                        tt_error.setMessage(wx_response.getErrmsg());
                        break;
                }

                throw  tt_error;
            }
            ////////////
            apps__remove_user_storage_response tt_response = new apps__remove_user_storage_response();
            tt_response.setError(0);
            return tt_response;
        } catch (Exception e) {
            e.printStackTrace();
            ToutiaoError error = new ToutiaoError();
            error.setError(74077);
            error.setErrcode(74077);
            error.setErrmsg(e.getMessage());
            throw error;
        }
    }

    @Override
    public byte[] apps__qrcode(
            apps__qrcode_body tt_body
    ) throws ToutiaoError {
        if(tt_body == null) {
            ToutiaoError toutiaoError = new ToutiaoError();
            toutiaoError.setError(40002);
            toutiaoError.setErrmsg("access_token");
            throw toutiaoError;
        }

        try {
            final String wx_access_token = tt_body.getAccess_token();
            wxaapp__createwxaqrcode_body wx_body =new wxaapp__createwxaqrcode_body();
            wx_body.setPath(tt_body.getPath());
            wx_body.setWidth(tt_body.getWidth());
            //////////////
            WeixinError wx_error = new WeixinError();
            if(!wx_error.equals(0)){
                ToutiaoError tt_error = new ToutiaoError();
                switch (wx_error.getErrcode()){
                    case -1:
                        tt_error.setError(1);
                        tt_error.setErrcode(-1);
                        tt_error.setErrmsg(wx_error.getErrmsg());
                        tt_error.setMessage(wx_error.getMessage());

                    default:
                        tt_error.setError(74077);
                        tt_error.setErrcode(74077);
                        tt_error.setErrmsg(wx_error.getErrmsg());
                        tt_error.setMessage(wx_error.getMessage());
                        break;
                }
            }

            return  weixinSDK.cgi_bin__wxaapp__createwxaqrcode(wx_access_token, wx_body);
        } catch (WeixinError wx_error) {
            ToutiaoError tt_error = new ToutiaoError();
            tt_error.setError(74077);
            tt_error.setErrcode(wx_error.getErrcode());
            tt_error.setErrmsg(wx_error.getErrmsg());
            throw tt_error;
        }

    }


    @Override
    public apps__subscribe_notification__developer__notify_response apps__subscribe_notification__developer__notify(
            apps__subscribe_notification__developer__notify_body tt_body
    ) throws ToutiaoError {
        if(tt_body == null) {
            ToutiaoError toutiaoError = new ToutiaoError();
            toutiaoError.setError(1);
            toutiaoError.setErrcode(40014);
            toutiaoError.setErrmsg("bad params");
            toutiaoError.setMessage("bad parameters");
            throw toutiaoError;
        }
        try {
            Subscribe2Subscribe subscribe2subscribe=new Subscribe2Subscribe();
            //
            String access_token = tt_body.getAccess_token();
            subscribe__send_body wx_body=new subscribe__send_body();

            wx_body.setTouser(tt_body.getOpen_id());
            wx_body.setPage(tt_body.getPage());
            wx_body.setTemplate_id(subscribe2subscribe.id2id(tt_body.getTpl_id()));

            HashMap<String, String> key2key = subscribe2subscribe.id2keys(tt_body.getTpl_id());
            HashMap<String, subscribe__send_body.Data.DataValue> data = new HashMap<>();

            for (Map.Entry<String, String> entry : tt_body.getData().entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                subscribe__send_body.Data.DataValue dataValue = new subscribe__send_body.Data.DataValue();
                dataValue.setValue(value);

                data.put(key2key.get(key),dataValue);
            }

            wx_body.setData(data);
            WeixinResponse wx_response = weixinSDK.cgi_bin__message__subscribe__send(access_token,wx_body);
            if (wx_response.getErrcode() != 0) {
                apps__subscribe_notification__developer__notify_response tt_error = new apps__subscribe_notification__developer__notify_response();
                tt_error.setErr_no(74077);


                return tt_error;
            }
            apps__subscribe_notification__developer__notify_response tt_response =new apps__subscribe_notification__developer__notify_response();
            return tt_response;
        }catch (Exception e) {
            e.printStackTrace();
            ToutiaoError error = new ToutiaoError();
            error.setError(74077);
            error.setErrcode(74077);
            error.setErrmsg(e.getMessage());
            throw error;
        }
    }

    @Override
    public apps__game__template__send_response apps__game__template__send(
            apps__game__template__send_body tt_body
    ) throws ToutiaoError {
        if(tt_body == null) {
            ToutiaoError toutiaoError = new ToutiaoError();
            toutiaoError.setError(1);
            toutiaoError.setErrcode(40014);
            toutiaoError.setErrmsg("bad params");
            toutiaoError.setMessage("bad parameters");
            throw toutiaoError;
        }
        try {
        Push2Push push2push=new Push2Push();
        //
        String access_token = tt_body.getAccess_token();
        subscribe__send_body wx_body=new subscribe__send_body();

        wx_body.setTouser(tt_body.getTouser());
        wx_body.setPage(tt_body.getPage());
        wx_body.setTemplate_id(push2push.id2id(tt_body.getTemplate_id()));

        HashMap<String, String> key2key = push2push.id2keys(tt_body.getTemplate_id());
        HashMap<String, subscribe__send_body.Data.DataValue> data = new HashMap<>();

            for (Map.Entry<String, apps__game__template__send_body.SubData> entry : tt_body.getData().entrySet()) {
                String key = entry.getKey();
                apps__game__template__send_body.SubData value = entry.getValue();
                subscribe__send_body.Data.DataValue dataValue = new subscribe__send_body.Data.DataValue();
                dataValue.setValue(value.getValue());
                data.put(key2key.get(key),dataValue);
            }

        wx_body.setData(data);
        WeixinResponse wx_response = weixinSDK.cgi_bin__message__subscribe__send(access_token,wx_body);
        if (wx_response.getErrcode() != 0) {
            apps__game__template__send_response tt_error = new apps__game__template__send_response();
            tt_error.setErrcode(wx_response.getErrcode());
            tt_error.setErrmsg(wx_response.getErrmsg());

            return tt_error;
        }
            apps__game__template__send_response tt_response =new apps__game__template__send_response();
        return tt_response;
    }catch (Exception e) {
            e.printStackTrace();
            ToutiaoError error = new ToutiaoError();
            error.setError(74077);
            error.setErrcode(74077);
            error.setErrmsg(e.getMessage());
            throw error;
        }

    }
}
