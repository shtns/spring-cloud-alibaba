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
import com.sh.api.common.constant.DigitalConstants;
import com.sh.api.common.constant.RedisConstants;
import com.sh.api.common.vo.PageRespVo;
import com.sh.api.organization.city.entity.CityInfo;
import com.sh.api.organization.city.vo.query.CityQueryVo;
import com.sh.api.organization.country.dto.page.CountryPageDto;
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
     * 删除国家信息
     *
     * @param countryId 国家id
     * @return 是否删除成功
     */
    public Boolean removeCountryInfo(Long countryId) {

        this.checkCountryId(countryId);

        CountryInfo countryInfo = this.getById(countryId);
        if (ObjectUtil.isNull(countryInfo)) {
            return null;
        }

        //通过缓存key删除国家信息缓存
        String countryCacheKey = this.genderCountryCacheKey(DigitalConstants.ZERO, countryId);
        if (this.checkCountryCacheKeyExist(countryCacheKey)) {
            this.redisTemplate.delete(countryCacheKey);
        }

        //国家下面存在城市，通过国家城市缓存key，删除国家城市信息缓存
        List<CityQueryVo> cityQueryVos = cityInfoService.queryCityInfos(countryInfo.getCountry2Code());
        if (CollUtil.isNotEmpty(cityQueryVos)) {
            String countryCityCacheKey = this.genderCountryCacheKey(DigitalConstants.ONE, countryId);
            if (this.checkCountryCacheKeyExist(countryCityCacheKey)) {
                this.redisTemplate.delete(countryCityCacheKey);
            }
        }

        return this.removeById(countryId);
    }

    /**
     * 更新国家信息
     *
     * @param countryUpdateDto 国家更新dto
     * @return 是否更新成功
     */
    public Boolean updateCountryInfo(CountryUpdateDto countryUpdateDto) {

        String countryCacheKey = this.genderCountryCacheKey(DigitalConstants.ZERO, countryUpdateDto.getCountryId());
        if (this.checkCountryCacheKeyExist(countryCacheKey)) {
            this.redisTemplate.opsForValue().set(countryCacheKey, new CountryQueryVo(this.getById(countryUpdateDto.getCountryId())));
        }

        return this.updateById(countryUpdateDto.toCountryInfo());
    }

    /**
     * 查询国家信息
     *
     * @param countryId 国家id
     * @return 国家查询vo
     */
    public CountryQueryVo queryCountryInfo(Long countryId) {

        this.checkCountryId(countryId);

        CountryQueryVo countryQueryVo = null;

        //通过国家缓存key查询缓存，有直接取，没有查询数据库放入缓存
        String countryCacheKey = this.genderCountryCacheKey(DigitalConstants.ZERO, countryId);
        if (this.checkCountryCacheKeyExist(countryCacheKey)) {
            countryQueryVo = this.redisTemplate.opsForValue().get(countryCacheKey);
        } else {
            CountryInfo countryInfo = this.getById(countryId);
            if (ObjectUtil.isNotNull(countryInfo)) {
                CountryQueryVo country = new CountryQueryVo(countryInfo);
                this.redisTemplate.opsForValue().set(countryCacheKey, country);
                countryQueryVo = country;
            }
        }

        return countryQueryVo;
    }

    /**
     * 查询国家城市信息
     *
     * @param countryId 国家id
     * @return 国家城市查询vo
     */
    public CountryCityQueryVo queryCountryCityInfo(Long countryId) {

        this.checkCountryId(countryId);

        CountryCityQueryVo countryCityQueryVo = null;

        //通过国家城市缓存key查询缓存，有直接取，没有再查询国家城市信息放入缓存
        String countryCityCacheKey = this.genderCountryCacheKey(DigitalConstants.ONE, countryId);
        if (this.checkCountryCacheKeyExist(countryCityCacheKey)) {
            countryCityQueryVo = this.cityRedisTemplate.opsForValue().get(countryCityCacheKey);
        } else {
            CountryInfo countryInfo = this.getById(countryId);
            if (ObjectUtil.isNotEmpty(countryInfo)) {
                List<CityQueryVo> cityQueryVos = this.cityInfoService.queryCityInfos(countryInfo.getCountry2Code());
                //此国家下存在城市时，国家城市一块缓存
                if (CollUtil.isNotEmpty(cityQueryVos)) {
                    countryCityQueryVo = new CountryCityQueryVo(new CountryQueryVo(countryInfo), cityQueryVos);
                    this.cityRedisTemplate.opsForValue().set(countryCityCacheKey, countryCityQueryVo);
                } else {
                    //不存在，重新生成国家缓存key，先通过缓存key查询，存在则取出来，没有则加入到国家城市缓存中
                    String countryCacheKey = this.genderCountryCacheKey(DigitalConstants.ZERO, countryId);
                    if (this.checkCountryCacheKeyExist(countryCacheKey)) {
                        countryCityQueryVo = new CountryCityQueryVo(this.redisTemplate.opsForValue().get(countryCacheKey), CollUtil.newArrayList());
                    } else {
                        countryCityQueryVo = new CountryCityQueryVo(new CountryQueryVo(countryInfo), CollUtil.newArrayList());
                        this.cityRedisTemplate.opsForValue().set(countryCityCacheKey, countryCityQueryVo);
                    }
                }
            }
        }

        return countryCityQueryVo;
    }

    /**
     * 分页查询国家信息
     *
     * @param iPage 分页插件
     * @param countryPageDto 国家分页dto
     * @return 国家查询分页vo
     */
    public PageRespVo<CountryQueryVo> pageQueryCountryInfo(IPage<CountryInfo> iPage, CountryPageDto countryPageDto) {

        //通过国家中文名、国家英文名、国家中文名简写、国家英文名简写进行模糊查询
        IPage<CountryInfo> countryPageInfo = this.page(iPage, Wrappers.<CountryInfo>lambdaQuery()
                .like(StrUtil.isNotBlank(countryPageDto.getCountryNameCn()), CountryInfo::getCountryNameCn, countryPageDto.getCountryNameCn())
                .like(StrUtil.isNotBlank(countryPageDto.getCountryNameEn()), CountryInfo::getCountryNameEn, countryPageDto.getCountryNameEn())
                .like(StrUtil.isNotBlank(countryPageDto.getCountryAbbreviateNameCn()),
                        CountryInfo::getCountryAbbreviateNameCn, countryPageDto.getCountryAbbreviateNameCn())
                .like(StrUtil.isNotBlank(countryPageDto.getCountryAbbreviateNameEn()),
                        CountryInfo::getCountryAbbreviateNameEn, countryPageDto.getCountryAbbreviateNameEn()));

        return new PageRespVo<>(countryPageInfo, countryPageInfo.getRecords().stream().map(CountryQueryVo::new).collect(Collectors.toList()));
    }

    /**
     * 分页查询国家详情
     *
     * @param iPage 分页插件
     * @param countryPageDto 国家分页查询dto
     * @return 国家详情分页vo
     */
    public PageRespVo<CountryDetailsVo> queryCountryDetails(IPage<CountryInfo> iPage, CountryPageDto countryPageDto) {
        return new PageRespVo<>(this.baseMapper.queryCountryDetails(iPage, countryPageDto));
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

    /**
     * 生成国家缓存key
     *
     * @param tag 标记
     * @param countryId 国家id
     * @return 缓存key
     */
    private String genderCountryCacheKey(Integer tag, Long countryId) {

        String cachePrefix;

        //tag0生成国家缓存key，tag1生成国家城市缓存key
        if (tag.equals(DigitalConstants.ZERO)) {
            cachePrefix = RedisConstants.CountryCacheKey.COUNTRY;
        } else {
            cachePrefix = RedisConstants.CountryCacheKey.COUNTRY_CITY;
        }

        return StrUtil.concat(Boolean.TRUE, cachePrefix, StringPool.COLON, countryId.toString());
    }

    /**
     * 检查国家缓存key是否存在
     *
     * @param countryCacheKey 国家缓存key
     * @return 是否存在
     */
    public Boolean checkCountryCacheKeyExist(String countryCacheKey) {
        return this.redisTemplate.hasKey(countryCacheKey);
    }
}
