package com.sh.api.organization.country.vo.details;

import com.sh.api.organization.city.vo.query.CityQueryVo;
import com.sh.api.organization.country.vo.query.CountryQueryVo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

/**
 * 国家详情vo
 *
 *
 * @author 盛浩
 * @date 2020/12/29 16:27
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryDetailsVo {

    /**
     * 国家查询vo列表
     */
    private List<CountryQueryVo> countryQueryVos;

    /**
     * 城市查询vo列表
     */
    private List<CityQueryVo> cityQueryVos;
}
