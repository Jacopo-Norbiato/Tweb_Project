import {createRouter, createWebHistory} from "vue-router";

import Home from "../views/Home.vue"
import Agenda from "../views/Agenda.vue"
import RegistrationForm from '../components/RegistrationForm.vue'
import Calendar from "@/views/Calendar.vue";
import Information from "@/views/Information.vue";
import information from "@/views/Information.vue";
import Logout from "@/views/Logout.vue";
import logout from "@/views/Logout.vue";
import LoginForm from "@/components/LoginForm.vue";
import Edit from "@/views/Edit.vue";

const routes = [
		{
		  path: '/',
		  name: 'home',
		  component: Home
		},
		{
			path: '/about',
			name: 'calendar',
			component: Calendar
		},

		{
		  path: '/agenda',
		  name: 'agenda',
		  component: Agenda
		},
		{
			path: '/registration',
			name: 'registration',
			component: RegistrationForm
		},
		{
			path: '/information',
			name: information,
			component: Information
		},
		{
			path: '/edit',
			name:'edit',
			component: Edit
		},
		{
			path: '/logout',
			name: logout,
			component: Logout
		},
		{
		path: '/loginForm',
		name: 'LoginForm',
		component: LoginForm
		},
		]

	const router = createRouter({
		history: createWebHistory(),
		routes
	})

	export default router