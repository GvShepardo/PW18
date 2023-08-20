-- ============================

-- Questo file � stato creato con la utility dblook di Derby.
-- Indicatore data/ora: 2023-08-06 17:03:09.772
-- Database di origine: mainDB
-- URL di connessione: jdbc:derby://localhost:1527/mainDB
-- La utility dblook prender� in analisi solo specifiche tabelle.
-- appendLogs: false

-- ----------------------------------------------
-- Istruzioni DDL per tabelle
-- ----------------------------------------------

CREATE TABLE "APP"."DONAZIONE" ("ID" INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "USERNAME" VARCHAR(255) NOT NULL, "IMPORTO" INTEGER NOT NULL, "DATA" TIMESTAMP NOT NULL);

CREATE TABLE "APP"."VISITE" ("PAGINA" VARCHAR(255) NOT NULL, "COUNTER" INTEGER);

CREATE TABLE "APP"."USERATTIVITA" ("USERNAME" VARCHAR(255) NOT NULL, "ATTIVITA" VARCHAR(255) NOT NULL);

CREATE TABLE "APP"."USERS" ("USERNAME" VARCHAR(255) NOT NULL, "EMAIL" VARCHAR(255), "PASSWORD" VARCHAR(255), "TYPE" VARCHAR(100), "NOME" VARCHAR(255) NOT NULL, "COGNOME" VARCHAR(255) NOT NULL, "TEL" VARCHAR(10) NOT NULL, "DOB" TIMESTAMP NOT NULL, "TOKEN" VARCHAR(50));

CREATE TABLE "APP"."ATTIVITA" ("NOME" VARCHAR(255) NOT NULL);

-- ----------------------------------------------
-- Istruzioni DDL per chiavi
-- ----------------------------------------------

-- PRIMARY/UNIQUE
ALTER TABLE "APP"."ATTIVITA" ADD CONSTRAINT "SQL230730183920960" PRIMARY KEY ("NOME");

ALTER TABLE "APP"."USERATTIVITA" ADD CONSTRAINT "SQL230730184343990" PRIMARY KEY ("USERNAME", "ATTIVITA");

ALTER TABLE "APP"."USERS" ADD CONSTRAINT "SQL230730183835030" PRIMARY KEY ("USERNAME");

ALTER TABLE "APP"."VISITE" ADD CONSTRAINT "SQL230730184007610" PRIMARY KEY ("PAGINA");

ALTER TABLE "APP"."DONAZIONE" ADD CONSTRAINT "SQL230730183844000" PRIMARY KEY ("ID");

-- FOREIGN
ALTER TABLE "APP"."USERATTIVITA" ADD CONSTRAINT "SQL230730184343991" FOREIGN KEY ("USERNAME") REFERENCES "APP"."USERS" ("USERNAME") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "APP"."USERATTIVITA" ADD CONSTRAINT "SQL230730184343992" FOREIGN KEY ("ATTIVITA") REFERENCES "APP"."ATTIVITA" ("NOME") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "APP"."DONAZIONE" ADD CONSTRAINT "SQL230730183844001" FOREIGN KEY ("USERNAME") REFERENCES "APP"."USERS" ("USERNAME") ON DELETE NO ACTION ON UPDATE NO ACTION;

--ADD THINGS TO VISITE
INSERT INTO VISiTE (PAGINA, COUNTER) VALUES ('Home', 0);

INSERT INTO VISiTE (PAGINA, COUNTER) VALUES ('ChiSiamo', 0);

INSERT INTO VISiTE (PAGINA, COUNTER) VALUES ('Attivita', 0);

INSERT INTO VISiTE (PAGINA, COUNTER) VALUES ('Attivita1', 0);

INSERT INTO VISiTE (PAGINA, COUNTER) VALUES ('Attivita2', 0);

INSERT INTO VISiTE (PAGINA, COUNTER) VALUES ('Attivita3', 0);

INSERT INTO VISiTE (PAGINA, COUNTER) VALUES ('Contatti', 0);

INSERT INTO VISiTE (PAGINA, COUNTER) VALUES ('LogIn', 0);

INSERT INTO VISiTE (PAGINA, COUNTER) VALUES ('SignIN', 0);

INSERT INTO VISiTE (PAGINA, COUNTER) VALUES ('Profilo', 0);
