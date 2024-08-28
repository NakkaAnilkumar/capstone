package com.controller;

import com.entity.Admin;
import com.service.AdminService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admins")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    
    @Autowired
	RestTemplate restTemplate;

    @PostMapping
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        Admin savedAdmin = adminService.saveAdmin(admin);
        return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable("id") Long id) {
        Optional<Admin> admin = adminService.getAdminById(id);
        return admin.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable("id") Long id, @RequestBody Admin admin) {
        try {
            Admin updatedAdmin = adminService.updateAdmin(id, admin);
            return ResponseEntity.ok(updatedAdmin);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable("id") Long id) {
        try {
            adminService.deleteAdmin(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Admin> getAdminByUsername(@PathVariable("username") String username) {
        Optional<Admin> admin = adminService.findByUsername(username);
        return admin.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
    
    //---------------------------------------------------------------------
//    @GetMapping(path = "/id/{id}")
//    @CircuitBreaker(name = "CUSTOMER-SERVICE", fallbackMethod = "getDataFallback")
//    public ResponseEntity<RequiredResponse> getAllDataBasedOnAdminId(@PathVariable Long id) {
//        RequiredResponse requiredResponse = new RequiredResponse();
//        
//        Optional<Admin> adminOpt = adminService.getAdminById(id);
//        if (adminOpt.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        Admin admin = adminOpt.get();
//        requiredResponse.setAdmin(admin);
//        
//        // Fetch Customer data from another service
//        Customer customer = restTemplate.getForObject("http://localhost:9091/api/customers/" + id, Customer.class);
//        requiredResponse.setCustomer(customer);
//
//        return new ResponseEntity<>(requiredResponse, HttpStatus.OK);
//    }
//
//    public ResponseEntity<RequiredResponse> getDataFallback(Long id, Throwable throwable) {
//        RequiredResponse requiredResponse = new RequiredResponse();
//        
//        Optional<Admin> adminOpt = adminService.getAdminById(id);
//        if (adminOpt.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        Admin admin = adminOpt.get();
//        requiredResponse.setAdmin(admin);
//
//        // Fallback handling: no customer data available, just return admin details
//        return new ResponseEntity<>(requiredResponse, HttpStatus.OK);
//    }

