
# API de Transações Financeiras + Estatísticas de Transações

Esse projeto foi desenvolvido para um desafio técnico visando avaliar habilidades de desenvolvedor java backend e tem como escopo o cadastro de transações financeiras para posterior apresentação de estatísticas resumo.

## Referência

 - [Javanauta - Desafios Técnicos para Desenvolvedores Backend](https://www.youtube.com/watch?v=9xrx1pxZEGU)

## Rodando localmente

Clone o projeto

```bash
  git clone https://github.com/saynarah/finance-transaction-api
```

Entre no diretório do projeto

```bash
  cd transacao-api
```

```bash
  docker build -t <nome_imagem>
```

```bash
  docker run -d -p 8080:8080 --name <nome_container> <nome_imagem>
```


## Documentação da API

#### Adiciona uma transação

```http
  POST /transacao
```

| Body   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `valor` | `Double` | **Obrigatório**. O valor da transação. |
| `dataHora` | `OffsetDateTime` | **Obrigatório**. Data e hora da transação no formato yyyy-MM-dd'T'HH:mm:ssXXX. |

#### Deleta todas as transações

```http
  DELETE /transacao
```

#### Buscar estatísticas de transações realizadas nos últimos 60 segundos

```http
  GET /estatistica
```

#### Buscar estatísticas de transações realizadas nos período especificado.

```http
  GET /estatistica/${intervaloBusca}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `intervaloBusca` | `Integer` | O intervalo de busca para buscar as estatítiscas no formato de segundos. |

## Rodando os testes

Para rodar os testes, rode o seguinte comando

```bash
  gradlew test 
```


## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://github.com/saynarah)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/saynarah-nabuco)


