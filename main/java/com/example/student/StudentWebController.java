package com.example.student;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentWebController {
	final StudentDAO dao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public StudentWebController(StudentDAO dao) {
		this.dao = dao;
	}
	
	//학생 추가
	@PostMapping("/add")
	public String insertStudent(@ModelAttribute Student student, Model m) {
		try {
		dao.insert(student);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("학생 추가(등록) 과정에서 문제 발생!!");
			m.addAttribute("error", "학생이 정상적으로 등록되지 않았습니다!!");
		}
		
		return "redirect:/student/list";
	}
	
	//학생목록 
	@GetMapping("/list")
	public String studentList(Model m) {
		List<Student> studentList;
		
		try {
			studentList = dao.getAll();
			m.addAttribute("studentList", studentList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("학생목록 생성과정에서 문제 발생!!");
			m.addAttribute("error", "뉴스 목록이 정상적으로 처리되지 않았습니다!!");
		}
		return "student/studentInfo";
	}
	
//	//학생 상세정보
//	@GetMapping("/{id}")
//	public String getStudent(@PathVariable int id, Model m) {
//		Student s;
//		
//		try {
//			s = dao.getStudent(id);
//			m.addAttribute("student", s);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("학생정보를 가져오는 과정에서 문제 발생!!");
//			m.addAttribute("error", "학생정보를 정상적으로 가져오지 못했습니다!!");
//		}
//		
//		return "student/studentInfo";
//	}
	
	

}
