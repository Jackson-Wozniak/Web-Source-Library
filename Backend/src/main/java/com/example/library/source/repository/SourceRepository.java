package com.example.library.source.repository;

import com.example.library.source.entity.SourceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SourceRepository<T> extends JpaRepository<T, SourceId> {
}
