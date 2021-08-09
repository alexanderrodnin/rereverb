## ReReverb
### Build and run
Run minikube 
```shell
minikube start --vm=true
minikube addons enable metallb
minikube addons enable ingress
```

Run *Postgres*
```shell
cd env
helm repo add bitnami https://charts.bitnami.com/bitnami
helm install pg bitnami/postgresql -f helm_postgres/bitnami_postgresql.yaml 
cd ..
```

Build **user-service** executable file:
```shell
cd user-service
mvn clean package -DskipTests
```  
  
Build docker images and run it with skaffold:
```shell

```