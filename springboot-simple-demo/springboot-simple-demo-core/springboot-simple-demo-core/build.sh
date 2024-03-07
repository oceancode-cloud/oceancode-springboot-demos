# Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.

#!/usr/bin/env bash
set -e
CORE_PATH=$(pwd)
function build_model(){
  cd ../$1
  mvn clean
  mvn install
}
cd ../../springboot-simple-demo-parent
mvn clean
mvn install

cd $CORE_PATH
build_model springboot-simple-demo-core-model
build_model springboot-simple-demo-core-function
build_model springboot-simple-demo-core