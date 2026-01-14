## 📋 Descrição

API REST para gestão de clínica, permitindo controle de médicos, pacientes e suas informações. Desenvolvido com Java, Spring Boot e MySQL, oferecendo endpoints para gerenciamento de médicos, pacientes e consultas.

## 🚀 Tecnologias Utilizadas

- **Java 21** - Linguagem de programação
- **Spring Boot 4.0.0** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Spring Web** - API REST
- **Spring Validation** - Validação de dados
- **Spring Security** - Segurança, autenticação e autorização de usuários
- **Auth0 JWT** - Geração e validação de tokens JWT
- **MySQL** - Banco de dados relacional
- **Flyway** - Versionamento e migração de banco de dados
- **Lombok** - Redução de código repetitivo
- **Spring Boot DevTools** - Automatização da reinicialização da aplicação durante desenvolvimento
- **SpringDoc OpenAPI** - Documentação automática de API com Swagger UI
- **JUnit 5 e Mockito** - Testes unitários e de integração.

## 📦 Pré-requisitos

Antes de começar, certifique-se de ter instalado em sua máquina:

- **Git** (para clonar o repositório)
  - Verifique a instalação: `git --version`
  - Download: [Git](https://git-scm.com/downloads)

Para rodar localmente:

- **Java Development Kit (JDK) 21** ou superior
  - Verifique a instalação: `java -version`
  - Download: [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) ou [OpenJDK](https://adoptium.net/)

-  **Maven 3.6+** (opcional, o projeto inclui Maven Wrapper)

- Verifique a instalação: `mvn -version`
- Download: [Apache Maven](https://maven.apache.org/download.cgi)

- **MySQL 8.0+**
  - O projeto está configurado para conectar a um banco MySQL
  - Certifique-se de ter as credenciais corretas

Para rodar com Docker:

- **Docker** - [Instale aqui](https://www.docker.com/products/docker-desktop)
- **Docker Compose** - Geralmente incluído com Docker Desktop

## 🚀 Instruções de Inicialização

### 1. Configurar o Projeto

Edite o arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/clinica_api
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
api.security.token.secret=SUA_CHAVE_JWT
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

#### Opção 1: Localmente

Execute um dos comandos:

Windows:
```powershell
.\mvnw.cmd spring-boot:run
```

Linux/Mac:
```bash
./mvnw spring-boot:run
```

Com Maven instalado:
```bash
mvn spring-boot:run
```

Ou com JAR compilado:
```bash
java -jar target/clinica-0.0.1-SNAPSHOT.jar
```

#### Opção 2: Usando Docker

Execute o seguinte comando:

```bash
docker compose up -d --build
```

Para parar: 

```bash
docker compose down
```

### 4. Verificar Execução

**Console esperado:**
```
Started ClinicaApplication in X.XXX seconds
```

**Testar a API:**

Faça uma requisição GET para verificar se a aplicação está respondendo:

```bash
curl http://localhost:8080/api/teste
```

Resposta esperada:
- Status: `200 OK`
- Body: `OK`

**Acessar documentação:**

A documentação interativa da API está disponível em:

```
http://localhost:8080/swagger-ui.html
```

## 📁 Estrutura do Projeto

```
clinica-api/
├── src/
│   ├── main/
│   │   ├── java/com/alura/clinica/
│   │   │   ├── controller/               # Controllers REST
│   │   │   │   ├── AuthController.java
│   │   │   │   ├── MedicoController.java
│   │   │   │   ├── PacienteController.java
│   │   │   │   └── ConsultaController.java
│   │   │   │   └── TesteController.java
│   │   │   ├── domain/                   # Lógica de domínio (entidades, serviços, validações)
│   │   │   │   ├── medico/
│   │   │   │   ├── paciente/
│   │   │   │   ├── consulta/
│   │   │   │   └── usuario/
│   │   │   ├── infra/                    # Infraestrutura (segurança, exceções, configurações)
│   │   │   │   ├── exception/            # Tratamento de erros
│   │   │   │   ├── security/             # JWT, autenticação e autorização
│   │   │   │   └── springdoc/            # Configuração de documentação Swagger
│   │   │   └── ClinicaApplication.java
│   │   └── resources/
│   │       ├── application.properties          # Configurações principais
│   │       ├── application-prod.properties     # Configurações para produção
│   │       ├── application-test.properties     # Configurações para testes
│   │       ├── ValidationMessages.properties   # Mensagens de validação
│   │       └── db/migration/                   # Migrations Flyway
│   └── test/                             # Testes unitários
├── docker-compose.yaml                   # Orquestração para desenvolvimento local (build a partir do código-fonte)
├── docker-compose.prod.yaml              # Orquestração para execução via imagens do Docker Hub (sem clone)
├── Dockerfile                            # Definição da imagem da aplicação
├── pom.xml                               # Configuração do Maven
└── README.md                             # Este arquivo
```

## 📝 Endpoints da API

A API está disponível no prefixo `/api` e oferece os seguintes recursos:

### Teste
- `GET /api/teste` - Verificar se a API está respondendo (sem autenticação)
  - Retorna: Status `200 OK` com body `OK`

### Autenticação
- `POST /api/auth/register` - Registrar novo usuário
  - Requer: `email` e `senha`
  - Retorna: Confirmação de registro
- `POST /api/auth/login` - Realizar login e obter token JWT
  - Requer: `email` e `senha`
  - Retorna: `token` JWT para autenticação em requisições subsequentes

### Médicos
- `POST /api/medicos` - Cadastrar novo médico (requer autenticação)
- `GET /api/medicos` - Listar médicos com paginação (requer autenticação)
  - Parâmetros: `page`, `size`, `sort`
  - Padrão: 10 itens por página, ordenado por nome
- `GET /api/medicos/{id}` - Obter detalhes de um médico (requer autenticação)
- `PUT /api/medicos/{id}` - Atualizar dados do médico (requer autenticação)
- `DELETE /api/medicos/{id}` - Deletar médico (requer autenticação, exclusão lógica)

### Pacientes
- `POST /api/pacientes` - Cadastrar novo paciente (requer autenticação)
- `GET /api/pacientes` - Listar pacientes com paginação (requer autenticação)
  - Parâmetros: `page`, `size`, `sort`
  - Padrão: 10 itens por página, ordenado por nome
- `GET /api/pacientes/{id}` - Obter detalhes de um paciente (requer autenticação)
- `PUT /api/pacientes/{id}` - Atualizar dados do paciente (requer autenticação)
- `DELETE /api/pacientes/{id}` - Deletar paciente (requer autenticação, exclusão lógica)

### Consultas
- `POST /api/consultas` - Agendar nova consulta (requer autenticação)
  - Requer: `medicoId`, `pacienteId`, `data`
- `GET /api/consultas/{id}` - Obter detalhes de uma consulta (requer autenticação)
- `GET /api/consultas` - Listar consultas com paginação (requer autenticação)
  - Parâmetros: `page`, `size`, `sort`
  - Padrão: 10 itens por página, ordenado por data (mais recentes primeiro)
- `DELETE /api/consultas/{id}` - Cancelar consulta (requer autenticação)
  - Requer: `motivo` (PACIENTE_DESISTIU, MEDICO_CANCELOU, OUTROS)

## 💡 Recursos Principais

- **Autenticação JWT**: Sistema seguro de autenticação com tokens JWT
- **Paginação**: Todos os endpoints de listagem suportam paginação configurável
- **Exclusão Lógica**: Médicos e pacientes não são removidos do banco, apenas marcados como inativos
- **DTOs**: Separação entre requisições e respostas da API
- **Validação Automática**: Validações em tempo de requisição com mensagens de erro detalhadas
- **Tratamento de Erros**: Respostas padronizadas para diferentes tipos de erro (4xx, 5xx)
- **Spring Security**: Integração completa com Spring Security para autorização
- **Flyway Migrations**: Controle de versão do banco de dados
- **Lombok**: Redução de código boilerplate nas entidades e DTOs
- **Swagger UI / OpenAPI**: Documentação interativa da API
- **Agendamento de Consultas**: Sistema completo de agendamento com validações
- **Cancelamento de Consultas**: Cancelamento com motivos rastreáveis (paciente desistiu, médico cancelou, outros)

## 🔐 Segurança

- Todos os endpoints (exceto `/api/teste`, `/api/auth/register` e `/api/auth/login`) requerem autenticação via token JWT
- O token JWT deve ser enviado no header `Authorization: Bearer {token}`
- A senha do usuário é criptografada no banco de dados
- A aplicação utiliza Spring Security para controlar o acesso aos recursos

## 🗄️ Estrutura do Banco de Dados

### Tabela: usuarios
- `id` (BIGINT) - Identificador único
- `email` (VARCHAR) - Email único do usuário
- `senha` (VARCHAR) - Senha criptografada

### Tabela: medicos
- `id` (BIGINT) - Identificador único
- `nome` (VARCHAR) - Nome do médico
- `email` (VARCHAR) - Email único do médico
- `crm` (VARCHAR) - CRM único do médico
- `telefone` (VARCHAR) - Telefone para contato
- `especialidade` (VARCHAR) - Especialidade médica
- `endereco` (VARCHAR) - Endereço do consultório
- `ativo` (BOOLEAN) - Flag de exclusão lógica

### Tabela: pacientes
- `id` (BIGINT) - Identificador único
- `nome` (VARCHAR) - Nome do paciente
- `email` (VARCHAR) - Email único do paciente
- `cpf` (VARCHAR) - CPF único do paciente
- `telefone` (VARCHAR) - Telefone para contato
- `endereco` (VARCHAR) - Endereço residencial
- `ativo` (BOOLEAN) - Flag de exclusão lógica

### Tabela: consultas
- `id` (BIGINT) - Identificador único
- `medico_id` (BIGINT) - Referência ao médico (FK)
- `paciente_id` (BIGINT) - Referência ao paciente (FK)
- `data` (DATETIME) - Data e hora da consulta
- `motivo_cancelamento` (VARCHAR) - Motivo do cancelamento (PACIENTE_DESISTIU, MEDICO_CANCELOU, OUTROS)
