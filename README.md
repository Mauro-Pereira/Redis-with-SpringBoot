# Projeto Spring Boot com Redis Cache

## Introdução

### Importância do Uso de Cache em uma Aplicação

O uso de cache em uma aplicação é fundamental para melhorar a performance e eficiência do sistema. Cachear dados permite que a aplicação recupere informações rapidamente, sem a necessidade de acessar o banco de dados ou outros recursos externos constantemente. Isso reduz a latência, melhora a experiência do usuário e diminui a carga nos servidores de banco de dados. Em aplicações de grande escala, o cache é essencial para garantir que o sistema continue responsivo e escalável.

### Uso do Redis como Cache

O Redis é uma das soluções de cache mais populares e eficientes disponíveis atualmente. Trata-se de um banco de dados em memória que suporta estruturas de dados ricas, como strings, hashes, listas, conjuntos e muito mais. Redis é conhecido por sua alta performance, facilidade de uso e suporte para persistência de dados. 

No contexto de uma aplicação Spring Boot, o Redis pode ser facilmente integrado para gerenciar o cache, proporcionando benefícios significativos em termos de velocidade e eficiência.

![Redis Logo](./screenshot/Redis_logo.svg.png)

## Anotações de Cache no Spring Boot

Neste projeto Spring Boot, utilizei as seguintes anotações para gerenciar o cache com Redis: `@Cacheable`, `@CachePut` e `@CacheEvict`. Cada uma delas desempenha um papel específico na gestão do cache:

### @Cacheable

A anotação `@Cacheable` é usada para marcar métodos cujos resultados devem ser armazenados em cache. Quando o método é chamado, o framework verifica se o resultado já está no cache. Se estiver, o valor em cache é retornado; se não estiver, o método é executado e o resultado é armazenado em cache para futuras chamadas.

#### Exemplo:
```java
@Cacheable(value = "Client")
public List<Client> returnAllClient(){

    return this.clientRepository.findAl();
}
```

### @CachePut

A anotação `@CachePut` é usada para atualizar o cache com o resultado de um método. Diferente de @Cacheable, esta anotação garante que o método será sempre executado e o resultado será colocado no cache. É útil para operações que modificam os dados e precisamos garantir que o cache está atualizado.

### Exemplo:
```java
@CachePut(value = "Client", key = "#id")
public Client updateClient(Long id Client client){

    Optional<Client> returnedClient = this.clientRepository.findById(id);

    if(returnedClient.isEmpty()){
        System.out.println("I must to return an exception here after");
    }

    Client updatedClient = returnedClient.get(); 
        
    updatedClient.setName(client.getName());
    updatedClient.setSurname(client.getSurname());
    updatedClient.setEmail(client.getEmail());
    updatedClient.setAdmin(client.isAdmin());
    updatedClient.setPassword(client.getPassword());

    return updatedClient;
}
```

### @CacheEvict

A anotação @CacheEvict é usada para remover entradas do cache. É especialmente útil para operações que alteram o estado dos dados e precisamos garantir que os dados em cache sejam invalidado para não retornar dados desatualizados.

### Exemplo:

```java
@CacheEvict(value = "Client", key = "#id", allEntries = true)
public void removeClient(Long id){
    Optional<Client> returnedClient = this.clientRepository.findById(id);

    if(returnedClient.isEmpty()){
        System.out.println("I must to return a exception here after");
    }

    this.clientRepository.deleteById(id);
}

```

### Como Executar essa Aplicação

Para executar esta aplicação, siga os seguintes passos:

### 1. Instalar o Docker

Certifique-se de ter o Docker instalado na sua máquina. Você pode baixar e instalar o Docker a partir do site oficial.

### 2. Baixar a Imagem do Redis

No terminal, execute o comando abaixo para baixar a imagem do Redis do Docker Hub:

```bash

docker pull redis/redis-stack-server:latest
```

### 3. Executar a Imagem do Redis

Depois de baixar a imagem, execute o seguinte comando para iniciar o Redis:

```bash
docker run -d --name redis-stack-server -p 6379:6379 redis/redis-stack-server:latest
```
### 4. Abrir o Projeto na IDE

Abra o projeto em uma IDE de sua preferência (por exemplo, IntelliJ IDEA, Eclipse, VSCode).

### 5. Executar o Projeto

Execute o projeto a partir da sua IDE. O Spring Boot estará configurado para utilizar o Redis como cache.

### Conclusão

O uso de Redis como cache em uma aplicação Spring Boot é uma abordagem poderosa para melhorar a performance e escalabilidade do sistema. As anotações `@Cacheable`, `@CachePut` e `@CacheEvict` fornecem uma maneira simples e eficaz de gerenciar o cache, garantindo que a aplicação utilize os dados de maneira eficiente e atualizada.