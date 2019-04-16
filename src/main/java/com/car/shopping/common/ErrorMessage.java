package com.car.shopping.common;



/**
 * @Author: 72038667
 * @Date: 2019/3/16 14:20
 * @Version: 1.0
 */
public enum  ErrorMessage {
    /** 操作成功 */
    SUCCESS("0", "操作成功"),

    /** 操作失败 */
    FAIL("5000", "操作失败"),

    /** 空指针异常 */
    NullpointerException("5001", "空指针异常"),

    /** 自定义异常之返回值为空 */
    NullResponseException("5002", "自定义异常之返回值为空"),


    /** 自定义异常之验证码错误 */
    VCODEEXCEPTION("5003", "验证码错误"),


    /** 自定义异常之用户不存在 */
    USER_NOT_EXIST("5004", "用户不存在");

    ErrorMessage(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String code;
    private  String desc;
}
