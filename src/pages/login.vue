<template>
  <div   class="fullscreen my-bg"  >
    <div class="   q-pa-md absolute-center " style="max-width: 100%;width: 100%;background-color: white;opacity: 0.9">
      <div class="row text-h5 justify-center q-pb-lg">
        登录
      </div>
      <q-form
        class="q-gutter-md"
      >
        <q-input
          filled
          v-model="loginform.username"
          label="邮箱"
          hint="输入你注册的邮箱"
          lazy-rules
          :rules="[ val => val && val.length > 0 || '邮箱不能为空']"
          type="email"
        />
        <q-input
          filled
          type="password"
          v-model="loginform.password"
          label="密码"
          lazy-rules
          :rules="[
          val => val !== null && val !== '' || '输入密码'
        ]"
        />
        <div class="full-width row wrap justify-center ">
          <div class="col-auto q-ma-md ">
            <q-btn @click.prevent="login"   label="登录" type="submit" color="primary"/>
          </div>
          <div class="col-auto q-ma-md ">
            <q-btn to="register"  label="注册" />
          </div>
        </div>
      </q-form>

    </div>
  </div>
</template>

<script>
import {reactive,onMounted} from 'vue'
import {api} from "boot/axios";
import qs from 'qs'
export default {
  name: "login",
  setup(){
    const loginform=reactive({username:'',password:''})

    const API=process.env.API_URL

    onMounted(()=>{
      // api.post(API+"/login",{
      //   username:loginform.username
      //
      // })




    })


    return{
      loginform,
      API
    }

  },
  methods:{
    login(){
      let form= qs.stringify({username:this.loginform.username,password:this.loginform.password})

      api.post("/login",form).then(res=>{
        console.log(res)
        if(res.data.code===200) {
          let authentication = res.headers['authorization']
          localStorage.setItem('email',this.loginform.username)
          console.log(res)
          localStorage.setItem('auth', authentication)
          this.$router.push({name:'main'})
        }
        else {
          this.$q.notify({
            message:'账号或密码错误',
            textColor:'orange',
            position:"center"
          })
        }
      })

    }
  }
}
</script>

<style scoped>

</style>
