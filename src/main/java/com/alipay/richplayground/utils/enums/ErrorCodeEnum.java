/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.alipay.richplayground.utils.enums;

import com.alipay.common.error.ErrorContext;
import com.alipay.common.error.constant.ErrorLevels;
import com.alipay.common.error.constant.ErrorTypes;
import com.alipay.sofa.common.utils.StringUtil;

/**
 * 商户错误码定义
 *
 * 注意：ErrorLevels和ErrorTypes必须填写
 *
 */
public enum ErrorCodeEnum {

    // ============================= 公共类错误码[000-299]==================================

    /**
     * 状态异常
     */
    UNKNOWN_ERROR("999", ErrorLevels.ERROR, ErrorTypes.SYSTEM, "未知异常"),

    USER_NOT_LOGIN("001", ErrorLevels.ERROR, ErrorTypes.SYSTEM, "用户未登录"),

    MER_ARGUMENT_ILLEGAL("002", ErrorLevels.ERROR, ErrorTypes.SYSTEM, "参数错误"),

    SYSTEM_ERROR("003", ErrorLevels.ERROR, ErrorTypes.SYSTEM, "系统异常"),

    THIRD_SYSTEM_ERROR("004", ErrorLevels.ERROR, ErrorTypes.SYSTEM, "三方系统异常"),

    // ============================= platform业务异常[300-399]======================================

    ROLE_CODE_EXISTED("301", ErrorLevels.ERROR, ErrorTypes.BIZ, "角色编码已存在"),
    ROLE_NAME_EXISTED("302", ErrorLevels.ERROR, ErrorTypes.BIZ, "角色名称已存在"),
    ROLE_NOT_EXISTED("303", ErrorLevels.ERROR, ErrorTypes.BIZ, "角色不存在"),
    PERMISSION_CODE_EXISTED("304", ErrorLevels.ERROR, ErrorTypes.BIZ, "权限编码已存在"),
    PERMISSION_NAME_EXISTED("305", ErrorLevels.ERROR, ErrorTypes.BIZ, "权限名称已存在"),
    PERMISSION_NOT_EXISTED("306", ErrorLevels.ERROR, ErrorTypes.BIZ, "权限不存在"),
    OPERATOR_PERMISSION_ERROR("307", ErrorLevels.ERROR, ErrorTypes.BIZ, "操作人权限异常"),
    SCENE_NAME_EXISTED("308", ErrorLevels.ERROR, ErrorTypes.BIZ, "场景名称已存在"),
    SCENE_NOT_EXIST("309", ErrorLevels.ERROR, ErrorTypes.BIZ, "场景不存在"),
    TENANT_NOT_EXIST("309", ErrorLevels.ERROR, ErrorTypes.BIZ, "租户不存在"),
    TENANT_NAME_EXISTED("309", ErrorLevels.ERROR, ErrorTypes.BIZ, "租户名称已存在"),
    TENANT_CODE_BLANK("310", ErrorLevels.ERROR, ErrorTypes.BIZ, "租户编码获取失败"),
    SCENE_CODE_BLANK("311", ErrorLevels.ERROR, ErrorTypes.BIZ, "业务场景编码获取失败"),
    USER_TENANT_PERMISSION_ERROR("312", ErrorLevels.ERROR, ErrorTypes.BIZ, "当前操作人未拥有当前租户权限"),
    USER_SCENE_PERMISSION_ERROR("313", ErrorLevels.ERROR, ErrorTypes.BIZ, "当前操作人未拥有当前场景权限"),
    LABEL_NOT_EXIST("314", ErrorLevels.ERROR, ErrorTypes.BIZ, "标签不存在"),
    LABEL_NAME_IS_EXISTED("315", ErrorLevels.ERROR, ErrorTypes.BIZ, "标签名称已存在"),
    ODPS_TABLE_INFO_NOT_EXISTED("316", ErrorLevels.ERROR, ErrorTypes.BIZ, "odps未查询到数据"),
    LABEL_VERSION_NOT_EXIST("317", ErrorLevels.ERROR, ErrorTypes.BIZ, "标签版本不存在"),
    LABEL_IS_ONLINE("318", ErrorLevels.ERROR, ErrorTypes.BIZ, "标签已上线"),
    LABEL_IS_OFFLINE("319", ErrorLevels.ERROR, ErrorTypes.BIZ, "标签已下线"),
    PRODUCE_IS_EXECUTING("320", ErrorLevels.ERROR, ErrorTypes.BIZ, "生产任务正在执行中"),

    // ============================= 投放方案业务异常[400-499]======================================

    SOLUTION_NOT_EXISTED("400", ErrorLevels.ERROR, ErrorTypes.BIZ, "投放方案不存在"),
    SOLUTION_SNAPSHOT_NOT_EXISTED("401", ErrorLevels.ERROR, ErrorTypes.BIZ, "投放方案快照不存在"),
    SOLUTION_SERVICE_NOT_EXISTED("402", ErrorLevels.ERROR, ErrorTypes.BIZ, "投放服务不存在"),
    SOLUTION_PUBLISH_LIMIT("403", ErrorLevels.ERROR, ErrorTypes.BIZ, "服务发布执行超出次数限制"),

    ;

    /**
     * 常量代表固定标识
     */
    protected final static String AE = "AE";

    /**
     * 常量代表版本号
     */
    protected final static String VERSION = "0";

    /**
     * 错误信息code
     */
    private final String errDtlCode;

    /**
     * 错误级别
     */
    private final String level;

    /**
     * 错误类型
     */
    private final String type;

    /**
     * 描述说明
     */
    private final String desc;

    /**
     * 私有的构造函数
     *
     * @param errDtlCode 错误场景
     * @param levels     错误级别
     * @param type       错误类型
     * @param desc       错误描述
     */
    private ErrorCodeEnum(String errDtlCode, String levels, String type, String desc) {

        this.errDtlCode = errDtlCode;
        this.level = levels;
        this.type = type;
        this.desc = desc;
    }

    /**
     * 私有的构造函数
     *
     * @param errDtlCode 错误场景
     * @param desc       错误描述
     */
    private ErrorCodeEnum(String errDtlCode, String desc) {

        this.errDtlCode = errDtlCode;
        this.level = ErrorLevels.ERROR;
        this.type = ErrorTypes.BIZ;
        this.desc = desc;
    }

//    public ErrorCode toErrorCode() {
//        return new ErrorCode(ErrorCodeEnum.getVersion(), getLevel(), getType(),
//                MrchmngConstants.MERCHANT_ERROR_SCENE, getErrDtlCode(), ErrorCodeEnum.getAE());
//    }

    // ~~ 使用方法

    /**
     * Getter method for property <tt>mer</tt>.
     *
     * @return property value of MER
     */
    public static String getAE() {
        return AE;
    }

    /**
     * Getter method for property <tt>version</tt>.
     *
     * @return property value of VERSION
     */
    public static String getVersion() {
        return ErrorCodeEnum.VERSION;
    }

    /**
     * Getter method for property <tt>level</tt>.
     *
     * @return property value of level
     */
    public String getLevel() {
        return level;
    }

    /**
     * Getter method for property <tt>type</tt>.
     *
     * @return property value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Getter method for property <tt>desc</tt>.
     *
     * @return property value of desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Getter method for property <tt>errDtlCode</tt>.
     *
     * @return property value of errDtlCode
     */
    public String getErrDtlCode() {
        return errDtlCode;
    }

    /**
     * 根据编码查询枚举。
     *
     * @param code 编码。
     * @return 枚举。
     */
    public static ErrorCodeEnum getByErrDtlCode(String code) {
        for (ErrorCodeEnum value : ErrorCodeEnum.values()) {
            if (StringUtil.equals(code, value.getErrDtlCode())) {
                return value;
            }
        }
        return null;
    }

    public static ErrorCodeEnum getByDesc(String desc) {
        for (ErrorCodeEnum value : ErrorCodeEnum.values()) {
            if (StringUtil.equals(desc, value.getDesc())) {
                return value;
            }
        }
        return null;
    }

    public static ErrorCodeEnum getByErrorContext(ErrorContext errorContext) {
        String errorCode = errorContext.fetchCurrentErrorCode();
        return getByErrDtlCode(StringUtil.substring(errorCode, 9));
    }
    /**
     public static void main(String[] args) {
     //找出错误码重复的枚举项
     for (ErrorCodeEnum one : ErrorCodeEnum.values()) {
     for (ErrorCodeEnum compare : ErrorCodeEnum.values()) {
     if (StringUtil.equals(one.getErrDtlCode(), compare.getErrDtlCode())
     && !one.equals(compare)) {
     System.out.println(one);
     }
     }
     }
     System.out.println("========================");
     for (ErrorCodeEnum one : ErrorCodeEnum.values()) {
     for (ErrorCodeEnum compare : ErrorCodeEnum.values()) {
     if (StringUtil.equals(one.getDesc(), compare.getDesc()) && !one.equals(compare)) {
     System.out.println(one);
     }
     }
     }
     //找出可用code,100开始
     //        for (int i = 100; i < 1000; i++) {
     //            ErrorCodeEnum errorcodeEnum = ErrorCodeEnum.getByErrDtlCode(i + "");
     //            if (errorcodeEnum == null) {
     //                System.out.println(i);
     //            }
     //        }
     }
     **/
}
