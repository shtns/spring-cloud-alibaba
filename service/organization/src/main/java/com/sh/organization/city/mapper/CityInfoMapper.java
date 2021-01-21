package com.sh.organization.city.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sh.api.organization.city.dto.page.CityPageQueryDto;
import com.sh.api.organization.city.entity.CityInfo;
import com.sh.api.organization.city.vo.query.CityQueryVo;
import org.apache.ibatis.annotations.Param;

/**
 * 城市信息映射
 *
 *
 * @author 盛浩
 * @date 2020/12/29 17:09
 */
public interface CityInfoMapper extends BaseMapper<CityInfo> {

    /**
     * 分页查询城市信息
     *
     * @param iPage 分页插件
     * @param cityPageQueryDto 查询条件
     * @return 城市信息分页数据
     */
    IPage<CityQueryVo> pageQueryCityInfo(IPage<CityInfo> iPage, @Param(value = "query") CityPageQueryDto cityPageQueryDto);
}
