package com.tonlp.sc.service;

import com.tonlp.sc.mapper.StorageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageService {

    @Autowired
    private StorageMapper storageMapper;

    public void decrease(Long productId, Integer count) {
        storageMapper.updateCountByProduct(productId, count);
    }
}
