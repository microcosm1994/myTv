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
