package net.javaguides.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
