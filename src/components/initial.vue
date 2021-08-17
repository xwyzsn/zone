<template>


<div class="fixed-center full-width">
  <q-fab  class="q-mp-md" color="white" icon="keyboard_arrow_right" text-color="black"  direction="right">
    <q-fab-action color="whith"  text-color="black" :to="{name:'login'}" icon="home" />
    <q-fab-action color="whith" text-color="black" @click="reset"  icon="settings" />
  </q-fab>
  <q-stepper
    v-model="step"
    ref="stepper"
    animated
    done-color="primary"
    active-color="purple"
    inactive-color="grey"
    header-nav
    keep-alive
    :vertical="platform"

  >
    <q-step
      :name="1"
      title="用户一"
      icon="settings"
      :done="step > 1"
      ref="emailRef"
      caption="用户信息一"
    >
    <q-input
    type="email"
    hint="邮箱"
    v-model="mail"
    :rules="[val =>{
      if(checkEmail(val)){
        next = true;
        return true
      }
      else {
        next=false
        return false
      }
    }]"
    error-message="不合法邮箱"
    >
      <template v-slot:before>
        <q-icon name="mail" />
      </template>
      <template v-slot:append>
      <q-icon name="cancel" @click.stop="mail=undefined" class="cursor-pointer" />
      </template>
    </q-input>
      <q-input
        :type="isPwd ? 'password' : 'text'"
        hint="密码"
        v-model="password"
        :rules="[val=>{return val.length>6}]"
        error-message="密码长度大于6位"
      >
        <template v-slot:before>
          <q-icon name="password" />
        </template>
        <template v-slot:append>
          <q-icon name="cancel" @click.stop="password=''" class="cursor-pointer" />
          <q-icon
            :name="isPwd ? 'visibility_off' : 'visibility'"
            class="cursor-pointer"
            @click="isPwd = !isPwd"
          />
        </template>
      </q-input>
      <q-input
      hint="用户名"
      v-model="username"
      :rules="[val=>{return val.length>0}]"
      >
       <template v-slot:before>
         <q-icon name="person" />
       </template>
        <template v-slot:append >
          <q-icon name="cancel" @click.stop="username=''" class="cursor-pointer" />
        </template>

      </q-input>
    </q-step>

    <q-step
      :name="2"
      title="用户二"
      caption="输入注册信息"
      icon="settings"
      :done="step > 2"
      :header-nav="step!==1"
    >

        <q-input
          type="email"
          hint="邮箱"
          v-model="user_2.mail"
          :rules="[val =>{
      if(checkEmail(val)){
        user_2.next = true;
        return true
      }
      else {
        user_2.next=false
        return false
      }
    }]"
          error-message="不合法邮箱"
        >
          <template v-slot:before>
            <q-icon name="mail" />
          </template>
          <template v-slot:append>
            <q-icon name="cancel" @click.stop="user_2.mail=undefined" class="cursor-pointer" />
          </template>
        </q-input>
        <q-input
          :type="isPwd ? 'password' : 'text'"
          hint="密码"
          v-model="user_2.password"
          :rules="[val=>{return val.length>6}]"
          error-message="密码长度大于6位"
        >
          <template v-slot:before>
            <q-icon name="password" />
          </template>
          <template v-slot:append>
            <q-icon name="cancel" @click.stop="user_2.password=''" class="cursor-pointer" />
            <q-icon
              :name="isPwd ? 'visibility_off' : 'visibility'"
              class="cursor-pointer"
              @click="isPwd = !isPwd"
            />
          </template>
        </q-input>
        <q-input
          hint="用户名"
          v-model="user_2.username"
          :rules="[val=>{return val.length>0}]"
        >
          <template v-slot:before>
            <q-icon name="person" />
          </template>
          <template v-slot:append >
            <q-icon name="cancel" @click.stop="user_2.username=''" class="cursor-pointer" />
          </template>

        </q-input>
    </q-step>

    <q-step
      :name="3"
      title="基础配置"
      icon="settings"
    >
      <q-input hint="在一起时间" v-model="config.day"  >
        <template v-slot:append>
          <q-icon name="calendar_today" @click="d=!d" >
            <q-popup-proxy ref="qDateProxy" transition-show="scale" transition-hide="scale">
              <q-date v-model="config.day" mask="YYYY-MM-DD">
                <div class="row items-center justify-end">
                  <q-btn v-close-popup label="确定" color="primary" flat />
                </div>
              </q-date>
            </q-popup-proxy>
          </q-icon>
        </template>
      </q-input>
    </q-step>

    <template v-slot:navigation>
      <q-stepper-navigation>

        <q-btn v-if="step<=2" @click="moveNext" color="deep-orange" label="下一步" />
        <q-btn v-if="step===3" color="deep-orange" label="注册" @click="register"/>
        <q-btn v-if="step > 2" flat color="deep-orange" @click="$refs.stepper.previous()" label="Back" class="q-ml-sm" />

      </q-stepper-navigation>
    </template>
  </q-stepper>

</div>
</template>

<script>
import { ref,reactive } from 'vue'
import {checkEmail} from "src/JS/utils";
import {useQuasar,date} from 'quasar'
import {api} from '../boot/axios'
export default {
  name: "initial",
  setup(){
    const user_2 = reactive({
      mail:'',
      password:'',
      username:'',
      next:false
    })
    const config = reactive({
      day:date.formatDate(Date.now(),'YYYY-MM-DD')
    })
    const $q = useQuasar();
    console.log($q.platform.is.mobile)
    console.log($q.platform.is.desktop)
    const  step = ref(1)
    const mail = ref('')
    const emailRef = ref(null)
    const password = ref('')
    const  isPwd = ref(true)
    const next = ref(false)
    const username = ref('')
    const d = ref(false)
    const platform = !$q.platform.is.desktop
    return{
     step,
      mail,
      checkEmail,
      emailRef,
      password,
      isPwd,
      next,
      username,
      user_2,
      config,
      d,
      platform
    }

  },
  methods:{
    moveNext(){
      let password =''
      let username = ''
      let next = false
      this.step===1?password=this.password: password= this.user_2.password;
      this.step===1?username = this.username:username =this.user_2.username;
      next = this.step===1?this.next:this.user_2.next;
      console.log(password,username,this.step)
      if (next===true && password!=='' && password && username!=='' && username){
        this.$refs.stepper.next()
      }
      else {
        this.$q.notify({
          message:'检查输入是否合法!',
          classes:'text-orange',
          position:"center",
          icon:'warning'
        })
      }

    },
    register(){
      this.$q.loading.show({
        message:'<span>注册中</span><br/>请稍等...',
      })
      api.post('/api/zone/register',{
        emailFir:this.mail,
        userNameFir:this.username,
        passwordFir:this.password,
        emailSec:this.user_2.mail,
        userNameSec:this.user_2.username,
        passwordSec:this.user_2.password,
        date:this.config.day
      }).then(res=>{
        if(res.status===200){
          this.$q.loading.hide()
          this.$q.notify({
            message:'注册成功,请使用邮箱进行登录',
            position:"center",
            icon:"success"
          })
        }else{
          this.$q.loading.hide()
          this.$q.notify({
            message:'注册失败,请检查你的账号信息',
            position:"center",
            icon:"fail"
          })
        }
      })

    },
    reset(){
      this.mail=''
      this.password=''
      this.username=''
      this.user_2.mail=''
      this.user_2.password=''
      this.user_2.username=''
      this.config.day=date.formatDate(Date.now(),'YYYY-MM-DD')
      this.step=1
      this.next=false
      this.user_2.next=false
    }
  }
}
</script>

<style scoped>

</style>
