package com.rexen.rest.common.util;

import java.util.UUID;

/**
 * UUID 帮助类
 *
 * @author Mayueteng
 */
public class UUIDGenerateUtils {

    /**
     * 获取通用唯一识别码
     *
     * @return 新生成的唯一识别码
     */
    public static String generateNormalUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取无分隔线唯一识别码
     *
     * @return 新生成的唯一识别码
     */
    public static String generateWithoutLineUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
