CREATE TABLE TICKET (
  ID INTEGER IDENTITY PRIMARY KEY,
  TIME_IN TIME,
  TIME_OUT TIME,
  DATE DATE,
  IS_LOST BOOLEAN,
);