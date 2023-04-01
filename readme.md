Para subir o keycloack, execute o seguinte comando no seu cmd/terminal com o docker instalado

```
docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:21.0.1 start-dev
```
