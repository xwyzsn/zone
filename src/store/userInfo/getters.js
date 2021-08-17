export function getUser (state) {
  return {
    username: state.username,
    userId:state.userId,
    groupId:state.groupId,
    date:state.date,
  }
}
