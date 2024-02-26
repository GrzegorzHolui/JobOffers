
# Job Offers - Backend

A brief description of what this project does and who it's for


## API Reference


| ENDPOINT       | METHOD | REQUEST                 | RESPONSE         | FUNCTION                                       |
|----------------|--------|-------------------------|------------------|------------------------------------------------|
| /token         | POST   | JSON BODY (credentials) | JSON (JWT token) | returns token after successfully authorization |
| /offers        | GET    | -                       | JSON (offers)    | returns all offers
| /offers        | POST   | JSON BODY (offer)       | JSON (uuid)      | creates new offer                              |                 
| /register      | POST   | JSON BODY (user)        | JSON (id)        | creates new user                               |

## Tech Stack

Code:

![enter image description here](https://camo.githubusercontent.com/142c1ca57c4a85ddae844e196b62ffd9095552d94e559f68907d2f6031ece170/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6a6176615f31372d6f72616e67653f7374796c653d666f722d7468652d6261646765266c6f676f3d6f70656e6a646b266c6f676f436f6c6f723d7768697465)
![](https://camo.githubusercontent.com/cec4f3deeda1cde8d7e0729e689a9946a7286fc2a79be3e8b32fafa4b9f0396a/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f537072696e675f426f6f745f332d3644423333463f7374796c653d666f722d7468652d6261646765266c6f676f3d737072696e67266c6f676f436f6c6f723d7768697465)
![](https://camo.githubusercontent.com/a963b142be25ca1e2df9adddf3ebe21a5accb30a6d67763f94bd591eeaeeb387/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f72656469732d2532334444303033312e7376673f7374796c653d666f722d7468652d6261646765266c6f676f3d7265646973266c6f676f436f6c6f723d7768697465)
![](https://camo.githubusercontent.com/7e95531437f8c91626ae46cb69240160dfde5c39c1119c550cd174ba8a19e712/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4d6f6e676f44422d2532333465613934622e7376673f7374796c653d666f722d7468652d6261646765266c6f676f3d6d6f6e676f6462266c6f676f436f6c6f723d7768697465)


Tests:

![](https://camo.githubusercontent.com/6cf47d9ca3b8d62efb942ad8e9c9335f5bd5196ec76150d42fcc1a65f8486ddf/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4a756e6974352d3235413136323f7374796c653d666f722d7468652d6261646765266c6f676f3d6a756e697435266c6f676f436f6c6f723d7768697465)
![](https://camo.githubusercontent.com/d38819e2d4efdc0a84acb94de6e2c94a02997234c5a72e72b1c250bb5a980e6f/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4d6f636b69746f2d3738413634313f7374796c653d666f722d7468652d6261646765)
![](https://camo.githubusercontent.com/64222af02483697dcb725214353024d87b41710a78ce20af9c9e78b747355169/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f54657374636f6e7461696e6572732d3942343839413f7374796c653d666f722d7468652d6261646765)
![](https://camo.githubusercontent.com/66e60c319a5b4770d0f1dcff76529064813cfa8082c2a59b4fa381cd417af2a5/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f576972654d6f636b2d6163343634323f7374796c653d666f722d7468652d6261646765)

Other:

![](https://camo.githubusercontent.com/8396abd667a0eca7d28cdb29ec63b6bf29a7854c7c3d467e6ece648c7e9b81e1/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f646f636b65722d2532333064623765642e7376673f7374796c653d666f722d7468652d6261646765266c6f676f3d646f636b6572266c6f676f436f6c6f723d7768697465)
![](https://camo.githubusercontent.com/c3b871d02afde0384d676dfb0872461bca6d18199375067e04e0d67ff0f9bfae/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6d6176656e2d4337314133363f7374796c653d666f722d7468652d6261646765266c6f676f3d6170616368656d6176656e266c6f676f436f6c6f723d7768697465)


## Solved Problems
During the development of this project I had to face of a bunch of problems. These are a few of them.

 -  Organize code with independent modules
 -  Manage dependencies for these modules
 -  Build code that is maximal encapsulated
 -  Stick to predetermined architecture (Ports-and-Adapters)
 -  Authorization with JWT token and Spring Security
 -  Integration tests with TestContainers
 -  Stick to the SOLID rules
 -  AWS deploying with EC2 and ECR



## How to build the project on your own
#### To build the project:

##### 1. Clone the repository: #####

```bash
git clone https://github.com/GrzegorzHolui/JobOffers.git
```
##### 2. Go to the folder with cloned repository #####

##### 3. Run the command: #####

```bash
mvn package -DskipTests
```

##### 4. In folder target you should find a file named: application-{version}-SNAPSHOT.jar #####


#### To build the docker image with Docker Compose: ####

##### 1. Clone the repository: #####
```bash
git clone https://github.com/GrzegorzHolui/JobOffers.git
```

##### 2. Go to the folder with cloned repository: #####


##### 3. Run the command: #####
```bash
docker-compose build
```

##### 4. By using #####
```bash
docker images
```




