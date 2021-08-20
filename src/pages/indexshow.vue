<template>
  <q-page padding>
    <div class="text-h5 text-center">
      DAY'S MATTER
    </div>
    <div class="row justify-center q-ma-md">
    <q-card class="q-ma-md bg-brown-11" v-for="(i,index) in diff.length" :key="i">
      <q-card-section >
     <span>{{diff[index]}}</span>
      </q-card-section>
    </q-card>
    </div>
  </q-page>
</template>

<script >
import {useStore} from 'vuex'
import {date} from 'quasar'
export default {
  // name: 'PageName',

  setup(){
    const $store = useStore()
    const userInfo = $store.state.userInfo;
    const start = date.extractDate(userInfo.date,'YYYY-MM-DD');
    const now = new Date();

    localStorage.setItem('userId',userInfo.userId);
    localStorage.setItem('username',userInfo.username);
    localStorage.setItem('groupId',userInfo.groupId);
    const diff = date.getDateDiff(now,start).toString().split('').map(Number);
      return{
        start,
        diff
      }

  }
}
</script>
