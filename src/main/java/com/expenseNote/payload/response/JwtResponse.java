package com.expenseNote.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String roles;

    public JwtResponse(String token, Long id, String firstName, String lastName, String username, String email, String roles) {
        this.token = token;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

}
