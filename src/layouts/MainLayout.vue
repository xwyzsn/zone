<template>
  <q-layout view="hHh lpR fFf">

    <q-header reveal elevated id="header" class="text-white text-center bg-amber-10">
      <q-toolbar>
        <q-btn dense flat round icon="menu" @click="toggleLeftDrawer" />
        <q-toolbar-title class="text-center">
          ZONE
        </q-toolbar-title>
        <q-icon name="logout" size="sm" @click="logout"/>
      </q-toolbar>
    </q-header>

    <q-drawer show-if-above v-model="leftDrawerOpen" side="left" elevated>

      <q-scroll-area class="fit" >      <!-- drawer content -->
      <q-list>

        <q-item clickable v-ripple to="/main/index" >
          <q-item-section avatar >
            <q-icon name="inbox" />
          </q-item-section>
          <q-item-section>
            首页
          </q-item-section>
        </q-item>
        <q-item clickable v-ripple to="/main/chart">
          <q-item-section avatar>
            <q-icon :name="outlinedTimeline" ></q-icon>
          </q-item-section>
          <q-item-section>
            曲线
          </q-item-section>
        </q-item>
        <q-item clickable v-ripple to="/main/addscore">
          <q-item-section avatar >
            <q-icon :name="outlinedEdit" />
          </q-item-section>
          <q-item-section>
            积分
          </q-item-section>
        </q-item>
        <q-item clickable v-ripple to="/main/gift">
          <q-item-section avatar>
            <q-icon :name="outlinedCardGiftcard" />
          </q-item-section>
          <q-item-section>
            礼物
          </q-item-section>
        </q-item>
      </q-list>
      </q-scroll-area>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>

  </q-layout>
</template>

<script>
import { ref } from 'vue'
import {useRouter} from 'vue-router'
import {outlinedEdit,outlinedTimeline,outlinedCardGiftcard} from "@quasar/extras/material-icons-outlined"
export default {
  preFetch ({ store, currentRoute, previousRoute, redirect, ssrContext, urlPath, publicPath }) {

    return  store.dispatch('userInfo/getUser').then(res=>{
      store.dispatch('score/getScore',localStorage.getItem('groupId'))
    })
  },




  setup () {
    const leftDrawerOpen = ref(false)
    const $router = useRouter();
    function logout(){
      $router.push({name:'login'})
      localStorage.clear();
      sessionStorage.clear();
    }
    return {
      leftDrawerOpen,
      toggleLeftDrawer () {
        leftDrawerOpen.value = !leftDrawerOpen.value
      },
      outlinedEdit,
      outlinedTimeline,
      outlinedCardGiftcard,
      logout
    }
  }
}
</script>
