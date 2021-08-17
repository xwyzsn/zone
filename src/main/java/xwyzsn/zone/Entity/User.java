package xwyzsn.zone.Entity;

import lombok.Data;
@Data
public class User {
    public String username;
    public String email;
    public String userId;
    public String password;
    public String groupId;

    public User(String username, String email, String userId, String password, String groupId) {
        this.username = username;
        this.email = email;
        this.userId = userId;
        this.password = password;
        this.groupId = groupId;
    }

    public User(String username, String email, String password, String groupId) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.groupId = groupId;
    }
}
