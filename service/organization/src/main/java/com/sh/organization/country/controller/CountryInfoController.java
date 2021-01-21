package com.sh.organization.country.controller;

import com.sh.api.common.dto.PageReqDto;
import com.sh.api.common.util.R;
import com.sh.api.common.vo.PageRespVo;
import com.sh.api.organization.country.dto.page.CountryPageQueryDto;
import com.sh.api.organization.country.dto.save.CountrySaveDto;
import com.sh.api.organization.country.dto.update.CountryUpdateDto;
import com.sh.api.organization.country.entity.CountryInfo;
import com.sh.api.organization.country.vo.details.CountryDetailsVo;
import com.sh.api.organization.country.vo.query.CountryCityQueryVo;
import com.sh.api.organization.country.vo.query.CountryQueryVo;
import com.sh.organization.country.service.CountryInfoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * 国家信息管理
 *
 *
 * @author 盛浩
 * @date 2020/12/29 15:38
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/country")
public class CountryInfoController {

    private final CountryInfoServiceImpl countryInfoService;

    /**
     * 查询国家信息
     *
     * @param countryId 国家id
     * @return 国家信息vo
     */
    @GetMapping(value = "/{countryId}")
    public R<CountryQueryVo> queryCountryInfo(@PathVariable Long countryId) {
        return R.ok(this.countryInfoService.queryCountryInfo(countryId));
    }

    /**
     * 查询国家下的城市信息
     *
     * @param countryId 国家id
     * @return 国家下的城市信息vo
     */
    @GetMapping(value = "/city/{countryId}")
    public R<CountryCityQueryVo> queryCountryCityInfo(@PathVariable Long countryId) {
        return R.ok(this.countryInfoService.queryCountryCityInfo(countryId));
    }

    /**
     * 分页查询国家信息
     *
     * @param pageReqDto 分页插件
     * @param countryPageQueryDto 查询条件
     * @return 国家信息分页数据vo
     */
    @GetMapping(value = "/page")
    public R<PageRespVo<CountryQueryVo>> pageQueryCountryInfo(PageReqDto<CountryInfo> pageReqDto,
                                                              CountryPageQueryDto countryPageQueryDto) {
        return R.ok(this.countryInfoService.pageQueryCountryInfo(pageReqDto.toPlusPage(), countryPageQueryDto));
    }

    /**
     * 分页查询国家城市信息
     *
     * @param pageReqDto 分页插件
     * @param countryPageQueryDto 查询条件
     * @return 国家城市信息分页数据vo
     */
    @GetMapping(value = "/all")
    public R<PageRespVo<CountryDetailsVo>> queryCountryDetails(PageReqDto<CountryInfo> pageReqDto,
                                                               CountryPageQueryDto countryPageQueryDto) {
        return R.ok(this.countryInfoService.queryCountryDetails(pageReqDto.toPlusPage(), countryPageQueryDto));
    }

    /**
     * 保存国家信息
     *
     * @param countrySaveDto 国家保存dto
     * @return 是否保存成功
     */
    @PostMapping
    public R<Boolean> addCountryInfo(@RequestBody @Valid CountrySaveDto countrySaveDto) {
        return R.ok(this.countryInfoService.save(countrySaveDto.toCountryInfo()));
    }

    /**
     * 修改国家信息
     *
     * @param countryUpdateDto 国家修改dto
     * @return R<Boolean>
     */
    @PutMapping
    public R<Boolean> modifyCountryInfo(@RequestBody @Valid CountryUpdateDto countryUpdateDto) {
        return R.ok(this.countryInfoService.modifyCountryInfo(countryUpdateDto));
    }

    /**
     * 删除国家信息
     *
     * @param countryId 国家id
     * @return R<Boolean>
     */
    @DeleteMapping(value = "/{countryId}")
    public R<Boolean> delCountryInfo(@PathVariable Long countryId) {
        return R.ok(this.countryInfoService.delCountryInfo(countryId));
    }
}
