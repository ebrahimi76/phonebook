package ir.ebrahimi.phonebook.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneBookRow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Pattern(regexp = "[A-Z][a-z]*")
    private String firstName;
    @Pattern(regexp = "[A-Z][a-z]*")
    private String lastName;
    @Email
    private String email;
    @Pattern(regexp = "(^$|[0-9]{10})")
    private String phoneNumber;
}
