package neu.edu.AirLinePortal.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author YUlia
 * @version 1.0
 */
@Data
public class UserUpdate {

    private String uuid;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must be less than or equal to 50 characters")
    @Pattern(regexp = "[A-Za-z]+", message = "Invalid name format")
    private String fname;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must be less than or equal to 50 characters")
    @Pattern(regexp = "[A-Za-z]+", message = "Invalid name format")
    private String lname;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    @NotBlank(message = "phone number is required")
    @Pattern(regexp = "(\\(\\+[0-9]+\\))?\\s?[0-9]{3}-[0-9]{3}-[0-9]{4}", message = "Invalid phone number format")
    private String phoneno;

    private String role;

    @NotBlank(message = "gender is required")
    private String gender;

    private String status;

    @Override
    public String toString() {
        return "UserUpdate{" +
                "uuid='" + uuid + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", dob=" + dob +
                ", phoneno='" + phoneno + '\'' +
                ", role='" + role + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
