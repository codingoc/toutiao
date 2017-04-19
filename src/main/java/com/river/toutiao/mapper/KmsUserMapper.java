package com.river.toutiao.mapper;

import com.river.toutiao.model.KmsUser;

public interface KmsUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(KmsUser record);

    int insertSelective(KmsUser record);

    KmsUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(KmsUser record);

    int updateByPrimaryKey(KmsUser record);
}