package com.sh.api.common.constant;

/**
 * 消息队列常量值
 *
 *
 * @author 盛浩
 * @date 2021/1/19 22:23
 */
public interface RabbitmqConstants {

    /**
     * 配置
     */
    interface Config {

        /**
         * 交换机
         */
        interface Exchange {

            /**
             * 国内
             */
            interface Dom {

                /**
                 * 国内交换机名称
                 */
               String EXCHANGE_NAME = "dom_exchange_name";
            }

            /**
             * 国际
             */
            interface Inter {

                /**
                 * 国际交换机名称
                 */
                String EXCHANGE_NAME = "inter_exchange_name";
            }
        }

        /**
         * 路由
         */
        interface Routing {

            /**
             * 国内
             */
            interface Dom {

                /**
                 * 国内路由key名称
                 */
                String KEY_NAME = "dom_routing_key_name";
            }

            /**
             * 国际
             */
            interface Inter {

                /**
                 * 国际路由key名称
                 */
                String KEY_NAME = "inter_routing_key_name";
            }
        }
    }

    /**
     * 消息
     */
    interface Msg {

        /**
         *  国内机票预定成功短信
         */
        String SEND_DOM_TICKET_RESERVE_SUCCESS_SMS = "国内机票预定成功短信";

        /**
         *  国际机票预定成功短信
         */
        String SEND_INTER_TICKET_RESERVE_SUCCESS_SMS = "国际机票预定成功短信";
    }
}
