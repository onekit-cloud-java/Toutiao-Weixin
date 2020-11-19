package cn.onekit.x2x.cloud.toutiao_weixin;

import cn.onekit.thekit.AJAX;
import cn.onekit.thekit.JSON;
import com.google.gson.JsonObject;
import com.qq.weixin.api.WeixinSDK;
import com.qq.weixin.api.entity.WeixinResponse;
import com.qq.weixin.api.entity.wxa__msg_sec_check_body;
import com.qq.weixin.api.entity.wxa__remove_user_storage_body;
import com.qq.weixin.api.entity.wxa__set_user_storage_body;
import com.toutiao.developer.ToutiaoAPI2;
import com.toutiao.developer.entity.Predict;
import com.toutiao.developer.entity.ToutiaoError;
import com.toutiao.developer.entity.apps__remove_user_storage_response;
import com.toutiao.developer.entity.v2.*;
import javafx.concurrent.Task;
import org.bouncycastle.util.encoders.Base64;

import java.util.ArrayList;

public abstract class ToutiaoServer2 implements ToutiaoAPI2 {

    private final String wx_appid;
    private final String wx_secret;
    WeixinSDK weixinSDK= new WeixinSDK("https://api.weixin.qq.com");
    public ToutiaoServer2(
            String wx_appid, String wx_secret) {
        this.wx_appid = wx_appid;
        this.wx_secret = wx_secret;
    }


    //////////////////////////////////////
    abstract protected void _jscode_openid(String wx_jscode, String wx_openid);
    abstract protected String _jscode_openid(String wx_jscode);
    abstract protected void _openid_sessionkey(String wx_openid, String wx_sessionkey);

    abstract protected String _openid_sessionkey(String wx_openid);

    @Override
    public tags__text__antidirt_response tags__text__antidirt(String tt_X_Token, tags__text__antidirt_body tt_body) throws ToutiaoError2 {

        //////////////
    try{
        tags__text__antidirt_response tt_response = new tags__text__antidirt_response();
        ArrayList<tags__text__antidirt_response.Data> datas = new ArrayList<>();
        for(tags__text__antidirt_body.Task task: tt_body.getTasks()) {
            wxa__msg_sec_check_body wx_body = new wxa__msg_sec_check_body();
            wx_body.setContent(task.getContent());
            WeixinResponse wx_response = weixinSDK.wxa__msg_sec_check(tt_X_Token, wx_body);
//
            tags__text__antidirt_response.Data data = new tags__text__antidirt_response.Data();
            if (wx_response.getErrcode() != 0) {
               data.setMsg("");
               data.setCode(0);
               data.setTask_id("");
               Predict predict =new Predict();
               predict.setProb(1);
               predict.setHit(true);
               predict.setTarget(null);
               predict.setModel_name("short_content_antidirt");
               ArrayList<Predict> predicts =new ArrayList<>();
               predicts.add(predict);
               data.setPredicts(predicts);
               data.setData_id(null);
            }else{
                data.setMsg("");
                data.setCode(0);
                data.setTask_id("");
                Predict predict =new Predict();
                predict.setProb(0);
                predict.setHit(false);
                predict.setTarget(null);
                predict.setModel_name("short_content_antidirt");
                ArrayList<Predict> predicts =new ArrayList<>();
                predicts.add(predict);
                data.setPredicts(predicts);
                data.setData_id(null);
            }
            datas.add(data);
        }
        tt_response.setData(datas);
        return tt_response;
    } catch (Exception e) {
        e.printStackTrace();
        ToutiaoError2 error = new ToutiaoError2();
        error.setCode(9527);
        error.setMessage(e.getMessage());
        throw error;
    }
    }

    @Override
    public tags__image_response tags__image(String tt_X_Token, tags__image_body tt_body) throws ToutiaoError2 {
    try{

        tags__image_response tt_response = new tags__image_response();
        ArrayList<tags__image_response.Data> datas = new ArrayList<>();
        for(tags__image_body.Task task: tt_body.getTasks()) {
            byte[] wx_body;
            if(task.getImage_data()!=null){
                wx_body = Base64.decode(task.getImage_data().substring(task.getImage_data().indexOf(",")+1));
            }else if(task.getImage()!=null){
                wx_body = AJAX.download(task.getImage(),"GET",null);
            }else{
                ToutiaoError toutiaoError = new ToutiaoError();
                toutiaoError.setErrcode(0);
                toutiaoError.setError(0);
                toutiaoError.setErrmsg("xxxx");
                throw toutiaoError;
            }
            WeixinResponse wx_response = weixinSDK.wxa__img_sec_check(tt_X_Token, wx_body);
            tags__image_response.Data data = new tags__image_response.Data();
            if (wx_response.getErrcode() != 0) {
                data.setMsg("");
                data.setCode(0);
                data.setTask_id("");
                Predict predict = new Predict();
                predict.setProb(1);
                predict.setHit(true);
                predict.setTarget(null);
                predict.setModel_name("short_content_antidirt");
                ArrayList<Predict> predicts = new ArrayList<>();
                predicts.add(predict);
                data.setPredicts(predicts);
                data.setData_id(null);
            } else {
                data.setMsg("");
                data.setCode(0);
                data.setTask_id("");
                Predict predict = new Predict();
                predict.setProb(0);
                predict.setHit(false);
                predict.setTarget(null);
                predict.setModel_name("short_content_antidirt");
                ArrayList<Predict> predicts = new ArrayList<>();
                predicts.add(predict);
                data.setPredicts(predicts);
                data.setData_id(null);
            }
            datas.add(data);
        }
        tt_response.setData(datas);
        return tt_response;
    } catch (Exception e) {
        e.printStackTrace();
        ToutiaoError2 error = new ToutiaoError2();
        error.setCode(9527);
        error.setMessage(e.getMessage());
        throw error;
    }
}
}
