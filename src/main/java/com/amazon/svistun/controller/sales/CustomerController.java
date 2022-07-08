package com.amazon.svistun.controller.sales;


import com.amazon.svistun.dto.sales.CustomerDto;
import com.amazon.svistun.entity.sales.Customer;
import com.amazon.svistun.service.sales.customerService.CustomerServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerServiceImpl clientService;

    public CustomerController(CustomerServiceImpl clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClient(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }
    @PostMapping
    public void createClient(@RequestBody CustomerDto customerDto){
        clientService.createClient(customerDto);
    }

    @PatchMapping("/{id}")
    public Customer editClient(@PathVariable Long id,
                               @RequestBody CustomerDto customerDto) {

        return clientService.updateClient(id,customerDto);
    }


}
