/*
export function someAction (context) {
}
*/
import {api} from "boot/axios";

export function getScore({commit},id){

  return api.get(process.env.API_URL+"/score/"+id)
    .then(res=>{
      commit('setScoreList',res.data)
      sessionStorage.setItem('scoreList',JSON.stringify(res.data))
    })
}
