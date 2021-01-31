package com.expenseNote.payload.request;

import com.expenseNote.apps.role.model.Role;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SignupRequest {

    @NotBlank
    @Size(min = 2, max = 15)
    private final String firstName;

    @NotBlank
    @Size(min = 2, max = 15)
    private final String lastName;

    @NotBlank
    @Size(min = 2, max = 20)
    private final String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private final String email;

    @NotBlank
    @Size(min = 8, max = 40)
    private final String password;

    private final Role role;

//    private final Set<String> role;
}
