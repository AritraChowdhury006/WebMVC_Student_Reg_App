package com.cts.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer>{

}
