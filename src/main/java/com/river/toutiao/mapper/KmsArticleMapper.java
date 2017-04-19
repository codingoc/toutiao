package com.river.toutiao.mapper;

import com.river.toutiao.model.KmsArticle;

public interface KmsArticleMapper {
    int deleteByPrimaryKey(Integer seqid);

    int insert(KmsArticle record);

    int insertSelective(KmsArticle record);

    KmsArticle selectByPrimaryKey(Integer seqid);

    int updateByPrimaryKeySelective(KmsArticle record);

    int updateByPrimaryKey(KmsArticle record);
}