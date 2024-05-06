package net.javaguides.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	Department findByDepartmentCode(String departmentCode);
}
