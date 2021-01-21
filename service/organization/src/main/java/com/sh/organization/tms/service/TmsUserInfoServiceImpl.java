package com.sh.organization.tms.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sh.api.organization.tms.entity.TmsUserInfo;
import com.sh.organization.tms.mapper.TmsUserInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * 数据同步业务
 *
 *
 * @author 盛浩
 * @date 2020/12/29 18:54
 */
@Service
@DS("sqlserver")
@RequiredArgsConstructor
public class TmsUserInfoServiceImpl extends ServiceImpl<TmsUserInfoMapper, TmsUserInfo> implements IService<TmsUserInfo> {

    private final JdbcTemplate jdbcTemplate;

   /**
     * 查询数据同步列表
     *
     * @return 数据同步列表
     */
    public List<TmsUserInfo> queryTmsUserInfos() {
        return this.quertTmsUserInfos("SELECT *  FROM BTMS_USERS");
    }

    /**
     * 查询数据同步列表
     *
     * @param sql 查询sql
     * @return 数据同步列表
     */
    public List<TmsUserInfo> quertTmsUserInfos(String sql) {
        return this.jdbcTemplate.query(sql, new RowMapper<TmsUserInfo>() {
            @Override
            public TmsUserInfo mapRow(ResultSet resultSet, int i) throws SQLException {
                TmsUserInfo tmsUserInfo = new TmsUserInfo();
                tmsUserInfo.setUserId(getString(resultSet, "USER_ID"));
                tmsUserInfo.setStatus(getString(resultSet, "STATUS"));
                tmsUserInfo.setGroupCode(getString(resultSet, "GROUP_CODE"));
                tmsUserInfo.setGroupName(getString(resultSet, "GROUP_NAME"));
                tmsUserInfo.setCreateDt(getLocalDate(resultSet, "CREATE_DT"));
                tmsUserInfo.setModifyDt(getLocalDate(resultSet, "MODIFY_DT"));
                tmsUserInfo.setName(getString(resultSet, "NAME"));
                tmsUserInfo.setCode(getString(resultSet, "CODE"));
                tmsUserInfo.setLoginId(getString(resultSet, "LOGIN_ID"));
                tmsUserInfo.setExPersg(getString(resultSet, "EX_PERSG"));
                tmsUserInfo.setStartDate(getString(resultSet, "START_DATE"));
                tmsUserInfo.setEndDate(getString(resultSet, "END_DATE"));
                tmsUserInfo.setEmail(getString(resultSet, "EMAIL"));
                tmsUserInfo.setMobile(getString(resultSet, "MOBILE"));
                tmsUserInfo.setHiredDt(getLocalDate(resultSet, "HIRED_DT"));
                tmsUserInfo.setExZipCode(getString(resultSet, "EX_ZIPCODE"));
                tmsUserInfo.setExHomeAddress(getString(resultSet, "EX_HOMEADDRESS"));
                tmsUserInfo.setExBirthday(getString(resultSet, "EX_BIRTHDAY"));
                tmsUserInfo.setExSex(getString(resultSet, "EX_SEX"));
                tmsUserInfo.setExFirstName(getString(resultSet, "EX_FIRSTNAME"));
                tmsUserInfo.setExLastName(getString(resultSet, "EX_LASTNAME"));
                tmsUserInfo.setExExMail(getString(resultSet, "EX_EXMAIL"));
                tmsUserInfo.setBuCode(getString(resultSet, "EX_BUCODE"));
                tmsUserInfo.setBuName(getString(resultSet, "EX_BUNAME"));
                tmsUserInfo.setExOfficePhone(getString(resultSet, "EX_OFFICEPHONE"));
                tmsUserInfo.setExSapComCode(getString(resultSet, "EX_SAPCOMCODE"));
                tmsUserInfo.setExKostl(getString(resultSet, "EX_KOSTL"));
                tmsUserInfo.setExKText(getString(resultSet, "EX_KTEXT"));
                tmsUserInfo.setExPrctr(getString(resultSet, "EX_PRCTR"));
                tmsUserInfo.setExPText(getString(resultSet, "EX_PTEXT"));
                tmsUserInfo.setExNatio(getString(resultSet, "EX_NATIO"));
                tmsUserInfo.setExNattx(getString(resultSet, "EX_NATTX"));
                tmsUserInfo.setExRfpnr(getString(resultSet, "EX_RFPNR"));
                tmsUserInfo.setExLunar(getString(resultSet, "EX_LUNAR"));
                tmsUserInfo.setExEinDag(getString(resultSet, "EX_EINDAG"));
                tmsUserInfo.setExFtper(getString(resultSet, "EX_FTPER"));
                tmsUserInfo.setExFtTxt(getString(resultSet, "EX_FTTXT"));
                tmsUserInfo.setExObjc3(getString(resultSet, "EX_OBJC3"));
                tmsUserInfo.setExSTxt3(getString(resultSet, "EX_STXT3"));
                tmsUserInfo.setExSTell(getString(resultSet, "EX_STELL"));
                tmsUserInfo.setExSTxt4(getString(resultSet, "EX_STXT4"));
                tmsUserInfo.setExAccountNatio(getInt(resultSet, "EX_ACCOUNT_NATIO"));
                tmsUserInfo.setExWerks(getInt(resultSet, "EX_WERKS"));
                tmsUserInfo.setExWerksText(getInt(resultSet, "EX_WERKSTEXT"));
                tmsUserInfo.setExBtrtl(getInt(resultSet, "EX_BTRTL"));
                tmsUserInfo.setExBtrtlText(getInt(resultSet, "EX_BTRTL_TEXT"));
                tmsUserInfo.setExLvpos(getString(resultSet, "EX_LVPOS"));
                tmsUserInfo.setExLvposName(getString(resultSet, "EX_LVPOS_NAME"));
                tmsUserInfo.setExCmpCode(getString(resultSet, "EX_CMP_CODE"));
                tmsUserInfo.setExCmpName(getString(resultSet, "EX_CMP_NAME"));
                tmsUserInfo.setExPosCode(getString(resultSet, "EX_POS_CODE"));
                tmsUserInfo.setExPosName(getString(resultSet, "EX_POS_NAME"));
                tmsUserInfo.setExTskCode(getString(resultSet, "EX_TSK_CODE"));
                tmsUserInfo.setExTskName(getString(resultSet, "EX_TSK_NAME"));
                tmsUserInfo.setExEmpCode(getString(resultSet, "EX_EMP_CODE"));
                tmsUserInfo.setExEmpName(getString(resultSet, "EX_EMP_NAME"));
                tmsUserInfo.setExReJoin(getString(resultSet, "EX_REJOIN"));
                tmsUserInfo.setExCreateUser(getString(resultSet, "EX_CREATE_USER"));
                tmsUserInfo.setExErpId(getString(resultSet, "EX_ERP_ID"));
                tmsUserInfo.setExPtyPe(getString(resultSet, "EX_PTYPE"));
                tmsUserInfo.setIdCard(getString(resultSet, "ID_CARD"));
                return tmsUserInfo;
            }
        });
    }

    private String getString(ResultSet resultSet, String column) throws SQLException {
        String string = resultSet.getString(column);
        if(StrUtil.isBlank(string)){
            return null;
        }
        return StrUtil.trim(string);
    }

    private Integer getInt(ResultSet resultSet, String column) throws SQLException{
        return resultSet.getInt(column);
    }

    private LocalDate getLocalDate(ResultSet resultSet, String column) throws SQLException{
        Date date = resultSet.getDate(column);
        if(date == null) {
            return null;
        }
        return date.toLocalDate();
    }
}
