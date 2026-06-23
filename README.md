<div align="center">

<img src="https://img.shields.io/badge/SkillUp-Plataforma%20de%20Cursos-7f22fe?style=for-the-badge&logo=graduation-cap&logoColor=white" alt="SkillUp" />

<br/><br/>

![Java](https://img.shields.io/badge/Java_17-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=flat-square&logo=springsecurity&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=flat-square&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=flat-square&logo=jsonwebtokens&logoColor=white)
![Cloudinary](https://img.shields.io/badge/Cloudinary-3448C5?style=flat-square&logo=cloudinary&logoColor=white)
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow?style=flat-square)

<br/>

**API REST de uma plataforma de cursos online desenvolvida para as disciplinas de Análise e Projeto de Sistemas e Programação de Software e Aplicativos I — IFPR Sistemas de Informação.**

</div>

---

## 📖 Sobre o Projeto

O **SkillUp** é uma plataforma de cursos online onde **instrutores** publicam e gerenciam seus cursos e **alunos** se matriculam, acompanham o progresso e avaliam o conteúdo.

Esta API segue arquitetura em camadas com **Spring Boot + JPA/Hibernate**, autenticação stateless via **JWT**, upload de imagens com **Cloudinary** e banco de dados **PostgreSQL** hospedado no **Supabase**.

Vídeo de demonstração: **https://drive.google.com/file/d/1l83lf0VSuJMqYEMVThDhYMX1AdCPQKej/view?usp=sharing**

---

## 🎯 Funcionalidades

### 👨‍🎓 Aluno
- Cadastro e login na plataforma
- Matrícula em cursos publicados
- Acompanhamento do progresso (% concluído)
- Avaliação de cursos cursados

### 👨‍🏫 Instrutor
- Cadastro e login na plataforma *(mínimo 18 anos)*
- Criação, edição e exclusão de cursos
- Upload de imagem de capa via Cloudinary
- Publicação e despublicação de cursos

### 📚 Cursos
- Listagem geral e por instrutor
- Filtro de cursos publicados no catálogo
- Detalhes com categoria, carga horária, preço e avaliação média

---

## 🛠 Tecnologias

| Tecnologia | Uso |
|---|---|
| Java 17 | Linguagem principal |
| Spring Boot | Framework back-end |
| Spring Data JPA + Hibernate | Persistência |
| Spring Security | Autenticação e autorização |
| JWT (Auth0 java-jwt 4.4.0) | Tokens de acesso |
| PostgreSQL via Supabase | Banco de dados |
| Cloudinary | Upload de imagens |
| JUnit | Testes automatizados |
| Maven | Build e dependências |
| Lombok | Redução de boilerplate |

---

## 🏗 Arquitetura

```
src/main/java/br.edu.ifpr.bsi.projetoexemplo/
│
├── adapters/               # Integração de usuários com Spring Security
│   └── UserAdapter
│
├── components/             # Componentes reutilizáveis
│   └── JwtAuthenticationFilter
│
├── configurations/         # Configurações globais
│   ├── SecurityConfig      # Spring Security + CORS + RBAC
│   └── CloudinaryConfig    # Configuração do upload de imagens
│
├── controllers/            # Endpoints REST
├── enums/                  # Role (INSTRUTOR, ALUNO)
├── mappers/                # Conversão Entidade ↔ DTO
├── model/                  # Entidades JPA + DTOs + Records
├── repositories/           # Interfaces Spring Data JPA
└── services/               # Regras de negócio
```

---

## 🔐 Autenticação JWT

A autenticação é **stateless** — o servidor não mantém sessão.

```
1. Cliente envia  →  POST /auth  { username, senha }
2. Spring Security valida credenciais contra o banco (BCrypt)
3. TokenService gera JWT assinado com HMAC256 (validade: 2h)
4. Resposta  →  { usuario: { id, nome, email, role }, token }
5. Próximas requisições enviam  →  Authorization: Bearer <token>
6. JwtAuthenticationFilter valida e injeta o usuário no SecurityContext
```

### Roles e Controle de Acesso (RBAC)

| Role | Permissões |
|---|---|
| `ALUNO` | Matricular-se em cursos, avaliar cursos, ver catálogo |
| `INSTRUTOR` | Criar, editar, excluir e publicar seus próprios cursos |

---

## 📡 Endpoints da API

### 🔐 Autenticação

| Método | Rota | Descrição | Auth |
|---|---|---|---|
| POST | `/auth` | Login | Público |

**Request:**
```json
{ "username": "usuario@email.com", "senha": "123456" }
```

**Response:**
```json
{
  "usuario": { "id": 1, "nome": "João", "email": "joao@email.com", "role": "ALUNO" },
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

### 👨‍🎓 Alunos

| Método | Rota | Descrição | Auth |
|---|---|---|---|
| POST | `/alunos` | Cadastrar aluno | Público |
| GET | `/alunos` | Listar alunos | Autenticado |
| GET | `/alunos/{codigo}` | Buscar aluno | Autenticado |
| PUT | `/alunos/{codigo}` | Atualizar aluno | Autenticado |
| DELETE | `/alunos/{codigo}` | Excluir aluno | Autenticado |

---

### 👨‍🏫 Instrutores

| Método | Rota | Descrição | Auth |
|---|---|---|---|
| POST | `/instrutores` | Cadastrar instrutor | Público |
| GET | `/instrutores` | Listar instrutores | Autenticado |
| GET | `/instrutores/{codigo}` | Buscar instrutor | Autenticado |
| PUT | `/instrutores/{codigo}` | Atualizar instrutor | Autenticado |
| DELETE | `/instrutores/{codigo}` | Excluir instrutor | Autenticado |

---

### 📚 Cursos

| Método | Rota | Descrição | Auth |
|---|---|---|---|
| GET | `/cursos` | Listar todos os cursos | Autenticado |
| GET | `/cursos/{codigo}` | Buscar curso | Autenticado |
| GET | `/cursos/instrutor/{id}` | Listar cursos de um instrutor | Autenticado |
| POST | `/cursos` | Criar curso | `INSTRUTOR` |
| PUT | `/cursos/{codigo}` | Atualizar curso | `INSTRUTOR` |
| PUT | `/cursos/{codigo}/imagem` | Upload de imagem de capa | `INSTRUTOR` |
| DELETE | `/cursos/{codigo}` | Excluir curso | `INSTRUTOR` |

> Upload de imagem: `multipart/form-data`, campo `imagem`.

---

### 📝 Matrículas

| Método | Rota | Descrição | Auth |
|---|---|---|---|
| GET | `/matriculas` | Listar matrículas | Autenticado |
| GET | `/matriculas/{codigo}` | Buscar matrícula | Autenticado |
| GET | `/matriculas/aluno/{alunoId}` | Listar matrículas de um aluno | Autenticado |
| POST | `/matriculas` | Matricular em curso | `ALUNO` |
| PUT | `/matriculas/{codigo}` | Atualizar progresso | `ALUNO` |
| DELETE | `/matriculas/{codigo}` | Cancelar matrícula | Autenticado |

---

### ⭐ Avaliações

| Método | Rota | Descrição | Auth |
|---|---|---|---|
| GET | `/avaliacoes` | Listar avaliações | Autenticado |
| GET | `/avaliacoes/{codigo}` | Buscar avaliação | Autenticado |
| POST | `/avaliacoes` | Criar avaliação | `ALUNO` |
| PUT | `/avaliacoes/{codigo}` | Atualizar avaliação | `ALUNO` |
| DELETE | `/avaliacoes/{codigo}` | Excluir avaliação | Autenticado |

---

## ▶️ Como Executar

### Pré-requisitos

- Java 17+
- Maven 3.8+
- Conta no [Supabase](https://supabase.com) (PostgreSQL)
- Conta no [Cloudinary](https://cloudinary.com)

### 1. Clonar o repositório

```bash
git clone https://github.com/GabrielSantinDev/ProjetoIntegrador.git
cd ProjetoIntegrador
```

### 2. Configurar `application.properties`

```properties
spring.application.name=SkillUp

# Banco de dados (Supabase)
spring.datasource.url=jdbc:postgresql://<host>:5432/<banco>
spring.datasource.username=<usuario>
spring.datasource.password=<senha>

# JWT — use variável de ambiente em produção, nunca versione a chave real
api.security.token.secret=${JWT_SECRET:chave-local-para-dev}

# Cloudinary
cloudinary.cloud-name=<cloud-name>
cloudinary.api-key=<api-key>
cloudinary.api-secret=<api-secret>
```

### 3. Executar

```bash
mvn clean install
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

---

## 🧪 Testes

```bash
mvn test
```

```
src/test/java/.../
├── controllers/
├── model/
├── repositories/
└── services/
```

---

## 📌 Conceitos Aplicados

- Arquitetura em Camadas (Controller → Service → Repository)
- DTO Pattern (Request/Response separados)
- Repository Pattern com Spring Data JPA
- Autenticação Stateless com JWT
- RBAC (Role-Based Access Control)
- Upload de arquivos com Cloudinary
- Persistência com JPA/Hibernate + PostgreSQL

---

## 🔗 Projetos Relacionados

| Repositório | Descrição | Tecnologia |
|---|---|---|
| [ProjetoIntegrador](https://github.com/GabrielSantinDev/ProjetoIntegrador) | **Este repositório** — API REST | Java + Spring Boot |
| [projeto-integrador-react](https://github.com/GabrielSantinDev/projeto-integrador-react) | Front-end que consome esta API | React + TailwindCSS |
| [ProjetoIntegradorWeb](https://github.com/GabrielSantinDev/ProjetoIntegradorWeb) | Versão web alternativa | PHP + Bootstrap |

---

## 👥 Equipe

Projeto desenvolvido para as disciplinas de **Análise e Projeto de Sistemas** e **Programação de Software e Aplicativos I** do curso de **Sistemas de Informação — IFPR**.
