package vn.shortsoft.products.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import vn.shortsoft.products.model.Roles;
import vn.shortsoft.products.reponsitory.RoleRepository;

@Component
@Order(1)
@Slf4j
public class CreateRole implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            Roles adminRole = Roles.builder().name("admin").build();
            Roles customerRole = Roles.builder().name("customer").build();
            roleRepository.save(adminRole);
            roleRepository.save(customerRole);
            log.info(">>>> Created admin and customer roles...");
        }
        System.out.println(">>> Hello from the CreateRoles CommandLineRunner...");
    }

    public Roles findFirstByName() {
        return roleRepository.findFirstByName("admin");
    }
}
