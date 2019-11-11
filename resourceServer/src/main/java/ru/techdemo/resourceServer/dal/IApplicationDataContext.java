/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.techdemo.resourceServer.dal;

import ru.techdemo.resourceServer.dal.entity.RoleEntity;
import ru.techdemo.resourceServer.dal.entity.UserEntity;
import ru.techdemo.resourceServer.dal.repository.IRepository;

/**
 *
 * @author michael
 */
public interface IApplicationDataContext {
    public IRepository<UserEntity, Long> getUsersRepository();
    public IRepository<RoleEntity, Long> getRolesRepository();
}
