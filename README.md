
# Kafka com spring - Producer | Consumer

Projeto dedicado a conhecer e implementar utilização de menssageria kafka com spring. Objetivo de simular uma loja via api Rest (producer) e um service (consumer) de comunicação.




## Instalação

Pré requisitos:

JRE instalado.

Baixe o Zookeeper  em :  https://zookeeper.apache.org/releases.html#download

Descompacte no C: 

reenomei para zookeeper

1. Dentro de \conf renomeei o arquivo zoo_sample.cfg para zoo.cfg;

2. Edite 'dataDir' apontando para uma pasta Data entro do projeto ou relativa:
valor: C:\\zookeeper\\data

Abra o terminal no caminho e execute

```bash
.\bin\zkserver
```

Baixe o Kafka em : https://kafka.apache.org/downloads

Descompacte no C: 

reenomei para kafka

1. Editei o arquivo \config\server.properties apotando para pasta 'logs' que vai criar dentro do dir kafka:

log.dirs=C:\\kafka\\logs

Abra o terminal no caminho kafka e execute
```bash
.\bin\windows\kafka-server-start.bat .\config\server.properties
```
## Autores

- [@DiogoHumberto](https://www.github.com/DiogoHumberto)


## 🛠 Habilidades
Java, Spring, Kafka, API Rest...

