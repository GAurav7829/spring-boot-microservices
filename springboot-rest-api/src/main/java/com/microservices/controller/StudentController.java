package com.microservices.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.bean.Student;

@RestController
@RequestMapping("/students")
public class StudentController {

	// http://localhost:8080/students/student
	@GetMapping("/student")
	public ResponseEntity<Student> getStudent() {
		Student student = new Student(1, "Gaurav", "Singh");
//		return new ResponseEntity<>(student, HttpStatus.OK);
//		return ResponseEntity.ok(student);
		return ResponseEntity.ok().header("custom-header", "Gaurav").body(student);
	}

	// http://localhost:8080/students
	@GetMapping
	public ResponseEntity<List<Student>> getStudents() {
		List<Student> students = populateStudents();
		return ResponseEntity.ok(students);
	}

	// http://localhost:8080/students/1
	@GetMapping("/{id}")
	public ResponseEntity<Student> studentPathVariable(@PathVariable int id) {
		List<Student> students = populateStudents();
		Student student = null;
		for (Student st : students) {
			if (st.getId() == id) {
				student = st;
				break;
			}
		}
		return ResponseEntity.ok(student);
	}

	// spring boot rest api with request param
	// http://localhost:8080/students/query?id=1&firstName=Grv
	@GetMapping("/query")
	public ResponseEntity<Student> studentRequestVariable(@RequestParam("id") int id, @RequestParam String firstName) {
		List<Student> students = populateStudents();
		Student student = null;
		for (Student st : students) {
			if (st.getId() == id) {
				student = st;
				break;
			}
		}
		student.setFirstName(firstName);
		return ResponseEntity.ok(student);
	}

	// handle post request
	// http://localhost:8080/students/create
	@PostMapping("/create")
//	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		System.out.println(student.getId());
		System.out.println(student.getFirstName());
		System.out.println(student.getLastName());
		return new ResponseEntity<>(student, HttpStatus.CREATED);
	}

	// handle put request
	// http://localhost:8080/students/1/update
	@PutMapping("/{id}/update")
	public ResponseEntity<?> updateStudent(@PathVariable int id, @RequestBody Student student) {
		List<Student> students = populateStudents();
		for (Student st : students) {
			if (st.getId() == id) {
				st.setFirstName(student.getFirstName());
				st.setLastName(student.getLastName());
				return ResponseEntity.ok(st);
			}
		}
		return new ResponseEntity<>("User With Id "+id+" Not Found", HttpStatus.NOT_FOUND);
	}

	// handle delete request
	// http://localhost:8080/students/1/delete
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<List<Student>> deleteStudent(@PathVariable int id) {
		List<Student> students = populateStudents();
		Iterator<Student> iterator = students.iterator();
		while (iterator.hasNext()) {
			Student student = iterator.next();
			if (student.getId() == id) {
				students.remove(student);
				System.out.println("Student with id " + id + " deleted successfully");
			}
		}
		return ResponseEntity.ok(students);
	}

	private List<Student> populateStudents() {
		List<Student> students = new ArrayList<>();
		students.add(new Student(1, "Gaurav", "Singh"));
		students.add(new Student(2, "Saurav", "Singh"));
		students.add(new Student(3, "Ram", "Singh"));
		return students;
	}
}
