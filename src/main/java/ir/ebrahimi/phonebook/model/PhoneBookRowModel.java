package ir.ebrahimi.phonebook.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;


@Getter
@Setter
public class PhoneBookRowModel {
    @Pattern(regexp = "[A-Z][a-z]*")
    private String firstName;
    @Pattern(regexp = "[A-Z][a-z]*")
    private String lastName;
    @Email
    private String emailAddress;
    @Pattern(regexp = "(^$|[0-9]{10})")
    private String phoneNumber;

    public PhoneBookRow translateModelToEntity(){
        return PhoneBookRow.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .email(this.emailAddress)
                .phoneNumber(this.phoneNumber)
                .build();
    }
}
