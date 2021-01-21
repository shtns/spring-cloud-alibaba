package com.sh.organization.country.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sh.api.common.constant.CountryInfoConstants;
import com.sh.api.common.constant.RedisConstants;
import com.sh.api.common.vo.PageRespVo;
import com.sh.api.organization.city.entity.CityInfo;
import com.sh.api.organization.city.vo.query.CityQueryVo;
import com.sh.api.organization.country.dto.page.CountryPageQueryDto;
import com.sh.api.organization.country.dto.update.CountryUpdateDto;
import com.sh.api.organization.country.entity.CountryInfo;
import com.sh.api.organization.country.vo.details.CountryDetailsVo;
import com.sh.api.organization.country.vo.query.CountryCityQueryVo;
import com.sh.api.organization.country.vo.query.CountryQueryVo;
import com.sh.organization.city.service.CityInfoServiceImpl;
import com.sh.organization.country.mapper.CountryInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 国家信息业务
 *
 *
 * @author 盛浩
 * @date 2020/12/29 17:00
 */
@Service
@RequiredArgsConstructor
public class CountryInfoServiceImpl extends ServiceImpl<CountryInfoMapper, CountryInfo> implements IService<CountryInfo> {

    private final CityInfoServiceImpl cityInfoService;

    private final RedisTemplate<String, CountryQueryVo> redisTemplate;

    private final RedisTemplate<String, CountryCityQueryVo> cityRedisTemplate;

    /**
     * 查询国家信息
     *
     * @param countryId 国家id
     * @return 国家信息vo
     */
    public CountryQueryVo queryCountryInfo(Long countryId) {

        this.checkCountryId(countryId);
        String keyName =  StrUtil.concat(Boolean.TRUE,
                RedisConstants.CountryCacheKey.COUNTRY, StringPool.COLON, countryId.toString());

        CountryQueryVo countryQueryVo = null;

        if (this.redisTemplate.hasKey(keyName)) {
            countryQueryVo = this.redisTemplate.opsForValue().get(keyName);
        } else {
            CountryInfo countryInfo = this.getById(countryId);
            if (ObjectUtil.isNotNull(countryInfo)) {
                CountryQueryVo country = new CountryQueryVo(countryInfo);
                this.redisTemplate.opsForValue().set(keyName, country);
                countryQueryVo = country;
            }
        }

        return countryQueryVo;
    }

    /**
     * 查询国家下的城市信息
     *
     * @param countryId 国家id
     * @return 国家下的城市信息
     */
    public CountryCityQueryVo queryCountryCityInfo(Long countryId) {

        this.checkCountryId(countryId);
        String keyName =  StrUtil.concat(Boolean.TRUE,
                RedisConstants.CountryCacheKey.COUNTRY_CITY, StringPool.COLON, countryId.toString());

        CountryCityQueryVo countryCityQueryVo = null;

        if (this.cityRedisTemplate.hasKey(keyName)) {
            countryCityQueryVo = this.cityRedisTemplate.opsForValue().get(keyName);
        } else {
            CountryInfo countryInfo = this.getById(countryId);
            if (ObjectUtil.isNotEmpty(countryInfo)) {
                List<CityInfo> cityInfos = this.cityInfoService.list(Wrappers.<CityInfo>lambdaQuery()
                        .eq(CityInfo::getCountry2Code, countryInfo.getCountry2Code()));
                if (CollUtil.isNotEmpty(cityInfos)) {
                    CountryCityQueryVo country = new CountryCityQueryVo(new CountryQueryVo(countryInfo),
                            cityInfos.stream().map(CityQueryVo::new).collect(Collectors.toList()));
                    this.cityRedisTemplate.opsForValue().set(keyName, country);
                    countryCityQueryVo = country;
                } else {
                    this.cityRedisTemplate.opsForValue().set(keyName,
                            new CountryCityQueryVo(new CountryQueryVo(countryInfo), CollUtil.newArrayList()));
                    countryCityQueryVo = this.cityRedisTemplate.opsForValue().get(keyName);
                }
            }
        }

        return countryCityQueryVo;
    }

    /**
     * 分页查询国家信息
     *
     * @param iPage 分页插件
     * @param countryPageQueryDto 查询条件
     * @return 国家信息分页数据vo
     */
    public PageRespVo<CountryQueryVo> pageQueryCountryInfo(IPage<CountryInfo> iPage, CountryPageQueryDto countryPageQueryDto) {

        IPage<CountryInfo> pageCountryInfo = this.page(iPage, Wrappers.<CountryInfo>lambdaQuery()
                .like(StrUtil.isNotBlank(countryPageQueryDto.getCountryNameCn()),
                        CountryInfo::getCountryNameCn, countryPageQueryDto.getCountryNameCn())
                .like(StrUtil.isNotBlank(countryPageQueryDto.getCountryNameEn()),
                        CountryInfo::getCountryNameEn, countryPageQueryDto.getCountryNameEn()));

        return new PageRespVo<>(pageCountryInfo, pageCountryInfo.getRecords().stream().map(CountryQueryVo::new).collect(Collectors.toList()));
    }

    /**
     * 分页查询国家城市信息
     *
     * @param iPage 分页插件
     * @param countryPageQueryDto 查询条件
     * @return 国家城市信息分页数据vo
     */
    public PageRespVo<CountryDetailsVo> queryCountryDetails(IPage<CountryInfo> iPage, CountryPageQueryDto countryPageQueryDto) {
        return new PageRespVo<>(this.baseMapper.queryCountryDetails(iPage, countryPageQueryDto));
    }

    /**
     * 修改国家信息
     *
     * @param countryUpdateDto 国家修改dto
     * @return Boolean
     */
    public Boolean modifyCountryInfo(CountryUpdateDto countryUpdateDto) {
        String keyName =  StrUtil.concat(Boolean.TRUE,
                RedisConstants.CountryCacheKey.COUNTRY, StringPool.COLON, countryUpdateDto.getCountryId().toString());
        boolean flag = this.updateById(countryUpdateDto.toCountryInfo());
        this.redisTemplate.opsForValue().set(keyName, new CountryQueryVo(this.getById(countryUpdateDto.getCountryId())));
        return flag;
    }

    /**
     * 删除国家信息
     *
     * @param countryId 国家id
     * @return Boolean
     */
    public Boolean delCountryInfo(Long countryId) {
        this.checkCountryId(countryId);
        String keyName =  StrUtil.concat(Boolean.TRUE,
                RedisConstants.CountryCacheKey.COUNTRY, StringPool.COLON, countryId.toString());
        this.redisTemplate.delete(keyName);
        return this.removeById(countryId);
    }

    /**
     * 检查国家id是否为空
     *
     * @param countryId 国家id
     */
    private void checkCountryId(Long countryId) {
        if (countryId == null) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
                    CountryInfoConstants.ForegroundPrompt.COUNTRY_ID_CANNOT_BE_EMPTY);
        }
    }
}
