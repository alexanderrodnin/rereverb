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
Be careful: all vps should be disabled.

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
  
Build docker images and run **user-service** with skaffold:
```shell
cd user-service
skaffold run
cd ..
```

### Run auth ingress configuration
```shell
cd env
kubectl apply -f app-ingress.yaml
kubectl apply -f auth-ingress.yaml
```
