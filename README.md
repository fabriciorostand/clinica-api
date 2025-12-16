## 📋 Descrição

API REST para gestão de clínica, permitindo controle de médicos, pacientes e suas informações. Desenvolvido com Spring Boot, Java 21 e MySQL, oferecendo endpoints para gerenciamento completo de médicos e pacientes com suporte a paginação, exclusão lógica e autenticação segura via JWT.

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

### 1. Configurar Variáveis de Ambiente

A aplicação utiliza variáveis de ambiente para configurar credenciais sensíveis. Configure as seguintes variáveis:

**Windows (PowerShell):**
```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/clinica"
$env:DB_USERNAME="root"
$env:DB_PASSWORD="sua_senha_aqui"
$env:JWT_SECRET="sua_chave_secreta_jwt_aqui"
```

**Linux/Mac (Bash):**
```bash
export DB_URL="jdbc:mysql://localhost:3306/clinica"
export DB_USERNAME="root"
export DB_PASSWORD="sua_senha_aqui"
export JWT_SECRET="sua_chave_secreta_jwt_aqui"
```

Ou edite o arquivo `src/main/resources/application.properties` diretamente:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/clinica
spring.datasource.username=root
spring.datasource.password=SUA_SENHA
api.security.token.secret=SUA_CHAVE_JWT
```

**Nota:** A chave JWT (`JWT_SECRET`) deve ser uma string segura e suficientemente longa para gerar tokens seguros.

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

### Autenticação
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

## 📖 Exemplos de Uso

### 1. Realizar Login

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "medico@example.com",
    "senha": "senha123"
  }'
```

**Resposta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### 2. Cadastrar um Médico

```bash
curl -X POST http://localhost:8080/api/medicos \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer {token}" \
  -d '{
    "nome": "Dr. João Silva",
    "email": "joao@example.com",
    "crm": "123456",
    "telefone": "11999999999",
    "especialidade": "Cardiologia",
    "endereco": "Rua das Flores, 123"
  }'
```

### 3. Listar Médicos com Paginação

```bash
curl -X GET "http://localhost:8080/api/medicos?page=0&size=10&sort=nome,asc" \
  -H "Authorization: Bearer {token}"
```

### 4. Obter Detalhes de um Médico

```bash
curl -X GET http://localhost:8080/api/medicos/1 \
  -H "Authorization: Bearer {token}"
```

### 5. Atualizar Dados de um Médico

```bash
curl -X PUT http://localhost:8080/api/medicos/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer {token}" \
  -d '{
    "nome": "Dr. João Silva Atualizado",
    "telefone": "11988888888"
  }'
```

### 6. Deletar um Médico

```bash
curl -X DELETE http://localhost:8080/api/medicos/1 \
  -H "Authorization: Bearer {token}"
```

## 🔐 Segurança

- Todos os endpoints (exceto `/api/auth/login`) requerem autenticação via token JWT
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