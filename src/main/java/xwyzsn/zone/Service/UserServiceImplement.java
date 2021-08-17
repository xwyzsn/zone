package xwyzsn.zone.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xwyzsn.zone.Entity.User;
import xwyzsn.zone.Mapper.UserMapper;

@Service
public class UserServiceImplement extends ServiceImpl<UserMapper,User> implements UserService{

}
