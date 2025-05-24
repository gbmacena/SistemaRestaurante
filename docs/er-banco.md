### Cliente

- `id_cliente` (INTEGER, PK)
- `nome` (TEXT, NOT NULL)
- `telefone` (TEXT)
- `email` (TEXT)

| id_cliente | nome        | telefone    | email                   |
| ---------- | ----------- | ----------- | ----------------------- |
| 1          | Ana Beatriz | 83999999999 | ana.b@gmail.com         |
| 2          | João Pedro  | 83988888888 | joao.pedro@hotmail.com  |
| 3          | Maria Clara | 83977777777 | maria.clara@exemplo.com |

---

### Prato

- `id_prato` (INTEGER, PK)
- `nome` (TEXT, NOT NULL)
- `descricao` (TEXT)
- `preco` (REAL, NOT NULL)
- `categoria` (TEXT, NOT NULL)
  - Enum: `Entrada`, `Principal`, `Sobremesa`, `Bebida`

| id_prato | nome           | descricao                   | preco | categoria |
| -------- | -------------- | --------------------------- | ----- | --------- |
| 1        | Salada Caesar  | Alface, frango, croutons    | 18.50 | Entrada   |
| 2        | Lasanha        | Lasanha de carne com queijo | 32.00 | Principal |
| 3        | Pudim          | Pudim de leite condensado   | 12.00 | Sobremesa |
| 4        | Coca-Cola Lata | Refrigerante 350ml          | 6.00  | Bebida    |
| 5        | Água Mineral   | Água sem gás 500ml          | 4.00  | Bebida    |

---

### Pedido

- `id_pedido` (INTEGER, PK)
- `id_cliente` (INTEGER, FK → Cliente)
- `data_hora` (TEXT)
- `status` (TEXT, NOT NULL)
  - Enum`PENDENTE`, `EM_PREPARO`, `ENTREGUE`, `CANCELADO`

| id_pedido | id_cliente | data_hora           | status     |
| --------- | ---------- | ------------------- | ---------- |
| 1         | 1          | 2025-05-23 12:45:00 | ENTREGUE   |
| 2         | 2          | 2025-05-23 13:00:00 | EM_PREPARO |
| 3         | 3          | 2025-05-23 13:20:00 | PENDENTE   |

---

### PedidoPrato (Relacionamento N:N entre Pedido e Prato)

- `id_pedido` (INTEGER, FK → Pedido)
- `id_prato` (INTEGER, FK → Prato)
- `quantidade` (INTEGER, NOT NULL)

| id_pedido | id_prato | quantidade |
| --------- | -------- | ---------- |
| 1         | 2        | 1          |
| 1         | 4        | 2          |
| 2         | 1        | 1          |
| 2         | 3        | 1          |
| 3         | 2        | 1          |
| 3         | 5        | 1          |

---

## Relacionamentos

- Um **cliente** pode fazer vários **pedidos** (1:N)
- Um **pedido** pode conter vários **pratos**, e um **prato** pode estar em vários **pedidos** (N:N com `PedidoPrato`)
- Cada **pedido** pertence a um único **cliente**

---
