package com.java.cache.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.cache.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
