## ReReverb
### Build and run
Run minikube 
```shell
minikube start --vm=true
minikube addons enable metallb
minikube addons enable ingress
```

Run **Postgres**
```shell
cd env
helm repo add bitnami https://charts.bitnami.com/bitnami
helm install pg bitnami/postgresql -f helm_postgres/bitnami_postgresql.yaml 
cd ..
```

Add schemas to Postgres
```shell
kubectl port-forward {pg-podname} 55432:5432
//connect to PG and execute SQl: create schema user_schema
```

Build **user-service** executable file:
```shell
cd user-service
mvn clean package -DskipTests
cd ..
```  
  
Build docker images and run it with skaffold:
```shell
skaffold run
```