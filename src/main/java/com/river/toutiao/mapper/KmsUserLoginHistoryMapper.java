package com.river.toutiao.mapper;

import com.river.toutiao.model.KmsUserLoginHistory;

public interface KmsUserLoginHistoryMapper {

    int deleteByPrimaryKey(Integer seqid);

    int insert(KmsUserLoginHistory record);

    int insertSelective(KmsUserLoginHistory record);

    KmsUserLoginHistory selectByPrimaryKey(Integer seqid);

    int updateByPrimaryKeySelective(KmsUserLoginHistory record);

    int updateByPrimaryKey(KmsUserLoginHistory record);
}