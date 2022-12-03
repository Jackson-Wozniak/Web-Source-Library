package com.example.library.source;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SourceRepository extends JpaRepository<Source, Long> {

    @Query(value = "select * from source where user=:username", nativeQuery = true)
    List<Source> findSourceByName(String username);
}
