package com.rci.wyndham.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;

import com.rci.wyndham.entity.Employee;
 
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // Additional query methods (if needed) can go here
}
