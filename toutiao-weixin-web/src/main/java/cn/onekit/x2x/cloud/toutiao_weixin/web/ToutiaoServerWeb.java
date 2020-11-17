package cn.onekit.x2x.cloud.toutiao_weixin.web;

import cn.onekit.thekit.DB;
import cn.onekit.thekit.JSON;
import cn.onekit.x2x.cloud.toutiao_weixin.*;
import com.toutiao.developer.entity.*;
import com.toutiao.developer.entity.v2.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/toutiao")
public class ToutiaoServerWeb {
    ToutiaoServer toutiaoServer = new ToutiaoServer(WeixinAccount.appid, WeixinAccount.secret) {
        @Override
        protected void _jscode_openid(String wx_jscode, String wx_openid) {
            DB.set("[toutiao2weixin] jscode_openid", wx_jscode, wx_openid);
        }

        @Override
        protected String _jscode_openid(String wx_jscode) {
            return DB.get("[toutiao2weixin] jscode_openid", wx_jscode);
        }

        @Override
        protected void _openid_sessionkey(String openid, String sessionkey) {
            DB.set("[toutiao2weixin] openid_sessionkey", openid, sessionkey);
        }

        @Override
        protected String _openid_sessionkey(String openid) {
            return DB.get("[toutiao2weixin] openid_sessionkey", openid);
        }
    };
    ToutiaoServer2 ToutiaoServer2 = new ToutiaoServer2(WeixinAccount.appid, WeixinAccount.secret) {
        @Override
        protected void _jscode_openid(String wx_jscode, String wx_openid) {
            DB.set("[toutiao2weixin] jscode_openid", wx_jscode, wx_openid);
        }

        @Override
        protected String _jscode_openid(String wx_jscode) {
            return DB.get("[toutiao2weixin] jscode_openid", wx_jscode);
        }

        @Override
        protected void _openid_sessionkey(String openid, String sessionkey) {
            DB.set("[toutiao2weixin] openid_sessionkey", openid, sessionkey);
        }

        @Override
        protected String _openid_sessionkey(String openid) {
            return DB.get("[toutiao2weixin] openid_sessionkey", openid);
        }
    };

    @RequestMapping(method = RequestMethod.GET, value = "/api/apps/token")
    public String getAccessToken(
            @RequestParam String appid,
            @RequestParam String secret,
            @RequestParam String grant_type
    ) throws Exception {
        try {
            return JSON.object2string(toutiaoServer.apps__token(appid, secret, grant_type));
        } catch (ToutiaoError error) {
            return JSON.object2string(error);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/apps/jscode2session")
    public String code2Session1(
            @RequestParam String appid,
            @RequestParam String secret,
            @RequestParam String code,
            HttpServletResponse response
    ) throws Exception {
        try {
            return JSON.object2string(toutiaoServer.apps__jscode2session(appid, secret, code, null));
        } catch (ToutiaoError error) {
            return JSON.object2string(error);
        }
    }

    @RequestMapping("/setUserStorage")
    public String setUserStorage(
            @RequestParam String session_key,
            @RequestParam String access_token,
            @RequestParam String openid,
            @RequestParam String signature,
            @RequestBody String body
    ) throws Exception {
        try {
            return JSON.object2string(toutiaoServer.apps__set_user_storage(session_key, access_token, openid, signature, JSON.string2object(body, apps__set_user_storage_body.class)));
        } catch (ToutiaoError error) {
            return JSON.object2string(error);
        }
    }

    @RequestMapping("/removeUserStorage")
    public String removeUserStorage(
            @RequestParam String session_key,
            @RequestParam String access_token,
            @RequestParam String openid,
            @RequestParam String signature,
            @RequestBody String body
    ) throws Exception {
        try {
            return JSON.object2string(toutiaoServer.apps__remove_user_storage(session_key, access_token, openid, signature, JSON.string2object(body, apps__remove_user_storage_body.class)));
        } catch (ToutiaoError error) {
            return JSON.object2string(error);
        }
    }

    @RequestMapping("/createQRCode")
    public byte[] createQRCode(
            @RequestBody String body
    ) throws Exception {
        try {
            return toutiaoServer.apps__qrcode(JSON.string2object(body, apps__qrcode_body.class));
        } catch (ToutiaoError error) {
            return JSON.object2string(error).getBytes();
        }
    }

    @RequestMapping("/checkContent")
    public String checkContent(
            HttpServletRequest request,
            @RequestBody String body
    ) throws Exception {
        String x_Token = request.getHeader("X-Token");
        try {
            return JSON.object2string(ToutiaoServer2.tags__text__antidirt(x_Token, JSON.string2object(body, tags__text__antidirt_body.class)));
        } catch (ToutiaoError2 error) {
            return JSON.object2string(error);
        }
    }

    @RequestMapping("/checkImage")
    public String checkImage(
            HttpServletRequest request,
            @RequestBody String body
    ) throws Exception {
        String x_Token = request.getHeader("X-Token");
        try {
            return JSON.object2string(ToutiaoServer2.tags__image(x_Token, JSON.string2object(body, tags__image_body.class)));
        } catch (ToutiaoError2 error) {
            return JSON.object2string(error);
        }
    }
}