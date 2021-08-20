
const routes = [
  {
    path: '/main',
    component: () => import('layouts/MainLayout.vue'),
    name:'main',
    redirect:'/main/index',
    children: [
      { path: 'index', component: () => import('pages/indexshow.vue') },
      {path: 'chart',component:()=>import('pages/score.vue')},
      {path: 'addscore',component:()=>import('pages/addscore.vue')},
      {path:'gift',component:()=>import('pages/gift.vue')}
    ]
  },
  {path:'/',
   name:'login',
   component:()=>import('pages/login.vue')
  },
  {path: '/refresh',component:()=>import('pages/blank.vue')},
  {
    path: '/register',
    name: 'register',
    component:()=>import('components/initial.vue')
  },
  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/Error404.vue')
  }
]

export default routes
