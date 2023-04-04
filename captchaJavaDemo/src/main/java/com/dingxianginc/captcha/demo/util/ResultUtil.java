package com.dingxianginc.captcha.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.HtmlUtils;

import java.util.regex.Pattern;

public class ResultUtil {

    private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    private static final String REG_EX = "[`~!@#$%^&*()+=|{}':;,\\[\\].<>/?￥（）—【】‘；：”“’。，、？]";
    private static final Pattern COMPILE = Pattern.compile(REG_EX);

    /**
     * 根据请求参数和响应结果构造 {@link ApiResult}
     *
     * @param data 返回的结果
     * @return
     */
    public static Object getSuccessResult(Object data) {
        return getSuccessResult(data, null);
    }

    public static Object getSuccessResult(Object data, CallbackParam param) {
        return getSuccessResultWithDateFormat(data, param, null);
    }

    public static Object getSuccessResultWithDateFormat(Object data, CallbackParam param, String format) {
        CallbackParam paramParse = judgeCallBackParam(param);
        String apiResult = returnJSONString(ApiResult.getSuccess(data), format);
        // 返回JSONP格式
        if (paramParse != null && StringUtils.isNotBlank(paramParse.getCallback())) {
            return returnJSONPString(paramParse, apiResult);
        }
        return apiResult;
    }


    private static String returnJSONString(ApiResult result, String format) {
        if (StringUtils.isBlank(format)) {
            return JSON.toJSONStringWithDateFormat(result, YYYY_MM_DD_HH_MM_SS,
                    SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect);
        } else {
            return JSON.toJSONStringWithDateFormat(result, format,
                    SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect);
        }
    }

    private static Object returnJSONPString(CallbackParam param, String jsonString) {
        StringBuilder sb = new StringBuilder(64);
        if (param != null && StringUtils.isNotBlank(param.getScriptWrapping())) {
            sb.append("<script>");
            if (StringUtils.isNotBlank(param.getCallback())) {
                sb.append(param.getCallback()).append("(").append(jsonString).append(")");
            } else {
                sb.append(jsonString);
            }
            sb.append("</script>");
            return sb.toString();
        }
        if (param != null && StringUtils.isNotBlank(param.getCallback())) {
            sb.append(param.getCallback()).append("(").append(jsonString).append(")");
            return sb.toString();
        }
        return sb;
    }

    /**
     * 根据请求参数和响应结果构造 {@link ApiResult}
     *
     * @param msg 错误描述
     * @return
     */
    public static Object getFailedResult(String msg) {
        return getFailedResult(msg, null);
    }

    public static Object getFailedResult(String msg, CallbackParam param) {
        return getFailedResult(msg, null, param);
    }

    public static Object getFailedResult(String msg, String errorCode, CallbackParam param) {
        CallbackParam paramParse = judgeCallBackParam(param);
        String apiResult = returnJSONString(ApiResult.getFailed(msg), null);
        // 返回JSONP格式
        if (paramParse != null && StringUtils.isNotBlank(paramParse.getCallback())) {
            return returnJSONPString(paramParse, apiResult);
        }
        return apiResult;
    }

    /**
     * 对返回参数进行转换
     * @param param
     */
    private static CallbackParam judgeCallBackParam(CallbackParam param) {
        if (param != null){
            if (param.getCallback() != null) {
                param.setCallback(HtmlUtils.htmlEscape(param.getCallback()));
            }
            if (param.getScriptWrapping() != null) {
                param.setScriptWrapping(HtmlUtils.htmlEscape(param.getScriptWrapping()));
            }
        }
        return param;
    }


}
