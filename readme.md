# Customer Service

Projeto feito para o canal do youtube, link da playlist: https://www.youtube.com/playlist?list=PLC0HOi4k6IJrJXnEPTj6BHsw_XuADquCn

### Pré requisitos
- Instalar o docker: https://www.docker.com/
- Instalar o docker compose: https://docs.docker.com/compose/
- Executar o docker-compose que está na raiz com o seguinte comando:
```
$ docker-compose up
```
- Instalar o awslocal para visualizar a fila e as mensagens: https://docs.localstack.cloud/user-guide/integrations/aws-cli/#localstack-aws-cli-awslocal 

### SQS
- Para criar as filas execute o .sh na raiz:
```
$ ./create-queues.sh
```
- Para visualizar as filas criadas execute o comando:
```
$ awslocal sqs list-queues
```
- Para ver as mensagens da fila execute o comando:
```
$ awslocal sqs receive-message --queue-url http://localhost:4566/000000000000/customer-service-queue
```
