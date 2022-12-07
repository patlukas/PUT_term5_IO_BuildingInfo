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

## Przykłady
#### Przykład 1
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
            "light": 5.5
          },
          {
            "id": 4,
            "area": 12,
            "cube": 24,
            "heating": 10.5,
            "light": 5.5
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
            "light": 5.5
          }
        ]
      }
    ]
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