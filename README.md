# Transferência API - Documentação


Esta API RESTful gerencia transferências financeiras, calculando taxas com base na data de agendamento e no valor da transferência.

## Funcionalidades Principais
#### Criar Transferência: 
    Permite criar uma nova transferência, definindo conta de origem, conta de destino, valor da transferência, data de agendamento, CPF do beneficiário. A API calcula automaticamente a taxa a ser aplicada.
#### Consultar Transferência por ID:
    Recupera os detalhes de uma transferência específica usando seu ID único.
#### Listar Todas as Transferências: 
    Retorna uma lista de todas as transferências registradas.
#### Deletar Transferência por ID: 
Exclui uma transferência do sistema.

## Arquitetura
 A API é construída usando Spring Boot, com os seguintes componentes principais:

##### Controlador REST: 
    TransferControllerV1 - Gerencia as requisições HTTP e respostas.
##### Serviço de Transferência: 
    TransferService - Implementa a lógica de negócios para gerenciar transferências.
##### Serviço de Cálculo de Taxas: 
    TaxCalculatorService - Responsável por calcular as taxas de transferência com base em regras predefinidas.
##### Repositório de Transferências: 
    TransferRepository - Interface para persistência de dados de transferência (provavelmente usando Spring Data JPA).
##### DTOs: 
    TransferDto - Objetos de transferência de dados para comunicação entre camadas.
## Fluxos de Trabalho
### Criar Transferência:
#### ° O cliente envia uma requisição POST para /api/v1/transfer com os dados da transferência no corpo da requisição (formato JSON).
#### °     O TransferControllerV1 recebe a requisição e chama o TransferService para criar a transferência.
#### °     O TransferService chama o TaxCalculatorService para calcular a taxa.
#### °     O TransferService salva a transferência no banco de dados através do TransferRepository.
#### °     A API retorna o código de status 201 (Created) com a representação da transferência criada, incluindo links HATEOAS.

## Consultar Transferência por ID:
#### ° O cliente envia uma requisição GET para /api/v1/transfer/{id}, onde {id} é o ID da transferência.
#### °  O TransferControllerV1 chama o TransferService para buscar a transferência pelo ID.
#### °  O TransferService recupera a transferência do banco de dados usando o TransferRepository.
#### °  A API retorna o código de status 200 (OK) com os detalhes da transferência ou 404 (Not Found) se a transferência não for encontrada.
#### °  Listar Todas as Transferências:
#### °  O cliente envia uma requisição GET para /api/v1/transfer.
#### °  O TransferControllerV1 chama o TransferService para buscar todas as transferências.
#### °  O TransferService recupera todas as transferências do banco de dados usando o TransferRepository.
#### °  A API retorna o código de status 200 (OK) com a lista de transferências, incluindo links HATEOAS.

## Deletar Transferência por ID:
#### ° O cliente envia uma requisição DELETE para /api/v1/transfer/{id}, onde {id} é o ID da transferência.
#### ° O TransferControllerV1 chama o TransferService para excluir a transferência.
#### ° O TransferService remove a transferência do banco de dados usando o TransferRepository.
#### ° A API retorna o código de status 204 (No Content).

## Tratamento de Erros
#### ° A API usa códigos de status HTTP para indicar o resultado das operações. Códigos de erro comuns incluem:
#### ° 400 Bad Request: Indica que a requisição é inválida, por exemplo, dados ausentes ou formato incorreto.
#### ° 404 Not Found: Indica que o recurso solicitado não foi encontrado.
#### ° 500 Internal Server Error: Indica um erro interno do servidor.
#### ° Mensagens de erro mais detalhadas podem ser incluídas no corpo da resposta.

## Cálculo de Taxas
#### ° O cálculo da taxa é realizado pelo TaxCalculatorService e é baseado na diferença em dias entre a data atual e a data agendada, bem como no valor da transferência.

## Testes
#### ° A API possui testes unitários para garantir a qualidade e o funcionamento correto dos componentes principais. Os testes utilizam JUnit e Mockito.

## Executando a Aplicação
#### ° Certifique-se de ter o Java e o Maven instalados.
#### ° Clone o repositório da API.
#### ° Navegue até o diretório raiz do projeto.
#### ° Execute o comando mvn spring-boot:run para iniciar a aplicação.

## Tecnologias Utilizadas
#### ° Java
#### ° Spring Boot
#### ° Spring Data JPA
#### ° Spring HATEOAS
#### ° Swagger (OpenAPI)
#### ° JUnit
#### ° Mockito

## ° Este README fornece uma visão geral da API de Transferências. Para mais detalhes, consulte a documentação da API gerada pelo Swagger.