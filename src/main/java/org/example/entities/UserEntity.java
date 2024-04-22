package org.example.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.example.messages.ErrorMessages;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    private @NotBlank(message = ErrorMessages.NOT_BLANK) String fullName;
    private @NotBlank(message = ErrorMessages.NOT_BLANK) @Email String email;
    private String address;
    private @NotBlank(message = ErrorMessages.NOT_BLANK) String password;
}
