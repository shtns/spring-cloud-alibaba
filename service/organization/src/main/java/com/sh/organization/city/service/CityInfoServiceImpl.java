package com.sh.organization.city.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sh.api.common.constant.CityInfoConstants;
import com.sh.api.common.vo.PageRespVo;
import com.sh.api.organization.city.dto.page.CityPageQueryDto;
import com.sh.api.organization.city.dto.update.CityUpdateDto;
import com.sh.api.organization.city.entity.CityInfo;
import com.sh.api.organization.city.vo.query.CityQueryVo;
import com.sh.organization.city.mapper.CityInfoMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

/**
 * 城市信息业务
 *
 *
 * @author 盛浩
 * @date 2020/12/29 17:10
 */
@Service
@CacheConfig(cacheNames = "city")
public class CityInfoServiceImpl extends ServiceImpl<CityInfoMapper, CityInfo> implements IService<CityInfo> {

    /**
     * 查询城市信息
     *
     * @param cityId 城市id
     * @return 城市信息vo
     */
    @Cacheable(key = "#cityId", unless = "#result == null")
    public CityQueryVo queryCityInfo(Long cityId) {
        if (cityId == null) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
                    CityInfoConstants.ForegroundPrompt.CITY_ID_CANNOT_BE_EMPTY);
        }

        CityInfo cityInfo = this.getById(cityId);
        if (ObjectUtil.isNull(cityInfo)) {
            return null;
        }

        return new CityQueryVo(cityInfo);
    }

    /**
     * 分页查询城市信息
     *
     * @param iPage 分页插件
     * @param cityPageQueryDto 查询条件
     * @return 城市信息分页数据
     */
    public PageRespVo<CityQueryVo> pageQueryCityInfo(IPage<CityInfo> iPage, CityPageQueryDto cityPageQueryDto) {
        return new PageRespVo<>(this.baseMapper.pageQueryCityInfo(iPage, cityPageQueryDto));
    }

    /**
     * 修改城市信息
     *
     * @param cityUpdateDto 城市修改dto
     * @return 城市信息vo
     */
    @CachePut(key = "#cityUpdateDto.cityId", unless = "#result == null")
    public CityQueryVo modifyCityInfo(CityUpdateDto cityUpdateDto) {
        this.updateById(cityUpdateDto.toCityInfo());
        return new CityQueryVo(this.getById(cityUpdateDto.getCityId()));
    }

    /**
     * 删除城市信息
     *
     * @param cityId 城市id
     * @return Boolean
     */
    @CacheEvict(key = "#cityId")
    public Boolean delCityInfo(Integer cityId) { return this.removeById(cityId); }
}
