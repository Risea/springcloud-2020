package com.tonlp.sc.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StorageMapper {

    void updateCountByProduct(@Param("productId") Long productId, @Param("count") Integer count);
}
