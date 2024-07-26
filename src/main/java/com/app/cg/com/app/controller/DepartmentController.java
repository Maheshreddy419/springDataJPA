package com.app.cg.com.app.controller;

import com.app.cg.com.app.entities.DepartmentEntity;
import com.app.cg.com.app.services.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/{departmentId}")
    public DepartmentEntity getAllDepartments(@PathVariable(name = "departmentId") Long id){
        return departmentService.getDepartmentByID(id);
    }

    @PostMapping
    public DepartmentEntity saveDepartment(@RequestBody DepartmentEntity departmentEntity){
        return departmentService.saveDepartment(departmentEntity);
    }

    @PutMapping(path = "/{departmentId}/manager/{employeeId}")
    public DepartmentEntity assignMangerToDepartment(@PathVariable Long departmentId,
                                                     @PathVariable Long employeeId){
        return departmentService.assignMangerToDepartment(departmentId,employeeId);
    }

    @GetMapping(path = "/assignedDepartmentToManger/{employeeId}")
    public DepartmentEntity getAssignedDepartmentOfManger(@PathVariable(name = "employeeId") Long id){
        return departmentService.getAssignedDepartmentOfManger(id);
    }

    @PutMapping(path = "/{departmentId}/worker/{employeeId}")
    public DepartmentEntity assignWorkerToDepartment(@PathVariable Long departmentId,
                                                     @PathVariable Long employeeId){
        return departmentService.assignWorkerToDepartment(departmentId,employeeId);
    }
}
