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

´´´
$ http://localhost:8080/api/game
```
Med indata i request-bodyn
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

