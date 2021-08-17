package xwyzsn.zone.Entity;

import lombok.Data;

@Data
public class UserGroup {
    String groupId;
    String userFirstId;
    String userSecId;
    String date;

    public UserGroup(String groupId, String userFirstId, String userSecId, String date) {
        this.groupId = groupId;
        this.userFirstId = userFirstId;
        this.userSecId = userSecId;
        this.date = date;
    }
}
