package com.sh.api.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 令牌vo
 *
 *
 * @author 盛浩
 * @date 2021/1/17 22:00
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Oauth2TokenVo", description = "令牌vo")
public class Oauth2TokenVo {

        /**
         * 名称英文
         */
        @ApiModelProperty(value = "名称英文")
        private String nameEn;

        /**
         * 范围
         */
        @ApiModelProperty(value = "范围")
        private String scope;

        /**
         * jti
         */
        @ApiModelProperty(value = "jti")
        private String jti;

        /**
         * 令牌类型
         */
        @ApiModelProperty(value = "令牌类型")
        private String tokenType;

        /**
         * 访问令牌
         */
        @ApiModelProperty(value = "访问令牌")
        private String accessToken;

        /**
         * 刷新令牌
         */
        @ApiModelProperty(value = "刷新令牌")
        private String refreshToken;

        /**
         * 有效时间（秒）
         */
        @ApiModelProperty(value = "有效时间（秒）")
        private int expiresIn;
}
