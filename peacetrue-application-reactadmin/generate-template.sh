#!/bin/bash

echo '-------- generate template start -------------'

source="$workingDir/peacetrue-application-reactadmin/"
target="$workingDir/peacetrue-template/peacetrue-template-reactadmin/src/main/resources/reactadmin/"
# 模板变量
name='peacetrue-application-reactadmin'
title='React Admin'

cd "$source" || exit
rm -rf "${target}" && mkdir -p "${target}"  # rm -rf "${target}/*" zsh 有确认提示

# 复制必要的文件
fileNames=(public src .env* package.json pnpm-lock.yaml tsconfig.json)
for fileName in "${fileNames[@]}" ; do
  cp -r "$fileName" "${target}$fileName"
done

# 制作模板 vm 文件
cd "${target}public" || exit
mv index.html index.html.vm
mv manifest.json manifest.json.vm
sed -i "s|$name|\${repository.name}|" *.vm
sed -i "s|$title|\${repository.title}|" *.vm

cd "${target}src/modules" || exit
mv installedModules.tsx installedModules.tsx.vm
sed -i '/const modules/a\ #foreach($dependency in $dependencies)...(require("$dependency.name") as InstalledModule).default,\n#end' "installedModules.tsx.vm"

cd "${target}" || exit
mv package.json package.json.vm
sed -i "s|$name|\${repository.name}|" "package.json.vm"
sed -i '/web-vitals/a\ #foreach($dependency in $dependencies)\t,"$dependency.name": "$dependency.version"\n#end' "package.json.vm"
mv .env.local .env.local.vm
echo '#if($dependencies)REACT_APP_BASE_URL=http://localhost:8080#end' > .env.local.vm

echo '-------- generate template over -------------'
