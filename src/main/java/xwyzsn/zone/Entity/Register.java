package xwyzsn.zone.Entity;


import lombok.Data;

@Data
public class Register {

    String emailFir;
    String userNameFir;
    String passwordFir;

    String emailSec;
    String userNameSec;
    String passwordSec;

    String date;

    public Register(String emailFir, String userNameFir, String passwordFir, String emailSec, String userNameSec, String passwordSec, String date) {
        this.emailFir = emailFir;
        this.userNameFir = userNameFir;
        this.passwordFir = passwordFir;
        this.emailSec = emailSec;
        this.userNameSec = userNameSec;
        this.passwordSec = passwordSec;
        this.date = date;
    }
}
