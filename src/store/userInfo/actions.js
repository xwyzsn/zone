import {api} from "boot/axios";
export function getUser ({commit}) {
  let email = localStorage.getItem('email')
  return api.get(process.env.API_URL+"/"+email).then(res=>{
    console.log(res)
    commit('setUser',res.data)
  })
}
