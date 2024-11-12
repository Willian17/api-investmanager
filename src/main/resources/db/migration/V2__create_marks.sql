CREATE TYPE category_enum AS ENUM (
    'ACOES_NACIONAIS',
    'ACOES_INTERNACIONAIS',
    'FUNDOS_IMOBILIARIOS',
    'REITS',
    'RENDA_FIXA',
    'CRIPTOMOEDA'
);


CREATE TABLE marks (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    category category_enum NOT NULL,
    percentage INTEGER CHECK (percentage BETWEEN 0 AND 100) NOT NULL,
    id_user UUID NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (id_user) REFERENCES users(id)
);