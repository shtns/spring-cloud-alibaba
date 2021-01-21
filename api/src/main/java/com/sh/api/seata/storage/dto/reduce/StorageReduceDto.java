package com.sh.api.seata.storage.dto.reduce;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 库存减少dto
 *
 *
 * @author 盛浩
 * @date 2020/12/28 15:23
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StorageReduceDto {

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 数量
     */
    private Integer count;
}
