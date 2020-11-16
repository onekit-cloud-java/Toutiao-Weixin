package cn.onekit.x2x.cloud.toutiao_weixin.web;

import cn.onekit.thekit.DB;
import cn.onekit.thekit.FILE;
import cn.onekit.thekit.JSON;
import cn.onekit.x2x.cloud.toutiao_weixin.*;
import com.toutiao.developer.entity.*;
import com.toutiao.developer.entity.v2.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/toutiao")
public class ToutiaoServerWeb {
    ToutiaoServer toutiaoServer = new ToutiaoServer(WeixinAccount.appid, WeixinAccount.secret) {
        @Override
        protected void _jscode_openid(String wx_jscode, String wx_openid) {
            DB.set("[toutiao2weixin] jscode_openid",wx_jscode,wx_openid);
        }

        @Override
        protected String _jscode_openid(String wx_jscode) {
            return DB.get("[toutiao2weixin] jscode_openid",wx_jscode);
        }

        @Override
        protected void _openid_sessionkey(String openid, String sessionkey) {
            DB.set("[toutiao2weixin] openid_sessionkey",openid,sessionkey);
        }

        @Override
        protected String _openid_sessionkey(String openid) {
            return DB.get("[toutiao2weixin] openid_sessionkey", openid);
        }
    };
    ToutiaoServer2 ToutiaoServer2 = new ToutiaoServer2(WeixinAccount.appid, WeixinAccount.secret) {
        @Override
        protected void _jscode_openid(String wx_jscode, String wx_openid) {
            DB.set("[toutiao2weixin] jscode_openid",wx_jscode,wx_openid);
        }

        @Override
        protected String _jscode_openid(String wx_jscode) {
            return DB.get("[toutiao2weixin] jscode_openid",wx_jscode);
        }

        @Override
        protected void _openid_sessionkey(String openid, String sessionkey) {
            DB.set("[toutiao2weixin] openid_sessionkey",openid,sessionkey);
        }

        @Override
        protected String _openid_sessionkey(String openid) {
            return DB.get("[toutiao2weixin] openid_sessionkey", openid);
        }
    };

    @RequestMapping(method = RequestMethod.GET,value="/api/apps/token")
    public apps__token_response getAccessToken(
            @RequestParam String appid,
            @RequestParam String secret,
            @RequestParam String grant_type
    ) throws Exception {
        return toutiaoServer.apps__token(appid, secret, grant_type);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/api/apps/jscode2session")
    public apps__jscode2session_response code2Session1(
            @RequestParam String appid,
            @RequestParam String secret,
            @RequestParam String code
    ) throws Exception {
        return toutiaoServer.apps__jscode2session(appid, secret, code, null);

    }

    @RequestMapping("/setUserStorage")
    public apps__set_user_storage_response setUserStorage(
            @RequestParam String session_key,
            @RequestParam String access_token,
            @RequestParam String openid,
            @RequestParam String signature,
            @RequestBody String body
    ) throws Exception {
        return toutiaoServer.apps__set_user_storage(session_key, access_token, openid, signature, JSON.string2object(body, apps__set_user_storage_body.class));
    }

    @RequestMapping("/removeUserStorage")
    public apps__remove_user_storage_response removeUserStorage(
            @RequestParam String session_key,
            @RequestParam String access_token,
            @RequestParam String openid,
            @RequestParam String signature,
            @RequestBody String body
    ) throws Exception {
        return toutiaoServer.apps__remove_user_storage(session_key, access_token, openid, signature, JSON.string2object(body, apps__remove_user_storage_body.class));
    }

    @RequestMapping("/createQRCode")
    public byte[] createQRCode(
            @RequestBody String body
    ) throws Exception {
        return toutiaoServer.apps__qrcode(JSON.string2object(body, apps__qrcode_body.class));
    }

    @RequestMapping("/checkContent")
    public tags__text__antidirt_response checkContent(
            HttpServletRequest request,
            @RequestBody String body
    ) throws Exception {
        String x_Token = request.getHeader("X-Token");
        return ToutiaoServer2.tags__text__antidirt(x_Token, JSON.string2object(body, tags__text__antidirt_body.class));
    }

    @RequestMapping("/checkImage")
    public tags__image_response checkImage(
            HttpServletRequest request,
            @RequestBody String body
    ) throws Exception {
        String x_Token = request.getHeader("X-Token");
        return ToutiaoServer2.tags__image(x_Token, JSON.string2object(body, tags__image_body.class));
    }
}