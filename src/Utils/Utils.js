
function sumScore(scoreList){
  const data =[];
  const loginUserId = localStorage.getItem('userId');
  let mySum=0;
  let otherSum = 0;
  for(var i= 0;i<scoreList.length;i++){
    if(scoreList[i].userId!==loginUserId){
      otherSum+=scoreList[i].score;
    }
    else{
      mySum+=scoreList[i].score;
    }
  }
  data.push({
    name:'you',total:mySum
  },{name:'other',total:otherSum})
  return data;
}
function refreshScoreStore(store){
  return store.dispatch('score/getScore', localStorage.getItem('groupId'))

}
export {sumScore,refreshScoreStore}
