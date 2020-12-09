package cn.onekit.x2x.cloud.toutiao_weixin;

import cn.onekit.thekit.AJAX;
import cn.onekit.thekit.FileDB;
import com.qq.weixin.api.WeixinSDK;
import com.qq.weixin.api.entity.WeixinResponse;
import com.qq.weixin.api.entity.wxa__msg_sec_check_body;
import com.toutiao.developer.ToutiaoAPI2;
import com.toutiao.developer.entity.Predict;
import com.toutiao.developer.entity.v2.*;
import org.bouncycastle.util.encoders.Base64;

import java.util.ArrayList;

public abstract class ToutiaoServer2 implements ToutiaoAPI2 {



    @SuppressWarnings({"unused", "FieldCanBeLocal"})
    private final String wx_appid;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final String wx_secret;
    private WeixinSDK weixinSDK= new WeixinSDK("https://api.weixin.qq.com");
    @SuppressWarnings("WeakerAccess")
    public ToutiaoServer2(
            String wx_appid, String wx_secret) {
        this.wx_appid = wx_appid;
        this.wx_secret = wx_secret;
    }


    //////////////////////////////////////
    @SuppressWarnings("unused")
    abstract protected void _jscode_openid(String wx_jscode, String wx_openid);
    @SuppressWarnings("unused")
    abstract protected FileDB.Data _jscode_openid(String wx_jscode);
    @SuppressWarnings("unused")
    abstract protected void _openid_sessionkey(String wx_openid, String wx_sessionkey);

    @SuppressWarnings("unused")
    abstract protected FileDB.Data _openid_sessionkey(String wx_openid);


    @SuppressWarnings("DuplicatedCode")
    @Override
    public tags__text__antidirt_response tags__text__antidirt(String tt_X_Token, tags__text__antidirt_body tt_body) throws ToutiaoError2 {

        //////////////
        //noinspection CaughtExceptionImmediatelyRethrown
        try{
        tags__text__antidirt_response tt_response = new tags__text__antidirt_response();
        ArrayList<tags__text__antidirt_response.Data> datas = new ArrayList<>();
        for(tags__text__antidirt_body.Task task: tt_body.getTasks()) {
            wxa__msg_sec_check_body wx_body = new wxa__msg_sec_check_body();
            wx_body.setContent(task.getContent());
            WeixinResponse wx_response = weixinSDK.wxa__msg_sec_check(tt_X_Token, wx_body);


            _checkError(wx_response);

            tags__text__antidirt_response.Data data = new tags__text__antidirt_response.Data();

               data.setMsg("");
               data.setCode(0);
               data.setTask_id("");
               Predict predict =new Predict();
            if (wx_response.getErrcode() == 87014) {
                predict.setProb(1);
                predict.setHit(true);
            }else {
                predict.setProb(0);
                predict.setHit(false);
            }
               predict.setTarget(null);
               predict.setModel_name("short_content_antidirt");
               ArrayList<Predict> predicts =new ArrayList<>();
               predicts.add(predict);
               data.setPredicts(predicts);
               data.setData_id(null);

            datas.add(data);
        }
        tt_response.setData(datas);
        return tt_response;
    } catch (ToutiaoError2 tt_error) {
        throw tt_error;
    }
    }


    @SuppressWarnings("DuplicatedCode")
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
                ToutiaoError2 tt_error = new ToutiaoError2();
                tt_error.setError_id("0");
                tt_error.setCode(0);
                tt_error.setMessage("xxx");
                tt_error.setException("xxx");
                throw tt_error;
            }
            WeixinResponse wx_response = weixinSDK.wxa__img_sec_check(tt_X_Token, wx_body);


            _checkError(wx_response);

            tags__image_response.Data data = new tags__image_response.Data();
            data.setMsg("");
            data.setCode(0);
            data.setTask_id("");
            Predict predict = new Predict();
            if (wx_response.getErrcode() == 87014) {
                predict.setProb(1);
                predict.setHit(true);
            } else {
                predict.setProb(0);
                predict.setHit(false);
            }
            predict.setTarget(null);
            predict.setModel_name("short_content_antidirt");
            ArrayList<Predict> predicts = new ArrayList<>();
            predicts.add(predict);
            data.setPredicts(predicts);
            data.setData_id(null);
            datas.add(data);
        }
        tt_response.setData(datas);
        return tt_response;
    } catch (ToutiaoError2 tt_error) {

        throw tt_error;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
    }

    private void _checkError(WeixinResponse wx_response) throws ToutiaoError2 {
        if (wx_response.getErrcode() != 0 && wx_response.getErrcode() != 87014) {
            ToutiaoError2 tt_error = new ToutiaoError2();
            switch (wx_response.getErrcode()){
                case 40001:
                    tt_error.setError_id("1");
                    tt_error.setCode(401);
                    tt_error.setMessage("[app token sign fail] bad token");
                    tt_error.setException("[app token sign fail] bad token");
                    break;
                case 44002:
                    tt_error.setError_id("1");
                    tt_error.setCode(400);
                    tt_error.setMessage("'tasks' is a required property");
                    tt_error.setException("'tasks' is a required property");
                    break;
                default:
                    tt_error.setError_id("74077");
                    tt_error.setCode(74077);
                    tt_error.setMessage(wx_response.getErrmsg());
                    tt_error.setMessage(wx_response.getErrmsg());
                    break;
            }
            throw tt_error;


        }
    }
}
