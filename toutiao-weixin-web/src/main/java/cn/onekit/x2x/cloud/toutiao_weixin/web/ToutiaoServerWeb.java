package cn.onekit.x2x.cloud.toutiao_weixin.web;

import cn.onekit.thekit.DB;
import cn.onekit.thekit.JSON;
import cn.onekit.x2x.cloud.toutiao_weixin.ToutiaoServer;
import com.toutiao.developer.entity.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
            @RequestParam(required = false) String appid,
            @RequestParam(required = false) String secret,
            @RequestParam(required = false) String grant_type
    )  {
        try {
            return JSON.object2string(toutiaoServer().apps__token(appid, secret, grant_type));
        } catch (ToutiaoError toutiaoError) {
            return JSON.object2string(toutiaoError);
        }catch (Exception error){
            ToutiaoError toutiaoError = new ToutiaoError();
            toutiaoError.setError(500);
            toutiaoError.setErrcode(500);
            toutiaoError.setErrmsg(error.getMessage());
            return JSON.object2string(toutiaoError);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/apps/jscode2session")
    public String code2Session(
            @RequestParam(required=false) String appid,
            @RequestParam(required=false) String secret,
            @RequestParam(required=false) String code,
            @RequestParam(required=false)  String anonymous_code
    )  {
        try {
            return JSON.object2string(toutiaoServer().apps__jscode2session(appid, secret, code, anonymous_code));
        }catch (ToutiaoError toutiaoError) {
            return JSON.object2string(toutiaoError);
        }catch (Exception error){
            ToutiaoError toutiaoError = new ToutiaoError();
            toutiaoError.setError(500);
            toutiaoError.setErrcode(500);
            toutiaoError.setErrmsg(error.getMessage());
            return JSON.object2string(toutiaoError);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/apps/set_user_storage")
    public String setUserStorage(
            HttpServletRequest request,
            @RequestBody String body
    )  {
        try {
            String sig_method = request.getParameter("sig_method");
            String access_token = request.getParameter("access_token");
            String openid = request.getParameter("openid");
            String signature = request.getParameter("signature");
            return JSON.object2string(toutiaoServer().apps__set_user_storage( access_token, openid, signature, sig_method,JSON.string2object(body, apps__set_user_storage_body.class)));
        } catch (ToutiaoError toutiaoError) {
            return JSON.object2string(toutiaoError);
        }catch (Exception error){
            ToutiaoError toutiaoError = new ToutiaoError();
            toutiaoError.setError(500);
            toutiaoError.setErrcode(500);
            toutiaoError.setErrmsg(error.getMessage());
            return JSON.object2string(toutiaoError);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/apps/remove_user_storage")
    public String removeUserStorage(
            @RequestParam String session_key,
            @RequestParam String access_token,
            @RequestParam String openid,
            @RequestParam String signature,
            @RequestBody String body
    )  {
        try {
            return JSON.object2string(toutiaoServer().apps__remove_user_storage(session_key, access_token, openid, signature, JSON.string2object(body, apps__remove_user_storage_body.class)));
        } catch (ToutiaoError toutiaoError) {
            return JSON.object2string(toutiaoError);
        }catch (Exception error){
            ToutiaoError toutiaoError = new ToutiaoError();
            toutiaoError.setError(500);
            toutiaoError.setErrcode(500);
            toutiaoError.setErrmsg(error.getMessage());
            return JSON.object2string(toutiaoError);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/apps/qrcode",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] createQRCode(
            @RequestBody String body,
            HttpServletResponse response
    )  {
        try {
            return toutiaoServer().apps__qrcode(JSON.string2object(body, apps__qrcode_body.class));
        } catch (ToutiaoError toutiaoError) {
            return null;
        }catch (Exception error){
            ToutiaoError toutiaoError = new ToutiaoError();
            toutiaoError.setError(500);
            toutiaoError.setErrcode(500);
            toutiaoError.setErrmsg(error.getMessage());
            return null;
        }
    }
    @RequestMapping(method = RequestMethod.POST, value = "api/apps/subscribe_notification/developer/v1/notify")
    public String subscribe(
            @RequestBody String body
    ){
        try {
            return JSON.object2string(toutiaoServer().apps__subscribe_notification__developer__notify(JSON.string2object(body, apps__subscribe_notification__developer__notify_body.class)));
        } catch (ToutiaoError toutiaoError) {
            return JSON.object2string(toutiaoError);
        }catch (Exception error) {
            ToutiaoError toutiaoError = new ToutiaoError();
            toutiaoError.setError(500);
            toutiaoError.setErrcode(500);
            toutiaoError.setErrmsg(error.getMessage());
            return JSON.object2string(toutiaoError);
        }

    }
    @RequestMapping(method = RequestMethod.POST, value = "api/apps/game/template/send")
    public String send(
            @RequestBody String body
    ){
        try {
            return JSON.object2string(toutiaoServer().apps__game__template__send(JSON.string2object(body,apps__game__template__send_body.class)));
        } catch (ToutiaoError toutiaoError) {
            return JSON.object2string(toutiaoError);
        }catch (Exception error) {
            ToutiaoError toutiaoError = new ToutiaoError();
            toutiaoError.setError(500);
            toutiaoError.setErrcode(500);
            toutiaoError.setErrmsg(error.getMessage());
            return JSON.object2string(toutiaoError);
        }

    }


}