/* ---------------------------------------------------- */
/*  Generated by Enterprise Architect Version 13.0 		*/
/*  Created On : 16-���-2018 7:40:39 				*/
/*  DBMS       : PostgreSQL 						*/
/* ---------------------------------------------------- */

/* Drop Sequences for Autonumber Columns */

 

/* Drop Tables */

DROP TABLE IF EXISTS admin_data CASCADE
;

/* Create Tables */

CREATE TABLE admin_data
(
	value serial NOT NULL,
	user_id integer NOT NULL
)
;

/* Create Primary Keys, Indexes, Uniques, Checks */

ALTER TABLE admin_data ADD CONSTRAINT PK_admins
	PRIMARY KEY (value)
;

ALTER TABLE admin_data 
  ADD CONSTRAINT user_id_unique UNIQUE (user_id)
;

/* Create Foreign Key Constraints */

ALTER TABLE admin_data ADD CONSTRAINT FK_admins_users
	FOREIGN KEY (user_id) REFERENCES user_data (value) ON DELETE Restrict ON UPDATE Cascade
;

/* Create Table Comments, Sequences for Autonumber Columns */

 