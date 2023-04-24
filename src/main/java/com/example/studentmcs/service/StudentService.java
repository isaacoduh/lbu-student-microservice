package com.example.studentmcs.service;

import com.example.studentmcs.dto.requestDto.ProfileUpdateDto;
import com.example.studentmcs.dto.requestDto.StudentRequestDto;
import com.example.studentmcs.dto.mapper;

import com.example.studentmcs.dto.responseDto.StudentResponseDto;
import com.example.studentmcs.model.Account;
import com.example.studentmcs.model.Course;
import com.example.studentmcs.model.Invoice;
import com.example.studentmcs.model.Student;
import com.example.studentmcs.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentService implements IStudentService {
    private final StudentRepository studentRepository;
    private final ICourseService courseService;

    private final IntegrationService integrationService;

    public StudentService(StudentRepository studentRepository, ICourseService courseService, IntegrationService integrationService){
        this.studentRepository = studentRepository;
        this.courseService = courseService;
        this.integrationService = integrationService;
    }

    @Override
    public Student registerStudent(StudentRequestDto studentRequestDto) {
        Student student = new Student();
        student.setStudentId(studentRequestDto.getStudentId());
        student.setEmail(studentRequestDto.getEmail());
        student.setFirstName(studentRequestDto.getFirstName());
        student.setLastName(studentRequestDto.getLastName());
        return studentRepository.save(student);
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
//        List<Student> students = StreamSupport.stream(
//                studentRepository.findAll().spliterator(), false
//        ).collect(Collectors.toList());
//        return mapper.studentsToStudentResponseDtos(students);
        return null;
    }

    @Override
    public Student viewStudent(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException("student with studentId: " + studentId + " could not be found!"));
    }

    public Student viewStudentByEmail(String email){
        return studentRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("student with email: " + email + " could not be found!"));
    }

    @Override
    public Student getStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new IllegalArgumentException("student with id: " + studentId + " could not be found")
        );
        return student;
    }

    @Transactional
    @Override
    public Student updateStudentProfile(Long studentId, StudentRequestDto studentRequestDto) {
        Student studentProfileToUpdate = viewStudent(studentId);
        studentProfileToUpdate.setEmail(studentRequestDto.getEmail());
        studentProfileToUpdate.setFirstName(studentRequestDto.getFirstName());
        studentProfileToUpdate.setLastName(studentRequestDto.getLastName());
        return studentRepository.save(studentProfileToUpdate);
    }

//    @Transactional
    @Override
    public Student updateStudentProfileAuth(String email, ProfileUpdateDto profileUpdateDto) {
        Student studentProfileToUpdate = viewStudentByEmail(email);
        System.out.print(profileUpdateDto.getFirstName());
        System.out.print(profileUpdateDto.getLastName());
        studentProfileToUpdate.setFirstName(profileUpdateDto.getFirstName());
        studentProfileToUpdate.setLastName(profileUpdateDto.getLastName());
        return studentRepository.save(studentProfileToUpdate);
    }

    @Override
    public Student enrollInCourse(String email, Long courseId) {
        Student student = viewStudentByEmail(email);
        System.out.print(student);
        Course course = courseService.getCourse(courseId);
        student.addCourseToStudent(course);

        var saveStudent = studentRepository.save(student);

        // generate an account
        Account account = new Account();
        account.setId(1L);
        account.setStudentId(student.getStudentId());
        account.setHasOutstandingBalance(true);

        // generate an invoice
        Invoice invoice = new Invoice();
        invoice.setId(1L);
        invoice.setReference(generateInvoiceReference());
        invoice.setAmount(course.getCourseFee());
        invoice.setDueDate(LocalDate.parse(LocalDate.now().plusWeeks(3).format(DateTimeFormatter.ISO_LOCAL_DATE)));
        invoice.setType(Invoice.Type.TUITION_FEE);
        invoice.setStatus(Invoice.Status.OUTSTANDING);
        invoice.setAccount(account);

        integrationService.postInvoiceData(invoice);

        return saveStudent;
    }

    @Override
    public Student getStudentEnrollments(String email) {
        Student student = viewStudentByEmail(email);
        System.out.print(student);
        return student;
    }

    @Override
    public Optional<Student> getStudentByUsername(String username) {
        return studentRepository.findByUsername(username);
    }

    @Override
    public boolean hasStudentWithUsername(String username) {
        return studentRepository.existsByUsername(username);
    }

    @Override
    public boolean hasStudentWithEmail(String email) {
        return studentRepository.existsByEmail(email);
    }

    @Override
    public Student validateAndGetStudentByUsername(String username) {
        return getStudentByUsername(username).orElseThrow(() -> new IllegalArgumentException(String.format("Student with username not found!")));
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }


    public static String generateInvoiceReference() {
        UUID uuid = UUID.randomUUID();
        String reference = uuid.toString().substring(0, 8);
        return reference;
    }

    public static String generateDueDate()
    {
        LocalDate due = LocalDate.now().plusWeeks(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dueDateStr = due.format(formatter);

        return dueDateStr;
    }


}
