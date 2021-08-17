package xwyzsn.zone.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xwyzsn.zone.Entity.Register;
import xwyzsn.zone.Entity.User;
import xwyzsn.zone.Mapper.UserMapper;
import xwyzsn.zone.Service.ControllerService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/zone")
@CrossOrigin(value = "*")
public class controller {

    @Autowired
    private ControllerService controllerService;

    @GetMapping("hello")
    public User getHello(){
        return controllerService.getHello();
    }


    @PostMapping(path = "adduser")
    public void postUser(@RequestBody User user){
         controllerService.postUser(user);
    }

    @PostMapping(path="register")
    public void registerNew(@RequestBody Register register){
        controllerService.registerNew(register);
    }


}
