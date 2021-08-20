package xwyzsn.zone.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Score {
    @TableId(value = "id",type = IdType.AUTO)
    public Integer id;
    public String date;
    public int score;
    public String giftName;
    public String giftFinish;
    public String userId;
    public String groupId;

    public Score(String date, int score, String giftName, String giftFinish, String userId, String groupId) {
        this.date = date;
        this.score = score;
        this.giftName = giftName;
        this.giftFinish = giftFinish;
        this.userId = userId;
        this.groupId = groupId;
    }


}
