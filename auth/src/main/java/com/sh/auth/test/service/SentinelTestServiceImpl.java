package com.sh.auth.test.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.sh.api.common.config.ServerErrorException;
import com.sh.api.common.constant.DigitalConstant;
import com.sh.api.common.constant.SentinelConstant;
import com.sh.api.common.util.R;
import com.sh.auth.feign.service.OrganizationService;
import com.sh.auth.test.handler.CustomBlockHandler;
import com.sh.auth.test.handler.CustomFallbackHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
            blockHandlerClass = CustomBlockHandler.class, blockHandler = SentinelConstant.BlockHandler.SPECIFIC_TREATMENT_METHOD,
            fallbackClass = CustomFallbackHandler.class, fallback = SentinelConstant.Fallback.SPECIFIC_TREATMENT_METHOD)
    public R<String> sentinelTest(Long id) {

        if (id == null) {
            throw new ServerErrorException(SentinelConstant.ForegroundPrompt.ID_CANNOT_BE_EMPTY);
        }
        if (id.equals(Long.valueOf(DigitalConstant.ZERO))) {
            throw new ServerErrorException(SentinelConstant.ForegroundPrompt.ID_CANNOT_BE_ZERO);
        }
        return organizationService.sentinelTest(id);
    }
}
