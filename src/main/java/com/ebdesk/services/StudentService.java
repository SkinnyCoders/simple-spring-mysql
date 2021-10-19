package com.ebdesk.services;

import java.util.List;

import com.ebdesk.models.Student;

public interface StudentService {

	public List<Student> getAllStudent();
	
	public Student getStudentById(int id);
	
	public void addStudent(Student student);
	
	public void deleteStudent(int id);
	
	public Student getStudentByName(String name, String last);
}
