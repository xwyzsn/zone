<template>
  <q-page padding>
    <!-- content -->
    <div id="bar"  class="q-pa-md" style="transform: translate(0%,20%)"/>
  </q-page>
</template>

<script>
import {onMounted} from "vue";
import {Bar} from "@antv/g2plot"
import {sumScore} from "src/Utils/Utils";

export default {
  // name: 'PageName',



  setup(){


    const scoreList = JSON.parse(sessionStorage.getItem('scoreList'))

    const data = sumScore(scoreList);
    onMounted(()=>{
      const bar = new Bar('bar',{
        data,
        xField: 'total',
        yField: 'name',
        seriesField: 'name',
        legend: {
          position: 'top-left',
        },
        autoFit:true,
        maxBarWidth:50
      })
      bar.render();

    })


    return{

    }

  }

}
</script>
