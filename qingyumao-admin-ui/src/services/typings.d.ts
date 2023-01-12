// @ts-ignore
/* eslint-disable */
// 记录一些通用的格式
declare namespace C {
  /**
   * 空响应，没有数据的正常响应
   */
  type EmptyResponse = {
    success: boolean;
    errorCode?: string;
    errorMessage?: string;
    traceId?: string;
  };

  /**
   * 单个对象响应
   */
  type SingleResponse<T> = {
    success: boolean;
    data: T;
    errorCode?: string;
    errorMessage?: string;
    traceId?: string;
  };

  /**
   * 数组响应
   */
  type MultipleResponse<T> = {
    success: boolean;
    data: [T];
    errorCode?: string;
    errorMessage?: string;
    traceId?: string;
  };

  /**
   * 分页查询
   */
  type PageQuery = {
    pageIndex?: number; // 当前页数
    needTotalCount?: boolean;// 是否需要总数
    pageSize?: number;// 页大小
  }

  /**
   * 分页结果
   */
  type PageResult<T> = {
    pageIndex: number; // 当前页数
    totalCount: number;// 总数
    pageSize: number;// 页大小
    list: [T];// 响应集合
  }

  /**
   * 流式查询结果
   */
  type SectionResult<T> = {
    startIndex: number; // 开始位置
    totalCount: number;// 总数
    hasMore: boolean;// 是否还有跟多
    list: [T];// 响应集合
  }
}
