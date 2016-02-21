package cz.fi.muni.pa165.seminar.pkmnleague.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author Oldrich Faldik
 */
public class TrainerCreateDTO {

    @NotNull
    @Size(min = 4, max = 40)
    private String fullName;

    @NotNull
    private Date dateOfBirth;

    @NotNull
    private String email;

    @NotNull
    private String password;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}