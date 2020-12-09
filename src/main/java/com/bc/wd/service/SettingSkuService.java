package com.bc.wd.service;

import com.bc.wd.entity.model.SettingSkuKeyModel;
import com.bc.wd.entity.model.SettingSkuValueModel;
import com.bc.wd.mapper.SettingSkuMapper;
import com.bc.wd.utils.CommonUtils;
import com.bc.wd.utils.Result;
import com.bc.wd.utils.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: whl-project
 * @description:
 * @author: Mr.Wang
 * @create: 2020-04-22 11:47
 **/
@Service
public class SettingSkuService {

    @Autowired
    private SettingSkuMapper settingSkuMapper;

    public Result getAll(String storeId) {
        return Result.success(settingSkuMapper.getAll(storeId));
    }

    @Transactional
    public Result insert(SettingSkuKeyModel settingSkuKeyModel) {
        settingSkuKeyModel.setId(CommonUtils.generateID());
        Integer maxSort = settingSkuMapper.getKeyMaxSort(settingSkuKeyModel.getStoreId());
        String kCode = "k_";
        String vCode = "v_";
        String prefixKCode = StringUtils.getAllFirstLetter(settingSkuKeyModel.getName()) + "_";
        if(maxSort == null || maxSort.intValue() == 0){
            maxSort = 1;
        }else {
            maxSort += 1;
        }
        if(maxSort < 10){
            kCode = kCode + prefixKCode + "00"+maxSort;
        }else if(maxSort < 100){
            kCode = kCode + prefixKCode + "0"+maxSort;
        }
        settingSkuKeyModel.setSort(maxSort);
        settingSkuKeyModel.setCode(kCode);
        settingSkuMapper.insertSkuKey(settingSkuKeyModel);
        if(CollectionUtils.isNotEmpty(settingSkuKeyModel.getSettingSkuValueModelList())){
            int i = 1;
            for(SettingSkuValueModel m : settingSkuKeyModel.getSettingSkuValueModelList()){
                m.setId(CommonUtils.generateID());
                m.setStoreId(settingSkuKeyModel.getStoreId());
                m.setKeyId(settingSkuKeyModel.getId());
                m.setSort(i++);
                if(maxSort<10){
                    m.setCode(vCode + prefixKCode + "00" + m.getSort());
                }else if(maxSort < 100){
                    m.setCode(vCode + prefixKCode + "0" + m.getSort());
                }
            }
            settingSkuMapper.insertSkuValueList(settingSkuKeyModel.getSettingSkuValueModelList());
        }
        return Result.success();
    }

    public Result insertValue(SettingSkuValueModel settingSkuValueModel) {
        String id = CommonUtils.generateID();
        settingSkuValueModel.setId(id);
        String vCode = "v_";
        SettingSkuKeyModel s = settingSkuMapper.getById(settingSkuValueModel.getKeyId());
        String prefixKCode = StringUtils.getAllFirstLetter(s.getName()) + "_";
        Integer maxSort = settingSkuMapper.getValueMaxSort(settingSkuValueModel.getKeyId());
        if(maxSort == null || maxSort.intValue() == 0){
            maxSort = 1;
        }else {
            maxSort += 1;
        }
        if(maxSort<10){
            settingSkuValueModel.setCode(vCode + prefixKCode + "00" + settingSkuValueModel.getSort());
        }else if(maxSort < 100){
            settingSkuValueModel.setCode(vCode + prefixKCode + "0" + settingSkuValueModel.getSort());
        }
        settingSkuValueModel.setSort(maxSort);
        settingSkuValueModel.setCode(vCode);
        settingSkuMapper.insertSkuValue(settingSkuValueModel);
        return Result.success(id);
    }

    public Result insertKey(SettingSkuKeyModel settingSkuKeyModel) {
        String id = CommonUtils.generateID();
        settingSkuKeyModel.setId(id);
        String kCode = "k_";
        String prefixKCode = StringUtils.getAllFirstLetter(settingSkuKeyModel.getName()) + "_";
        Integer maxSort = settingSkuMapper.getKeyMaxSort(settingSkuKeyModel.getStoreId());
        if(maxSort == null || maxSort.intValue() == 0){
            maxSort = 1;
        }else {
            maxSort += 1;
        }
        if(maxSort < 10){
            kCode = kCode + prefixKCode + "00"+maxSort;
        }else if(maxSort < 100){
            kCode = kCode + prefixKCode + "0"+maxSort;
        }
        settingSkuKeyModel.setSort(maxSort);
        settingSkuKeyModel.setCode(kCode);
        settingSkuMapper.insertSkuKey(settingSkuKeyModel);
        return Result.success(id);
    }
}
