# Test av designmönstret Facade! 

En sten, sax & påse server-implementation i tre lager (moduler); rest, logik och data som med designmönstret Facade skapar en variant på spelet. 

För att starta upp servern, gör följande: 

```sh 

$ git@github.com:antonmoeriksson/tmp.git 

$ cd tmp/backend 

$ mvn clean install && mvn spring-boot:run -pl rest 

``` 

Nu kommer Maven att starta servern, där sedan serverns tester ska exekveras för att sedan börja serva localhost:8080. 

Där sedan end-pointen som beskrivs i uppgiften används. 


Sedan kan vi använda Postman för att utföra POST och GET request på API:et enligt uppgiftbeskrivningen. 

Ett exempel: 

Med indata i request-bodyn och ett POST-request placerat på http://localhost:8080/api/game 

```json 

{ 
    "name": "anton" 
} 

``` 

Som kommer API:et returnera ett JSON representation av ett Game objekt. 

  

```json 
{ 
    "player1": { 
        "players": {}, 
        "name": "anton", 
        "move": "UNKNOWN", 
        "hasMadeMove": false 
    }, 
    "state": "ONGOING", 
    "games": {}, 
    "id": "3e064a92-bbd5-4fd1-bcd8-48ae33bea48a" 
} 

``` 

Sedan kan en annan spelare gå med i spelet genom att utföra ett POST-request på http://localhost:8080/api/game/{id}/join: 

Där {id} syftar till "3e064a92-bbd5-4fd1-bcd8-48ae33bea48a", det unika id:t för spelet. 

```json 
{ 
    "name": "johan", 
} 

``` 

Som resulterar i  

```json 
{ 
    "player1": { 
        "name": "anton", 
        "moves": [ 
            "ROCK", 
            "PAPER", 
            "SCISSOR", 
            "UNKNOWN" 
        ] 
    }, 

    "player2": { 
        "name": "johan", 
        "moves": [ 
            "ROCK", 
            "PAPER", 
            "SCISSOR", 
            "UNKNOWN" 
        ] 
    }, 
    "state": "ONGOING", 
    "id": "3e064a92-bbd5-4fd1-bcd8-48ae33bea48a" 
} 

``` 

Där sedan båda spelarna kan göra drag tills båda spelarna har valt sitt drag och då evalueras resultatet av matchen och en vinnare koras. Stegen för att åstadkomma föregående följer: 

POST-request på http://localhost:8080/api/game/{id}/join med 

```json 
{ 
    "name": "anton", 
    "move": "rock" 
} 

``` 

och sedan: 

```json 
{ 
    "name": "johan", 
    "move": "scissors" 
} 
``` 

Vilket resulterar i: 

```json 
{ 
    "player1": { 
        "name": "anton", 
        "moves": [ 
            "ROCK", 
            "PAPER", 
            "SCISSOR", 
            "UNKNOWN" 
        ] 
    }, 
    "player2": { 
        "name": "johan", 
        "moves": [ 
            "ROCK", 
            "PAPER", 
            "SCISSOR", 
            "UNKNOWN" 
        ] 
    }, 
    "state": "PLAYER_ONE_WIN", 
    "id": "3e064a92-bbd5-4fd1-bcd8-48ae33bea48a" 
} 

``` 
