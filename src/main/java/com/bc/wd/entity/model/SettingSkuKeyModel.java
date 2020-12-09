package com.bc.wd.entity.model;

import com.bc.wd.entity.SettingSkuKey;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: server
 * @description:
 * @author: Mr.Wang
 * @create: 2020-12-03 09:49
 **/
@Data
public class SettingSkuKeyModel extends SettingSkuKey implements Serializable {

    private List<SettingSkuValueModel> settingSkuValueModelList;
}
