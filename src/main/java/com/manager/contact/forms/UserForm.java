package com.manager.contact.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserForm {
    @NotBlank(message="First name is required")
    @Size(min=3, message="minimum 3 characters required")
    private String fName;

    @NotBlank(message="Last name is required")
    @Size(min=3, message="minimum 3 chaeacters required")
    private String lName;

    @NotBlank(message="Email is required")
    @Pattern(regexp="^(?!.*\\.\\.)[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message="Invalid email address")   
    private String email;

    @NotBlank(message="Password is required")
    @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message="Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one number, and one special character")
    private String password;

    @NotBlank
    private String gender;

    @NotBlank(message="Date of birth is required")
    private String dob;

    @NotBlank(message="Phone number is required")
    @Pattern(regexp="^[0-9]{10}$", message="Invalid phone number")
    private String phone;
    
    @NotBlank(message="about is required")
    private String about;
}
