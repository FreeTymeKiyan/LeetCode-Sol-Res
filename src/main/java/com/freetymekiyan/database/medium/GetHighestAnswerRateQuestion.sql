-- Get the highest answer rate question from a table survey_log with these columns: uid, action, question_id, answer_id, q_num, timestamp.
--
-- uid means user id; action has these kind of values: "show", "answer", "skip"; answer_id is not null when action column is "answer", while is null for "show" and "skip"; q_num is the numeral order of the question in current session.
--
-- Write a sql query to identify the question which has the highest answer rate.
--
-- Example:
-- Input:
-- +------+-----------+--------------+------------+-----------+------------+
-- | uid  | action    | question_id  | answer_id  | q_num     | timestamp  |
-- +------+-----------+--------------+------------+-----------+------------+
-- | 5    | show      | 285          | null       | 1         | 123        |
-- | 5    | answer    | 285          | 124124     | 1         | 124        |
-- | 5    | show      | 369          | null       | 2         | 125        |
-- | 5    | skip      | 369          | null       | 2         | 126        |
-- +------+-----------+--------------+------------+-----------+------------+
-- Output:
-- +-------------+
-- | survey_log  |
-- +-------------+
-- |    285      |
-- +-------------+
-- Explanation:
-- question 285 has answer rate 1/1, while question 369 has 0/1 answer rate, so output 285.
-- Note: The highest answer rate meaning is: answer number's ratio in show number in the same question.

-- Questions: 1. What if for a question there is no show but only skip?

-- Sub-query, SUM and CASE
-- IF is used to avoid 0 shows
SELECT question_id AS 'survey_log'
FROM
(
    SELECT question_id,
        SUM(CASE WHEN action='answer' THEN 1 ELSE 0 END) as num_answer,
        SUM(CASE WHEN action='show' THEN 1 ELSE 0 END) as num_show
    FROM survey_log
    GROUP BY question_id
) AS tbl
ORDER BY IF(num_show = 0, num_answer, (num_answer / num_show)) DESC LIMIT 1

-- COUNT(IF...)
-- Use the COUNT() function to sum the answer and show time combining with the IF() function
SELECT
  question_id AS 'survey_log'
FROM
  survey_log
GROUP BY question_id
ORDER BY COUNT(answer_id) / COUNT(IF(action = 'show', 1, 0)) DESC
LIMIT 1;
