package az.perfect.msbookingapp.model.dto.response;

import az.perfect.msbookingapp.model.enums.Nationality;
import az.perfect.msbookingapp.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String firstName;

    private String lastName;

    private String email;

    private Role role;

    private String phoneNumber;

    private LocalDate dateOfBirth;

    private Nationality nationality;

}
