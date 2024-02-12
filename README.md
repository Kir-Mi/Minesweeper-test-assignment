```json
Endpoints:
- POST /new - создать новую игру. Пример запроса:

{
  "width": 3,
  "height": 2,
  "mines_count": 3
}
 
 пример ответа:

{
  "game_id": "3E4C77BC-DB87-D121-F525-E8877EF9CFDF",
  "width": 3,
  "height": 2,
  "mines_count": 3,
  "completed": false,
  "field": [
    [" ", " ", " "],
    [" ", " ", " "]
  ]
}



 - POST /turn - ход пользователя. Пример запроса:

{
  "game_id": "3E4C77BC-DB87-D121-F525-E8877EF9CFDF",
  "col": 1,
  "row": 0
}


пример ответа:

{
  "game_id": "3E4C77BC-DB87-D121-F525-E8877EF9CFDF",
  "width": 3,
  "height": 2,
  "mines_count": 3,
  "completed": false,
  "field": [
    [" ", "3", " "],
    [" ", " ", " "]
  ]
}
