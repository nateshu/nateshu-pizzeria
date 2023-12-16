package com.nateshu.pizza.persitence.repository;

import com.nateshu.pizza.persitence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {
}