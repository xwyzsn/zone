package xwyzsn.zone.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xwyzsn.zone.Entity.*;
import xwyzsn.zone.Mapper.GroupMapper;
import xwyzsn.zone.Mapper.ScoreMapper;
import xwyzsn.zone.Mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ControllerService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private ScoreMapper scoreMapper;

    @Autowired
    private GroupMapper groupMapper;

    public User getHello(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username","zzh");
        User user =userMapper.selectOne(queryWrapper);
        return user;
    }


    public void postUser(User user) {
        userService.save(user);
    }

    public void registerNew(Register register) {
        String emailFir = register.getEmailFir();
        String userNameFir = register.getUserNameFir();
        String passwordFir = register.getPasswordFir();
        String emailSec = register.getEmailSec();
        String userNameSec = register.getUserNameSec();
        String passwordSec = register.getPasswordSec();
        String date = register.getDate();
        String userIdFir = UUID.randomUUID().toString().replace("-","");
        String userIdSec = UUID.randomUUID().toString().replace("-","");
        String group = UUID.randomUUID().toString().replace("-","");
        List<User> list = new ArrayList<>();
        list.add(new User(userNameFir,emailFir,userIdFir,passwordFir,group));
        list.add(new User(userNameSec,emailSec,userIdSec,passwordSec,group));
        UserGroup g = new UserGroup(group,userIdFir,userIdSec,date);
        groupMapper.insert(g);
        userService.saveBatch(list);
    }

    public UserGroupDetail getUser(String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",email);
        QueryWrapper<UserGroup> userGroupQueryWrapper =new QueryWrapper<>();
        User user = userMapper.selectOne(queryWrapper);
        String id = user.getGroupId();
        userGroupQueryWrapper.eq("group_id",id);
        UserGroup userGroup = groupMapper.selectOne(userGroupQueryWrapper);
        UserGroupDetail userGroupDetail = new UserGroupDetail(user.getUsername(),user.getEmail(),user.getUserId(),
                user.getGroupId(),userGroup.getUserFirstId(),userGroup.getUserSecId(),
                userGroup.getDate());
        return userGroupDetail;
    }

    public Object getScore(String groupId) {

        QueryWrapper<Score> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("group_id",groupId);
        List<Score> scoreList = scoreMapper.selectList(queryWrapper);
        return scoreList;
    }

    public void postScore(Score score) {
        scoreMapper.insert(score);
    }

    public void giftFinish(String id, String finish) {
        Integer queryId = Integer.valueOf(id);
        UpdateWrapper<Score> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",queryId).set("gift_finish",finish);
        scoreMapper.update(null,updateWrapper);

    }
}
