package com.bidding.bootstrap;

import com.bidding.model.Role;
import com.bidding.model.User;
import com.bidding.repository.RoleRepository;
import com.bidding.repository.UserRepository;
import com.bidding.service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BootstrapDataPopulator implements InitializingBean {

    private final Logger LOG = LoggerFactory.getLogger(BootstrapDataPopulator.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional()
    public void afterPropertiesSet() throws Exception {
        LOG.info("Bootstrapping data...");
        createUserRole();
        createAdminRole();
        createUser();

        createAdminUser();

        LOG.info("...Bootstrapping completed");
    }

    private void createAdminUser() {
        if (userRepository.findByLogin("ADMIN") != null) {
            return;
        }

        LOG.info("... creating admin");

        User user = new User();
        user.setLogin("admin");
        user.setName("admin");
        user.setPassword("admin");
        userDetailsService.saveUser(user, "ROLE_ADMIN");
    }

    private void createAdminRole() {
        if (roleRepository.findByName("ROLE_ADMIN") != null) {
            return;
        }
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        roleRepository.save(role);
    }

    private void createUserRole() {
        if (roleRepository.findByName("ROLE_USER") != null) {
            return;
        }
        Role role = new Role();
        role.setName("ROLE_USER");
        roleRepository.save(role);
    }

    private void createUser() {
        if (userRepository.findByLogin("user") != null) {
            return;
        }

        LOG.info("... creating user");

        User user = new User();
        user.setLogin("user");
        user.setName("user");
        user.setPassword("user");
        userDetailsService.saveUser(user, "ROLE_USER");
    }

}