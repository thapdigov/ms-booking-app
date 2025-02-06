package az.perfect.msbookingapp.model.dto.request;

import az.perfect.msbookingapp.model.enums.Nationality;
import az.perfect.msbookingapp.model.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequest {
    @NotNull
    @Size(max = 20)
    private String firstName;
    @NotNull
    @Size(max = 20)
    private String lastName;
    @Email
    @Size(max = 30)
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Size(min = 10, max = 30)
    @NotNull
    private String phoneNumber;
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDay;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Nationality nationality;
}
