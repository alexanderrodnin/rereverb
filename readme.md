# ReReverb
## Build and run
### Run minikube 
```shell
minikube start \
--cpus=4 \
--memory=8g \
--cni=flannel \
--kubernetes-version="v1.19.0" \
--driver=hyperkit
minikube addons enable ingress
```

### Run user-service
Run user-service **Postgres**
```shell
cd user-service/env
helm repo add bitnami https://charts.bitnami.com/bitnami
helm install user-service-pg bitnami/postgresql -f helm_postgres/bitnami_postgresql.yaml 
cd ../..
```

Build **user-service** executable file:
```shell
cd user-service
mvn clean package -DskipTests
cd ..
```  
  
Build docker images and run it with skaffold:
```shell
cd user-service
skaffold run
cd ..
```