package xwyzsn.zone.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xwyzsn.zone.Entity.Register;
import xwyzsn.zone.Entity.Score;
import xwyzsn.zone.Entity.User;
import xwyzsn.zone.Entity.UserGroupDetail;
import xwyzsn.zone.Service.ControllerService;

import java.util.HashMap;

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

    @GetMapping("/{email}")
    public UserGroupDetail getUser(@PathVariable("email") String email){
        return controllerService.getUser(email);

    }

    @GetMapping({"/score/{groupid}"})
    public Object getScore(@PathVariable("groupid") String groupId){
        return controllerService.getScore(groupId);
    }

    @PostMapping("/score")
    public void postScore(@RequestBody Score score){

        controllerService.postScore(score);
    }

    @PutMapping("/giftfinish")
    public void giftFinish(@RequestBody HashMap<String,String> map){
        String id = map.get("id");
        String finish = map.get("finish");
        controllerService.giftFinish(id,finish);
    }


}
