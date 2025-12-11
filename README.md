## 📋 Descrição

API REST para gestão de clínica, permitindo controle de médicos, pacientes e suas informações. Desenvolvido com Spring Boot, Java 21 e MySQL, oferecendo endpoints para gerenciamento completo de médicos e pacientes com suporte a paginação e exclusão lógica.

## 🚀 Tecnologias Utilizadas

- **Java 21** - Linguagem de programação
- **Spring Boot 4.0.0** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Spring Web** - API REST
- **Spring Validation** - Validação de dados
- **Spring Security** - Segurança, autenticação e autorização de usuários
- **MySQL** - Banco de dados relacional
- **Flyway** - Versionamento e migração de banco de dados
- **Lombok** - Redução de código repetitivo
- **Spring Boot DevTools** - Automatização da reinicialização da aplicação durante desenvolvimento

## 📦 Pré-requisitos

Antes de começar, certifique-se de ter instalado em sua máquina:

- **Java Development Kit (JDK) 21** ou superior
  - Verifique a instalação: `java -version`
  - Download: [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) ou [OpenJDK](https://adoptium.net/)

-  **Maven 3.6+** (opcional, o projeto inclui Maven Wrapper)

  - Verifique a instalação: `mvn -version`
  - Download: [Apache Maven](https://maven.apache.org/download.cgi)

- **MySQL 8.0+** (acesso ao servidor)
  - O projeto está configurado para conectar a um banco MySQL
  - Certifique-se de ter as credenciais corretas

- **Git** (para clonar o repositório)
  - Verifique a instalação: `git --version`
  - Download: [Git](https://git-scm.com/downloads)

## 🚀 Instruções de Inicialização

### 1. Configurar o Banco de Dados

Edite o arquivo `src/main/resources/application.properties` com suas credenciais:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/clinica
spring.datasource.username=root
spring.datasource.password=SUA_SENHA
```

### 2. Instalar Dependências

O projeto utiliza Maven Wrapper, então não é necessário ter Maven instalado globalmente.

#### No Windows (PowerShell):
```powershell
.\mvnw.cmd clean install
```

#### No Linux/Mac:
```bash
./mvnw clean install
```

Ou, se tiver Maven instalado:
```bash
mvn clean install
```

### 3. Executar o Projeto

#### Opção 1: Usando Maven Wrapper (Windows)
```powershell
.\mvnw.cmd spring-boot:run
```

#### Opção 2: Usando Maven Wrapper (Linux/Mac)
```bash
./mvnw spring-boot:run
```

#### Opção 3: Usando Maven (se instalado)
```bash
mvn spring-boot:run
```

#### Opção 4: Executando o JAR compilado
```bash
java -jar target/clinica-0.0.1-SNAPSHOT.jar
```

### 4. Verificar se está funcionando

Após iniciar o projeto, a aplicação estará disponível em:
```
http://localhost:8080
```

Você deverá ver logs no console indicando que a aplicação foi iniciada com sucesso:
```
Started ClinicaApplication in X.XXX seconds
```

## 📁 Estrutura do Projeto

```
clinica-api/
├── src/
│   ├── main/
│   │   ├── java/com/alura/clinica/
│   │   │   ├── controller/               # Controllers REST
│   │   │   ├── dto/                      # Data Transfer Objects
│   │   │   ├── model/                    # Entidades JPA
│   │   │   ├── repository/               # Repositórios JPA
│   │   │   ├── service/                  # Lógica de negócio
│   │   │   └── ClinicaApplication.java
│   │   └── resources/
│   │       ├── application.properties          # Configurações principais
│   │       └── db/migration/                   # Migrations Flyway
│   └── test/                             # Testes unitários
├── pom.xml                               # Configuração Maven
└── README.md                             # Este arquivo
```

## 📝 Endpoints da API

A API está disponível no prefixo `/api` e oferece os seguintes recursos:

### Médicos
- `POST /api/medicos` - Cadastrar novo médico
- `GET /api/medicos` - Listar médicos com paginação
- `PUT /api/medicos/{id}` - Atualizar dados do médico
- `DELETE /api/medicos/{id}` - Deletar médico (exclusão lógica)

### Pacientes
- `POST /api/pacientes` - Cadastrar novo paciente
- `GET /api/pacientes` - Listar pacientes com paginação
- `PUT /api/pacientes/{id}` - Atualizar dados do paciente
- `DELETE /api/pacientes/{id}` - Deletar paciente (exclusão lógica)

## 💡 Recursos Principais

- **Paginação**: Todos os endpoints de listagem suportam paginação configurável
- **Exclusão Lógica**: Médicos e pacientes não são removidos do banco, apenas marcados como inativos
- **DTOs**: Separação entre requisições e respostas da API
- **Validação Automática**: Validações em tempo de requisição
- **Flyway Migrations**: Controle de versão do banco de dados