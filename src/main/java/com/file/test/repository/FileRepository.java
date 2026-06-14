package com.file.test.repository;

import com.file.test.model.CustomFile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<CustomFile,Long> {
}
