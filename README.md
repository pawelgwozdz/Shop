# Shop
Jak coś to nie ma JavaDoc'ów/Swaggera bo już mi czasu nie wystarczyło. Operacje Potwierdzenia i Zakończenia zamówienia są w jednym api change status. kod sql do bazy jest w resources/sql/DB.sql

poniżej przykładowe zapytania:

put
/api/v1/order
req
{
    "orderDate": "2012-04-23T18:25:43.511Z",
    "customerId": 1,
    "products": [ {
        "id": 2,
        "amount": 2
    }, {
        "id": 4,
        "amount": 1
    }
    ]
}

post
/api/v1/order/update-status
{
    "orderId": 1,
    "orderStatus": "PAID" 
}

get
/api/v1/order?id=3

del
/api/v1/order
{
    "id": 3
}
