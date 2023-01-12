// @ts-ignore
/* eslint-disable */

declare namespace Login {

  type CurrUser = {
    id: string;
    name: string;
    avatarUrl: string;
    extraInfo : any;
  };

  type CurrentUser = {
    name: string;
    avatarUrl: string;
    type: 'TEAM' | 'SYSTEM', // SYSTEM：系统管理员，TEAM:团队管理员，
    selectedTeam: {
      id: string,
      name: string,
    } | undefined,// 当前选择的团队。undefined 表示没有选择。
    linkedTeams: {
      id: string,
      name: string,
    }[],// 关联的团队
    superState: 0 | 1, // 超级状态，0：非超级，1：超级

    // 下面暂时不用
    email?: string;
    signature?: string;
    title?: string;
    group?: string;
    tags?: { key?: string; label?: string }[];
    notifyCount?: number;
    unreadCount?: number;
    country?: string;
    access?: string;
    geographic?: {
      province?: { label?: string; key?: string };
      city?: { label?: string; key?: string };
    };
    address?: string;
    phone?: string;
  };

  type LoginParams = {
    username?: string;
    password?: string;
    autoLogin?: boolean;
    type?: string;
  };

  /**
   * Token
   */
  type Token = {
    token: string;
    client_id: string;
    expiresIn: number;
    openid: string;
    refresh_expires_in: number;
    refresh_token: string;
    scope: string;
  }

}
