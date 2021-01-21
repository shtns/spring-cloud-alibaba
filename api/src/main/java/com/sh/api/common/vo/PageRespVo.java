package com.sh.api.common.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import java.io.Serializable;
import java.util.List;

/**
 * 分页响应数据
 *
 *
 * @author 盛浩
 * @date 2020/12/29 19:47
 */
@Data
@NoArgsConstructor
@ApiModel(value = "PageRespVo", description = "分页响应数据")
public class PageRespVo<T> implements Serializable {

    /**
     * 一页的条数
     */
    @Value("10")
    @ApiModelProperty("一页的条数")
    private Long size;

    /**
     * 页码
     */
    @Value("1")
    @ApiModelProperty("页码")
    private Long currentPage;

    /**
     * 页码
     */
    @Value("1")
    @ApiModelProperty("总页数")
    private Long totalPage;

    /**
     * 总条数
     */
    @ApiModelProperty("总条数")
    private Long totalCount;

    /**
     * 列表数据
     */
    @ApiModelProperty("列表数据")
    private List<T> list;

    /**
     * 构造函数转属性
     */
    public PageRespVo(IPage<T> page){
        this.size = page.getSize();
        this.list = page.getRecords();
        this.totalCount = page.getTotal();
        this.totalPage = page.getPages();
        this.currentPage = page.getCurrent();
    }

    /**
     * 构造函数转属性
     */
    public PageRespVo(IPage page, List<T> list){
        this.size = page.getSize();
        this.list = list;
        this.totalCount = page.getTotal();
        this.totalPage = page.getPages();
        this.currentPage = page.getCurrent();
    }

    /**
     *  set Page 各属性的值
     */
    public void setPage(IPage<T> page){
        this.size = page.getSize();
        this.list = page.getRecords();
        this.totalCount = page.getTotal();
        this.totalPage = page.getPages();
        this.currentPage = page.getCurrent();
    }
}
