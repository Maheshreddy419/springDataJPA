package com.app.cg.com.app.services;

import com.app.cg.com.app.entities.DepartmentEntity;
import com.app.cg.com.app.entities.EmployeeEntity;
import com.app.cg.com.app.repositories.DepartmentRepository;
import com.app.cg.com.app.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final EmployeeRepository employeeRepository;

    public DepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<DepartmentEntity> getAllDepartments(){
        return departmentRepository.findAll();
    }

    public DepartmentEntity getDepartmentByID(Long id){
        return departmentRepository.findById(id).orElse(null);
    }

    public DepartmentEntity saveDepartment(DepartmentEntity departmentEntity){
        return departmentRepository.save(departmentEntity);
    }

    public DepartmentEntity assignMangerToDepartment(Long departmentId, Long employeeId) {
      Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
      Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

      return departmentEntity.flatMap(department ->
               employeeEntity.map(employee -> {
                   department.setManager(employee);
                   return departmentRepository.save(department);
               })).orElse(null);
    }

    public DepartmentEntity getAssignedDepartmentOfManger(Long id) {
       Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
       return employeeEntity.map(EmployeeEntity::getManagedDepartment).orElse(null);

//        EmployeeEntity employeeEntity = EmployeeEntity.builder().id(id).build();
//        return departmentRepository.findByManager(employeeEntity);
    }

    public DepartmentEntity assignWorkerToDepartment(Long departmentId, Long employeeId) {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

        return departmentEntity.flatMap(department ->
                employeeEntity.map(employee -> {
                    department.getWorkers().add(employee);
                    return departmentRepository.save(department);
                })).orElse(null);
    }
}
