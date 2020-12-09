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
            put("序号","排队号码");
            put("剩余排号","等候人数");
            put("小程序",null);
            put("备注","备注");
        }});
        put("过号提醒", new HashMap<String,String>(){{
            put("排号信息",null);
            put("小程序",null);
            put("备注","备注");
        }});
        put("支付成功通知", new HashMap<String,String>(){{
            put("支付金额","支付金额");
            put("小程序","商品名称");
            put("支付时间","支付时间");
            put("备注",null);
        }});
        put("评价提醒", new HashMap<String,String>(){{
            put("订单名称",null);
            put("小程序",null);
            put("备注","备注");
        }});
        put("发货通知", new HashMap<String,String>(){{
            put("商品名称","商品信息");
            put("小程序",null);
            put("快递编号","订单编号");
            put("快递服务商","快递公司");
            put("备注","备注");
        }});
        put("订单状态变更通知", new HashMap<String,String>(){{
            put("订单名称","订单标题");
            put("小程序",null);
            put("备注",null);
        }});
        put("商家回复提醒", new HashMap<String,String>(){{
            put("回复内容","回复内容");
            put("小程序",null);
            put("备注","备注");
        }});
        put("过期提醒", new HashMap<String,String>(){{
            put("优惠券名称","券名称");
            put("小程序",null);
            put("剩余时间",null);
            put("备注",null);
        }});
        put("核销提醒", new HashMap<String,String>(){{
            put("优惠券名称","券名称");
            put("小程序",null);
            put("备注",null);
        }});
        put("上线通知", new HashMap<String,String>(){{
            put("预约内容","名称");
            put("上线时间",null);
            put("小程序",null);
            put("备注","备注");
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
            put("失败原因","原因");
            put("单号","订单号");
            put("物品名称",null);
            put("下单时间","时间");
            put("问题描述","原因");
            put("付款商家",null);
            put("付款单号",null);
            put("付款金额",null);
            put("温馨提示",null);
            put("开始时间",null);
            put("结束时间",null);
            put("支付问题",null);
            put("订单编号",null);
            put("商品名称",null);
            put("支付时间",null);
            put("订单状态",null);
            put("备注",null);
        }});
        put("订单取消通知", new HashMap<String,String>(){{
            put("取消原因","原因");
            put("下单时间","时间");
            put("物品详情",null);
            put("订单金额",null);
            put("订单编号",null);
            put("商品详情",null);
            put("订单号","订单号");
            put("订单退款",null);
            put("状态更新",null);
            put("商户名称",null);
            put("温馨提示",null);
            put("商品名称",null);
            put("拼团产品",null);
            put("团长",null);
            put("拼团进度",null);
            put("备注",null);
        }});
        put("购买成功通知", new HashMap<String,String>(){{
            put("购买地点",null);
            put("购买时间","下单时间");
            put("物品名称","商品名称");
            put("交易单号","会员编号");
            put("购买价格",null);
            put("购买金额","支付金额");
            put("发货单号",null);
            put("售后客服",null);
            put("备注",null);
        }});
        put("购买失败通知", new HashMap<String,String>(){{
            put("购买时间","下单时间");
            put("物品名称","商品名称");
            put("购买地点",null);
            put("价格","支付金额");
            put("失败原因",null);
            put("订单总价",null);
            put("订单号","会员编号");
            put("备注",null);
        }});
        put("退款通知", new HashMap<String,String>(){{
            put("退款金额","支付金额");
            put("退款原因",null);
            put("退款时间","下单时间");
            put("商品名称","商品名称");
            put("订单编号",null);
            put("退款编号",null);
            put("退款方式","退款方式");
            put("退款状态",null);
            put("联系电话",null);
            put("客服电话",null);
            put("订单号","订单号");
            put("活动名称",null);
            put("退款商家",null);
            put("退款类型",null);
            put("退款账户",null);
            put("到账时间","退款时间");
            put("备注",null);
        }});
        put("成团提醒", new HashMap<String,String>(){{
            put("交易单号",null);
            put("服务名称",null);
            put("备注说明","备注");
            put("商品名称","商品名称");
            put("团长","团长昵称");
            put("成团人数","参团人数");
            put("发货时间",null);
            put("自提时间",null);
            put("成团时间","开团时间");
            put("温馨提示",null);
            put("活动名称","团购名称");
            put("订单号",null);
            put("取货店铺",null);
            put("拼团产品",null);
            put("拼团成员",null);
        }});
        put("拼团成功通知", new HashMap<String,String>(){{
            put("团长","团长");
            put("成团人数","参团人数");
            put("商品名称","商品名称");
            put("商品金额",null);
            put("开团时间","开团时间");
            put("成团时间","拼团时间");
            put("定金",null);
            put("拼团价","拼团价格");
            put("订单号","订单编号");
            put("拼团成员","拼团成员");
            put("发货时间",null);
            put("价格",null);
            put("备注","备注");
        }});
        put("秒杀失败通知", new HashMap<String,String>(){{
            put("商品名称","拼团商品");
            put("商品金额",null);
            put("秒杀时间",null);
            put("备注","备注");
            put("订购内容",null);
            put("订购时间",null);
            put("产品价格",null);
            put("商家名称","活动名称");
        }});
        put("取消退货通知", new HashMap<String,String>(){{
            put("退货状态","订单状态");
            put("订单编号","订单号");
            put("商品名称",null);
            put("温馨提示",null);
            put("退货金额",null);
            put("备注",null);
        }});
        put("退货成功通知", new HashMap<String,String>(){{
            put("交易单号","订单号");
            put("店铺名称",null);
            put("退货金额","支付金额");
            put("退货时间","退还时间");
            put("商品品牌",null);
            put("商品名称","商品名称");
            put("商品编码",null);
            put("操作人员",null);
            put("退款方式","退款方式");
            put("退货商品",null);
            put("退款金额",null);
            put("处理时间",null);
            put("备注",null);
        }});
        put("拒单通知", new HashMap<String,String>(){{
            put("退款金额",null);
            put("拒单原因",null);
            put("订单编号","订单编号");
            put("拒单时间","拒绝时间");
            put("商品名",null);
            put("实付金额",null);
            put("下单时间",null);
            put("订单类型",null);
            put("备注",null);
        }});
        put("订单发货提醒", new HashMap<String,String>(){{
            put("快递公司","快递公司");
            put("发货时间","发货时间");
            put("购买时间",null);
            put("物品名称","物品名称");
            put("发货平台",null);
            put("订单号","订单号");
            put("查收方式",null);
            put("收货地址","收货地址");
            put("订单编号","订单编号");
            put("物流公司",null);
            put("物流单号",null);
            put("快递单号","快递单号");
            put("配送方式",null);
            put("联系电话",null);
            put("商品清单",null);
            put("商品信息",null);
            put("到达时间",null);
            put("货物信息","收件信息");
            put("要求到达",null);
            put("预计收货时间",null);
            put("收货人地址","收货地址");
            put("备注","备注");
        }});
        put("确认收货通知", new HashMap<String,String>(){{
            put("温馨提示",null);
            put("订单编号",null);
            put("商品详情","商品详情");
            put("商家电话",null);
            put("买家昵称",null);
            put("订单状态",null);
            put("商品名称",null);
            put("确认收货时间","确认收货时间");
            put("商品清单",null);
            put("订单金额","订单金额");
            put("发货时间",null);
            put("订单时间",null);
            put("订单内容",null);
            put("收货人","收货人");
            put("收货人电话",null);
            put("收货门店",null);
            put("送达时间",null);
            put("备注",null);
        }});
        put("推迟发货通知", new HashMap<String,String>(){{
            put("订单编号",null);
            put("商品名称",null);
            put("原发货时间",null);
            put("预计发货时间",null);
            put("推迟原因",null);
            put("备注",null);
        }});
        put("货物发出通知", new HashMap<String,String>(){{
            put("发货人","寄件客户");
            put("货品",null);
            put("件数",null);
            put("发出时间","发出时间");
            put("单号","快递单号");
            put("备注",null);
        }});
        put("退货申请通知", new HashMap<String,String>(){{
            put("温馨提示","温馨提示");
            put("买家昵称",null);
            put("订单编号","订单编号");
            put("商品详情",null);
            put("金额",null);
            put("申请时间","申请时间");
            put("退货状态",null);
            put("商品名称",null);
            put("退款说明",null);
            put("退货地址",null);
            put("商家名称",null);
            put("退货金额",null);
            put("退货单号",null);
            put("申请单号",null);
            put("审核状态",null);
            put("退货原因","取消原因");
            put("门店",null);
            put("预订手机",null);
            put("客户昵称",null);
            put("退货商品",null);
            put("退货件数",null);
            put("退货时间",null);
            put("备注",null);
        }});
        put("退款失败通知", new HashMap<String,String>(){{
            put("订单号","订单号");
            put("商品名称","商品名称");
            put("退款金额","金额");
            put("失败原因","驳回原因");
            put("卖家手机",null);
            put("备注",null);
            put("失败时间","驳回时间");
            put("退款单号",null);
            put("温馨提示",null);
            put("商家名称",null);
            put("商户名",null);
            put("实付金额",null);
            put("下单时间",null);
            put("服务项目",null);
            put("到货时间",null);
            put("物流公司",null);
            put("审核时间",null);
            put("订单金额",null);
            put("客服电话",null);
            put("商品价格",null);
            put("退款时间",null);
            put("门店名称",null);
            put("原订单号",null);
            put("申请退款时间",null);
            put("订单信息",null);
        }});
        put("退货确认提醒", new HashMap<String,String>(){{
            put("退货单号","订单号");
            put("退货商品",null);
            put("退款金额",null);
            put("温馨提示",null);
            put("备注","备注");
        }});
        put("退款成功通知", new HashMap<String,String>(){{
            put("应退金额","支付金额");
            put("温馨提示","温馨提示");
            put("订单号","订单号");
            put("商户名称",null);
            put("商品名称","商品名称");
            put("退款时间","退款时间");
            put("退款说明",null);
            put("退款金额",null);
            put("备注",null);
            put("退费原因",null);
            put("到款时间",null);
            put("退款单号",null);
            put("退款方式","退款方式");
        }});
        put("退货审核通知", new HashMap<String,String>(){{
            put("审核结果","审核结果");
            put("商品信息",null);
            put("退货金额",null);
            put("审核说明",null);
            put("审核时间","审核时间");
            put("售后单号",null);
            put("商品名称",null);
            put("退货状态",null);
            put("退货说明",null);
            put("订单号",null);
            put("申请单号",null);
            put("服务单号",null);
            put("下单时间",null);
            put("订单金额",null);
            put("备注","备注");
        }});
        put("催货提醒", new HashMap<String,String>(){{
            put("订单号",null);
            put("订单详情",null);
            put("催货人",null);
            put("催货时间",null);
            put("发货地址",null);
        }});
        put("补单提醒", new HashMap<String,String>(){{
            put("货号",null);
            put("尺码",null);
            put("数量",null);
            put("订单号","订单号");
            put("店铺",null);
            put("补单号",null);
            put("补单额度",null);
            put("补单描述","订单状态");
            put("退货说明",null);
            put("补单确认码",null);
            put("服务地址",null);
            put("服务项目",null);
            put("交易金额",null);
            put("备注","备注");
        }});
        put("订单待发货提醒", new HashMap<String,String>(){{
            put("订单号","订单编号");
            put("订单金额",null);
            put("买家",null);
            put("订单状态",null);
            put("商品名称","商品信息");
            put("联系人",null);
            put("电话",null);
            put("地址",null);
            put("下单时间","下单时间");
            put("支付时间",null);
            put("顾客姓名",null);
            put("订单数",null);
            put("收货人",null);
            put("收货地址","收货地址");
            put("收货号码",null);
            put("门店",null);
            put("要求到货时间",null);
            put("学校",null);
            put("宿舍地址",null);
            put("联系电话",null);
            put("有效期限",null);
            put("回收总价",null);
            put("鉴别成色",null);
            put("商品信息",null);
            put("商品状况",null);
            put("奖品名称",null);
            put("中奖用户",null);
            put("发货提醒",null);
            put("备注","备注");
        }});
        put("店铺违规处理通知", new HashMap<String,String>(){{
            put("店铺",null);
            put("违规描述",null);
            put("处理结果",null);
            put("温馨提示",null);
        }});
        put("投诉处理提醒", new HashMap<String,String>(){{
            put("店铺名称",null);
            put("预约时间",null);
            put("备注",null);
            put("投诉单号",null);
            put("投诉主体",null);
            put("投诉内容","反馈内容");
            put("投诉时间","申请时间");
            put("投诉状态",null);
            put("订单编号",null);
            put("投诉进度",null);
            put("反馈结果",null);
            put("处理时间",null);
            put("拍品名称",null);
        }});
        put("订单仲裁通知", new HashMap<String,String>(){{
            put("订单编号","订单号");
            put("退款进度",null);
            put("店家回复",null);
            put("投诉原因",null);
            put("仲裁结果",null);
        }});
        put("收到投诉提醒", new HashMap<String,String>(){{
            put("温馨提示","温馨提示");
            put("投诉原因",null);
            put("投诉人",null);
            put("投诉时间","更新时间");
            put("拍品名称",null);
            put("投诉单号",null);
            put("备注",null);
        }});
        put("投诉建议提醒", new HashMap<String,String>(){{
            put("投诉单号",null);
            put("投诉时间","更新时间");
            put("客户姓名",null);
            put("手机号码",null);
            put("投诉内容",null);
            put("订单号",null);
            put("备注",null);
        }});
        put("客户投诉通知", new HashMap<String,String>(){{
            put("投诉单号",null);
            put("投诉时间",null);
            put("客户姓名",null);
            put("手机号码",null);
            put("投诉内容",null);
            put("备注",null);
        }});
        put("填写退货单号通知", new HashMap<String,String>(){{
            put("订单编号",null);
            put("商品名称",null);
            put("退款说明",null);
            put("备注",null);
        }});
        put("填写退货信息通知", new HashMap<String,String>(){{
            put("退款金额",null);
            put("温馨提示",null);
            put("同意时间",null);
            put("商品信息",null);
            put("退货地址",null);
            put("收货人",null);
            put("联系电话",null);
            put("订单编号","订单号");
            put("申请时间",null);
            put("退货单号",null);
            put("退款类型",null);
            put("售后类型",null);
            put("备注","备注");
        }});
        put("发货成功通知", new HashMap<String,String>(){{
            put("收货人",null);
            put("品名",null);
            put("件数",null);
            put("发货时间",null);
            put("项目名称",null);
            put("送货单号",null);
            put("车辆信息",null);
            put("货物详情",null);
            put("预计送达",null);
            put("单号",null);
            put("订单编号","订单编号");
            put("商品名称",null);
            put("收货地址","收货地址");
            put("订单状态",null);
            put("订单金额","订单金额");
            put("取货地点",null);
            put("下单人",null);
            put("联系电话",null);
            put("送货地址",null);
            put("下单时间","下单时间");
            put("物流单号","运单号");
            put("货运行",null);
            put("货运公司电话",null);
            put("快递公司","快递公司");
            put("店铺名称",null);
            put("卖家留言",null);
            put("商家留言",null);
            put("备注","备注");
        }});
        put("商品信息上传成功通知", new HashMap<String,String>(){{
            put("卖家信息",null);
            put("商品简介",null);
            put("上传时间",null);
            put("姓名",null);
            put("代码",null);
            put("商品名称",null);
            put("单价",null);
            put("图片",null);
            put("手机",null);
            put("产地",null);
            put("原价",null);
            put("拼团价",null);
        }});
        put("退货提醒", new HashMap<String,String>(){{
            put("订单编号","订单号");
            put("备注","备注");
            put("源订单号",null);
            put("商品名称",null);
            put("订单状态","订单状态");
            put("订单总额",null);
            put("退货人",null);
            put("退货时间",null);
            put("商品金额",null);
            put("退款方式",null);
            put("退货商品",null);
            put("退款金额",null);
            put("处理时间",null);
        }});
        put("取餐提醒", new HashMap<String,String>(){{
            put("餐品名称",null);
            put("小程序名",null);
            put("备注",null);
        }});
    }};

    @SuppressWarnings("WeakerAccess")
    public  HashMap<String,String> id2keys(String tt_template_id){
        String tt_template_name = applicationContext.getEnvironment().getProperty(String.format("%s_name",tt_template_id));
    return keys2keys.get(tt_template_name);
    }

    @SuppressWarnings("WeakerAccess")
    public  String id2id(String tt_template_id){
        return applicationContext.getEnvironment().getProperty(tt_template_id);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Subscribe2Subscribe.applicationContext =applicationContext;
    }
}
