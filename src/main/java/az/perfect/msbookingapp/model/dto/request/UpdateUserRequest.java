package az.perfect.msbookingapp.model.dto.request;

import az.perfect.msbookingapp.model.enums.Nationality;
import az.perfect.msbookingapp.model.enums.Role;
import az.perfect.msbookingapp.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class UpdateUserRequest {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    @NotNull
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    @NotBlank
    private String phoneNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Nationality nationality;
    @Enumerated(EnumType.STRING)
    private Status status;
}
