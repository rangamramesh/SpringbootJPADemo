package com.ramesh.demo.repositories;

import com.ramesh.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserJPARepository extends JpaRepository<User, Long> {

    List<User> findByName(String name);
}