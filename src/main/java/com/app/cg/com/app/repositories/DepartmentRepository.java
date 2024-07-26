package com.app.cg.com.app.repositories;

import com.app.cg.com.app.entities.DepartmentEntity;
import com.app.cg.com.app.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    DepartmentEntity findByManager(EmployeeEntity employeeEntity);
}
