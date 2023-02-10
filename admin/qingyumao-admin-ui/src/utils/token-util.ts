const TOKEN_KEY = "qym_admin_token";

export function getToken() {
  if (typeof window.localStorage !== 'undefined') {
    return window.localStorage.getItem(TOKEN_KEY);
  }
  return null;
}

export function setToken(token: string) {
  if (typeof window.localStorage !== 'undefined') {
    return window.localStorage.setItem(TOKEN_KEY, token);
  }
  return null;
}

export function clearToken() {
  if (typeof window.localStorage !== 'undefined') {
    window.localStorage.removeItem(TOKEN_KEY);
  }
}


/**
 * 判断请求是否需要携带token信息
 *
 * @param url 请求url
 * @returns
 */
export function ignoreCarryToken(url: string) {
  // 一些不需要认证的请求，请在此添加
  const ignoreCarryTokenUrl = [
    '/api/auth/token',
  ];
  if (ignoreCarryTokenUrl.indexOf(url) === -1) {
    return false;
  }
  return true;
}
