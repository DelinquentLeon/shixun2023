import request from '../utils/requests.js'
export function docSelectOne(data) {
	return request({
		url: '/doc/selectOne',//请求接口
		method: 'post',//请求方式
		data//请求参数
	})
}
