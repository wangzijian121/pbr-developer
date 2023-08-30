package com.zlht.pbr.algorithm.developer.enums;

import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;
import java.util.Optional;

/**
 * status enum
 *
 * @author zi jian Wang
 */
public enum Status {

    /**
     * 成功
     */
    SUCCESS(200, "success", "成功"),
    /**
     * 请求参数[{0}]无效
     */
    REQUEST_PARAMS_NOT_VALID_ERROR(10001, "request parameter {0} is not valid", "请求参数[{0}]无效"),
    /**
     * 当前用户没有操作权限
     */
    USER_NO_OPERATION_PERM(30001, "user has no operation privilege", "当前用户没有操作权限"),
    ;

    private final int code;
    private final String enMsg;
    private final String zhMsg;

    Status(int code, String enMsg, String zhMsg) {
        this.code = code;
        this.enMsg = enMsg;
        this.zhMsg = zhMsg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        if (Locale.SIMPLIFIED_CHINESE.getLanguage().equals(LocaleContextHolder.getLocale().getLanguage())) {
            return this.zhMsg;
        } else {
            return this.enMsg;
        }
    }

    /**
     * Retrieve Status enum entity by status code.
     */
    public static Optional<Status> findStatusBy(int code) {
        for (Status status : Status.values()) {
            if (code == status.getCode()) {
                return Optional.of(status);
            }
        }
        return Optional.empty();
    }
}
