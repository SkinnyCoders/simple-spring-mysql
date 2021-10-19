package com.ebdesk.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ebdesk.models.MessageModel;
import com.ebdesk.models.Student;
import com.ebdesk.services.Impl.StudentServiceImpl;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
	
	@Autowired
	StudentServiceImpl servImpl;
	
	@GetMapping("/list")
	public ResponseEntity<?> getAllData(){
		MessageModel msg = new MessageModel();
		
		List<Student> studentData = new ArrayList<Student>();
		servImpl.getAllStudent().forEach(studentData::add);
		
		Map<String, Object> datas = new HashMap<String, Object>();
		datas.put("datas", studentData);
		datas.put("total", studentData.size());
		
		msg.setStatus(true);
		msg.setMessage("Success Get Data");
		msg.setData(datas);
		
		
		if (studentData.isEmpty()) {
			msg.setStatus(false);
			msg.setMessage("Data Not Found");
			return new ResponseEntity<MessageModel>(msg, HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(msg, HttpStatus.OK) ;
	}
	
	@GetMapping("/byid")
	public ResponseEntity<?> getById(@RequestParam int id){
		MessageModel msg = new MessageModel();
		
		try {
			Student data_student = servImpl.getStudentById(id);
			msg.setData(data_student);
			msg.setStatus(true);
			msg.setMessage("success");
			return new ResponseEntity<MessageModel>(msg, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			msg.setStatus(false);
			msg.setMessage("failed");
			return new ResponseEntity<MessageModel>(msg, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/byname")
	public ResponseEntity<?> getByName(@RequestParam String name, @RequestParam String last){
		MessageModel msg = new MessageModel();
		
		try {
			Student data_student = servImpl.getStudentByName(name, last);
			msg.setData(data_student);
			msg.setStatus(true);
			msg.setMessage("success");
			return new ResponseEntity<MessageModel>(msg, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			msg.setStatus(false);
			msg.setMessage("failed");
			return new ResponseEntity<MessageModel>(msg, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/add-student")
	public ResponseEntity<?> addStudent(@RequestBody Student stdn){
		MessageModel msg = new MessageModel();
		
		try {
			servImpl.addStudent(stdn);
			
			msg.setStatus(true);
			msg.setMessage("suceess");
			return new ResponseEntity<>(msg, HttpStatus.CREATED) ;
		} catch (Exception e) {
			// TODO: handle exception
			msg.setStatus(false);
			msg.setMessage("failed");
			return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR) ;
		}
	}
}
