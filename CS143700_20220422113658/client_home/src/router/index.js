import Vue from 'vue';
import VueRouter from 'vue-router';
import index from '../views/index.vue'
import login from '../views/account/login.vue';

Vue.use(VueRouter)

const routes = [
	// 主页ss
	{
		path: '/',
		name: 'index',
		component: index
	},
	// 登录
	{
		path: '/account/login',
		name: 'login',
		component: login
	},
	// 忘记密码
	{
		path: '/account/forgot',
		name: 'forgot',
		component: () => import('../views/account/forgot.vue')
	},
	// 注册账号
	{
		path: '/account/register',
		name: 'register',
		component: () => import('../views/account/register.vue')
	},
	// 媒体图片
	{
		path: '/media/image',
		name: 'media_image',
		component: () => import('../views/media/image.vue')
	},
	// 音乐
	{
		path: '/media/music',
		name: 'media_music',
		component: () => import('../views/media/music.vue')
	},
	// 媒体视频
	{
		path: '/media/video',
		name: 'media_video',
		component: () => import('../views/media/video.vue')
	},
	// 文章路由
	{
		path: '/article/list',
		name: 'article_list',
		component: () => import('../views/article/list.vue')
	},
	{
		path: '/article/details',
		name: 'article_details',
		component: () => import('../views/article/details.vue')
	},
	// 浏览网站
	// 收藏路由
	{
		path: '/user/collect',
		name: 'collect_list',
		component: () => import('../views/user/collect.vue')
	},
	// 公告路由
	{
		path: '/notice/list',
		name: 'notice_list',
		component: () => import('../views/notice/list.vue')
	},
	{
		path: '/notice/details',
		name: 'notice_details',
		component: () => import('../views/notice/details.vue')
	},
	//发展历程列表路由
	{
		path: '/development_history/list',
		name: '/development_history_list',
		component: () => import('../views/development_history/list.vue')
	},
	//发展历程详情路由
	{
		path: '/development_history/details',
		name: '/development_history_details',
		component: () => import('../views/development_history/details.vue')
	},
	//图书资料列表路由
	{
		path: '/books_and_materials/list',
		name: '/books_and_materials_list',
		component: () => import('../views/books_and_materials/list.vue')
	},
	//图书资料详情路由
	{
		path: '/books_and_materials/details',
		name: '/books_and_materials_details',
		component: () => import('../views/books_and_materials/details.vue')
	},
	//经典文献列表路由
	{
		path: '/classic_literature/list',
		name: '/classic_literature_list',
		component: () => import('../views/classic_literature/list.vue')
	},
	//经典文献详情路由
	{
		path: '/classic_literature/details',
		name: '/classic_literature_details',
		component: () => import('../views/classic_literature/details.vue')
	},
	//视频资料列表路由
	{
		path: '/video_data/list',
		name: '/video_data_list',
		component: () => import('../views/video_data/list.vue')
	},
	//视频资料详情路由
	{
		path: '/video_data/details',
		name: '/video_data_details',
		component: () => import('../views/video_data/details.vue')
	},

	// 用户路由
	{
		path: '/user/index',
		name: 'user_index',
		component: () => import('../views/user/index.vue')
	},
	// 基本信息
	{
		path: '/user/info',
		name: 'user_info',
		component: () => import('../views/user/info.vue')
	},
	// 找回密码
	{
		path: '/user/password',
		name: 'user_password',
		component: () => import('../views/user/password.vue')
	},

	// 搜索
	{
		path: '/search',
		name: 'search',
		component: () => import('../views/search/index.vue')
	},
	// 局部搜索
	{
		path: '/search/details',
		name: 'search_details',
		component: () => import('../views/search/details.vue')
	}
]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})

router.afterEach((to, from, next) => {
	let title = "智慧党建系统-home";
	document.title = title;
	document.logo = "智慧党建系统"
})

export default router
