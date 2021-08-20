<template>
<div>

</div>
</template>

<script>
import { useRouter } from "vue-router";
import {} from 'vue'
export default {
  // name: 'PageName',
  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.$router.replace(from.path)
    })
  },
  setup(){
    const $router = useRouter();
  }
}
</script>
