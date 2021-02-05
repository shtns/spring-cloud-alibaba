package com.sh.organization.city.controller;

import com.sh.api.common.dto.PageReqDto;
import com.sh.api.common.util.R;
import com.sh.api.common.vo.PageRespVo;
import com.sh.api.organization.city.dto.page.CityPageDto;
import com.sh.api.organization.city.dto.save.CitySaveDto;
import com.sh.api.organization.city.dto.update.CityUpdateDto;
import com.sh.api.organization.city.entity.CityInfo;
import com.sh.api.organization.city.vo.query.CityQueryVo;
import com.sh.organization.city.service.CityInfoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * 城市信息管理
 *
 *
 * @author 盛浩
 * @date 2020/12/29 17:05
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/city")
public class CityInfoController {

    private final CityInfoServiceImpl cityInfoService;

    /**
     * 保存城市信息
     *
     * @param citySaveDto 城市保存dto
     * @return 是否保存成功
     */
    @PostMapping
    public R<Boolean> saveCityInfo(@RequestBody @Valid CitySaveDto citySaveDto) { return R.ok(this.cityInfoService.save(citySaveDto.toCityInfo())); }

    /**
     * 删除城市信息
     *
     * @param cityId 城市id
     * @return 是否删除成功
     */
    @DeleteMapping(value = "/{cityId}")
    public R<Boolean> removeCityInfo(@PathVariable Long cityId) { return R.ok(this.cityInfoService.removeCityInfo(cityId)); }

    /**
     * 更新城市信息
     *
     * @param cityUpdateDto 城市更新dto
     * @return 城市信息vo
     */
    @PutMapping
    public R<CityQueryVo> updateCityInfo(@RequestBody @Valid CityUpdateDto cityUpdateDto) {
        return R.ok(this.cityInfoService.updateCityInfo(cityUpdateDto));
    }

    /**
     * 查询城市信息
     *
     * @param cityId 城市id
     * @return 城市查询vo
     */
    @GetMapping(value = "/{cityId}")
    public R<CityQueryVo> queryCityInfo(@PathVariable Long cityId) { return R.ok(this.cityInfoService.queryCityInfo(cityId)); }

    /**
     * 分页查询城市信息
     *
     * @param pageReqDto 分页插件
     * @param cityPageDto 城市分页dto
     * @return 城市查询分页vo
     */
    @GetMapping(value = "/page")
    public R<PageRespVo<CityQueryVo>> pageQueryCityInfo(PageReqDto<CityInfo> pageReqDto, CityPageDto cityPageDto) {
        return R.ok(this.cityInfoService.pageQueryCityInfo(pageReqDto.toPlusPage(), cityPageDto));
    }
}
