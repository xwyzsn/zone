import {ref} from 'vue'
import { Notify } from 'quasar'

function checkEmail(val){
  let regExp = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
  if(!regExp.test(val)){
    return false;
  }else {
  return true
  }
}

export {checkEmail}
