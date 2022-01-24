//BUSINESS LOGIC
package com.example.demo.student;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService 
{
	private final StudentRepository studentRepository;
	
	@Autowired
	public StudentService(StudentRepository studentRepository)
	{
		this.studentRepository = studentRepository;
	}
	
	public List<Student> getStudents()
	{
		/*return List.of(
				new Student(1L,"Kush",23,"tmng.mohit@gmail.com", LocalDate.of(1997, 11, 21))
				);
		*/	
		return studentRepository.findAll();
				
	}

	public void addNewStudent(Student student) 
	{
		// TODO Auto-generated method stub
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
		
		if(studentOptional.isPresent())
		{
			throw new IllegalStateException("EMAIL IS ALREADY TAKEN");
		}
		
		studentRepository.save(student);
		System.out.println(student);	
	}

	public void deleteStudent(Long studentId) 
	{
		// TODO Auto-generated method stub
		boolean exist = studentRepository.existsById(studentId);
		if(!exist)
		{
			throw new IllegalStateException("Student with id: " + studentId + "doesn't exist");
			
		}
		studentRepository.deleteById(studentId);
	}

	@Transactional
	public void updateStudent(Long studentId, String name, String email) 
	{
		// TODO Auto-generated method stub
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new IllegalStateException
				("student with id: " + studentId + "doesn't exist."));
		
		if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name))
		{
			student.setName(name);
		}
		
		if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email))
		{
			Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
			if(studentOptional.isPresent())
			{
				throw new IllegalStateException("email is already taken");
			}
			student.setEmail(email);
		}
		
	}
}
