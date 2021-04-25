package cn.onekit.x2x.cloud.toutiao_weixin;

import cn.onekit.thekit.FileDB;
import cn.onekit.x2x.cloud.toutiao_weixin.web.WeixinAccount;

import java.io.IOException;

public class FileDBToutiaoServer extends ToutiaoServer{
    private FileDB fileDB = new FileDB("toutiao-weixin");

    protected FileDBToutiaoServer(String wx_appid, String wx_secret) {
        super(wx_appid, wx_secret);
    }


    @Override
                protected void setaccess_token(String access_token) throws IOException {
                    fileDB.set("access_token","token",access_token);
                }

                @Override
                protected FileDB.Data getaccess_token() {
                    return fileDB.get("access_token","token");
                }

                @Override
                protected void _code_openid(String wx_jscode, String wx_openid) throws IOException {
                    fileDB.set("jscode_openid",wx_jscode,wx_openid);
                }

                @Override
                protected FileDB.Data _code_openid(String wx_jscode) {
                    return  fileDB.get("jscode_openid",wx_jscode);
                }

                @Override
                protected void _openid_sessionkey(String wx_openid, String wx_sessionkey) throws IOException {
                    fileDB.set("openid_sessionkey",wx_openid,wx_sessionkey);
                }

                @Override
                protected FileDB.Data _openid_sessionkey(String wx_openid) {
                    return  fileDB.get("openid_sessionkey",wx_openid);

                }



}
