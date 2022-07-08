package com.amazon.svistun.controller.sales;

import com.amazon.svistun.dto.sales.StoresDto;
import com.amazon.svistun.entity.sales.Stores;
import com.amazon.svistun.service.sales.storesService.StoresServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/v1/store")
public class StoresController {
    private final StoresServiceImpl storesService;

    public StoresController(StoresServiceImpl storesService) {
        this.storesService = storesService;
    }

    @GetMapping
    public ResponseEntity<?> getAllStores() {
        return ResponseEntity.ok().body(storesService.getAllStore());
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getStoreByStoreName(@PathVariable String name) {
        return ResponseEntity.ok().body(storesService.getStoreByStoreName(name));
    }

    @PostMapping
    public void addStore(@RequestBody StoresDto storesDto) {
        storesService.addStore(storesDto);
    }

    @PatchMapping("/{id}")
    public void editStore(@PathVariable Long id,
                          @RequestBody StoresDto storesDto) {
        storesService.updateStoreById(id, storesDto);
    }
    @DeleteMapping("/{id}")
    public  void deleteStore(@PathVariable Long id){
        storesService.deleteStoreById(id);
    }

}
