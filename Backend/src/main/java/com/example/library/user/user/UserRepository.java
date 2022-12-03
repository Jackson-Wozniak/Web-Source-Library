package com.example.library.user.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, String> {

    @Transactional
    @Modifying
    @Query("UPDATE user a " +
            "SET a.enabled = TRUE WHERE a.username =:username")
    int enableUser(String username);
}
