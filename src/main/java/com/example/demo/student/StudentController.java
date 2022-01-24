// controller class that has all the resources for our API 
package com.example.demo.student;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //used for creating RESTful web services, assigns the class to serve REST endpoint 
@RequestMapping(path = "api/v1/student") // go to this endpoint for sending GET request to getStudents()
public class StudentController 
{
	private final StudentService studentService;
	
	@Autowired // injecting studentService component into StudentController class, using autowiring 
	public StudentController(StudentService studentService)
	{
		this.studentService = studentService;
	}
	
	@GetMapping // this service method is served using REST API GET request
	public List<Student> getStudents()
	{
		return studentService.getStudents();
	}
	
	@PostMapping
	public void registerStudent(@RequestBody Student student)//map the request body of student we want to add to 'student'
	{
		studentService.addNewStudent(student);
	}
	
	@DeleteMapping(path = "{studentId}")
	public void deleteStudent(@PathVariable("studentId") Long studentId)
	{
		studentService.deleteStudent(studentId);
	}
	
	@PutMapping(path = "{studentId}")
	public void updateStudent(
			@PathVariable("studentId") Long studentId,
			@RequestParam(required = false) String name, // name and email are not required
			@RequestParam(required = false) String email)			
	{
		studentService.updateStudent(studentId, name, email);
	}
}
