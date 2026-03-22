package com.cts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cts.binding.Student;
import com.cts.entity.StudentEntity;
import com.cts.repo.StudentRepository;

import org.springframework.ui.Model;



@Controller
public class StudentController {
	//method to load student form
	@Autowired
	private StudentRepository repo;
	
	@GetMapping("/")
	public String loadForm(Model model) {//model obj is used to send the data from controller to UI
		loadFromData(model); 
		
		
		
		
		
		return "index";
		
	}

	private void loadFromData(Model model) {
		List<String> coursesList=new ArrayList<>();
		coursesList.add("Java");
		coursesList.add("DevOps");
		coursesList.add("AWS");
		coursesList.add("Python");
		List<String> timingsList=new ArrayList<>();
		timingsList.add("Morning");
		timingsList.add("Afternoon");
		timingsList.add("Evening");
		
		model.addAttribute("courses",coursesList);
		model.addAttribute("timings",timingsList);
		
		Student student=new Student();
		model.addAttribute("student", student);
	}
	
	//method to save student data
	@PostMapping("/save")
	public String handleSubmit(Student s,Model model) {
		System.out.println(s);
		
		StudentEntity entity=new StudentEntity();
		//copy the data from student obj(binding obj) to student entity obj
		BeanUtils.copyProperties(s, entity);
		
		entity.setTimings(Arrays.toString(s.getTimings()));
		repo.save(entity);
		
		loadFromData(model); 
		
		model.addAttribute("msg","Student Saved");
		
		return "index";
		
		
		
	}
	
	//method to display saved student data
	@GetMapping("/viewStudents")
	public String getStudentsData(Model model) {
		
		//logic to fetch and send students data
		
		List<StudentEntity> studentsList=repo.findAll();
		model.addAttribute("students", studentsList);
		
		return "data";
	}

}
