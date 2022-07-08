package com.amazon.svistun.repository.sales;

import com.amazon.svistun.entity.sales.Stores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoresRepository extends JpaRepository<Stores, Long> {
    Stores getStoresByStoreId(Long id);
    Stores getStoresByStoreName(String storeName);
    Boolean existsStoresByStoreName(String storeName);
    void deleteByStoreId(Long storeId);


}
