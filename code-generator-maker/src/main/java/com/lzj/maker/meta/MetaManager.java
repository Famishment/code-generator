package com.lzj.maker.meta;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;

/**
 * @Auther: lzj
 * @Date: 2024/7/12-07-12-18:56
 * @Description: com.lzj.maker.meta
 */

public class MetaManager {

    // 双检锁单例
    private static volatile Meta meta;

    public static Meta getMetaObject() {
        if (meta == null) {
            synchronized (MetaManager.class) {
                if (meta == null) {
                    meta = initMeta();
                }
            }
        }
        return meta;
    }

    private static Meta initMeta() {
        String metaJson = ResourceUtil.readUtf8Str("meta.json");
        Meta newMeta = JSONUtil.toBean(metaJson, Meta.class);
        // todo 校验配置文件的参数，处理默认值
        return newMeta;
    }

}
