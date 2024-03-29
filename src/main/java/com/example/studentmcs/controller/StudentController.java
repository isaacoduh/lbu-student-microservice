package com.example.studentmcs.controller;

import com.example.studentmcs.dto.StudentDto;
import com.example.studentmcs.dto.StudentPlainDto;
import com.example.studentmcs.dto.StudentProfileDto;
import com.example.studentmcs.dto.requestDto.EnrollmentDto;
import com.example.studentmcs.dto.requestDto.ProfileUpdateDto;
import com.example.studentmcs.dto.requestDto.StudentRequestDto;
import com.example.studentmcs.dto.responseDto.StudentResponseDto;
import com.example.studentmcs.model.Student;
import com.example.studentmcs.service.IStudentService;
import com.example.studentmcs.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    private final IStudentService studentService;


    @GetMapping("/basic")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<String> helloStudent(){
        return ResponseEntity.ok("Hello from Secure Student Endpoint");
    }

    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<StudentProfileDto> currentUserProfileInformation(@AuthenticationPrincipal Student student){
        StudentProfileDto studentProfileDto = new StudentProfileDto();
        studentProfileDto.setStudentId(student.getStudentId());
        studentProfileDto.setFirstName(student.getFirstName());
        studentProfileDto.setLastName(student.getLastName());
        studentProfileDto.setEmail(student.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(studentProfileDto);
    }

    @PutMapping("/profile/update")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<Student> updateProfileAuth(@AuthenticationPrincipal Student student, @RequestBody final ProfileUpdateDto profileUpdateDto){
        Student studentToUpdate = studentService.updateStudentProfileAuth(student.getEmail(), profileUpdateDto);
        return ResponseEntity.status(HttpStatus.OK).body(studentToUpdate);
    }

    @PostMapping("/enroll")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<StudentDto> enrollInCourse(
            @AuthenticationPrincipal Student student,
            @RequestBody final EnrollmentDto enrollmentDto)
    {
        System.out.println(enrollmentDto.getId());
        Student studentObj = studentService.enrollInCourse(student.getEmail(), enrollmentDto.getId());
        return ResponseEntity.status(HttpStatus.OK).body(StudentDto.from(studentObj));
    }

    @GetMapping("/check/graduation")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<?> checkMyStatus(@AuthenticationPrincipal Student student) {
//        Student studentObj = studentService.checkMyStatus(student.getEmail());
        System.out.print("String from controller" + student.getStudentId());
        Mono<Boolean> status = studentService.checkGraduation(student.getStudentId());
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @GetMapping("/status")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<?> checkStatus(@AuthenticationPrincipal Student student)
    {
        System.out.print("String from controller " + student.getStudentId());
        Boolean status = studentService.getGraduationStatus(student.getStudentId());

        System.out.print(status);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @GetMapping("/enrollments")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<StudentDto> getMyEnrollments(@AuthenticationPrincipal Student student){
        Student studentObj = studentService.getStudentEnrollments(student.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(StudentDto.from(studentObj));
    }
    @Autowired
    public StudentController(IStudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping("/register")
    public ResponseEntity<Student> register(@RequestBody final StudentRequestDto studentRequestDto)
    {
        Student student = studentService.registerStudent(studentRequestDto);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<StudentResponseDto>> getAllStudents(){
        List<StudentResponseDto> studentResponseDtos = studentService.getAllStudents();
        return new ResponseEntity<>(studentResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable final Long id)
    {
        Student student = studentService.viewStudent(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PutMapping("/updateProfile/{id}")
    public ResponseEntity<Student> updateStudentProfileById(@RequestBody final StudentRequestDto studentRequestDto, @PathVariable final Long id){
        Student student = studentService.updateStudentProfile(id, studentRequestDto);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
