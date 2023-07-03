import request from '../utils/requests.js'

export function queryById(data) {
	return request({
		url: '/student/getStudent',//请求接口
		method: 'post',//请求方式
		data//请求参数
	})
}

//登录
export function login(data) {
	return request({
		url: '/login/login',//请求接口
		method: 'post',//请求方式
		data//请求参数
	})
}

//退出登录
export function loginOut(data) {
	return request({
		url: '/login/loginOut',//请求接口
		method: 'post',//请求方式
		data//请求参数
	})
}

