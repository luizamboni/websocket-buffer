# /bin/bash

cd buffer
mvn clean install
cd ../

cd productor
mvn clean install
cd ../

cd consumer
mvn clean install


cd ../
