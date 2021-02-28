package com.sh.auth.test.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.sh.api.common.constant.DigitalConstants;
import com.sh.api.common.constant.SentinelConstants;
import com.sh.api.common.util.R;
import com.sh.auth.feign.service.OrganizationService;
import com.sh.auth.test.handler.CustomBlockHandler;
import com.sh.auth.test.handler.CustomFallbackHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

/**
 * sentinel测试业务
 *
 *
 * @author 盛浩
 * @date 2021/2/28 19:48
 */
@Service
@RequiredArgsConstructor
public class SentinelTestServiceImpl {

    private final OrganizationService organizationService;

    /**
     * sentinel测试
     *
     * @param id id
     * @return 端口+id
     */
    @SentinelResource(value = "sentinelTest",
            blockHandlerClass = CustomBlockHandler.class, blockHandler = SentinelConstants.BlockHandler.SPECIFIC_TREATMENT_METHOD,
            fallbackClass = CustomFallbackHandler.class, fallback = SentinelConstants.Fallback.SPECIFIC_TREATMENT_METHOD)
    public R<String> sentinelTest(Long id) {

        if (id == null) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, SentinelConstants.ForegroundPrompt.ID_CANNOT_BE_EMPTY);
        }
        if (id.equals(Long.valueOf(DigitalConstants.ZERO))) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, SentinelConstants.ForegroundPrompt.ID_CANNOT_BE_ZERO);
        }
        return this.organizationService.sentinelTest(id);
    }
}
