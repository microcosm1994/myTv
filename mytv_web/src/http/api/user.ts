import request from '@/http/request';

/**
 * 注册账号
 */
export function register(data) {
  return request({
    url: '/user/register',
    method: 'post',
    data
  });
}

/**
 * 登录
 */
export function login(data) {
  return request({
    url: '/user/login',
    method: 'post',
    data
  });
}

/**
 * 获取用户信息
 */
export function getUserInfo() {
  return request({
    url: '/user/get',
    method: 'get'
  });
}

/**
 * 登出
 */
export function logout() {
  return request({
    url: '/user/logout',
    method: 'get'
  });
}

/**
 * 查询用户
 */
export function searchUserInfo(data) {
  return request({
    url: '/user/search',
    method: 'post',
    data
  });
}
