--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1
-- Dumped by pg_dump version 13.1

-- Started on 2021-05-24 20:46:42

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 202 (class 1259 OID 24795)
-- Name: serialtelefone; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.serialtelefone
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.serialtelefone OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 24786)
-- Name: serialusuario; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.serialusuario
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.serialusuario OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 203 (class 1259 OID 24797)
-- Name: telefone; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.telefone (
    id bigint DEFAULT nextval('public.serialtelefone'::regclass) NOT NULL,
    numero character varying(100),
    tipo character varying(100),
    usuario bigint NOT NULL,
    ddd bigint
);


ALTER TABLE public.telefone OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 24788)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    id bigint DEFAULT nextval('public.serialusuario'::regclass) NOT NULL,
    nome character varying,
    email character varying,
    senha character varying
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 2996 (class 0 OID 24797)
-- Dependencies: 203
-- Data for Name: telefone; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.telefone (id, numero, tipo, usuario, ddd) FROM stdin;
6	9999-9999	Trabalho	9	81
8	8888-8888	Trabalho	10	81
9	7777-7777	Pessoal	10	81
10	9999-9999	Trabalho	11	81
11	8888-8888	Pessoal	11	81
13	5555-5555	Residêncial	11	81
21	9999-9999	Trabalho	18	81
22	8888-8888	Pessoal	9	81
\.


--
-- TOC entry 2994 (class 0 OID 24788)
-- Dependencies: 201
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario (id, nome, email, senha) FROM stdin;
1	Administrador	admin@gmail.com	admin
9	teste01	teste01@gmail.com	teste01
10	teste02	teste02@gmail.com	teste02
11	teste03	teste03@gmail.com	teste03
18	teste04	teste04@gmail.com	teste04
\.


--
-- TOC entry 3002 (class 0 OID 0)
-- Dependencies: 202
-- Name: serialtelefone; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.serialtelefone', 26, true);


--
-- TOC entry 3003 (class 0 OID 0)
-- Dependencies: 200
-- Name: serialusuario; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.serialusuario', 20, true);


--
-- TOC entry 2862 (class 2606 OID 24802)
-- Name: telefone fone_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.telefone
    ADD CONSTRAINT fone_pkey PRIMARY KEY (id);


--
-- TOC entry 2860 (class 2606 OID 24804)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


-- Completed on 2021-05-24 20:46:42

--
-- PostgreSQL database dump complete
--

