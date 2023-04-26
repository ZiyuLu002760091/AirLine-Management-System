package neu.edu.AirToronto.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * See database "users" table
 */
@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

    /**
     * This uuid is generated in code
     *
     * @see neu.edu.AirLinePortal.service.RegisterService#regiSuccess(UserRequestEntity)
     */
    @Id
    private String uuid;

    private String fname;

    private String lname;

    private Date dob;

    private String password;

    private String email;

    private String phoneno;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Gender {
        male, female, other
    }

    public enum Status {
        active, inactive, deleted
    }

    public enum Role {
        customer, admin
    }
}
