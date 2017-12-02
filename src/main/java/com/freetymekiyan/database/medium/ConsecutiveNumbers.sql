Write a SQL query to find all numbers that appear at least three times consecutively.

+----+-----+
| Id | Num |
+----+-----+
| 1  |  1  |
| 2  |  1  |
| 3  |  1  |
| 4  |  2  |
| 5  |  1  |
| 6  |  2  |
| 7  |  2  |
+----+-----+
For example, given the above Logs table, 1 is the only number that appears consecutively for at least three times.

------

------

SELECT DISTINCT num 
FROM
(SELECT num,
    CASE 
        when @record = num then @count:=@count+1
        when @record <> @record:=num then @count:=1 end as n
    FROM 
        Logs ,(SELECT @count:=0,@record:=(SELECT num FROM Logs LIMIT 0,1)) r
) a
WHERE a.n>=3