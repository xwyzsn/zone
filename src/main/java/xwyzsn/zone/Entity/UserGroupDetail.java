package xwyzsn.zone.Entity;

import lombok.Data;

@Data
public class UserGroupDetail {
    public String username;
    public String email;
    public String userId;
    public String groupId;
    public String userFirstId;
    public String userSecId;
    public String date;

    public UserGroupDetail(String username, String email, String userId, String groupId, String userFirstId, String userSecId, String date) {
        this.username = username;
        this.email = email;
        this.userId = userId;
        this.groupId = groupId;
        this.userFirstId = userFirstId;
        this.userSecId = userSecId;
        this.date = date;
    }
}
