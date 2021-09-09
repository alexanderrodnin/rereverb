# ReReverb
## Requirements
[Here (lang: ru)](docs/requirements/stories.md)

## System design
[Here (lang: ru)](docs/system-design/services-description.md)

## Build and run
### Run minikube 
```shell
## mac os
minikube start \
--cpus=4 \
--memory=8g \
--cni=flannel \
--kubernetes-version="v1.19.0" \
--driver=hyperkit
minikube addons enable ingress

## linux based
minikube start \
--cpus=3 \
--memory=6g
minikube addons enable ingress
```
Be careful: all vps should be disabled.

### Run infrastructure
```shell
## Add Bitnami repo for helm
helm repo add bitnami https://charts.bitnami.com/bitnami

## Add PG for user-service
helm install user-service-pg bitnami/postgresql -f user-service/env/helm_postgres/bitnami_postgresql.yaml 
## Add PG for advertisement-service
helm install advertisement-service-pg bitnami/postgresql -f advertisement-service/env/helm_postgres/bitnami_postgresql.yaml
## Add PG for order-service
helm install order-service-pg bitnami/postgresql -f order-service/env/helm_postgres/bitnami_postgresql.yaml
## Add Kafka
helm install kafka bitnami/kafka -f env/kafka/bitnami_kafka.yaml 
## Add Prometheus
## For accessing to prometheus use port-forward.
## Example
## kubectl port-forward [pod name of prometheus server] 9090
## open browser on http://localhost:9090
helm repo add stable https://charts.helm.sh/stable 
helm install rereverb-prometheus stable/prometheus 
## Add Grafana
helm install my-grafana stable/grafana
```

### Run user-service
```shell
cd user-service
mvn clean package -DskipTests
skaffold run
cd ..
```
### Run advertisement-service
```shell
cd advertisement-service
mvn clean package -DskipTests
skaffold run
cd ..
```
### Run order-service
```shell
cd order-service
mvn clean package -DskipTests
skaffold run
cd ..
```

### Run auth ingress configuration
```shell
cd env
kubectl apply -f env/app-ingress.yaml
kubectl apply -f env/auth-ingress.yaml
```

## Usage test

### Postman collection
[here](docs/postman/Diploma%20-%20final.postman_collection.json)

### Curl flow example
```shell
## ----------------------------------#
##        Users registration         #
## ----------------------------------#

## Register first user
## response contains:
## Set-Cookie: session_id=YzE1MDJhN2UtNmUwMC00NjVlLThjOWQtMmJkZGQ4OTc4OWY5O0drZ0VXV1Zj; Path=/
## User this cookie for authentication and authorization in next requests
curl --request POST '192.168.49.2/auth/register' --header "Host: arch.homework"  --header 'Content-Type: application/json' --data-raw '{"email": "first@user", "name": "first", "password": "first"}' -v

## Login (optional example) first user
## response contains:
## Set-Cookie: session_id=YzE1MDJhN2UtNmUwMC00NjVlLThjOWQtMmJkZGQ4OTc4OWY5O0drZ0VXV1Zj; Path=/
## User this cookie for authentication and authorization in next requests
curl --request POST '192.168.49.2/auth/login' --header "Host: arch.homework"  --header 'Content-Type: application/json' --data-raw '{"name": "first", "password": "first"}' -v

## Getting info about first user (optional example)
## Be careful, here and for another secured requests call with --cookie "session_id=YzE1MDJhN2UtNmUwMC00NjVlLThjOWQtMmJkZGQ4OTc4OWY5O0drZ0VXV1Zj"
## Set-Cookie: session_id=YzE1MDJhN2UtNmUwMC00NjVlLThjOWQtMmJkZGQ4OTc4OWY5O0drZ0VXV1Zj; Path=/
## Response will look:
## {"id":"c1502a7e-6e00-465e-8c9d-2bddd89789f9","email":"first@user","name":"first","password":"first"}
curl --request GET '192.168.49.2/users/me' --header "Host: arch.homework"  --cookie "session_id=YzE1MDJhN2UtNmUwMC00NjVlLThjOWQtMmJkZGQ4OTc4OWY5O0drZ0VXV1Zj" -v

## Register first user
## response contains:
## Set-Cookie: session_id=NGUzZTcwNzItYmE1Yi00MzdjLWIxNDUtNmJiZjdmZmRjZGE4O3VHMGRmdXBm; Path=/
## User this cookie for authentication and authorization in next requests
curl --request POST '192.168.49.2/auth/register' --header "Host: arch.homework"  --header 'Content-Type: application/json' --data-raw '{"email": "second@user", "name": "second", "password": "second"}' -v


## ----------------------------------#
##    Logic with advs and orders     #
## ----------------------------------#

## First user posts adv
## Response:
## HTTP/1.1 200 with empty body 
curl --location --request POST '192.168.49.2/advertisements/' --header "Host: arch.homework" --cookie "session_id=YzE1MDJhN2UtNmUwMC00NjVlLThjOWQtMmJkZGQ4OTc4OWY5O0drZ0VXV1Zj" --header 'Content-Type: application/json' --data-raw '{"header": "Fender telecaster", "description": "1995", "price": 1500.00}' -v

## First user checks his own advs
## Response:
## [
##    {
##       "description" : "1995",
##       "header" : "Fender Teletaster",
##       "id" : "b0abffd0-ec33-4fec-9e09-5a6aafdc1205",
##       "price" : 1500,
##       "status" : "OPENED",
##       "userId" : "c1502a7e-6e00-465e-8c9d-2bddd89789f9"
##    }
## ]
curl --location --request GET '192.168.49.2/advertisements/my' --header "Host: arch.homework" --cookie "session_id=YzE1MDJhN2UtNmUwMC00NjVlLThjOWQtMmJkZGQ4OTc4OWY5O0drZ0VXV1Zj" -v

## Second user posts new order
## Response: HTTP/1.1 200
curl --location --request POST '192.168.49.2/orders/' --cookie "session_id=NGUzZTcwNzItYmE1Yi00MzdjLWIxNDUtNmJiZjdmZmRjZGE4O3VHMGRmdXBm" --header "Host: arch.homework" --header 'Content-Type: application/json' --data-raw '{"advertisementId": "b0abffd0-ec33-4fec-9e09-5a6aafdc1205", "message": "Hi! I wanna buy this stuff!"}' -v

## Second user checks his orders
## Response: 
## [
##    {
##       "advertisementId" : "b0abffd0-ec33-4fec-9e09-5a6aafdc1205",
##       "buyerId" : "4e3e7072-ba5b-437c-b145-6bbf7ffdcda8",
##       "id" : "198e3991-6362-43bb-91fa-303513e68ddf",
##       "orderChatMessages" : [],
##       "status" : "ACTIVE"
##    }
## ]
curl --location --request GET '192.168.49.2/orders/my' --cookie "session_id=NGUzZTcwNzItYmE1Yi00MzdjLWIxNDUtNmJiZjdmZmRjZGE4O3VHMGRmdXBm" --header "Host: arch.homework"

## First user approve the order
## Response: HTTP/1.1 200
curl --location --request POST '192.168.49.2/orders/status' --header "Host: arch.homework" --cookie "session_id=YzE1MDJhN2UtNmUwMC00NjVlLThjOWQtMmJkZGQ4OTc4OWY5O0drZ0VXV1Zj" --header 'Content-Type: application/json' --data-raw '{"orderId": "36b28e32-0d83-44d8-a3a3-f5034484b6c1", "status": "SUCCESS"}'


## Second user checks his orders (it's repeat of previous one)
## Response: 
## [
##    {
##       "advertisementId" : "b0abffd0-ec33-4fec-9e09-5a6aafdc1205",
##       "buyerId" : "4e3e7072-ba5b-437c-b145-6bbf7ffdcda8",
##       "id" : "198e3991-6362-43bb-91fa-303513e68ddf",
##       "orderChatMessages" : [],
##       "status" : "SUCCESS"
##    }
## ]
curl --location --request GET '192.168.49.2/orders/my' --cookie "session_id=NGUzZTcwNzItYmE1Yi00MzdjLWIxNDUtNmJiZjdmZmRjZGE4O3VHMGRmdXBm" --header "Host: arch.homework"


## First user checks his own advs (it's repeat of previous one)
## Response:
## [
##    {
##       "description" : "1995",
##       "header" : "Fender Teletaster",
##       "id" : "b0abffd0-ec33-4fec-9e09-5a6aafdc1205",
##       "price" : 1500,
##       "status" : "CLOSED",
##       "userId" : "c1502a7e-6e00-465e-8c9d-2bddd89789f9"
##    }
## ]
curl --location --request GET '192.168.49.2/advertisements/my' --header "Host: arch.homework" --cookie "session_id=YzE1MDJhN2UtNmUwMC00NjVlLThjOWQtMmJkZGQ4OTc4OWY5O0drZ0VXV1Zj" -v
```
