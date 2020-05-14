package com.tonlp.sc.mapper;

import com.tonlp.sc.domain.ScOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {

    void createOrder(ScOrder order);

    void update(@Param("id") Long id, @Param("status") Integer status);

}
