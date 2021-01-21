package com.sh.api.organization.country.vo.query;

import com.sh.api.organization.city.vo.query.CityQueryVo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

/**
 * 国家查询信息vo
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
     * 国家信息
     */
    private CountryQueryVo countryInfo;

    /**
     * 城市信息列表
     */
    private List<CityQueryVo> cityInfos;
}
