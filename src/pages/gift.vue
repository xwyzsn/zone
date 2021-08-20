<template>
  <q-page padding>
    <!-- content -->
    <div class="row justify-center">
        <q-input v-model="num" label="消耗的积分数" />
    </div>
    <div  class="row justify-center q-mt-lg">
      <q-btn  label="确定" @click="confirm" color="primary"/>
      <q-btn label="重置" class="q-ml-md" @click="num=undefined" />
    </div>
  <q-separator class="q-mt-lg q-mb-lg" />
    <q-list bordered padding class="rounded-borders" style="max-width: 100%;width: 100%;">
      <q-item-label header>你的兑换记录</q-item-label>
      <q-item clickable v-ripple v-for="(item,index) in other_gift" :key="item.id">
        <q-item-section avatar top>
          <q-avatar :icon="mdiGift" color="grey-1" text-color="pink-6" />
        </q-item-section>
        <q-item-section v-if="item.giftFinish==='t'" >
          <q-item-label lines="1" style="text-decoration: line-through;color: grey">{{item.giftName}}</q-item-label>
          <q-item-label caption>{{item.date}}dsadsa</q-item-label>
        </q-item-section>
        <q-item-section v-else >
          <q-item-label lines="1"  >{{item.giftName}}</q-item-label>
          <q-item-label caption>{{item.date}}sdadd</q-item-label>
        </q-item-section>
        <q-item-section  v-if="item.giftFinish==='f'" >
          <q-checkbox v-model="other_not_finish[index]" label="未完成"></q-checkbox>
        </q-item-section>
        <q-item-section  v-else style="text-decoration: line-through;color: grey">
          <q-checkbox disable v-model="other_not_finish[index]" label="已完成" ></q-checkbox>
        </q-item-section>
      </q-item>
      <div class="row justify-center">
        <q-btn label="确认" color="primary" @click="finish()" />
      </div>
    </q-list>
    <q-separator class="q-ma-lg" />

    <q-list bordered padding class="rounded-borders" style="max-width: 100%;width: 100%;">
      <q-item-label header>等待对方兑换</q-item-label>
      <q-item clickable v-ripple v-for="(item,index) in my_gift" :key="item.id">
        <q-item-section avatar top>
          <q-avatar :icon="mdiGift" color="grey-1" text-color="pink-6" />
        </q-item-section>

        <q-item-section v-if="item.giftFinish==='t'" >
          <q-item-label lines="1" style="text-decoration: line-through;color: grey">{{item.giftName}}</q-item-label>
          <q-item-label caption>{{item.date}}</q-item-label>
        </q-item-section>
        <q-item-section v-else >
          <q-item-label lines="1"  >{{item.giftName}}</q-item-label>
          <q-item-label caption>{{item.date}}</q-item-label>
        </q-item-section>
        <q-item-section  v-if="item.giftFinish === 'f'" >
          <q-checkbox disable v-model="not_finish[index]"  label="未完成"></q-checkbox>
        </q-item-section>
        <q-item-section  v-else style="text-decoration: line-through;color: grey">
          <q-checkbox disable  v-model="not_finish[index]" label="已完成" ></q-checkbox>
        </q-item-section>
      </q-item>


    </q-list>


  </q-page>
</template>

<script>
import {ref,reactive} from 'vue'
import {date,useQuasar} from "quasar";
import {api} from "boot/axios";
import {refreshScoreStore, sumScore} from "src/Utils/Utils";
import {useStore} from 'vuex'
import { useRouter } from "vue-router";
import {mdiGift} from "@quasar/extras/mdi-v4"
export default {
  // name: 'PageName',

  setup(){
    const $q = useQuasar();
    const scoreList = JSON.parse(sessionStorage.getItem('scoreList'))
    const username = localStorage.getItem('userId')
    var   num = ref(undefined);
    const $store = useStore();
    const $router = useRouter();
    function refresh(){
      $router.replace({
        path: '/refresh',
        query: {
          t: Date.now()
        }
      })
    }
    function confirm(){
      const today = date.formatDate(Date.now(),'YYYY-MM-DD')
      const score = num.value;
      const gift_name = num.value + "积分礼物";
      const gift_finish = "f";
      const user_id = localStorage.getItem('userId')
      const group_id = localStorage.getItem('groupId')
      const sum = sumScore(scoreList)
      const my = sum.filter(r=>{
        return r.name ==='you'
      })
      if(my[0].total<score){
        $q.notify({
          message:'积分不足',
          position:"center"
        })
      }
      else{
      if(score===undefined||score===0){
        $q.notify({
          message:"输入为空!",
          position:'center'
        })
      }      else {
        $q.loading.show({
          message:'正在上传请稍后...'
          //TODO : quasar 2.0 BUG 不能解析tag
        })
        api.post(process.env.API_URL+"/score",{
          date:today,
          score:+(score*-1),
          giftName:gift_name,
          giftFinish:gift_finish,
          userId:user_id,
          groupId:group_id
        })
          .then(res=>{
            refreshScoreStore($store).then(r=>{
              $q.loading.hide();

              refresh();
            })
          })

      }
      }

    }


    let  not_finish =[] ;


    const my_gift = scoreList.filter(s=>{
      return s.userId === username &&s.score<0
    })
    let temp = []
    my_gift.forEach(s=>{
      if(s.giftFinish==='f'){
        temp.push(false)
      }
      else {
        temp.push(true)
      }
    })
    not_finish = reactive(temp)
    const other_gift = scoreList.filter(s=>{
      return s.userId !==username &&s.score<0;
    })
    temp=[]
    other_gift.forEach(s=>{
      if(s.giftFinish==='f'){
        temp.push(false)
      }
      else {
        temp.push(true)
      }
    })
    let other_not_finish = reactive(temp)
    function finish(){
      let id = undefined;
      for(var i =0;i<other_not_finish.length;i++){
        if(other_not_finish[i]===true && other_gift[i].giftFinish==="f"){
          id = other_gift[i].id
          break;
        }
      }
      if(id===undefined){
        $q.notify({
          message:'some error occur',
          position:"center"
        })
      }
      else{
        api.put(process.env.API_URL+"/giftfinish",{
          id:+id,
          finish:'t'
        })
        .then(()=>{
          refreshScoreStore($store)
          .then(()=>{refresh()});
        })
      }

    }

    return{
      num,
      confirm,
      scoreList,
      my_gift,
      other_gift,
      mdiGift,
      not_finish,
      other_not_finish,
      finish
    }
  }
}
</script>
