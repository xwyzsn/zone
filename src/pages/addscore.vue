<template>
  <q-page padding>
    <!-- content -->

    <div class="row justify-center " style="text-align: center">
      <div class="col-5">
      <q-input type="number" v-model="num"  label="输入积分数" />
      </div>
    </div>
    <div class="fit row wrap justify-center items-center content-center q-mt-lg ">
        <q-btn  label="确认积分" color="primary" @click="postScore"  />
        <q-btn label="查看积分" color="withe" text-color="black" class="q-ml-md" @click="getMyScore"   />
    </div>
    <q-separator  class="q-mt-lg"/>
  </q-page>
</template>

<script>
import {ref} from 'vue'
import {useQuasar} from 'quasar'
import {sumScore,refreshScoreStore} from "src/Utils/Utils";
import {date} from 'quasar'
import {api} from "boot/axios";
import {useStore} from "vuex";
export default {
  // name: 'PageName',
  setup(){
    const $q = useQuasar();
    const $store = useStore();
    const num =ref(undefined);
    function getMyScore(){
      const scoreList = JSON.parse(sessionStorage.getItem('scoreList'))
      const score = sumScore(scoreList)
      const my = score.filter(s=>{return s.name === 'you'})
      $q.notify({
        message:'你的积分总数为'+my[0].total,
        position:"center"
      })
    }
    function postScore(){
      const today = date.formatDate(Date.now(),'YYYY-MM-DD')
      const score = num.value;
      const gift_name = null;
      const gift_finish = "f";
      const user_id = localStorage.getItem('userId')
      const group_id = localStorage.getItem('groupId')
      if(score===undefined||score===0){
        $q.notify({
          message:"输入为空!",
          position:'center'
        })
      }
      else {
      $q.loading.show({
        message:'正在上传请稍后...'
        //TODO : quasar 2.0 BUG 不能解析tag
      })
      api.post(process.env.API_URL+"/score",{
        date:today,
        score:+score,
        giftName:gift_name,
        giftFinish:gift_finish,
        userId:user_id,
        groupId:group_id
      })
      .then(res=>{
       refreshScoreStore($store).then(r=>{
         $q.loading.hide();
       })
      })}


    }


    return{
      num,
      getMyScore,
      postScore




    }



  }
}
</script>
