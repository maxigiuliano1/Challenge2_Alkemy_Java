package com.example.challenge2_alkemy.repositories;

import com.example.challenge2_alkemy.model.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{
    public Users findByEmail(String email);
    public Users findByUsername(String username);
}
