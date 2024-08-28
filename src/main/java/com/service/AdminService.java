package com.service;

import com.entity.Admin;
import java.util.List;
import java.util.Optional;

public interface AdminService {

    Admin saveAdmin(Admin admin);

    Optional<Admin> getAdminById(Long id);

    List<Admin> getAllAdmins();

    Admin updateAdmin(Long id, Admin admin);

    void deleteAdmin(Long id);

    Optional<Admin> findByUsername(String username);
}
