package com.sh.api.common.util;

import com.sh.api.common.constant.OauthTwoConstant;
import com.sh.api.common.constant.ResponseInfoBodyConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 响应信息主体
 *
 *
 * @author 盛浩
 * @date 2020/12/25 20:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "R", description = "响应信息主体")
public class R<T> {

    /**
     * 状态码：0 成功 1 失败
     */
    @ApiModelProperty(value = "状态码：0 成功 1 失败")
    private Integer code;

    /**
     * 数据
     */
    @ApiModelProperty(value = "数据")
    private T data;

    /**
     * 返回信息
     */
    @ApiModelProperty(value = "返回信息")
    private String msg;

    public static <T> R<T> ok() {
        return restResult(ResponseInfoBodyConstants.ForegroundPrompt.CODE_SUCCESS, null,
                ResponseInfoBodyConstants.ForegroundPrompt.MSG_SUCCESS);
    }

    public static <T> R<T> ok(T data) {
        return restResult(ResponseInfoBodyConstants.ForegroundPrompt.CODE_SUCCESS, data,
                ResponseInfoBodyConstants.ForegroundPrompt.MSG_SUCCESS);
    }

    public static <T> R<T> ok(String msg, T data) {
        return restResult(ResponseInfoBodyConstants.ForegroundPrompt.CODE_SUCCESS, data, msg);
    }

    public static <T> R<T> failed() {
        return restResult(ResponseInfoBodyConstants.ForegroundPrompt.CODE_FAIL, null,
                ResponseInfoBodyConstants.ForegroundPrompt.MSG_FAILURE);
    }

    public static <T> R<T> failed(String msg) {
        return restResult(ResponseInfoBodyConstants.ForegroundPrompt.CODE_FAIL,null, msg);
    }

    public static <T> R<T> forbidden() {
        return restResult(ResponseInfoBodyConstants.ForegroundPrompt.CODE_FAIL, null,
                OauthTwoConstant.ForegroundPrompt.MSG_NO_LOGIN_OR_TOKEN_HAS_EXPIRED);
    }

    public static <T> R<T> unauthorized() {
        return restResult(ResponseInfoBodyConstants.ForegroundPrompt.CODE_FAIL, null,
                OauthTwoConstant.ForegroundPrompt.MSG_NO_ACCESS_RIGHTS);
    }

    private static <T> R<T> restResult(Integer code, T data, String msg) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }
}
