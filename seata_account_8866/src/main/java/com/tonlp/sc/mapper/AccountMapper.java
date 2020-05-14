package com.tonlp.sc.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface AccountMapper {

    void updateMoneyByUserId(@Param("userId") Long userId, @Param("money") BigDecimal money);

}
