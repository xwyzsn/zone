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
          label="用户名"
          hint="输入你的用户名"
          lazy-rules
          :rules="[ val => val && val.length > 0 || '用户名不能为空']"
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
            <q-btn    label="登录" type="submit" color="primary"/>
          </div>
          <div class="col-auto q-ma-md ">
            <q-btn   label="重置" type="reset"/>
          </div>
        </div>
      </q-form>

    </div>
  </div>
</template>

<script>
import {reactive} from 'vue'
export default {
  name: "login",
  setup(){
    const loginform=reactive({username:'',password:''})

    return{
      loginform
    }

  }
}
</script>

<style scoped>

</style>
