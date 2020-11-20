package cn.onekit.x2x.cloud.toutiao_weixin;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@PropertySource(value = {"classpath:subscribe2subscribe.properties"},ignoreResourceNotFound = true)
public class Subscribe2Subscribe implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    private HashMap<String,HashMap<String,String>> keys2keys = new HashMap<String,HashMap<String,String>>(){{
        put("排序变更提醒", new HashMap<String,String>(){{
            put("序号","");
            put("剩余排号","");
            put("小程序","");
            put("备注","");
        }});
        put("过号提醒", new HashMap<String,String>(){{
            put("排号信息","");
            put("小程序","");
            put("备注","");
        }});
        put("支付成功通知", new HashMap<String,String>(){{
            put("支付金额","");
            put("小程序","");
            put("支付时间","");
            put("备注","");
        }});
        put("评价提醒", new HashMap<String,String>(){{
            put("订单名称","");
            put("小程序","");
            put("备注","");
        }});
        put("发货通知", new HashMap<String,String>(){{
            put("商品名称","");
            put("小程序","");
            put("快递编号","");
            put("快递服务商","");
            put("备注","");
        }});
        put("订单状态变更通知", new HashMap<String,String>(){{
            put("订单名称","");
            put("小程序","");
            put("备注","");
        }});
        put("商家回复提醒", new HashMap<String,String>(){{
            put("回复内容","");
            put("小程序","");
            put("备注","");
        }});
        put("过期提醒", new HashMap<String,String>(){{
            put("优惠券名称","");
            put("小程序","");
            put("剩余时间","");
            put("备注","");
        }});
        put("核销提醒", new HashMap<String,String>(){{
            put("优惠券名称","");
            put("小程序","");
            put("备注","");
        }});
        put("上线通知", new HashMap<String,String>(){{
            put("预约内容","");
            put("上线时间","");
            put("小程序","");
            put("备注","");
        }});
        put("订单支付成功通知", new HashMap<String,String>(){{
            put("单号","运单号");
            put("金额","付款金额");
            put("下单时间","付款时间");
            put("物品名称",null);
            put("订单号码","订单编号");
            put("支付时间","支付时间");
            put("订单金额","费用金额");
            put("订单状态",null);
            put("订单编号",null);
            put("商品名称"," 商品名称");
            put("支付金额",null);
            put("支付方式","付款方式");
            put("订单时间",null);
            put("联系人姓名",null);
            put("联系人手机号",null);
            put("下单门店",null);
            put("商品编号",null);
            put("商家名称",null);
            put("配送时间",null);
            put("取货地址",null);
            put("收货地址","收货地址");
            put("发货时间",null);
            put("备注",null);
        }});
        put("订单支付失败通知", new HashMap<String,String>(){{
            put("失败原因","");
            put("单号","");
            put("物品名称","");
            put("下单时间","");
            put("问题描述","");
            put("付款商家","");
            put("付款单号","");
            put("付款金额","");
            put("温馨提示","");
            put("开始时间"," ");
            put("结束时间","");
            put("支付问题","");
            put("订单编号","");
            put("商品名称","");
            put("支付时间","");
            put("订单状态","");
            put("备注","");
        }});
        put("订单取消通知", new HashMap<String,String>(){{
            put("取消原因","");
            put("下单时间","");
            put("物品详情","");
            put("订单金额","");
            put("订单编号","");
            put("商品详情","");
            put("订单号","");
            put("订单退款","");
            put("状态更新","");
            put("商户名称"," ");
            put("温馨提示","");
            put("商品名称","");
            put("拼团产品","");
            put("团长","");
            put("拼团进度","");
            put("备注","");
        }});
        put("购买成功通知", new HashMap<String,String>(){{
            put("购买地点","");
            put("购买时间","");
            put("物品名称","");
            put("交易单号","");
            put("购买价格","");
            put("购买金额","");
            put("发货单号","");
            put("售后客服","");
            put("备注","");
        }});
        put("购买失败通知", new HashMap<String,String>(){{
            put("购买时间","");
            put("物品名称","");
            put("购买地点","");
            put("价格","");
            put("失败原因","");
            put("订单总价","");
            put("订单号","");
            put("备注","");
        }});
        put("退款通知", new HashMap<String,String>(){{
            put("退款金额","");
            put("退款原因","");
            put("退款时间","");
            put("商品名称","");
            put("订单编号","");
            put("退款编号","");
            put("退款方式","");
            put("退款状态","");
            put("联系电话","");
            put("客服电话","");
            put("订单号","");
            put("活动名称","");
            put("退款商家","");
            put("退款类型","");
            put("退款账户","");
            put("到账时间","");
            put("备注","");
        }});
        put("成团提醒", new HashMap<String,String>(){{
            put("交易单号","");
            put("服务名称","");
            put("备注说明","");
            put("商品名称","");
            put("团长","");
            put("成团人数","");
            put("发货时间","");
            put("自提时间","");
            put("成团时间","");
            put("温馨提示","");
            put("活动名称","");
            put("订单号","");
            put("取货店铺","");
            put("拼团产品","");
            put("拼团成员","");
        }});
        put("拼团成功通知", new HashMap<String,String>(){{
            put("团长","");
            put("成团人数","");
            put("商品名称","");
            put("商品金额","");
            put("开团时间","");
            put("成团时间","");
            put("定金","");
            put("拼团价","");
            put("订单号","");
            put("拼团成员","");
            put("发货时间","");
            put("价格","");
            put("备注","");
        }});
        put("秒杀失败通知", new HashMap<String,String>(){{
            put("商品名称","");
            put("商品金额","");
            put("秒杀时间","");
            put("备注","");
            put("订购内容","");
            put("订购时间","");
            put("产品价格","");
            put("商家名称","");
        }});
        put("取消退货通知", new HashMap<String,String>(){{
            put("退货状态","");
            put("订单编号","");
            put("商品名称","");
            put("温馨提示","");
            put("退货金额","");
            put("备注","");
        }});
        put("退货成功通知", new HashMap<String,String>(){{
            put("交易单号","");
            put("店铺名称","");
            put("退货金额","");
            put("退货时间","");
            put("商品品牌","");
            put("商品名称","");
            put("商品编码","");
            put("操作人员","");
            put("退款方式","");
            put("退货商品","");
            put("退款金额","");
            put("处理时间","");
            put("备注","");
        }});
        put("拒单通知", new HashMap<String,String>(){{
            put("退款金额","");
            put("拒单原因","");
            put("订单编号","");
            put("拒单时间","");
            put("商品名","");
            put("实付金额","");
            put("下单时间","");
            put("订单类型","");
            put("备注","");
        }});
        put("订单发货提醒", new HashMap<String,String>(){{
            put("快递公司","");
            put("发货时间","");
            put("购买时间","");
            put("物品名称","");
            put("发货平台","");
            put("订单号","");
            put("查收方式","");
            put("收货地址","");
            put("订单编号","");
            put("物流公司","");
            put("物流单号","");
            put("快递单号","");
            put("配送方式","");
            put("联系电话","");
            put("商品清单","");
            put("商品信息","");
            put("到达时间","");
            put("货物信息","");
            put("要求到达","");
            put("预计收货时间","");
            put("收货人地址","");
            put("备注","");
        }});
        put("确认收货通知", new HashMap<String,String>(){{
            put("温馨提示","");
            put("订单编号","");
            put("商品详情","");
            put("商家电话","");
            put("买家昵称","");
            put("订单状态","");
            put("商品名称","");
            put("确认收货时间","");
            put("商品清单","");
            put("订单金额","");
            put("发货时间","");
            put("订单时间","");
            put("订单内容","");
            put("收货人","");
            put("收货人电话","");
            put("收货门店","");
            put("送达时间","");
            put("备注","");
        }});
        put("推迟发货通知", new HashMap<String,String>(){{
            put("订单编号","");
            put("商品名称","");
            put("原发货时间","");
            put("预计发货时间","");
            put("推迟原因","");
            put("备注","");
        }});
        put("货物发出通知", new HashMap<String,String>(){{
            put("发货人","");
            put("货品","");
            put("件数","");
            put("发出时间","");
            put("单号","");
            put("备注","");
        }});
        put("退货申请通知", new HashMap<String,String>(){{
            put("温馨提示","");
            put("买家昵称","");
            put("订单编号","");
            put("商品详情","");
            put("金额","");
            put("申请时间","");
            put("退货状态","");
            put("商品名称","");
            put("退款说明","");
            put("退货地址","");
            put("商家名称","");
            put("退货金额","");
            put("退货单号","");
            put("申请单号","");
            put("审核状态","");
            put("退货原因","");
            put("门店","");
            put("预订手机","");
            put("客户昵称","");
            put("退货商品","");
            put("退货件数","");
            put("退货时间","");
            put("备注","");
        }});
        put("退款失败通知", new HashMap<String,String>(){{
            put("订单号","");
            put("商品名称","");
            put("退款金额","");
            put("失败原因","");
            put("卖家手机","");
            put("备注","");
            put("失败时间","");
            put("退款单号","");
            put("温馨提示","");
            put("商家名称","");
            put("商户名","");
            put("实付金额","");
            put("下单时间","");
            put("服务项目","");
            put("到货时间","");
            put("物流公司","");
            put("审核时间","");
            put("订单金额","");
            put("客服电话","");
            put("商品价格","");
            put("退款时间","");
            put("门店名称","");
            put("原订单号","");
            put("申请退款时间","");
            put("订单信息","");
        }});
        put("退货确认提醒", new HashMap<String,String>(){{
            put("退货单号","");
            put("退货商品","");
            put("退款金额","");
            put("温馨提示","");
            put("备注","");
        }});
        put("退款成功通知", new HashMap<String,String>(){{
            put("应退金额","");
            put("温馨提示","");
            put("订单号","");
            put("商户名称","");
            put("商品名称","");
            put("退款时间","");
            put("退款说明","");
            put("退款金额","");
            put("备注","");
            put("退费原因","");
            put("到款时间","");
            put("退款单号","");
            put("退款方式","");
        }});
        put("退货审核通知", new HashMap<String,String>(){{
            put("审核结果","");
            put("商品信息","");
            put("退货金额","");
            put("审核说明","");
            put("审核时间","");
            put("售后单号","");
            put("商品名称","");
            put("退货状态","");
            put("退货说明","");
            put("订单号","");
            put("申请单号","");
            put("服务单号","");
            put("下单时间","");
            put("订单金额","");
            put("备注","");
        }});
        put("催货提醒", new HashMap<String,String>(){{
            put("订单号","");
            put("订单详情","");
            put("催货人","");
            put("催货时间","");
            put("发货地址","");
        }});
        put("补单提醒", new HashMap<String,String>(){{
            put("货号","");
            put("尺码","");
            put("数量","");
            put("订单号","");
            put("店铺","");
            put("补单号","");
            put("补单额度","");
            put("补单描述","");
            put("退货说明","");
            put("补单确认码","");
            put("服务地址","");
            put("服务项目","");
            put("交易金额","");
            put("备注","");
        }});
        put("订单待发货提醒", new HashMap<String,String>(){{
            put("订单号","");
            put("订单金额","");
            put("买家","");
            put("订单状态","");
            put("商品名称","");
            put("联系人","");
            put("电话","");
            put("地址","");
            put("下单时间","");
            put("支付时间","");
            put("顾客姓名","");
            put("订单数","");
            put("收货人","");
            put("收货地址","");
            put("收货号码","");
            put("门店","");
            put("要求到货时间","");
            put("学校","");
            put("宿舍地址","");
            put("联系电话","");
            put("有效期限","");
            put("回收总价","");
            put("鉴别成色","");
            put("商品信息","");
            put("商品状况","");
            put("奖品名称","");
            put("中奖用户","");
            put("发货提醒","");
            put("备注","");
        }});
        put("店铺违规处理通知", new HashMap<String,String>(){{
            put("店铺","");
            put("违规描述","");
            put("处理结果","");
            put("温馨提示","");
        }});
        put("投诉处理提醒", new HashMap<String,String>(){{
            put("店铺名称","");
            put("预约时间","");
            put("备注","");
            put("投诉单号","");
            put("投诉主体","");
            put("投诉内容","");
            put("投诉时间","");
            put("投诉状态","");
            put("订单编号","");
            put("投诉进度","");
            put("反馈结果","");
            put("处理时间","");
            put("拍品名称","");
        }});
        put("订单仲裁通知", new HashMap<String,String>(){{
            put("订单编号","");
            put("退款进度","");
            put("店家回复","");
            put("投诉原因","");
            put("仲裁结果","");
        }});
        put("收到投诉提醒", new HashMap<String,String>(){{
            put("温馨提示","");
            put("投诉原因","");
            put("投诉人","");
            put("投诉时间","");
            put("拍品名称","");
            put("投诉单号","");
            put("备注","");
        }});
        put("投诉建议提醒", new HashMap<String,String>(){{
            put("投诉单号","");
            put("投诉时间","");
            put("客户姓名","");
            put("手机号码","");
            put("投诉内容","");
            put("订单号","");
            put("备注","");
        }});
        put("客户投诉通知", new HashMap<String,String>(){{
            put("投诉单号","");
            put("投诉时间","");
            put("客户姓名","");
            put("手机号码","");
            put("投诉内容","");
            put("备注","");
        }});
        put("填写退货单号通知", new HashMap<String,String>(){{
            put("订单编号","");
            put("商品名称","");
            put("退款说明","");
            put("备注","");
        }});
        put("填写退货信息通知", new HashMap<String,String>(){{
            put("退款金额","");
            put("温馨提示","");
            put("同意时间","");
            put("商品信息","");
            put("退货地址","");
            put("收货人","");
            put("联系电话","");
            put("订单编号","");
            put("申请时间","");
            put("退货单号","");
            put("退款类型","");
            put("售后类型","");
            put("备注","");
        }});
        put("发货成功通知", new HashMap<String,String>(){{
            put("收货人","");
            put("品名","");
            put("件数","");
            put("发货时间","");
            put("项目名称","");
            put("送货单号","");
            put("车辆信息","");
            put("货物详情","");
            put("预计送达","");
            put("单号","");
            put("订单编号","");
            put("商品名称","");
            put("收货地址","");
            put("订单状态","");
            put("订单金额","");
            put("取货地点","");
            put("下单人","");
            put("联系电话","");
            put("送货地址","");
            put("下单时间","");
            put("物流单号","");
            put("货运行","");
            put("货运公司电话","");
            put("快递公司","");
            put("店铺名称","");
            put("卖家留言","");
            put("商家留言","");
            put("备注","");
        }});
        put("商品信息上传成功通知", new HashMap<String,String>(){{
            put("卖家信息","");
            put("商品简介","");
            put("上传时间","");
            put("姓名","");
            put("代码","");
            put("商品名称","");
            put("单价","");
            put("图片","");
            put("手机","");
            put("产地","");
            put("原价","");
            put("拼团价","");
        }});
        put("退货提醒", new HashMap<String,String>(){{
            put("订单编号","");
            put("备注","");
            put("源订单号","");
            put("商品名称","");
            put("订单状态","");
            put("订单总额","");
            put("退货人","");
            put("退货时间","");
            put("商品金额","");
            put("退款方式","");
            put("退货商品","");
            put("退款金额","");
            put("处理时间","");
        }});
        put("取餐提醒", new HashMap<String,String>(){{
            put("餐品名称","");
            put("小程序名","");
            put("备注","");
        }});
    }};

    public  HashMap<String,String> id2keys(String tt_template_id){
        String tt_template_name = applicationContext.getEnvironment().getProperty(String.format("%s_name",tt_template_id));
    return keys2keys.get(tt_template_name);
    }

    public  String id2id(String tt_template_id){
        return applicationContext.getEnvironment().getProperty(tt_template_id);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Subscribe2Subscribe.applicationContext =applicationContext;
    }
}
