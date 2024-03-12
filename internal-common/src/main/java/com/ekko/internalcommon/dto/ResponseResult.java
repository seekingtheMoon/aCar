package com.ekko.internalcommon.dto;

import com.ekko.internalcommon.constant.CommonStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResponseResult<T> {


    private int code;
    private String message;
    private T data;

    /**
     * 无参成功类型
     * @return
     * @param <T>
     */
    public static <T> ResponseResult success() {
        return new ResponseResult()
                .setCode(CommonStatusEnum.SUCCESS.getCode())
                .setMessage(CommonStatusEnum.SUCCESS.getValue())
                .setData("");
    }

    /**
     * 自定义成功
     * @param data 给定类型 T
     * @return
     * @param <T>
     */
    public static <T> ResponseResult success(T data) {
        return new ResponseResult()
                .setCode(CommonStatusEnum.SUCCESS.getCode())
                .setMessage(CommonStatusEnum.SUCCESS.getValue())
                .setData(data);
    }

    /**
     * 自定义失败
     *
     * @param code    错误码
     * @param message 提示信息
     * @return
     */
    public static ResponseResult fail(int code, String message) {
        return new ResponseResult()
                .setCode(code)
                .setMessage(message);
    }

    /**
     * 自定义错误
     *
     * @param code    错误码
     * @param message 提示信息
     * @param data    具体错误
     * @return
     */
    public static ResponseResult fail(int code, String message, String data) {
        return new ResponseResult()
                .setCode(code)
                .setMessage(message)
                .setData(data);
    }

    /**
     * 统一失败
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult fail(T data) {
        return new ResponseResult()
                .setCode(CommonStatusEnum.FAIL.getCode())
                .setMessage(CommonStatusEnum.FAIL.getValue())
                .setData(data);
    }
}
