## Sistema de Gerenciamento de Ordens de Produção para uma Fábrica

- Funcionalidades
  - Registrar uma nova ordem de produção, especificando o produto a ser fabricado, a quantidade desejada e a data de entrega.
  - Listar todas as ordens de produção existentes, mostrando os detalhes de cada ordem, como o produto, a quantidade e a data de entrega.
  - Verificar se o produto pode ser produzido com base nos materiais disponíveis. Caso contrário, o sistema deve avisar que a produção não é possível devido à falta de materiais.
  - Atualizar o status de uma ordem de produção, indicando se foi concluída ou não.
    Visualizar relatórios de produção que mostrem as ordens em andamento e as concluídas.
- Linguagem de Programação: Java
- Banco de Dados: MySQL

## Configuração da Aplicação

1. Conectar com o banco de dados
   - Criar database no MySQL Workbench
   - Configurar a URL, USUARIO e SENHA do arquivo SistemaProducao dependendo das configurações do seu servidor no MySQL Workbench
2. Após criar o servidor, criar as tabelas para o database no mySQL

   - Criar database: sistema_de_producao
   - Criar tabela ordens_producao no database criado

     CREATE TABLE ordens_producao (
     id INT AUTO_INCREMENT PRIMARY KEY,
     produto VARCHAR(255),
     quantidade INT,
     data_entrega DATE,
     status ENUM('pendente', 'concluida')
     );

   - Criar tabela materiais no database criado

     CREATE TABLE materiais (
     id_material INT AUTO_INCREMENT PRIMARY KEY,
     produto VARCHAR(255) NOT NULL,
     quantidade INT NOT NULL
     );

## Execução da Aplicação

- Após ter feito todas as configurações, executar o arquivo SistemaProducaoApp da pasta src
- Analisar os dados armazenados na tabela ordens_producao
