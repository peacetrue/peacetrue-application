// 动态导出模块
export const resources = (process.env.REACT_APP_MODULES || '').split(",")
  .map(resource => require(`./${resource.trim()}`).resource as JSX.Element);

