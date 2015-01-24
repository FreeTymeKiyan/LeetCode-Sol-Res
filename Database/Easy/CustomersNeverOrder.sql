Suppose that a website contains two tables, the Customers table and the Orders table. Write a SQL query to find all customers who never order anything.

Table: Customers.

+----+-------+
| Id | Name  |
+----+-------+
| 1  | Joe   |
| 2  | Henry |
| 3  | Sam   |
| 4  | Max   |
+----+-------+
Table: Orders.

+----+------------+
| Id | CustomerId |
+----+------------+
| 1  | 3          |
| 2  | 1          |
+----+------------+
Using the above tables as example, return the following:

+-----------+
| Customers |
+-----------+
| Henry     |
| Max       |
+-----------+

------
Alias and NOT IN
------
SELECT A.Name from Customers A
WHERE A.Id NOT IN (SELECT B.CustomerId from Orders B)

------

------
SELECT A.Name from Customers A
WHERE NOT EXISTS (SELECT 1 FROM Orders B WHERE A.Id = B.CustomerId)

------
LEFT JOIN on same IDs
But customer Id is null
------
SELECT A.Name 
FROM Customers A
LEFT JOIN Orders B ON a.Id = B.CustomerId
WHERE b.CustomerId is NULL
