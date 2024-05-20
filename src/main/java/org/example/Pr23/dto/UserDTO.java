package org.example.Pr23.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDTO {
    private Long id;
    @NotEmpty(message = "Введите имя")
    private String firstName;
    @NotEmpty(message = "Введите фамилию")
    private String lastName;
    @NotEmpty(message = "Введите почту")
    @Email
    private String email;
    @NotEmpty(message = "ВВедите пароль")
    private String password;
}
