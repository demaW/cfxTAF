This is test framework which using some public APIs. As well JSON server used.

Install JSON server:
npm install -g json-server

Create db.json to store data. For ex:
```JSON
{
  "Cars": [
    {
      "model": 1,
      "id": "Check Todo"
    }
  ]
}
```
Run JSON server:
json-server --watch db.json