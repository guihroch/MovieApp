<h1 align="center">CineFlix</h1>

  <p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=24"><img src="https://img.shields.io/badge/API-24%2B-brightgreen.svg?style=flat" border="0" alt="API"></a>
  <br>
  <a href="https://wa.me/+5527998754853"><img alt="WhatsApp" src="https://img.shields.io/badge/WhatsApp-25D366?style=for-the-badge&logo=whatsapp&logoColor=white"/></a>
  <a href="https://www.linkedin.com/in/guilherme-martins-032861251/"><img alt="Linkedin" src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white"/></a>
  <a href="mailto:guihroch@gmail.com"><img alt="Gmail" src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white"/></a>
</p>

<p align="center">  

⭐ Esse é um projeto para demonstrar meu conhecimento técnico no desenvolvimento Android nativo com Kotlin. Mais informações técnicas abaixo.

🎥 Aplicativo com diversas opções e categorias diferentes de filmes, possuindo tela de detalhes, cadastro e autenticação de usuário.


</p>

</br>

<p float="left" align="center">

<img alt="screenshots" width="30%" src="screenshots/screenshot1.png"/>
  <img alt="screenshots" width="30%" src="screenshots/screenshot2.png"/>
  <img alt="screenshots" width="30%" src="screenshots/screenshot3.png"/>
  <img alt="screenshots" width="30%" src="screenshots/screenshot4.png"/>
  <img alt="screenshots" width="30%" src="screenshots/screenshot5.png"/>
  <img alt="screenshots" width="30%" src="screenshots/screenshot6.png"/> 
  
</p>


## Tecnologias usadas e bibliotecas de código aberto

- Minimum SDK level: 24
- [Linguagem Kotlin](https://kotlinlang.org/).

- Jetpack 
  - Lifecycle: Observe os ciclos de vida do Android e manipule os estados da interface do usuário após as alterações do ciclo de vida.
  - RecyclerView: Permite a criação de listas dinâmicas através do reaproveitamento de itens presentes na mesma, melhorando o desempenho do app.
  - ViewBinding: Liga os componentes do XML no Kotlin através de uma classe que garante segurança de tipo e outras vantagens.
  - Custom Views: View customizadas feitas do zero usando XML.
  - Material Design Components: Permite a criação de uma interface de usuário utilizando diversos componentes interativos e personalizáveis.
  - ConstraintLayout: Permite a criação de layouts complexos sem a necessidade de uma hierarquia de visualização aninhada.
  
- Bibliotecas
  - [Retrofit2](https://github.com/square/retrofit): Para realizar requisições seguindo o padrão HTTP.
  - [Glide](https://github.com/bumptech/glide): Para carregamento de imagens e cacheamento das mesmas.
  - [Firebase Authentication](https://firebase.google.com/docs/auth?hl=pt): O Firebase Authentication é um serviço de back-end projetado para simplificar o processo de autenticação de usuários.
  

## Arquitetura

****CineFlix**** utiliza a arquitetura MVC (Model - View - Controller) que é um padrão de projeto de software que separa a interface do usuário (View) e das regras de negócio e dados (Model) usando um mediador (Controller) para conectar o modelo à view. 
</br></br>
<img alt="screenshots" width="80%" src="screenshots/mvc_arquitetura_novo.png"/>
<br>

## API de terceiros

CineFlix utiliza a API de filmes (https://stackmobile.com.br/filmes) disponibilizada pelo professor Marcos Duarte no curso Stack Mobile de desenvolvimento android.

## Features

### Tela de Login
<img src="screenshots/feature 1.gif" width="25%"/>

 Autenticação do usuário através do Firebase authentication, caso o usuário digite um email ou uma senha inválida, ele será notificado. Caso haja sucesso na autenticação, o usuário cadastrado será encaminhado para a pagina principal do aplicativo.

### Tela Principal
<img src="screenshots/feature 2.gif" width="25%"/>

Pagina Principal do aplicativo, onde o usuário terá acesso a todos os filmes disponíveis e suas respectivas categorias. Enquanto a requisição a API é realizada, o usuário é informado do carregamento da tela e assim que finalizado, os filmes se tornam disponíveis.
### Tela de Cadastro
<img src="screenshots/feature 3.gif" width="25%"/>

Nessa feature o usuário precisa digitar um email válido(filtrado através do regex) para dar continuidade no cadastro, caso seja um email válido, ao clicar no botão de "continuar", ele terá acesso a nova etapa do cadastro, que agora inclui um campo de texto para digitar a senha e o botão para finalizar o cadastro. Caso o usuário digite uma senha inválida ou um usuário ja existente, ele será notificado. Caso ocorra tudo certo no processo de cadastro, ele irá receber um toast informado que o cadastro foi realizado com sucesso.

### Tela de Detalhes e Logout
<img src="screenshots/feature 4.gif" width="25%"/>

Nessa feature o usuário precisa digitar um email válido(filtrado através do regex) para dar continuidade no cadastro, caso seja um email válido, ao clicar no botão de "continuar", ele terá acesso a nova etapa do cadastro, que agora inclui um campo de texto para digitar a senha e o botão para finalizar o cadastro. Caso o usuário digite uma senha inválida ou um usuário ja existente, ele será notificado. Caso ocorra tudo certo no processo de cadastro, ele irá receber um toast informado que o cadastro foi realizado com sucesso.

# Licença

COLOQUE A LICENÇA - https://opensource.org/licenses

```xml

```
