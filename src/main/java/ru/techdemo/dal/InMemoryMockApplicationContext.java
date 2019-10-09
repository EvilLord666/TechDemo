/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.techdemo.dal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.techdemo.dal.entity.RoleEntity;
import ru.techdemo.dal.entity.UserEntity;
import ru.techdemo.dal.repository.IRepository;
import ru.techdemo.dal.repository.MockRepository;


public class InMemoryMockApplicationContext implements IApplicationDataContext {

    public InMemoryMockApplicationContext(){
        rolesRepository = new MockRepository<RoleEntity>(getMockedRoles());
        usersRepository = new MockRepository<UserEntity>(getMockedUsers());
    }
    
    @Override
    public IRepository<UserEntity, Long> getUsersRepository() {
        return usersRepository;
    }

    @Override
    public IRepository<RoleEntity, Long> getRolesRepository() {
        return rolesRepository;
    }
    
    private List<RoleEntity> getMockedRoles(){
        List<RoleEntity> roles = new ArrayList<RoleEntity>();
        roles.add(new RoleEntity(ADMIN_ROLE_ID, "ADMIN"));
        roles.add(new RoleEntity(USER_ROLE_ID, "USER"));
        roles.add(new RoleEntity(GUEST_ROLE_ID, "GUEST"));
        return roles;
    }
    
    private List<UserEntity> getMockedUsers(){
        List<UserEntity> users = new ArrayList<UserEntity>();
        RoleEntity adminRole = rolesRepository.getById(ADMIN_ROLE_ID);
        RoleEntity userRole = rolesRepository.getById(USER_ROLE_ID);
        
        users.add(new UserEntity(1L, "admin", passwordEncoder.encode("123"), "ADMIN", "ADMIN", Arrays.asList(adminRole, userRole)));
        users.add(new UserEntity(1L, "root", passwordEncoder.encode("123"), "SUPERUSER", "SUPERUSER", Arrays.asList(adminRole, userRole)));
        users.add(new UserEntity(1L, "evillord", passwordEncoder.encode("1234"), "Michael", "Ushakov", Arrays.asList(userRole)));
        return users;
    }
    
    private final Long ADMIN_ROLE_ID = 1L;
    private final Long USER_ROLE_ID = 2L;
    private final Long GUEST_ROLE_ID = 3L;
    
    private IRepository<UserEntity, Long> usersRepository;
    private IRepository<RoleEntity, Long> rolesRepository;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
}
