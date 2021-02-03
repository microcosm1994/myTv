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
 * 获取好友请求
 */
export function getFriendsAsk(params) {
  return request({
    url: '/friendsAsk/get',
    method: 'get',
    params
  });
}

/**
 * 添加好友
 */
export function putFriendsAsk(data) {
  return request({
    url: '/friendsAsk/put',
    method: 'post',
    data
  });
}

/**
 * 获取好友列表
 */
export function getFriendsList(params) {
  return request({
    url: '/friends/getList',
    method: 'get',
    params
  });
}
