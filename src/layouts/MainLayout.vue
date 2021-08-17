<template>
  <q-layout view="hHh lpR fFf">

    <q-header reveal elevated id="header" class="text-white text-center">
      <q-toolbar>
        <q-btn dense flat round icon="menu" @click="toggleLeftDrawer" />
        <q-toolbar-title>
          ZONE
        </q-toolbar-title>
      </q-toolbar>
    </q-header>

    <q-drawer show-if-above v-model="leftDrawerOpen" side="left" elevated>
      <!-- drawer content -->
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>

  </q-layout>
</template>

<script>
import { ref,onMounted } from 'vue'
import {useStore} from 'vuex'
export default {
  setup () {
    const leftDrawerOpen = ref(false)
    const $store = useStore()
    $store.dispatch('userInfo/getUser')

    return {
      leftDrawerOpen,
      toggleLeftDrawer () {
        leftDrawerOpen.value = !leftDrawerOpen.value
      }
    }
  }
}
</script>
