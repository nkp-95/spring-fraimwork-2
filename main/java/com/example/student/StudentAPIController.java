package com.example.student;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentAPIController {

	final StudentDAO dao;
	
	@Autowired
	public StudentAPIController(StudentDAO dao) {
		this.dao = dao;
	}
	
	//학생 추가 API
	@PostMapping
	public String insertStudent(@RequestBody Student student) {
		
		try {
			dao.insert(student);
		} catch (Exception e) {
			e.printStackTrace();
			return "Student API: 학생 등록 실패!!";
		}
		
		return "Student API: 학생 등록 성공!!";
	}
	
	//학생 목록 조회 API
	@GetMapping
	public List<Student> getStudentList(){
		List<Student> studentList = null;
		
		try {
			studentList = dao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentList;
	}
	
	//학생 상세정보
	@GetMapping("{id}")
	public Student getStudent(@PathVariable("id")int id) {
		Student student = null;
		
		try {
			student = dao.getStudent(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return student;
	}
	
	
}
