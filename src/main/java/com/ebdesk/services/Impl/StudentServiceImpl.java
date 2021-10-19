package com.ebdesk.services.Impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebdesk.models.Student;
import com.ebdesk.repositories.StudentRepository;
import com.ebdesk.services.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	@Autowired
	StudentRepository repo;

	@Override
	public List<Student> getAllStudent() {
		// TODO Auto-generated method stub
		return (List<Student>) repo.findAll();
	}

	@Override
	public Student getStudentById(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}

	@Override
	public void addStudent(Student student) {
		// TODO Auto-generated method stub
		repo.save(student);
	}

	@Override
	public void deleteStudent(int id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Student getStudentByName(String name, String last) {
		// TODO Auto-generated method stub
		return repo.getStudentByName(name, last);
	}
	
	
}
