-- Criar tabela cliente
CREATE TABLE cliente (
                         id BIGSERIAL PRIMARY KEY,
                         nome VARCHAR(100) NOT NULL,
                         email VARCHAR(100) NOT NULL UNIQUE,
                         cpf VARCHAR(11) NOT NULL UNIQUE,
                         telefone VARCHAR(15),
                         data_cadastro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Criar tabela endereco
CREATE TABLE endereco (
                          id BIGSERIAL PRIMARY KEY,
                          rua VARCHAR(255) NOT NULL,
                          numero VARCHAR(10),
                          bairro VARCHAR(100),
                          cidade VARCHAR(100) NOT NULL,
                          estado VARCHAR(50) NOT NULL,
                          cep VARCHAR(20) NOT NULL,
                          cliente_id BIGINT NOT NULL UNIQUE,
                          CONSTRAINT fk_endereco_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

-- Criar tabela produto
CREATE TABLE produto (
                         id BIGSERIAL PRIMARY KEY,
                         nome VARCHAR(100) NOT NULL,
                         descricao TEXT,
                         preco NUMERIC(10, 2) NOT NULL,
                         estoque INTEGER NOT NULL
);

-- Criar tabela categoria
CREATE TABLE categoria (
                           id BIGSERIAL PRIMARY KEY,
                           nome VARCHAR(100) NOT NULL,
                           descricao TEXT
);

-- Criar tabela produto_categoria (tabela de relacionamento many-to-many)
CREATE TABLE produto_categoria (
                                   produto_id BIGINT NOT NULL,
                                   categoria_id BIGINT NOT NULL,
                                   CONSTRAINT fk_produto_categoria_produto FOREIGN KEY (produto_id) REFERENCES produto(id),
                                   CONSTRAINT fk_produto_categoria_categoria FOREIGN KEY (categoria_id) REFERENCES categoria(id),
                                   PRIMARY KEY (produto_id, categoria_id)
);

-- Criar tabela pedido
CREATE TABLE pedido (
                        id BIGSERIAL PRIMARY KEY,
                        data_pedido TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        valor_total NUMERIC(10, 2) NOT NULL,
                        status_pedido VARCHAR(20) NOT NULL,
                        cliente_id BIGINT NOT NULL,
                        CONSTRAINT fk_pedido_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

-- Criar tabela item_pedido
CREATE TABLE item_pedido (
                             id BIGSERIAL PRIMARY KEY,
                             quantidade INTEGER NOT NULL,
                             valor_unitario NUMERIC(10, 2) NOT NULL,
                             pedido_id BIGINT NOT NULL,
                             produto_id BIGINT NOT NULL,
                             CONSTRAINT fk_item_pedido_pedido FOREIGN KEY (pedido_id) REFERENCES pedido(id),
                             CONSTRAINT fk_item_pedido_produto FOREIGN KEY (produto_id) REFERENCES produto(id)
);
