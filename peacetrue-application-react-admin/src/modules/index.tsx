// 动态导出模块

export function getResources() {
  let modules = process.env.REACT_APP_MODULES;
  if (!modules) return [];
  if (modules === 'guesser') return [require(`./guesser`)];
  return modules.split(",").map(module => require(`./${module.trim()}`));
}

export const resources = getResources().map(item => item.resource);

