package com.ebdesk.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ebdesk.models.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {
	
	@Query(value = "SELECT * FROM student WHERE firstname = ?1 AND lastname = ?2", nativeQuery = true)
	Student getStudentByName(String name, String last);
}
