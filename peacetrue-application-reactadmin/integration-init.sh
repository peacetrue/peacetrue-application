#!/bin/bash

pnpm install ra-dictionary
echo 'REACT_APP_BASE_URL=http://localhost:8080' > .env.local
sed -i 's|  //...|   ...|' "src/modules/installedModules.tsx"
