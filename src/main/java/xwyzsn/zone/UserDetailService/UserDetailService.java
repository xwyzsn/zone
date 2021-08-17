package xwyzsn.zone.UserDetailService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import xwyzsn.zone.Mapper.UserMapper;

import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        QueryWrapper<xwyzsn.zone.Entity.User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",s);
        xwyzsn.zone.Entity.User user = userMapper.selectOne(queryWrapper);
        List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList("role");
        if(user!=null){
            return new User(user.getUsername(),new BCryptPasswordEncoder().encode(user.getPassword()),auth);
        }
        else{
            throw  new UsernameNotFoundException("用户名查找失败");
        }


    }
}
