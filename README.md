# Projeto Integrador - Programação de Software e Aplicativos I

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-Framework-brightgreen)
![JPA](https://img.shields.io/badge/JPA-Hibernate-orange)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Supabase-blue)
![JUnit](https://img.shields.io/badge/JUnit-Testes-success)
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)

Projeto desenvolvido para as disciplinas de **Análise e Projeto de Sistemas** e **Programação de Software e Aplicativos I**, com o objetivo de aplicar conceitos de **modelagem de sistemas**, **desenvolvimento back-end com Spring Boot**, **persistência de dados com JPA** e **testes automatizados**.

O sistema foi desenvolvido utilizando **Java**, **Spring Boot**, **JPA/Hibernate**, **Supabase (PostgreSQL)** e **JUnit**, seguindo a estrutura de camadas trabalhada em aula.

---

## 📌 Objetivo do Projeto

Este projeto tem como finalidade desenvolver a base estrutural de uma aplicação back-end, aplicando conceitos fundamentais como:

- modelagem de entidades;
- arquitetura em camadas;
- persistência de dados com banco relacional;
- operações CRUD;
- criação de services e repositories;
- testes automatizados;
- desenvolvimento de endpoints para API REST.

---

## 🛠️ Tecnologias Utilizadas

- **Java**
- **IntelliJ IDEA**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **Lombok**
- **JUnit**
- **Supabase/PostgreSQL**
- **Postman**

---

## 📂 Estrutura do Projeto

O projeto foi organizado em camadas, separando responsabilidades entre controle, regras de negócio, persistência e testes.

```bash
ProjetoIntegrador/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br.edu.ifpr.bsi.projetoexemplo/
│   │   │       ├── controllers/     # Controllers da aplicação (endpoints da API)
│   │   │       ├── model/           # Entidades / classes que representam o domínio do sistema
│   │   │       ├── repositories/    # Interfaces responsáveis pelo acesso e persistência no banco
│   │   │       ├── services/        # Camada de regras de negócio e lógica da aplicação
│   │   │       ├── ProjetoIntegradorApplication.java   # Classe principal para inicialização do Spring Boot
│   │   │       └── ServletInitializer.java
│   │   │
│   │   └── resources/
│   │       ├── static/
│   │       ├── templates/
│   │       └── application.properties # Arquivo de configuração do projeto (porta, banco, JPA, etc.)
│   │
│   └── test/
│       └── java/
│           └── br.edu.ifpr.bsi.projetoexemplo/
│               ├── controllers/     # Testes dos controllers / endpoints
│               ├── model/           # Testes relacionados às entidades e validações
│               ├── repositories/    # Testes de persistência e operações CRUD no banco
│               ├── services/        # Testes da lógica de negócio
│               └── ProjetoIntegradorApplicationTests.java # Classe principal de testes da aplicação
│
├── pom.xml                           # Arquivo de dependências e build do Maven
└── README.md                         # Documentação principal do projeto
