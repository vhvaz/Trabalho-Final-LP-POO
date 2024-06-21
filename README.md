<p align="center">
  <img src="https://github.com/vhvaz/Trabalho-Final-LP-POO/assets/150188099/61c88d1d-2c41-480c-badc-d235d404820c" alt="logo_1-removebg-preview">
</p>

# VG Store - LojaOnline

## Sumário
- [Descrição do Projeto](#descrição-do-projeto)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Funcionalidades](#funcionalidades)
- [Configuração do Ambiente](#configuração-do-ambiente)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Como Executar o Projeto](#como-executar-o-projeto)
- [Descrição das Telas](#descrição-das-telas)
- [Descrição das Classes](#descrição-das-classes)
- [Scripts SQL](#scripts-sql)
- [Autores](#autores)
- [Créditos](#créditos)
- [Licença](#licença)

## Descrição do Projeto
O projeto **VG Store** foi desenvolvido como parte da disciplina de Linguagens de Programação e POO do curso de Sistemas de Informação na Newton Paiva. A ideia principal do projeto era criar uma loja online com sistema e interface gráfica, que facilitasse a venda de camisas de time. 

Somos dois amigos, **Vitor** e **Gustavo**, apaixonados por tecnologia e futebol, decidimos criar o **VG Store**, uma loja online de vendas de camisas de times de futebol. Este projeto tem como objetivo proporcionar uma experiência de compra agradável e intuitiva para os usuários, com funcionalidades completas de login, carrinho de compras e pagamento.

## Tecnologias Utilizadas
- **Java 17**
- **Swing** (para a interface gráfica)
- **MySQL** (para o banco de dados)
- **JDBC** (para a conexão com o banco de dados)

## Funcionalidades
- **Tela de Login**: Permite que os usuários façam login no sistema.
- **Tela Principal**: Vitrine de produtos com exibição de imagens, nomes, preços e estoque.
- **Tela de Carrinho de Compras**: Permite que os usuários visualizem os produtos adicionados ao carrinho, a quantidade total e o valor total.
- **Tela de Pagamento**: Permite que os usuários finalizem a compra escolhendo a forma de pagamento (Cartão de Crédito ou Pix).

## Configuração do Ambiente
1. **Instale o Java 17**: Certifique-se de que o Java 17 está instalado e configurado no seu sistema.
2. **Instale o MySQL**: Certifique-se de que o MySQL está instalado e em execução.
3. **Configuração do Banco de Dados**:
    - Crie um banco de dados chamado `shop`.
    - Execute os comandos SQL fornecidos na seção [Scripts SQL](#scripts-sql) para criar as tabelas necessárias.

## Diagrama de Classes UML
![diagrama projeto](https://github.com/vhvaz/Trabalho-Final-LP-POO/assets/150188099/c1145044-7e20-46cd-905f-7ade25f1d8cf)


```## Estrutura do Projeto
A estrutura do projeto está organizada da seguinte forma:
LojaOnline
│
├── src
│ ├── Dao
│ │ ├── ClienteDAO.java
│ │ ├── ConexaoBD.java
│ │ ├── ProdutoDAO.java
│ │ └── VendaDAO.java
│ │
│ ├── Entidades
│ │ ├── Carrinho.java
│ │ ├── Cliente.java
│ │ ├── Estoque.java
│ │ ├── FormaPagamento.java
│ │ ├── ItemVenda.java
│ │ ├── Pessoa.java
│ │ ├── Produto.java
│ │ ├── Sessao.java
│ │ ├── Venda.java
│ │ └── Vendedor.java
│ │
│ ├── Telas
│ │ ├── TelaCarrinho.java
│ │ ├── TelaLogin.java
│ │ ├── TelaPagamento.java
│ │ └── TelaPrincipal.java
│ │
│ └── Resources
│ ├── Camisa Atletico MG.png
│ ├── Palmeiras.png
│ ├── Vasco.png
│ ├── Gremio.png
│ ├── Real.png
│ ├── Barcelona.png
│ ├── City.png
│ ├── Bayer.png
│ ├── Girona.png
│ └── Inter Miami.png
```


## Como Executar o Projeto
1. **Clone o repositório:**
    ```sh
    git clone https://github.com/vhvaz/LojaOnline.git
    ```
2. **Configure o banco de dados:**
    - Siga as instruções na seção de configuração do banco de dados para criar e configurar o banco de dados `shop`.
3. **Adicione o driver MySQL ao classpath:**
    - Certifique-se de que o driver JDBC do MySQL (`mysql-connector-java`) está no classpath do seu projeto.
4. **Compile e execute o projeto:**
    - Use sua IDE preferida (como IntelliJ IDEA ou Eclipse) para compilar e executar a classe principal `Telas.TelaLogin`.

## Descrição das Telas
### Tela de Login
A Tela de Login permite que os usuários façam login no sistema. Após o login bem-sucedido, os usuários são redirecionados para a Tela Principal.

### Tela Principal
A Tela Principal exibe a vitrine de produtos, mostrando imagens, nomes, preços e a quantidade em estoque de cada produto. Os usuários podem adicionar produtos ao carrinho de compras a partir desta tela.

### Tela de Carrinho de Compras
A Tela de Carrinho de Compras permite que os usuários visualizem os produtos adicionados ao carrinho, a quantidade total de itens e o valor total da compra. Os usuários podem finalizar a compra a partir desta tela, o que os redireciona para a Tela de Pagamento.

### Tela de Pagamento
A Tela de Pagamento permite que os usuários escolham a forma de pagamento (Cartão de Crédito ou Pix) e insiram os dados necessários para finalizar a compra.

## Descrição das Classes
### Pacote `Dao`
- **ClienteDAO.java**: Gerencia as operações de CRUD para a entidade Cliente.
- **ConexaoBD.java**: Gerencia a conexão com o banco de dados.
- **ProdutoDAO.java**: Gerencia as operações de CRUD para a entidade Produto.
- **VendaDAO.java**: Gerencia as operações de CRUD para a entidade Venda.

### Pacote `Entidades`
- **Carrinho.java**: Representa o carrinho de compras do usuário.
- **Cliente.java**: Representa o cliente do sistema.
- **Estoque.java**: Gerencia o estoque de produtos.
- **FormaPagamento.java**: Representa a forma de pagamento escolhida pelo usuário.
- **ItemVenda.java**: Representa um item de venda.
- **Pessoa.java**: Classe base para as entidades Cliente e Vendedor.
- **Produto.java**: Representa um produto na vitrine.
- **Sessao.java**: Gerencia a sessão do usuário logado.
- **Venda.java**: Representa uma venda realizada.
- **Vendedor.java**: Representa o vendedor do sistema.

### Pacote `Telas`
- **TelaCarrinho.java**: Implementa a interface gráfica do carrinho de compras.
- **TelaLogin.java**: Implementa a interface gráfica de login.
- **TelaPagamento.java**: Implementa a interface gráfica de pagamento.
- **TelaPrincipal.java**: Implementa a interface gráfica da vitrine de produtos.

### Pacote `Resources`
Contém as imagens dos produtos, como camisetas dos times de futebol.

## Diagrama ERD
![diagrama banco de dados](https://github.com/vhvaz/Trabalho-Final-LP-POO/assets/150188099/72218e56-bd57-4c48-be58-3bb76c9457ed)


## Scripts SQL
```sql
CREATE DATABASE shop;

USE shop;

CREATE TABLE IF NOT EXISTS produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    estoque INT NOT NULL,
    imagem VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL
);

CREATE TABLE IF NOT EXISTS vendas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    data DATE NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);

CREATE TABLE IF NOT EXISTS itens_venda (
    id INT AUTO_INCREMENT PRIMARY KEY,
    venda_id INT NOT NULL,
    produto_id INT NOT NULL,
    quantidade INT NOT NULL,
    FOREIGN KEY (venda_id) REFERENCES vendas(id),
    FOREIGN KEY (produto_id) REFERENCES produtos(id)
);
```
## Representação das imagens das telas

### Tela de Login
![login](https://github.com/vhvaz/Trabalho-Final-LP-POO/assets/150188099/a3063911-4331-4d4f-96af-a1e2d74e7222)

### Tela Vitrine
![vitrine](https://github.com/vhvaz/Trabalho-Final-LP-POO/assets/150188099/f962df1d-a630-4373-a8c1-bd1fc44588b4)

### Tela Carrinho
![carrinho](https://github.com/vhvaz/Trabalho-Final-LP-POO/assets/150188099/704be33d-c242-4183-9301-a0aa06c2275d)

### Tela Pagamento
![pagamento](https://github.com/vhvaz/Trabalho-Final-LP-POO/assets/150188099/ae9852ab-538e-43a6-83ef-d52e4c392d10)

---

## Demonstração em Vídeo
Assista a uma demonstração do funcionamento da VG Store abaixo:

https://github.com/vhvaz/Trabalho-Final-LP-POO/assets/150188099/57132f2a-d62e-415f-b558-a0a9b90931a2




## Autores
[Vitor](https://github.com/vhvaz) - vhvaz  
[Gustavo](https://github.com/jmv98) - jmv98  
Estudantes de Sistemas de Informação na Newton Paiva.

## Créditos
Agradecemos aos professores da disciplina de Linguagens de Programação e POO por todo o suporte e conhecimento compartilhado. Este projeto não seria possível sem a orientação e apoio de todos os envolvidos.

## Considerações finais
Este projeto foi uma excelente oportunidade para aplicar os conceitos aprendidos nas disciplinas de Linguagens de Programação e Programação Orientada a Objetos e desenvolver uma aplicação prática e funcional. Agradecemos a todos que contribuíram para a realização deste projeto.

---

Espero que este README atenda às suas expectativas e forneça todas as informações necessárias para a correta utilização e entendimento do projeto. Se precisar de mais alguma coisa, estou à disposição.

## Licença
© 2024 VG Store. Todos os direitos reservados.
