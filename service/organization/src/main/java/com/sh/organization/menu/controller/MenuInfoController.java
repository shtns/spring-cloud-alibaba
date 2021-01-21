package com.sh.organization.menu.controller;

import com.sh.api.common.constant.MenuInfoConstants;
import com.sh.api.common.util.R;
import com.sh.organization.menu.service.MenuInfoServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 菜单信息管理
 *
 *
 * @author 盛浩
 * @date 2021/1/18 7:34
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/menu")
public class MenuInfoController {

    private final MenuInfoServiceImpl menuInfoService;

    /**
     * 首页
     *
     * @return 前台提示语：首页
     */
    @GetMapping(value = "/home_page")
    public R<String> homePage() {return R.ok(MenuInfoConstants.ForegroundPrompt.HOME_PAGE);}

    /**
     * 行程预定
     *
     * @return 前台提示语：行程预定
     */
    @GetMapping(value = "/travel_booking")
    public R<String> travelBooking() {return R.ok(MenuInfoConstants.ForegroundPrompt.TRAVEL_BOOKING);}

    /**
     * 国内机票
     *
     * @return 前台提示语：国内机票
     */
    @GetMapping(value = "/domestic_air_tickets")
    public R<String> domesticAirTickets() {return R.ok(MenuInfoConstants.ForegroundPrompt.DOMESTIC_AIR_TICKETS);}

    /**
     * 国际机票
     *
     * @return 前台提示语：国际机票
     */
    @GetMapping(value = "/international_air_ticket")
    public R<String> internationalAirTicket() {return R.ok(MenuInfoConstants.ForegroundPrompt.INTERNATIONAL_AIR_TICKET);}

    /**
     * 酒店预订
     *
     * @return 前台提示语：酒店预订
     */
    @GetMapping(value = "/hotel_reservation")
    public R<String> hotelReservation() {return R.ok(MenuInfoConstants.ForegroundPrompt.HOTEL_RESERVATION);}

    /**
     * 火车票
     *
     * @return 前台提示语：火车票
     */
    @GetMapping(value = "/train_ticket")
    public R<String> trainTicket() { return R.ok(MenuInfoConstants.ForegroundPrompt.TRAIN_TICKET); }

    /**
     * 我的申请
     *
     * @return 前台提示语：我的申请
     */
    @GetMapping(value = "/my_application")
    public R<String> myApplication() { return R.ok(MenuInfoConstants.ForegroundPrompt.MY_APPLICATION); }

    /**
     * 我的审批
     *
     * @return 前台提示语：我的审批
     */
    @GetMapping(value = "/my_approval")
    public R<String> myApproval() { return R.ok(MenuInfoConstants.ForegroundPrompt.MY_APPROVAL); }

    /**
     * 我的订单
     *
     * @return 前台提示语：我的订单
     */
    @GetMapping(value = "/my_order")
    public R<String> myOrder() { return R.ok(MenuInfoConstants.ForegroundPrompt.MY_ORDER); }

    /**
     * 我的行程
     *
     * @return 前台提示语：我的行程
     */
    @GetMapping(value = "/my_itinerary")
    public R<String> myItinerary() { return R.ok(MenuInfoConstants.ForegroundPrompt.MY_ITINERARY); }

    /**
     * 查询所有菜单访问路径
     *
     * @return 访问路径列表
     */
    @GetMapping(value = "/access_paths")
    public R<List<String>> queryAccessPaths() {
        return R.ok(this.menuInfoService.queryAccessPaths());
    }
}
