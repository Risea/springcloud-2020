package com.tonlp.sc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScAccount implements Serializable{

    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 总额度
     */
    private Integer total;

    /**
     * 已用额度
     */
    private Integer used;

    /**
     * 剩余额度
     */
    private Integer residue;

}
