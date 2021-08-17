
const routes = [
  {
    path: '/main',
    component: () => import('layouts/MainLayout.vue'),
    name:'main',
    children: [
      { path: '', component: () => import('pages/indexshow.vue') },
    ]
  },
  {path:'/',
   name:'login',
   component:()=>import('pages/login.vue')
  },
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
