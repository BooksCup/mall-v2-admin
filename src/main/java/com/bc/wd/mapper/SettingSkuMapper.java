package com.bc.wd.mapper;

import com.bc.wd.entity.model.SettingSkuKeyModel;
import com.bc.wd.entity.model.SettingSkuValueModel;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @program: whl-project
 * @description:
 * @author: Mr.Wang
 * @create: 2020-04-22 10:09
 **/
@Repository
public interface SettingSkuMapper {

    Page<SettingSkuKeyModel> getSkuKeyPage(Map<String, Object> params);

    SettingSkuKeyModel getById(String id);

    List<SettingSkuKeyModel> getAll(String storeId);

    void insertSkuKey(SettingSkuKeyModel settingSkuKeyModel);

    void insertSkuValue(SettingSkuValueModel settingSkuValueModel);

    void insertSkuValueList(List<SettingSkuValueModel> settingSkuValueModelList);

    void setKeyUsed(@Param("storeId") String storeId,@Param("keyList") List<String> keyList);

    void setValueUsed(@Param("storeId") String storeId,@Param("valueList") List<String> valueList);

    Integer getKeyMaxSort(String storeId);

    Integer getValueMaxSort(String keyId);
}
