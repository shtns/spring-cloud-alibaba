package com.sh.api.organization.country.vo.query;

import com.sh.api.organization.city.vo.query.CityQueryVo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

/**
 * 国家城市查询vo
 *
 * @author 盛浩
 * @date 2020/12/29 18:15
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryCityQueryVo {

    /**
     * 国家查询vo列表
     */
    private CountryQueryVo countryQueryVo;

    /**
     * 城市查询vo列表
     */
    private List<CityQueryVo> cityQueryVos;
}
