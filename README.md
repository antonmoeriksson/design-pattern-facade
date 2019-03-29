# Temporät repo för kodprov

En sten, sax & påse sever implementation i tre lager (moduler); rest, logik och data som med design möstret Facade löser probkemet.

För att starta upp servern, gör följande:
```sh
$ git@github.com:antonmoeriksson/tmp.git
$ cd backend
$ mvn clean install && mvn spring-boot:run -pl rest
```

Nu kommer Maven att sätta upp servern och serverns tester ska exekveras för att sedan börja serva localhost:8080.
Där sedan end-pointen som beskrivs i uppgiften används.

Sedan kan vi använda Postman för att utföra POST och GET request på API enligt uppgiftbeskrivningen.
Ett exemple:

Med indata i request-bodyn och ett POST-request placerat på http://localhost:8080/api/game
```json
{
    "name": "anton"
}
```
Som kommer retunera ett marshalat Game objekt.

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
Där {id} syftar till "3e064a92-bbd5-4fd1-bcd8-48ae33bea48a", det unika id:et för spelet.
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
Där sedan båda spelarna kan göra sitt drag till båda spelarna har valt sitt drag och då evalueras resultatet av matchen och en vinnare korras. Stgen för att åstakomma föregånde följer.
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
	"move": "rock"
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






