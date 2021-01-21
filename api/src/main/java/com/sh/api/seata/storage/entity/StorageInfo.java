package com.sh.api.seata.storage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 库存西悉尼
 *
 *
 * @author 盛浩
 * @date 2020/12/25 21:02
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StorageInfo extends Model<StorageInfo> {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long storageId;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 总库存
     */
    private Long total;

    /**
     * 已用库存
     */
    private Long used;

    /**
     * 剩余库存
     */
    private Long residue;
}
