import request from '@/http/request';

/**
 * 发送添加好友请求
 */
export function create(data) {
  return request({
    url: '/msg/create',
    method: 'post',
    data
  });
}

