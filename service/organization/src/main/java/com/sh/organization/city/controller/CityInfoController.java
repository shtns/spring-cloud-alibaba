package com.sh.organization.city.controller;

import com.sh.api.common.dto.PageReqDto;
import com.sh.api.common.util.R;
import com.sh.api.common.vo.PageRespVo;
import com.sh.api.organization.city.dto.page.CityPageQueryDto;
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
     * 查询城市信息
     *
     * @param cityId 城市id
     * @return 城市信息vo
     */
    @GetMapping(value = "/{cityId}")
    public R<CityQueryVo> queryCityInfo(@PathVariable Long cityId) { return R.ok(this.cityInfoService.queryCityInfo(cityId)); }

    /**
     * 分页查询城市信息
     *
     * @param pageReqDto 分页插件
     * @param cityPageQueryDto 查询条件
     * @return 城市信息分页数据vo
     */
    @GetMapping(value = "/page")
    public R<PageRespVo<CityQueryVo>> pageQueryCityInfo(PageReqDto<CityInfo> pageReqDto, CityPageQueryDto cityPageQueryDto) {
        return R.ok(this.cityInfoService.pageQueryCityInfo(pageReqDto.toPlusPage(), cityPageQueryDto));
    }

    /**
     * 保存城市信息
     *
     * @param citySaveDto 城市保存dto
     * @return 是否保存成功
     */
    @PostMapping
    public R<Boolean> saveCityInfo(@RequestBody @Valid CitySaveDto citySaveDto) { return R.ok(this.cityInfoService.save(citySaveDto.toCityInfo())); }

    /**
     * 修改城市信息
     *
     * @param cityUpdateDto 城市修改dto
     * @return 城市信息vo
     */
    @PutMapping
    public R<CityQueryVo> modifyCityInfo(@RequestBody @Valid CityUpdateDto cityUpdateDto) {
        return R.ok(this.cityInfoService.modifyCityInfo(cityUpdateDto));
    }

    /**
     * 删除城市信息
     *
     * @param cityId 城市id
     * @return R<Boolean>
     */
    @DeleteMapping(value = "/{cityId}")
    public R<Boolean> delCityInfo(@PathVariable Integer cityId) { return R.ok(this.cityInfoService.delCityInfo(cityId)); }
}
