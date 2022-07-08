package com.amazon.svistun.service.sales.storesService;

import com.amazon.svistun.dto.sales.StoresDto;
import com.amazon.svistun.entity.sales.Stores;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface StoresService {
    List<Stores> getAllStore();
    Stores getStoreByStoreName (String storeName);
    void addStore (StoresDto storesDto);
    void updateStoreById (Long id, StoresDto storesDto);
    void deleteStoreById (Long id);
}
