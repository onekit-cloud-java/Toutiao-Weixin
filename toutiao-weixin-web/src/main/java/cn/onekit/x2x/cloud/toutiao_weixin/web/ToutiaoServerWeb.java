package cn.onekit.x2x.cloud.toutiao_weixin.web;

import cn.onekit.thekit.DB;
import cn.onekit.thekit.JSON;
import cn.onekit.x2x.cloud.toutiao_weixin.ToutiaoServer;
import com.toutiao.developer.entity.ToutiaoError;
import com.toutiao.developer.entity.apps__qrcode_body;
import com.toutiao.developer.entity.apps__remove_user_storage_body;
import com.toutiao.developer.entity.apps__set_user_storage_body;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class ToutiaoServerWeb {

private ToutiaoServer _toutiaoServer;
    private ToutiaoServer toutiaoServer() {
       if(_toutiaoServer==null){
           _toutiaoServer = new ToutiaoServer(WeixinAccount.wx_appid, WeixinAccount.wx_secret) {

               @Override
               protected void _jscode_openid(String wx_jscode, String wx_openid) {
                   DB.set("[toutiao-weixin] jscode_openid",wx_jscode,wx_openid);
               }

               @Override
               protected String _jscode_openid(String wx_jscode) {
                   return  DB.get("[toutiao-weixin] jscode_openid",wx_jscode);
               }

               @Override
               protected void _openid_sessionkey(String wx_openid, String wx_sessionkey) {
                   DB.set("[toutiao-weixin] openid_sessionkey",wx_openid,wx_sessionkey);
               }

               @Override
               protected String _openid_sessionkey(String wx_openid) {
                   return DB.get("[toutiao-weixin] openid_sessionkey",wx_openid);
               }
           };
       }
       return _toutiaoServer;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/apps/token")
    public String getAccessToken(
            @RequestParam String appid,
            @RequestParam String secret,
            @RequestParam String grant_type
    ) throws Exception {
        try {
            return JSON.object2string(toutiaoServer().apps__token(appid, secret, grant_type));
        } catch (ToutiaoError error) {
            return JSON.object2string(error);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/apps/jscode2session")
    public String code2Session(
            @RequestParam String appid,
            @RequestParam String secret,
            @RequestParam(required=false) String code,
            @RequestParam(required=false)  String anonymous_code
    ) throws Exception {
        try {
            return JSON.object2string(toutiaoServer().apps__jscode2session(appid, secret, code, anonymous_code));
        } catch (ToutiaoError error) {
            return JSON.object2string(error);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/apps/set_user_storage")
    public String setUserStorage(
            @RequestParam String session_key,
            @RequestParam String access_token,
            @RequestParam String openid,
            @RequestParam String signature,
            @RequestBody String body
    ) throws Exception {
        try {
            return JSON.object2string(toutiaoServer().apps__set_user_storage(session_key, access_token, openid, signature, JSON.string2object(body, apps__set_user_storage_body.class)));
        } catch (ToutiaoError error) {
            return JSON.object2string(error);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/apps/remove_user_storage")
    public String removeUserStorage(
            @RequestParam String session_key,
            @RequestParam String access_token,
            @RequestParam String openid,
            @RequestParam String signature,
            @RequestBody String body
    ) throws Exception {
        try {
            return JSON.object2string(toutiaoServer().apps__remove_user_storage(session_key, access_token, openid, signature, JSON.string2object(body, apps__remove_user_storage_body.class)));
        } catch (ToutiaoError error) {
            return JSON.object2string(error);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/apps/qrcoden")
    public byte[] createQRCode(
            @RequestBody String body
    ) throws Exception {
        try {
            return toutiaoServer().apps__qrcode(JSON.string2object(body, apps__qrcode_body.class));
        } catch (ToutiaoError error) {
            return JSON.object2string(error).getBytes();
        }
    }


}