# Building Info
![example workflow](https://github.com/patlukas/PUT_term5_IO_BuildingInfo/actions/workflows/ci.yml/badge.svg)

## Opis
Dla administratorów budynków, którzy pragną optymalizować koszty zarządzania budynkami  nasza aplikacja Building Info umożliwi pozyskanie informacji o parametrach budynku na poziomie pomieszczeń, kondygnacji oraz całych budynków. Aplikacja będzie dostępna poprzez GUI a także jako zdalne API dzięki czemu można ją zintegrować z istniejącymi narzędziami.

## Struktura danych 
- Lokacja to:
    - **budynek**
    - **poziom**
    - **pomieszczenie**
- Budynek może składać się z poziomów, a te z pomieszczeń
- Każda lokalizacja jest charakteryzowana przez:
    - **id** – unikalny identyfikator
    - **name** – opcjonalna nazwa lokalizacji
- Pomieszczenie dodatkowo jest charakteryzowane przez:
    - **area** = powierzchnia w m^2
    - **cube** = kubatura pomieszczenia w m^3
    - **heating** = poziom zużycia energii ogrzewania (float)
    - **light** – łączna moc oświetlenia
    - **rent** - cena wynajmu pomieszczenia

## API
  - **[POST] /api/area/all** - zwraca sumę powierzchni wszystkich pomieszczeń
  - **[POST] /api/area/id/{ID}** - zwraca:
    - powierzchnię pomieszczenia, jeżeli lokacja o ID jest pomieszceniem
    - sumę powierzchni pomieszceń na poziomie, jeżeli lokacja o ID jest poziomem
    - sumę powierzchni pomieszceń w budynku, jeżeli lokacja o ID jest budynkiem
  - **[POST] /api/cube/all** - zwraca sumę kubatur wszystkich pomieszczeń
  - **[POST] /api/cube/id/{ID}** - zwraca:
    - kubaturę pomieszczenia, jeżeli lokacja o ID jest pomieszceniem
    - sumę kubatur pomieszceń na poziomie, jeżeli lokacja o ID jest poziomem
    - sumę kubatur pomieszceń w budynku, jeżeli lokacja o ID jest budynkiem
  - **[POST] /api/light/all** - zwraca średnią moc oświetlenia na poziomach
  - **[POST] /api/light/id/{ID}** - zwraca:
    - moc oświetlenia w pomieszczeniu, jeżeli lokacja o ID jest pomieszceniem
    - średnią moc oświetlenia na m^2 na poziomie, jeżeli lokacja o ID jest poziomem
    - średnią moc oświetlenia na poziomach, jeżeli lokacja o ID jest budynkiem
- **[POST] /api/heating/all** - zwraca średnie zużycie energii grzewczej na poziomach 
- **[POST] /api/heating/id/{ID}** - zwraca:
  - zużycie energii grzewczej w pomieszczeniu, jeżeli lokacja o ID jest pomieszceniem
  - średnie zużycie energii grzewczej na m^3 na poziomie, jeżeli lokacja o ID jest poziomem
  - średnie zużycie energii grzewczej na poziomach, jeżeli lokacja o ID jest budynkiem
- **[POST] /api/rent/all** - zwraca sumę cen wynajmu wszystkich pomieszczeń w budynku
- **[POST] /api/rent/id/{ID}** - zwraca:
    - zwraca cenę wynajmu pomieszczenia, jeżeli lokacja o ID jest pomieszceniem
    - zwraca sumę cen wynajmu wszystkich pomieszczeń na poziomie, jeżeli lokacja o ID jest poziomem
    - zwraca sumę cen wynajmu wszystkich pomieszczeń w budynku, jeżeli lokacja o ID jest budynkiem
- **[POST] /api/roomsUnderRentLimit/{RENTLIMIT}** - zwraca listę z ID pomieszczeń, które mają nie wyższą cenę wynajmu niż RENTLIMIT

### Request API Body
```
{
  "id": <int> - unikalny identyfikator
  "name": <String> - opcjonalna nazwa
  "levels": [
    {
        "id": <int> - unikalny identyfikator
        "name": <String> - opcjonalna nazwa
        "rooms": [
            {
                "id": <int> - unikalny identyfikator
                "name": <String> - opcjonalna nazwa
                "area": <Float dodatni> - powierzchnia pomieszczzenia w m^2
                "cube": <Float dodatni> - kubatura pomieszczenia w m^3
                "heating": <Float nieujemny> - poziom zużycia energii grzewczej w pomieszczeniu
                "light": <Float nieujemny> - łączna moc oświetlenia w pomieszczeniu
                "rent": <Float nieujemny> - cena wynajmu pomieszczenia
            }...
        ]
    }...
  ]
}
```

## Przykłady
### Przykład 1
Request body:
```json
  {
    "id": 1,
    "levels": [
      {
        "id": 2,
        "rooms": [
          {
            "id": 3,
            "area": 10,
            "cube": 20,
            "heating": 10.5,
            "light": 5.5,
            "rent": 1
          },
          {
            "id": 4,
            "area": 12,
            "cube": 24,
            "heating": 10.5,
            "light": 5.5,
            "rent": 1
          }
        ]
      },
      {
        "id": 5,
        "rooms": [
          {
            "id": 6,
            "area": 2,
            "cube": 10.0,
            "heating": 10.5,
            "light": 5.5,
            "rent": 2
          }
        ]
      }
    ]
  }
```
- **[POST] /api/area/all**
    - Odpowiedź:
  ```json
  {
    "result": 24,
    "status": "Success"
  }
  ```
- **[POST] /api/area/id/6**
  - Odpowiedź:
  ```json
  {
    "result": 2,
    "status": "Success"
  }
  ```
- **[POST] /api/area/id/999**
  - Odpowiedź:
  ```json
  {
    "message": "Nie istnieje pomieszczenie o żądanym Id",
    "status": "Error"
  }
  ```
- **[POST] /api/cube/all**
    - Odpowiedź:
  ```json
  {
    "result": 54,
    "status": "Success"
  }
  ```
### Przykład 2
Request body:
```json
  {
    "id": 99,
    "name": "Budynek",
    "levels": [
      {
        "id": -1000,
        "name": "Poziom",
        "rooms": [
          {
            "id": -5,
            "name": "pomieszczenie",
            "area": 1,
            "cube": 2,
            "heating": 3,
            "light": 4,
            "rent": 1
          },
          {
            "id": -6,
            "name": "pomieszczenie",
            "area": 2,
            "cube": 1,
            "heating": 3,
            "light": 4,
            "rent": 3
          },
          {
            "id": -7,
            "name": "pokój",
            "area": 1,
            "cube": 2,
            "heating": 3,
            "light": 4,
            "rent": 1
          },
          {
            "id": -8,
            "name": "pokój",
            "area": 1,
            "cube": 2,
            "heating": 3,
            "light": 4,
            "rent": 1
          }
        ]
      }
    ]
  }
```
- **[POST] /api/light/all**
    - Odpowiedź:
  ```json
    {
      "result": 3.2,
      "status": "Success"
    }
  ```

- **[POST] /api/heating/id/-6**
    - Odpowiedź:
  ```json
    {
      "result": 3,
      "status": "Success"
    }
  ```

### Przykład 3 
Request body:
```json
   {
  "id": 5,
  "levels": [
    {
      "id": 1,
      "rooms": [
        {
          "id": 3,
          "area": 1,
          "cube": 2,
          "heating": 3,
          "light": 4,
          "rent": 200
        },
        {
          "id": 4,
          "area": 2,
          "cube": 1,
          "heating": 3,
          "light": 4,
          "rent": 300
        }
      ]
    },
    {
      "id": 2,
      "rooms": [
        {
          "id": 6,
          "area": 1,
          "cube": 2,
          "heating": 3,
          "light": 4,
          "rent": 500
        },
        {
          "id": 7,
          "area": 2,
          "cube": 1,
          "heating": 3,
          "light": 4,
          "rent": 1100
        }
      ]
    }
  ]
}
```
- **[POST] /api/rent/all**
    - Odpowiedź:
  ```json
    {
      "result": 2100,
      "status": "Success"
    }
  ```

- **[POST] /api/rent/id/5**
    - Odpowiedź:
  ```json
    {
      "result": 2100,
      "status": "Success"
    }
  ```
- **[POST] /api/rent/id/1**
    - Odpowiedź:
  ```json
    {
      "result": 500,
      "status": "Success"
    }
  ```
- **[POST] /api/rent/id/3**
  - Odpowiedź:
  ```json
  {
    "result": 200,
    "status": "Success"
  }
  ```
- **[POST] /api/roomsUnderRentLimit/100**
    - Odpowiedź:
  ```json
  {
    "result": [],
    "status": "Success"
  }
  ```
- **[POST] /api/roomsUnderRentLimit/1000**
    - Odpowiedź:
  ```json
  {
    "result": [
        3,
        4,
        6
    ],
    "status": "Success"
  }
  ```
- **[POST] /api/roomsUnderRentLimit/1100**
    - Odpowiedź:
  ```json
  {
    "result": [
        3,
        4,
        6,
        7
    ],
    "status": "Success"
  }
  ```