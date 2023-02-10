// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';

const prefix = '/api/auth'

/** 获取当前的用户 GET /api/currentUser */
export async function fetchCurrentUser(options?: { [key: string]: any }) {
  return request<C.SingleResponse<Login.CurrUser>>(`${prefix}/current`, {
    method: 'GET',
    ...(options || {}),
  });
}


/** 退出登录接口 POST /api/login/outLogin */
export async function outLogin(options?: { [key: string]: any }) {
  return request<Record<string, any>>(`${prefix}/login_out`, {
    method: 'PUT',
    ...(options || {}),
  });
}

/** 登录接口 POST  */
export async function login(body: Login.LoginParams, options?: { [key: string]: any }) {
  return request<C.SingleResponse<Login.Token>>(`${prefix}/token`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    params: Object.assign(body, {client_id: '1002', grant_type: 'password'}),
    ...(options || {}),
  });
}
