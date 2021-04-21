package cn.onekit.x2x.cloud.toutiao_weixin;


import com.qq.weixin.api.WeixinSecuritySDK;
import com.toutiao.developer.v2.ToutiaoError2;
import com.toutiao.developer.v2.ToutiaoV2API;
import com.toutiao.developer.v2.request.ApiAppsCensorImageRequset;
import com.toutiao.developer.v2.request.ApiTagsTextAntidirtRequest;
import com.toutiao.developer.v2.response.ApiAppsCensorImageResponse;
import com.toutiao.developer.v2.response.ApiTagsTextAntidirtResponse;

public  class ToutiaoServer2 implements ToutiaoV2API{



    @SuppressWarnings({"unused", "FieldCanBeLocal"})
    private final String wx_appid;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final String wx_secret;
    private WeixinSecuritySDK weixinSDK= new WeixinSecuritySDK("https://api.weixin.qq.com");
    @SuppressWarnings("WeakerAccess")
    public ToutiaoServer2(
            String wx_appid, String wx_secret) {
        this.wx_appid = wx_appid;
        this.wx_secret = wx_secret;
    }




    /*@Override
    public apps__set_user_storage_response apps__set_user_storage(
            String tt_access_token,
            String tt_openid,
            String tt_signature,
            String tt_sig_method,
            apps__set_user_storage_body tt_body
    ) throws ToutiaoError {

        try {
            FileDB.Data wx_session_key = _openid_sessionkey(tt_openid);
            try {
                if (!_signBody(tt_sig_method, wx_session_key, JSON.object2string(tt_body)).equals(tt_signature)) {
                    throw new Exception("bad sign!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //////////////
            JsonObject body = (JsonObject) JSON.object2json(tt_body);
            wxa__set_user_storage_body wx_body = JSON.json2object(body, wxa__set_user_storage_body.class);
            String wx_signature = null;
            try {
                wx_signature = _signBody(wx_sig_method, wx_session_key, JSON.object2string(wx_body));
            } catch (Exception e) {
                e.printStackTrace();
            }
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
                    case 41001:
                        tt_error.setError(1);
                        tt_error.setErrcode(40014);
                        tt_error.setErrmsg("bad params");
                        tt_error.setMessage("bad params");
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
        } catch (ToutiaoError tt_error) {
            throw tt_error;
        }
        return new apps__set_user_storage_response();
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
            try {
                if (!_signBody(tt_sig_method, wx_session_key, JSON.object2string(tt_body)).equals(tt_signature)) {
                    throw new Exception("bad sign!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //////////////
            wxa__remove_user_storage_body wx_body = new wxa__remove_user_storage_body();
            wx_body.setKey(tt_body.getKey());
            String wx_signature = null;
            try {
                wx_signature = _signBody(wx_sig_method, wx_session_key, JSON.object2string(wx_body));
            } catch (Exception e) {
                e.printStackTrace();
            }
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

                throw   tt_error;
            }
            ////////////
            apps__remove_user_storage_response tt_response = new apps__remove_user_storage_response();
            return tt_response;
        } catch ( ToutiaoError tt_error) {
            throw tt_error;
        }
    }
*/
  /*  @Override
    public byte[] apps__qrcode(
            apps__qrcode_body tt_body
    ) throws ToutiaoError {

        try {
            final String wx_access_token = tt_body.getAccess_token();
            wxa__getwxacodeunlimit_body wx_body =new wxa__getwxacodeunlimit_body();
            wx_body.setPage(tt_body.getPath());
            wx_body.setWidth(tt_body.getWidth());
            wx_body.setScene("xxx");
            //////////////
            WeixinError wx_error = new WeixinError();
            //noinspection EqualsBetweenInconvertibleTypes
            if(!wx_error.equals(0)){
                ToutiaoError tt_error = new ToutiaoError();
                switch (wx_error.getErrcode()){
                    case -1:
                        tt_error.setErrcode(-1);
                        tt_error.setErrmsg(wx_error.getErrmsg());
                    case 42001:
                        tt_error.setErrcode(40002);
                        tt_error.setErrmsg(wx_error.getErrmsg());

                    default:
                        tt_error.setErrcode(74077);
                        tt_error.setErrmsg(wx_error.getErrmsg());
                        break;
                }
            }

            return  weixinSDK.wxa__getwxacodeunlimit(wx_access_token, wx_body);
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
            ToutiaoError tt_error = new ToutiaoError();
            switch (wx_response.getErrcode()){
                case 44002:
                    tt_error.setErrcode(40001);
                    tt_error.setErrmsg("bad body");
                    break;
                case 41001:
                    tt_error.setErrcode(40014);
                    tt_error.setErrmsg("missing access_token");
                    break;
                case 40001:
                    tt_error.setErrcode(-1);
                    tt_error.setErrmsg("internal error");
                case 41030:
                    tt_error.setErrcode(40041);
                    tt_error.setErrmsg(wx_response.getErrmsg());
                default:
                    tt_error.setErrcode(74077);
                    tt_error.setErrmsg(wx_response.getErrmsg());
            }
            throw  tt_error;
        }
        return new apps__subscribe_notification__developer__notify_response();
    }*/



    /*@Override
    public apps__game__template__send_response apps__game__template__send(
            apps__game__template__send_body tt_body
    ) throws ToutiaoError {
        try {
            Push2Push push2push = new Push2Push();
            //
            String access_token = tt_body.getAccess_token();
            subscribe__send_body wx_body = new subscribe__send_body();

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
                data.put(key2key.get(key), dataValue);
            }

            wx_body.setData(data);
            WeixinResponse wx_response = weixinSDK.cgi_bin__message__subscribe__send(access_token, wx_body);
            if (wx_response.getErrcode() != 0) {
                ToutiaoError tt_error = new ToutiaoError();
                switch (wx_response.getErrcode()) {
                    case 44002:
                        tt_error.setErrcode(40001);
                        tt_error.setErrmsg("bad body");
                        break;
                    case 41001:
                        tt_error.setErrcode(40014);
                        tt_error.setErrmsg("missing access_token");
                        break;
                    case 40003:
                        tt_error.setErrcode(40001);
                        tt_error.setErrmsg(wx_response.getErrmsg());
                        break;
                    case 40037:
                        tt_error.setErrcode(40037);
                        tt_error.setErrmsg(wx_response.getErrmsg());
                        break;
                    case 41030:
                        tt_error.setErrcode(40041);
                        tt_error.setErrmsg(wx_response.getErrmsg());
                        break;
                    default:
                        tt_error.setErrcode(74077);
                        tt_error.setErrmsg(wx_response.getErrmsg());
                        break;
                }
                throw  tt_error;
            }
            apps__game__template__send_response tt_response = new apps__game__template__send_response();
            return tt_response;
        } catch (ToutiaoError tt_error) {
            throw tt_error;

        }

    }*/


/*    //////////////////////////////////////
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
    }*/

    @Override
    public ApiTagsTextAntidirtResponse apiTagsTextAntidirt(ApiTagsTextAntidirtRequest apiTagsTextAntidirtRequest) throws ToutiaoError2 {
        return null;
    }

    @Override
    public ApiAppsCensorImageResponse apiAppsCensorImage(ApiAppsCensorImageRequset apiAppsCensorImageRequset) throws ToutiaoError2 {
        return null;
    }
}
