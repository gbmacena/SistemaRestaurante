# Sistema Restaurante

Este projeto é uma estrutura inicial para um sistema de restaurante em Java, utilizando SQLite como banco de dados.

## Pré-requisitos

- Java 17 ou superior
- [Driver JDBC do SQLite](https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.42.0.0/sqlite-jdbc-3.42.0.0.jar)

## Como rodar o projeto no Windows

1. **Clone o repositório:**

   Abra o Prompt de Comando ou PowerShell e execute:

   ```sh
   git clone <url-do-repositorio>
   cd sistema-restaurante
   ```

2. **Baixe o driver JDBC do SQLite:**

   Baixe o arquivo [sqlite-jdbc-3.42.0.0.jar](https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.42.0.0/sqlite-jdbc-3.42.0.0.jar) e coloque-o na pasta `libs` do projeto (crie a pasta se necessário).

3. **Compile o projeto:**

   No Prompt de Comando, execute:

   ```sh
   javac -d bin -cp libs\sqlite-jdbc-3.42.0.0.jar src\dao\Database.java src\Main.java
   ```

4. **Execute o projeto:**

   No Prompt de Comando, execute:

   ```sh
   java -cp "libs\sqlite-jdbc-3.42.0.0.jar;bin" Main
   ```

O banco de dados (`restaurante.db`) será criado automaticamente na primeira execução.

---
