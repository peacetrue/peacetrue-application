#!/bin/bash

pnpm uninstall ra-dictionary
echo '' > .env.local
sed -i 's|   ...|  //...|' "src/modules/installedModules.tsx"
