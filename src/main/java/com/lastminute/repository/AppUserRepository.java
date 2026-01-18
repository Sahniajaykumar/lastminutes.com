package com.lastminute.repository;

import com.lastminute.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {

    AppUser findByUsername(String username);
    AppUser findByEmail(String email);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
