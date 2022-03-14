<p align="center">
<img src="https://logosmarcas.net/wp-content/uploads/2020/11/Star-Wars-Logo.png" width="200"/>
</p>

<h1 align="center">Star Wars Resistence Social Network</h1>

O império continua sua luta incessante de dominar a galáxia, tentando ao máximo expandir seu território e eliminar os
rebeldes. Você, como um soldado da resistência, foi designado para desenvolver um sistema para compartilhar recursos
entre os rebeldes. Você irá desenvolver uma API REST, ao qual irá armazenar informação sobre os rebeldes, bem como os
recursos que eles possuem.

## Tecnologias

- Java
- Spring Boot
- Spring Data
- Hibernate
- H2 Database
- Maven

## Documentação

[Documentation - Swagger](https://shrouded-dusk-72580.herokuapp.com/docs)

## Funcionalidades

- Buscar todos os rebeldes de uma localização
- Buscar todos os rebeldes
- Adicionar rebeldes
- Atualizar localização do rebelde
- Reportar rebelde como traidor
- Rebeldes não podem Adicionar/Remover itens do seu inventário
- Negociar itens

## Referência da API

#### Adicionar localização

Uma localização deve conter os campos latitude, longitude e nome. Deve ser criada antes de um rebelde.

```http
	POST /localization
```

```json
{
  "latitude": -24.8919121,
  "longitude": -43.0998678,
  "basename": "Brasil"
}
```

#### Adicionar rebeldes

Um rebelde deve ter um nome, idade, gênero, localização (nome da localização). Um rebelde também possui um inventário
que deverá ser passado na requisição com os recursos em sua posse.

```http
	POST /rebel
```

```json
{
  "name": "AAAAA",
  "age": 52,
  "gender": "FEMALE",
  "basename": "Via",
  "inventory": {
    "items": [
      {
        "name": "weapons",
        "quantity": 3
      },
      {
        "name": "ammunition",
        "quantity": 80
      },
      {
        "name": "water",
        "quantity": 3
      },
      {
        "name": "food",
        "quantity": 4
      }
    ]
  }
}
```

#### Atualizar localização do rebelde

Um rebelde deve possuir a capacidade de reportar sua última localização, armazenando a nova latitude/longitude/nome (não
é necessário rastrear as localizações, apenas sobrescrever a última é o suficiente).

```http
	PUT /rebel/${id}
```

```json
{
  "latitude": -23.8919121,
  "longitude": -47.0998678,
  "basename": "Petrópolis"
}
```

#### Reportar o rebelde como um traidor

Eventualmente algum rebelde irá trair a resistência e se aliar ao império. Quando isso acontecer, nós precisamos
informar que o rebelde é um traidor. Um traidor não pode negociar os recursos com os demais rebeldes, não pode manipular
seu inventário, nem ser exibido em relatórios. Um rebelde é marcado como traidor quando, ao menos, três outros rebeldes
reportarem a traição. Uma vez marcado como traidor, os itens do inventário se tornam inacessíveis (eles não podem ser
negociados com os demais).

```http
	POST /report?rebelReportedId=${id}&rebelAccuserId=${id}
```

| Parâmetro | Tipo     | Descrição                       |
| :-------- | :------- | :-------------------------------- |
| `rebelReportedId`      | `string` | **Required**. Id do rebelde a ser reportado |
| `rebelAccuserId` | `string` | **Required** Id do acusador |

#### Negociar Itens

Os rebeldes poderão negociar itens entre eles. Para isso, eles devem respeitar a tabela de preços abaixo, onde o valor
do item é descrito em termo de pontos. Ambos os lados deverão oferecer a mesma quantidade de pontos. Por exemplo, 1 arma
e 1 água (1 x 4 + 1 x 2) valem 6 comidas (6 x 1) ou 2 munições (2 x 3). A negociação em si não será armazenada, mas os
itens deverão ser transferidos de um rebelde a outro.

```http
	POST /trading
```

| **ITEM** | **PONTOS** |
|:-----| :------|
| 1 Arma | 4 |
| 1 Munição | 3 |
| 1 Água | 2 |
| 1 Comida | 1 |

```json
[
  {
    "rebelId": 2,
    "items": [
      {
        "name": "food",
        "quantity": 8
      },
      {
        "name": "water",
        "quantity": 1
      }
    ]
  },
  {
    "rebelId": 1,
    "items": [
      {
        "name": "ammunition",
        "quantity": 1
      }
    ]
  }
]
```

## Rodar Localmente

Faça o clone do repositório

```bash
  git clone https://github.com/Alan-Patrik/star-wars-social-network.git
```

Vá para a pasta do projeto

```bash
  cd star-wars-social-network
```

Limpe o projeto

```bash
  mvn clean package
```

Rode o servidor

```bash
  java -jar /target/resistence-0.0.1-SNAPSHOT.jar
```

## Autor

- [Github](https://github.com/Alan-Patrik)
- [Linkedin](https://www.linkedin.com/in/alan-patrik-fragozo-dos-santos-461b8a1b5/)