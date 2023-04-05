package com.example.studentmcs;

import com.example.studentmcs.dto.CourseDto;
import com.example.studentmcs.model.Course;
import com.example.studentmcs.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MiscBeans {

    private static final Logger log = LoggerFactory.getLogger(MiscBeans.class);


    @Bean
    CommandLineRunner initDatabase(CourseRepository courseRepository){
        return args -> {

            Course sesc = new Course("COMP720","Software and Systems","WIN");
            Course pmgt = new Course("COMP753", "Project Management", "WIN");
            Course sas = new Course("COMP720", "Software and Systems", "WIN");

            Course rspractice = new Course("COMP738", "Research Practice", "AUT");
            Course dav = new Course("COMP757", "Data Analytics and Visualization", "AUT");
            Course adse = new Course("COMP725", "Advanced Software Engineering", "AUT");

            courseRepository.save(sesc);
            courseRepository.save(pmgt);
            courseRepository.save(sas);

            courseRepository.save(rspractice);
            courseRepository.save(dav);
            courseRepository.save(adse);



        };
    }
}
