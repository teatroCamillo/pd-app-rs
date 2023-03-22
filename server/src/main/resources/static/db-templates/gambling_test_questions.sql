DROP TABLE IF EXISTS public.gambling_test_questions;

CREATE TABLE public.gambling_test_questions
(
    id text NOT NULL,
    question text NOT NULL,
    name text NOT NULL,
    a1 text NOT NULL,
    a2 text NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.gambling_test_questions OWNER to postgres;
	
	
INSERT INTO public.gambling_test_questions(
	id, question, name, a1, a2)
	VALUES ('a1', '1. Have you ever lost time intended to work or school because of gambling?', 'q1-a', 'Yes', 'No');
    
INSERT INTO public.gambling_test_questions(
	id, question, name, a1, a2)
	VALUES ('a2', '2. Has gambling ever made your home life unhappy?', 'q2-a', 'Yes', 'No');    
    
INSERT INTO public.gambling_test_questions(
	id, question, name, a1, a2)
	VALUES ('a3', '3. Has gambling affected your reputation?', 'q3-a', 'Yes', 'No');    
    
INSERT INTO public.gambling_test_questions(
	id, question, name, a1, a2)
	VALUES ('a4', '4. Have you ever felt remorse after gambling?', 'q4-a', 'Yes', 'No');    
    
INSERT INTO public.gambling_test_questions(
	id, question, name, a1, a2)
	VALUES ('a5', '5. Have you ever gambled to get money to pay off debts either to solve financial difficulties?', 'q5-a', 'Yes', 'No');    
        
INSERT INTO public.gambling_test_questions(
	id, question, name, a1, a2)
	VALUES ('a6', '6. Has your gambling reduced your ambition or effectiveness?', 'q6-a', 'Yes', 'No');    
        
INSERT INTO public.gambling_test_questions(
	id, question, name, a1, a2)
	VALUES ('a7', '7. After losing, did you feel you had to come back as soon as possible to win back your losses?', 'q7-a', 'Yes', 'No');   

INSERT INTO public.gambling_test_questions(
	id, question, name, a1, a2)
	VALUES ('a8', '8. After winning, did you have a strong urge to come back and win more?', 'q8-a', 'Yes', 'No');  

INSERT INTO public.gambling_test_questions(
	id, question, name, a1, a2)
	VALUES ('a9', '9. Have you gambled frequently until your last dollar has been lost?', 'q9-a', 'Yes', 'No');  

INSERT INTO public.gambling_test_questions(
	id, question, name, a1, a2)
	VALUES ('a10', '10. Have you ever taken out loans to finance your gambling?', 'q10-a', 'Yes', 'No');  

INSERT INTO public.gambling_test_questions(
	id, question, name, a1, a2)
	VALUES ('a11', '11. Have you ever sold anything to finance gambling?', 'q11-a', 'Yes', 'No');  

INSERT INTO public.gambling_test_questions(
	id, question, name, a1, a2)
	VALUES ('a12', '12. Were you reluctant to use "gambling money" for normal expenses?', 'q12-a', 'Yes', 'No');  

INSERT INTO public.gambling_test_questions(
	id, question, name, a1, a2)
	VALUES ('a13', E'13. Has gambling caused you to lose care for your own or your family\'s well-being?', 'q13-a', 'Yes', 'No');  

INSERT INTO public.gambling_test_questions(
	id, question, name, a1, a2)
	VALUES ('a14', '14. Have you ever gambled longer than planned?', 'q14-a', 'Yes', 'No');  

INSERT INTO public.gambling_test_questions(
	id, question, name, a1, a2)
	VALUES ('a15', '15. Have you ever gambled to get away from worry and trouble?', 'q15-a', 'Yes', 'No');  

INSERT INTO public.gambling_test_questions(
	id, question, name, a1, a2)
	VALUES ('a16', '16. Have you ever committed or considered committing an illegal act to finance gambling?', 'q16-a', 'Yes', 'No');  

INSERT INTO public.gambling_test_questions(
	id, question, name, a1, a2)
	VALUES ('a17', '17. Has gambling caused you to have trouble sleeping?', 'q17-a', 'Yes', 'No');  

INSERT INTO public.gambling_test_questions(
	id, question, name, a1, a2)
	VALUES ('a18', '18. Do arguments, disappointments, or frustrations create within you an urge to gamble?', 'q18-a', 'Yes', 'No');  

INSERT INTO public.gambling_test_questions(
	id, question, name, a1, a2)
	VALUES ('a19', '19. Have you ever had the desire to celebrate some happy event by gambling for several hours?', 'q19-a', 'Yes', 'No');  

INSERT INTO public.gambling_test_questions(
	id, question, name, a1, a2)
	VALUES ('a20', '20. Have you ever considered self-destruction or suicide as a result of your gambling?', 'q20-a', 'Yes', 'No');  
	