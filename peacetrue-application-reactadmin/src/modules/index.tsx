import {LocalModule, Module} from "./Module";
import installedModules from "./installedModules"
import localModules from "./localModules";

// 获取模块集合
export function getModules(): Module[] {
  let modules = [];
  modules.push(...installedModules);
  modules.push(...localModules);
  if (modules.length === 0) modules.push((require(`./guesser`) as LocalModule).default);
  return modules;
}
