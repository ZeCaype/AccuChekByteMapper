# cae-backend
CAE-CMC Backend Project

## Travis-CI

 master -> [![Build Status](https://travis-ci.com/CentreInnovationLevio/cae-backend.svg?token=JqxtUsPnWrNqmkwMfxg7&branch=master)](https://travis-ci.com/CentreInnovationLevio/cae-backend) 
 
 develop -> [![Build Status](https://travis-ci.com/CentreInnovationLevio/cae-backend.svg?token=JqxtUsPnWrNqmkwMfxg7&branch=develop)](https://travis-ci.com/CentreInnovationLevio/cae-backend)

## URLs

### master branch
* https://staging-cmc.leviolab.com (frontend)
* https://api.staging-cmc.leviolab.com/v1 (backend)
* https://portainer.staging-cmc.leviolab.com (docker swarm DEV ONLY)
* https://kibana.staging-cmc.leviolab.com (kibana DEV ONLY)

### develop branch
* https://dev-cmc.leviolab.com (frontend)
* https://api.dev-cmc.leviolab.com/v1 (backend)
* https://portainer.dev-cmc.leviolab.com (docker swarm DEV ONLY)
* https://kibana.dev-cmc.leviolab.com (kibana DEV ONLY)

## Project setup
```
./mvnw install -DskipTests=true -B -V
```
### Run the tests
```
./mvnw test -B
```
### Build the docker image
```
./mvnw install dockerfile:build -DskipTests=true -B
```
