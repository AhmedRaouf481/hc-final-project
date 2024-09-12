package com.clinicare.server.requests;

import com.clinicare.server.domain.db.Role;
import com.clinicare.server.domain.db.Specialization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String name;
    private String username;
    private String email;
    private String password;
    private String phone;
    private ArrayList<Role> roles;
    private String summary;
    private Double salary;
    private Specialization specialization;

}
