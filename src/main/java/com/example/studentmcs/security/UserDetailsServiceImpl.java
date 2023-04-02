package com.example.studentmcs.security;

import com.example.studentmcs.model.Student;
import com.example.studentmcs.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final IStudentService studentService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentService.getStudentByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(student.getUsername()));
        return mapStudentToCustomUserDetails(student, authorities);
    }

    private CustomUserDetails mapStudentToCustomUserDetails(Student student, List<SimpleGrantedAuthority> authorities){
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setId(student.getId());
        customUserDetails.setUsername(student.getUsername());
        customUserDetails.setPassword(student.getPassword());
        customUserDetails.setStudentId(student.getStudentId());
        customUserDetails.setFirstName(student.getFirstName());
        customUserDetails.setLastName(student.getLastName());
        customUserDetails.setEmail(student.getEmail());
        customUserDetails.setAuthorities(authorities);
        return customUserDetails;
    }
}
