alter session set current_schema="UGTDDUADM";



TRUNCATE TABLE tdd_cliente;



DROP TRIGGER tdd_cliente_id_trigger;



DROP SEQUENCE tdd_cliente_id_seq;



ALTER TABLE tdd_cliente DROP CONSTRAINT tdd_cliente_pk;



DROP TABLE tdd_cliente;



CREATE TABLE tdd_cliente (

	id NUMBER NOT NULL

	,identificador_cliente VARCHAR2(16) NOT NULL

    ,nombre VARCHAR2(250) NOT NULL

    ,intento NUMBER(2) DEFAULT 1 NOT NULL

	,dato_cliente_seleccionado VARCHAR2(7) NOT NULL

	,dato_cliente_numero varchar2(4) NOT NULL

	,estatus_operacion VARCHAR2(2) DEFAULT 'EP' NOT NULL

	,nuevo_plastico VARCHAR2(4) NULL

    ,estatus NUMBER DEFAULT 1 NOT NULL

    ,fecha_creacion VARCHAR2(150) NOT NULL

    ,fecha_modificacion VARCHAR2(150) NOT NULL

);



ALTER TABLE tdd_cliente add CONSTRAINT tdd_cliente_pk PRIMARY KEY (id);



CREATE SEQUENCE tdd_cliente_id_seq  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;



CREATE OR REPLACE TRIGGER tdd_cliente_id_trigger

BEFORE INSERT ON tdd_cliente 

FOR EACH ROW



BEGIN

  SELECT tdd_cliente_id_seq.NEXTVAL

  INTO   :new.id

  FROM   dual;

END;

/



create or replace synonym UGTDDUAPP.tdd_cliente for UGTDDUADM.tdd_cliente;



DROP SEQUENCE sec_operacion_id;



TRUNCATE TABLE tdd_mca;



DROP TRIGGER tdd_mca_id_trigger;



DROP SEQUENCE tdd_mca_id_seq;



ALTER TABLE tdd_mca DROP CONSTRAINT tdd_mca_pk;



DROP TABLE tdd_mca;



CREATE TABLE tdd_mca (

	id NUMBER NOT NULL

	,id_bitacora VARCHAR2(30) NOT NULL -- sec_operacion

    ,id_operacion VARCHAR2(30) NOT NULL -- envio a mca

    ,peticion_xml CLOB NOT NULL -- xml

    ,respuesta_xml CLOB NOT NULL -- xml

    ,fecha TIMESTAMP -- fecha para filtrados, se obtiene en back

	,id_cliente VARCHAR2(16) NULL -- id de cliente (sic o numero cuenta)

	,peticion_json CLOB NOT NULL -- json

    ,respuesta_json CLOB NOT NULL -- json

    ,estatus NUMBER DEFAULT 1 NOT NULL

    ,fecha_creacion VARCHAR2(150) NOT NULL

    ,fecha_modificacion VARCHAR2(150) NOT NULL

);



ALTER TABLE tdd_mca add CONSTRAINT tdd_mca_pk PRIMARY KEY (id);



CREATE SEQUENCE tdd_mca_id_seq  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;





CREATE OR REPLACE TRIGGER tdd_mca_id_trigger

BEFORE INSERT ON tdd_mca 

FOR EACH ROW



BEGIN

  SELECT tdd_mca_id_seq.NEXTVAL

  INTO   :new.id

  FROM   dual;

END;

/



create or replace synonym UGTDDUAPP.tdd_mca for UGTDDUADM.tdd_mca;





CREATE SEQUENCE sec_operacion_id MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 NOCACHE  NOORDER  NOCYCLE;



declare

APPUSR varchar2(30):='UGTDDUAPP';

ADMUSR varchar2(30):='UGTDDUADM';

begin

--for i in (select sequence_name from all_sequences where sequence_owner=ADMUSR)

for i in (select sequence_name from all_sequences where sequence_owner='UGTDDUADM' 

and sequence_name in (upper('tdd_cliente_id_seq'),upper('tdd_mca_id_seq'),upper('sec_operacion_id')))

loop

	execute immediate 'create or replace synonym '||APPUSR||'.'||i.sequence_name||' for '||ADMUSR||'.'||i.sequence_name;

end loop;

END;

/





COMMIT;

