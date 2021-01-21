package com.sh.api.common.dto;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页响应数据
 *
 *
 * @author 盛浩
 * @date 2020/12/29 18:11
 */
@Data
@ApiModel(value = "PageReqDto", description = "分页请求数据")
public class PageReqDto<D> {

    /**
     * 一页的条数
     */
    @ApiModelProperty("一页的条数")
    private Integer size;

    /**
     * 页码
     */
    @ApiModelProperty("当前页码")
    private Integer current;

    /**
     * 转成myBatisPlus的 page对象
     * @return IPage<D>
     */
    public IPage<D> toPlusPage(){
        IPage<D> page = new Page<>();
        if(ObjectUtil.isNotNull(this.size)){
            page.setSize(this.size);
        }
        if(ObjectUtil.isNotNull(this.current)){
            page.setCurrent(this.current);
        }
        return page;
    }
}
