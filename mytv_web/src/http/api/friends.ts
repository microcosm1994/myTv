import request from '@/http/request';

/**
 * 发送添加好友请求
 */
export function addFriendsAsk(data) {
  return request({
    url: '/friendsAsk/add',
    method: 'post',
    data
  });
}

/**
 * 发送添加好友请求
 */
export function getFriendsAsk(params) {
    return request({
      url: '/friendsAsk/get',
      method: 'get',
      params
    });
  }

