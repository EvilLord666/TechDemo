/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.techdemo.dal;

import ru.techdemo.dal.entity.RoleEntity;
import ru.techdemo.dal.entity.UserEntity;
import ru.techdemo.dal.repository.IRepository;

/**
 *
 * @author michael
 */
public interface IApplicationDataContext {
    public IRepository<UserEntity, Long> getUsersRepository();
    public IRepository<RoleEntity, Long> getRolesRepository();
}
