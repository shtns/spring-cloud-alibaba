package com.sh.organization.country.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sh.api.organization.country.dto.page.CountryPageQueryDto;
import com.sh.api.organization.country.entity.CountryInfo;
import com.sh.api.organization.country.vo.details.CountryDetailsVo;
import org.apache.ibatis.annotations.Param;

/**
 * 国家信息映射
 *
 *
 * @author 盛浩
 * @date 2020/12/29 17:01
 */
public interface CountryInfoMapper extends BaseMapper<CountryInfo> {

    /**
     * 分页查询所有国家城市信息
     *
     * @param iPage 分页插件
     * @param countryPageQueryDto 查询条件
     * @return 国家城市信息分页数据vo
     */
    IPage<CountryDetailsVo> queryCountryDetails(IPage<CountryInfo> iPage,
                                                @Param(value = "query") CountryPageQueryDto countryPageQueryDto);
}
