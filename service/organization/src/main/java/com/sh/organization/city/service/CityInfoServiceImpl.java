package com.sh.organization.city.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sh.api.common.config.ServerErrorException;
import com.sh.api.common.constant.CityInfoConstant;
import com.sh.api.common.vo.PageRespVo;
import com.sh.api.organization.city.dto.page.CityPageDto;
import com.sh.api.organization.city.dto.update.CityUpdateDto;
import com.sh.api.organization.city.entity.CityInfo;
import com.sh.api.organization.city.vo.query.CityQueryVo;
import com.sh.organization.city.mapper.CityInfoMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

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
     * 删除城市信息
     *
     * @param cityId 城市id
     * @return 是否删除成功
     */
    @CacheEvict(key = "#cityId")
    public Boolean removeCityInfo(Long cityId) {
        checkCityId(cityId);
        return this.removeById(cityId);
    }

    /**
     * 更新城市信息
     *
     * @param cityUpdateDto 城市更新dto
     * @return 城市信息vo
     */
    @CachePut(key = "#cityUpdateDto.cityId", unless = "#result == null")
    public CityQueryVo updateCityInfo(CityUpdateDto cityUpdateDto) {
        this.updateById(cityUpdateDto.toCityInfo());
        return new CityQueryVo(getById(cityUpdateDto.getCityId()));
    }

    /**
     * 查询城市信息
     *
     * @param cityId 城市id
     * @return 城市查询vo
     */
    @Cacheable(key = "#cityId", unless = "#result == null")
    public CityQueryVo queryCityInfo(Long cityId) {

        checkCityId(cityId);

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
     * @param cityPageDto 城市分页dto
     * @return 城市查询分页vo
     */
    public PageRespVo<CityQueryVo> pageQueryCityInfo(IPage<CityInfo> iPage, CityPageDto cityPageDto) {

        //通过城市名称中文、城市名称英文、城市名称全拼、城市名称首拼、热门城市进行模糊查询
        IPage<CityInfo> cityPageInfo = this.page(iPage, Wrappers.<CityInfo>lambdaQuery()
                .like(StrUtil.isNotBlank(cityPageDto.getCityNameCn()), CityInfo::getCityNameCn, cityPageDto.getCityNameCn())
                .like(StrUtil.isNotBlank(cityPageDto.getCityNameEn()), CityInfo::getCityNameEn, cityPageDto.getCityNameEn())
                .like(StrUtil.isNotBlank(cityPageDto.getCityNameAllSpell()), CityInfo::getCityNameAllSpell, cityPageDto.getCityNameAllSpell())
                .like(StrUtil.isNotBlank(cityPageDto.getCityNameHeadSpell()), CityInfo::getCityNameHeadSpell, cityPageDto.getCityNameHeadSpell())
                .like(StrUtil.isNotBlank(cityPageDto.getCityPopular()), CityInfo::getCityPopular, cityPageDto.getCityPopular()));

        return new PageRespVo<>(cityPageInfo, cityPageInfo.getRecords().stream().map(CityQueryVo::new).collect(Collectors.toList()));
    }

    /**
     * 检查城市id是否为空
     *
     * @param cityId 城市id
     */
    private void checkCityId(Long cityId) {
        if (cityId == null) {
            throw new ServerErrorException(CityInfoConstant.ForegroundPrompt.CITY_ID_CANNOT_BE_EMPTY);
        }
    }

    /**
     * 通过国家二字码查询城市信息列表
     *
     * @param country2Code 国家二字码
     * @return 城市查询vo列表
     */
    public List<CityQueryVo> queryCityInfos(String country2Code) {
        return this.list(Wrappers.<CityInfo>lambdaQuery().eq(CityInfo::getCountry2Code, country2Code))
                .stream().map(CityQueryVo::new).collect(Collectors.toList());
    }
}
