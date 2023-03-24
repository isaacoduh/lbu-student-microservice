package com.example.studentmcs.dto;

import com.example.studentmcs.model.Staff;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StaffPlainDto {
    private Long id;
    private String name;
    private String email;
    private String gender;
    private Boolean isActive;

    public static StaffPlainDto from(Staff staff){
        return StaffPlainDto
                .builder()
                .id(staff.getId())
                .name(staff.getName())
                .email(staff.getEmail())
                .gender(staff.getGender())
                .isActive(staff.getIsActive())
                .build();
    }
}
