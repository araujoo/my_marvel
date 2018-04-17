# README

**Informacoes Tecnicas**
* Versão do Java: 1.8
* Versão do SpringBoot: 2.0.1
* Versão do Spring Framework: 5.0.5.RELEASE.jar
* Maven 3.3.9


**Para executar**

sudo mvn package && sudo mvn spring-boot:run





**Rotas/Endpoints criados**


**Listar thumbnails dos personagens onde estes começam com um determinado prefixo**
----
* **URL**
  /thumbnails/characters?nameStartsWith=<str>

* **Metodo:**
GET

* **Resposta:**
HTTP Status: 200

Content: json com o id, nome e url do thumbnail do personagem


**Listar thumbnails dos quadrinhos onde estes possuam um personagem cujo nome começa com um determinado prefixo**
----
* **URL**
  /thumbnails/comics?characterNameStartsWith=<str>

* **Metodo:**
GET

* **Resposta:**
HTTP Status: 200

Content: json com o id, nome e url do thumbnail do quadrinho
