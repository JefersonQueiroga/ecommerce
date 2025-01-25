-- Criação da tabela 'roles'
CREATE TABLE roles (
                       id BIGSERIAL PRIMARY KEY, -- BIGSERIAL para auto-incremento
                       name VARCHAR(255) NOT NULL UNIQUE -- Nome único para o papel
);

-- Criação da tabela 'users'
CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY, -- BIGSERIAL para auto-incremento
                       username VARCHAR(255) NOT NULL UNIQUE, -- Nome de usuário único
                       password VARCHAR(255) NOT NULL -- Senha do usuário
);

-- Criação da tabela intermediária 'user_roles' para a relação muitos-para-muitos
CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL, -- Referência para 'users'
                            role_id BIGINT NOT NULL, -- Referência para 'roles'
                            PRIMARY KEY (user_id, role_id), -- Chave composta
                            CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
                            CONSTRAINT fk_user_roles_role FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE
);
