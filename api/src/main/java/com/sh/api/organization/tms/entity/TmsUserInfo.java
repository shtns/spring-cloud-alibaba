package com.sh.api.organization.tms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;

/**
 * 数据同步entity
 *
 *
 * @author 盛浩
 * @date 2020/12/11 13:24
 */
@Data
@TableName(value = "TMS_USERS")
@EqualsAndHashCode(callSuper = true)
public class TmsUserInfo extends Model<TmsUserInfo> {

    /**
     * IAM固有的工号顺序
     */
    @TableId(type = IdType.INPUT)
    private String userId;

    /**
     * 所属部门代码
     */
    private String groupCode;

    /**
     * 所属部门名称
     */
    private String groupName;

    /**
     * 生出时间
     */
    private LocalDate createDt;

    /**
     * 修改时间
     */
    private LocalDate modifyDt;

    /**
     * 使用者名称
     */
    private String name;

    /**
     * 工号
     */
    private String code;

    /**
     * 登入id
     */
    private String loginId;

    /**
     * 在职状态
     */
    private String exPersg;

    /**
     * 开始日期
     */
    private String startDate;

    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 入职日期
     */
    private LocalDate hiredDt;

    /**
     * 邮编号
     */
    @TableField(value = "EX_ZIPCODE")
    private String exZipCode;

    /**
     * 住址
     */
    @TableField(value = "EX_HOMEADDRESS")
    private String exHomeAddress;

    /**
     * 出生日期
     */
    private String exBirthday;

    /**
     * 性别
     */
    private String exSex;

    /**
     * 英文名
     */
    @TableField(value = "EX_FIRSTNAME")
    private String exFirstName;

    /**
     * 英文性
     */
    @TableField(value = "EX_LASTNAME")
    private String exLastName;

    /**
     * 外部邮箱地址
     */
    @TableField(value = "EX_EXMAIL")
    private String exExMail;

    /**
     * buCode
     */
    @TableField(value = "EX_BUCODE")
    private String buCode;

    /**
     * buName
     */
    @TableField(value = "EX_BUNAME")
    private String buName;

    /**
     * 外部电话
     */
    @TableField(value = "EX_OFFICEPHONE")
    private String exOfficePhone;

    /**
     * SAP公司代码
     */
    @TableField(value = "EX_SAPCOMCODE")
    private String exSapComCode;

    /**
     * 成本中心代码
     */
    private String exKostl;

    /**
     * 成本中心名称
     */
    @TableField(value = "EX_KTEXT")
    private String exKText;

    /**
     * 利润中心代码
     */
    private String exPrctr;

    /**
     * 利润中心名称
     */
    @TableField(value = "EX_PTEXT")
    private String exPText;

    /**
     * 国家代码
     */
    private String exNatio;

    /**
     * 国家名
     */
    private String exNattx;

    /**
     * 参考员工号
     */
    @TableField(value = "EX_RFPNR")
    private String exRfpnr;

    /**
     * 阳历/阴历
     */
    private String exLunar;

    /**
     * 集团入职日期
     */
    @TableField(value = "EX_EINDAG")
    private String exEinDag;

    /**
     * 职业种类代码
     */
    @TableField(value = "EX_OBJC3")
    private String exObjc3;

    /**
     * 职业种类名称
     */
    @TableField(value = "EX_STXT3")
    private String exSTxt3;

    /**
     * 职务代码
     */
    @TableField(value = "EX_STELL")
    private String exSTell;

    /**
     * 职务名称
     */
    @TableField(value = "EX_STXT4")
    private String exSTxt4;

    /**
     * 人事下位领域代码
     */
    private Integer exBtrtl;

    /**
     * 职位LEVEL代码
     */
    private String exLvpos;

    /**
     * 职位LEVEL代码名称
     */
    private String exLvposName;

    /**
     * 所属法人代码
     */
    private String exCmpCode;

    /**
     * 所属法人名称
     */
    private String exCmpName;

    /**
     * 职位代码
     */
    private String exPosCode;

    /**
     * 职位名称
     */
    private String exPosName;

    /**
     * 职务类型代码
     */
    private String exEmpCode;

    /**
     * 职务代码
     */
    private String exTskCode;

    /**
     * 职务名称
     */
    private String exTskName;

    /**
     * 职务类型名称
     */
    private String exEmpName;

    /**
     * 身份证号
     */
    private String idCard;

    //以下的字段，是和文档中的字段对应不起来的，暂时不知道有没有用
    private String status;
    private String exFtper;
    @TableField(value = "EX_FTTXT")
    private String exFtTxt;
    private Integer exAccountNatio;
    private Integer exWerks;
    @TableField(value = "EX_WERKSTEXT")
    private Integer exWerksText;
    private Integer exBtrtlText;
    @TableField(value = "EX_REJOIN")
    private String exReJoin;
    private String exCreateUser;
    private String exErpId;
    @TableField(value = "EX_PTYPE")
    private String exPtyPe;
}