#!/bin/bash

echo '-------- generate template start -------------'

source="$workingDir/peacetrue-application-webmvc/"
target="$workingDir/peacetrue-template/peacetrue-template-webmvc/src/main/resources/webmvc/"
# 变量
group='com.github.peacetrue.application'
name='peacetrue-application-webmvc'
title='Spring WebMVC'
path=${group//.//} # com/github/peacetrue/application
#class_name=$(sed -r 's/(^|-)(\w)/\U\2/g' <<< "$name") # PeacetrueApplicationWebmvc TODO GNU supported、Mac unsupported
class_name='PeacetrueApplicationWebmvc'

cd "$source" || exit
rm -rf "${target}*"

# build.gradle
cp build.gradle "${target}build.gradle.vm"
target_build='group "${repository.group}"\nversion "${esc.d}{${lh.lc(${repository.name})}Version}${esc.d}{tailSnapshot}"\ndescription "${repository.title}"'
sed -i '' "s|description \"$title\"|$target_build|" "${target}build.gradle.vm"
# extend
mkdir "${target}extend"
tee "${target}extend/dependencies.gradle.vm"<<EOF
dependencies {
  #foreach(\$dependency in \${repository.dependencies})
  implementation "\$dependency"
  #end
}
EOF
# gradle.properties
tee "$target/gradle.properties.vm" <<EOF
tailSnapshot=-SNAPSHOT
peaceGradleVersion=1.1.1
springBootDependencies=2.7.0
\${lh.lc(\${repository.name})}Version=1.0.0
EOF

# src
echo 'handle src'
target_main_java=$target'src/main/java/${clazz.path(${repository.group})}/'
mkdir -p "$target_main_java"
target_main_application=$target_main_java'$lh.uc(${repository.name})Application.java.vm'
cp "src/main/java/$path/${class_name}Application.java" "$target_main_application"
sed -i '' "s|package $group;|package \${repository.group};|" "$target_main_application"
sed -i '' "s|${title}|\${repository.title}|" "$target_main_application"
sed -i '' "s|$class_name|\$lh.uc(\${repository.name})|" "$target_main_application"
target_main_configuration=$target_main_java'$lh.uc(${repository.name})Configuration.java.vm'
cp "src/main/java/$path/${class_name}Configuration.java" "$target_main_configuration"
sed -i '' "s|package $group;|package \${repository.group};|" "$target_main_configuration"
sed -i '' "s|${title}|\${repository.title}|" "$target_main_configuration"
sed -i '' "s|$class_name|\$lh.uc(\${repository.name})|" "$target_main_configuration"
target_main_resources=$target'src/main/resources/'
mkdir -p "$target_main_resources"
cp "src/main/resources/application.yml" "${target_main_resources}application.yml.vm"
sed -i '' "s|\\$|\${esc.d}|" "${target_main_resources}application.yml.vm"
sed -i '' "s|${name}|\${repository.name}|" "${target_main_resources}application.yml.vm"
cp "src/main/resources/log4jdbc.properties" "${target_main_resources}log4jdbc.properties"


target_test_java=$target'src/test/java/${clazz.path(${repository.group})}/'
mkdir -p "$target_test_java"
target_test_application=$target_test_java'$lh.uc(${repository.name})ApplicationTest.java.vm'
cp "src/test/java/$path/${class_name}ApplicationTest.java" "$target_test_application"
sed -i '' "s|package $group;|package \${repository.group};|" "$target_test_application"
sed -i '' "s|${title}|\${repository.title}|" "$target_test_application"
sed -i '' "s|$class_name|\$lh.uc(\${repository.name})|" "$target_test_application"
mkdir -p "${target}src/test/resources"
cp "src/test/resources/application-unittest.yml" "${target}src/test/resources/application-unittest.yml"

# 空目录添加占位文件
find "${target}" -type d -empty | xargs -I '{}' touch "{}/.gitkeep"

echo '-------- generate template over -------------'
