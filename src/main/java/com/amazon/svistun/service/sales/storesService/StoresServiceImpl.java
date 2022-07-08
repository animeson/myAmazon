package com.amazon.svistun.service.sales.storesService;

import com.amazon.svistun.dto.sales.StoresDto;
import com.amazon.svistun.entity.sales.Stores;
import com.amazon.svistun.repository.sales.StoresRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Store;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class StoresServiceImpl implements StoresService {
    private final StoresRepository storesRepository;

    public StoresServiceImpl(StoresRepository storesRepository) {
        this.storesRepository = storesRepository;
    }

    @Override
    public List<Stores> getAllStore() {
        return storesRepository.findAll();
    }

    @Override
    public Stores getStoreByStoreName(String storeName) {
        return storesRepository.getStoresByStoreName(storeName);
    }

    @Override
    @Transactional
    public void addStore(StoresDto storesDto) {
        if (storesDto != null && !storesDto.getStoreName().isEmpty()) {
            if (!storesRepository.existsStoresByStoreName(storesDto.getStoreName())) {
                Stores store = new Stores();
                BeanUtils.copyProperties(storesDto, store);
                storesRepository.save(store);
            } else {
                log.error("There's already a store with the name {}. Think of another name", storesDto.getStoreName());
            }
        } else {
            log.error("Information about the store can not be blank or equal null");
        }
    }

    @Override
    public void updateStoreById(Long id, StoresDto storesDto) {
        Stores store = storesRepository.getStoresByStoreId(id);
        BeanUtils.copyProperties(storesDto,store);
        storesRepository.save(store);
    }

    @Override
    @Transactional
    public void deleteStoreById(@PathVariable Long id) {
        storesRepository.deleteByStoreId(id);
    }
}
