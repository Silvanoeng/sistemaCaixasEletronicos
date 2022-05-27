# sistema para caixas eletrônicos

### 1°
Colocar o eureka-server para rodar.

### 2°
Colocar o server-config para rodar.

### 3°
Colocar o caixas para rodar.

### 4°
Testar os endpoint no seu navegador.

Criar um novo caixa (sem dinheiro)
http://localhost:8011/new

Colocar dinheiro (deve ser informado o caixa=1 e a quantia valor=1541.35)
http://localhost:8011/transferir?caixa=1&valor=1541.35

Verificar o total que um caixa tem (deve ser informado o caixa=1)
http://localhost:8011/total?caixa=1

Relatorio de notas e moedas que o caixa tem (deve ser informado o caixa=1)
http://localhost:8011/caixa?caixa=1

Relatorio de número de caixas do banco
http://localhost:8011/banco

Pode ser definido no application.yml o pais do bando (brasil - usa)
```yml
cloud:
    config:
      profile: usa
```

