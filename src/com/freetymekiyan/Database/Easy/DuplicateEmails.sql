Write a SQL query to find all duplicate emails in a table named Person.

+----+---------+
| Id | Email   |
+----+---------+
| 1  | a@b.com |
| 2  | c@d.com |
| 3  | a@b.com |
+----+---------+
For example, your query should return the following for the above table:

+---------+
| Email   |
+---------+
| a@b.com |
+---------+
Note: All emails are in lowercase.

-------
Group person by email
Return count > 1
-------

SELECT Email
FROM Person
GROUP BY Email
HAVING count(*) > 1


1. Use self join.

SELECT DISTINCT a.Email
FROM Person a JOIN Person b
ON (a.Email = b.Email)
WHERE a.Id <> b.Id

2. Use subquery with EXISTS:

SELECT DISTINCT a.Email
FROM Person a
WHERE EXISTS(
    SELECT 1
    FROM Person b
    WHERE a.Email = b.Email
    LIMIT 1, 1
)

3. Basic idea is this query:

SELECT DISTINCT Email FROM Person
MINUS
(SELECT Id, Email FROM Person GROUP BY Email)
But since MySQL does not support MINUS, we use LEFT JOIN:

SELECT DISTINCT a.Email FROM Person a
LEFT JOIN (SELECT Id, Email from Person GROUP BY Email) b
ON (a.email = b.email) AND (a.Id = b.Id)
WHERE b.Email IS NULL
