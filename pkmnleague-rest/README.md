# REST Module

To use the REST module, go into the pkmnleague-rest directory and launch

```
$ mvn tomcat7:run
```

Web server will start and the application will be available at `http://localhost:8080/pa165/rest`.

To test that it works, you can simply try following commands

## Get all Trainers

```
$ curl -i -X GET http://localhost:8080/pa165/rest/trainers
```

Your response will be similiar to following:

```json
[
  {
    "id": 1,
    "fullName": "Red",
    "email": "red@example.com",
    "dateOfBirth": "1970-01-01",
    "badges": [
      {
        "id": 3,
        "gym": {
          "id": 0,
          "city": "Cerulean City",
          "type": "WATER",
          "leader": {
            "id": 4,
            "fullName": "Misty",
            "email": "misty@example.com",
            "dateOfBirth": "1970-01-01",
            "badges": [],
            "pokemon": [
              {
                "id": 12,
                "speciesId": 120,
                "speciesName": "Staryu",
                "nickname": "Staryu",
                "primaryType": "WATER",
                "secondaryType": null,
                "level": 18
              },
              {
                "id": 13,
                "speciesId": 121,
                "speciesName": "Starmie",
                "nickname": "Starmie",
                "primaryType": "WATER",
                "secondaryType": "PSYCHIC",
                "level": 21
              }
            ]
          }
        }
      },
      {
        "id": 2,
        "gym": {
          "id": 0,
          "city": "Pewter City",
          "type": "ROCK",
          "leader": {
            "id": 3,
            "fullName": "Brock",
            "email": "brock@example.com",
            "dateOfBirth": "1970-01-01",
            "badges": [],
            "pokemon": [
              {
                "id": 11,
                "speciesId": 95,
                "speciesName": "Onyx",
                "nickname": "Onyx",
                "primaryType": "ROCK",
                "secondaryType": "GROUND",
                "level": 14
              },
              {
                "id": 10,
                "speciesId": 74,
                "speciesName": "Geodude",
                "nickname": "Geodude",
                "primaryType": "ROCK",
                "secondaryType": "GROUND",
                "level": 12
              }
            ]
          }
        }
      },
      {
        "id": 1,
        "gym": {
          "id": 0,
          "city": "Viridian City",
          "type": "GROUND",
          "leader": {
            "id": 2,
            "fullName": "Blue",
            "email": "blue@example.com",
            "dateOfBirth": "1970-01-01",
            "badges": [],
            "pokemon": [
              {
                "id": 9,
                "speciesId": 130,
                "speciesName": "Gyarados",
                "nickname": "Gyarados",
                "primaryType": "WATER",
                "secondaryType": "FLYING",
                "level": 52
              },
              {
                "id": 7,
                "speciesId": 103,
                "speciesName": "Exeggutor",
                "nickname": "Exeggutor",
                "primaryType": "GRASS",
                "secondaryType": "PSYCHIC",
                "level": 55
              },
              {
                "id": 8,
                "speciesId": 59,
                "speciesName": "Arcaine",
                "nickname": "Arcaine",
                "primaryType": "FIRE",
                "secondaryType": null,
                "level": 58
              }
            ]
          }
        }
      }
    ],
    "pokemon": [
      {
        "id": 5,
        "speciesId": 6,
        "speciesName": "Charziard",
        "nickname": "Charziard",
        "primaryType": "FIRE",
        "secondaryType": "FLYING",
        "level": 84
      },
      {
        "id": 6,
        "speciesId": 9,
        "speciesName": "Blastoise",
        "nickname": "Blastoise",
        "primaryType": "WATER",
        "secondaryType": null,
        "level": 84
      },
      {
        "id": 3,
        "speciesId": 143,
        "speciesName": "Snorlax",
        "nickname": "Snorlax",
        "primaryType": "NORMAL",
        "secondaryType": null,
        "level": 88
      },
      {
        "id": 2,
        "speciesId": 131,
        "speciesName": "Lapras",
        "nickname": "Lapras",
        "primaryType": "WATER",
        "secondaryType": "ICE",
        "level": 80
      },
      {
        "id": 4,
        "speciesId": 3,
        "speciesName": "Venusaur",
        "nickname": "Venusaur",
        "primaryType": "GRASS",
        "secondaryType": "POISON",
        "level": 84
      },
      {
        "id": 1,
        "speciesId": 25,
        "speciesName": "Pikachu",
        "nickname": "Pikachu",
        "primaryType": "ELECTRIC",
        "secondaryType": null,
        "level": 88
      }
    ]
  },
  {
    "id": 2,
    "fullName": "Blue",
    "email": "blue@example.com",
    "dateOfBirth": "1970-01-01",
    "badges": [],
    "pokemon": [
      {
        "id": 9,
        "speciesId": 130,
        "speciesName": "Gyarados",
        "nickname": "Gyarados",
        "primaryType": "WATER",
        "secondaryType": "FLYING",
        "level": 52
      },
      {
        "id": 7,
        "speciesId": 103,
        "speciesName": "Exeggutor",
        "nickname": "Exeggutor",
        "primaryType": "GRASS",
        "secondaryType": "PSYCHIC",
        "level": 55
      },
      {
        "id": 8,
        "speciesId": 59,
        "speciesName": "Arcaine",
        "nickname": "Arcaine",
        "primaryType": "FIRE",
        "secondaryType": null,
        "level": 58
      }
    ]
  },
  {
    "id": 3,
    "fullName": "Brock",
    "email": "brock@example.com",
    "dateOfBirth": "1970-01-01",
    "badges": [],
    "pokemon": [
      {
        "id": 11,
        "speciesId": 95,
        "speciesName": "Onyx",
        "nickname": "Onyx",
        "primaryType": "ROCK",
        "secondaryType": "GROUND",
        "level": 14
      },
      {
        "id": 10,
        "speciesId": 74,
        "speciesName": "Geodude",
        "nickname": "Geodude",
        "primaryType": "ROCK",
        "secondaryType": "GROUND",
        "level": 12
      }
    ]
  },
  {
    "id": 4,
    "fullName": "Misty",
    "email": "misty@example.com",
    "dateOfBirth": "1970-01-01",
    "badges": [],
    "pokemon": [
      {
        "id": 12,
        "speciesId": 120,
        "speciesName": "Staryu",
        "nickname": "Staryu",
        "primaryType": "WATER",
        "secondaryType": null,
        "level": 18
      },
      {
        "id": 13,
        "speciesId": 121,
        "speciesName": "Starmie",
        "nickname": "Starmie",
        "primaryType": "WATER",
        "secondaryType": "PSYCHIC",
        "level": 21
      }
    ]
  }
]
```

## Get single Trainer

```
$ curl -i -X GET http://localhost:8080/pa165/rest/trainers/2
```

Your response will be similiar to following

```json
{
  "id": 2,
  "fullName": "Blue",
  "email": "blue@example.com",
  "dateOfBirth": "1970-01-01",
  "badges": [],
  "pokemon": [
    {
      "id": 9,
      "speciesId": 130,
      "speciesName": "Gyarados",
      "nickname": "Gyarados",
      "primaryType": "WATER",
      "secondaryType": "FLYING",
      "level": 52
    },
    {
      "id": 7,
      "speciesId": 103,
      "speciesName": "Exeggutor",
      "nickname": "Exeggutor",
      "primaryType": "GRASS",
      "secondaryType": "PSYCHIC",
      "level": 55
    },
    {
      "id": 8,
      "speciesId": 59,
      "speciesName": "Arcaine",
      "nickname": "Arcaine",
      "primaryType": "FIRE",
      "secondaryType": null,
      "level": 58
    }
  ]
}
```

## Create a Trainer

```
$ curl -i -X POST -H "Content-Type: application/json" --data '{"fullName":"test","email":"foo@foo.foo","password":"abc","dateOfBirth":"1990-01-01"}' http://localhost:8080/pa165/rest/trainers
```

Reponse will be the created entity.

```json
{
  "id": 5,
  "fullName": "test",
  "email": "foo@foo.foo",
  "dateOfBirth": "1990-01-01",
  "badges": [],
  "pokemon": []
}
```

In case of non-unique trainer, an error is expected behavior.

## Delete a Trainer

```
$ curl -i -X DELETE http://localhost:8080/pa165/rest/trainers/5
```

Response will be just `200 OK`, no data is expected. In case of deleting a non-existent trainer, `404 Not Found` is expected.

You can't delete a trainer that already awarded a Badge.

## Get only Trainer's Pokémon

```
$ curl -i -X GET http://localhost:8080/pa165/rest/trainers/2/pokemon
```

The expected response is a list of Pokemon.

```json
[
  {
    "id": 9,
    "speciesId": 130,
    "speciesName": "Gyarados",
    "nickname": "Gyarados",
    "primaryType": "WATER",
    "secondaryType": "FLYING",
    "level": 52
  },
  {
    "id": 8,
    "speciesId": 59,
    "speciesName": "Arcaine",
    "nickname": "Arcaine",
    "primaryType": "FIRE",
    "secondaryType": null,
    "level": 58
  },
  {
    "id": 7,
    "speciesId": 103,
    "speciesName": "Exeggutor",
    "nickname": "Exeggutor",
    "primaryType": "GRASS",
    "secondaryType": "PSYCHIC",
    "level": 55
  }
]
```