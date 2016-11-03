--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.1
-- Dumped by pg_dump version 9.6.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: authorities; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE authorities (
    username character varying(256),
    authority character varying(256)
);


--
-- Name: cart; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE cart (
    id integer NOT NULL,
    customer_id integer NOT NULL,
    creation_time timestamp without time zone DEFAULT now() NOT NULL
);


--
-- Name: cart_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE cart_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: cart_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE cart_id_seq OWNED BY cart.id;


--
-- Name: cart_products; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE cart_products (
    id integer NOT NULL,
    cart_id integer NOT NULL,
    product_id integer NOT NULL
);


--
-- Name: cart_products_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE cart_products_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: cart_products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE cart_products_id_seq OWNED BY cart_products.id;


--
-- Name: customer; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE customer (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    creation_time timestamp without time zone DEFAULT now() NOT NULL
);


--
-- Name: customers_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE customers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: customers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE customers_id_seq OWNED BY customer.id;


--
-- Name: oauth_access_token; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE oauth_access_token (
    token_id character varying(256),
    token bytea,
    authentication_id character varying(256) NOT NULL,
    user_name character varying(256),
    client_id character varying(256),
    authentication bytea,
    refresh_token character varying(256)
);


--
-- Name: oauth_approvals; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE oauth_approvals (
    userid character varying(256),
    clientid character varying(256),
    scope character varying(256),
    status character varying(10),
    expiresat timestamp without time zone,
    lastmodifiedat timestamp without time zone
);


--
-- Name: oauth_client_details; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE oauth_client_details (
    client_id character varying(256) NOT NULL,
    resource_ids character varying(256),
    client_secret character varying(256),
    scope character varying(256),
    authorized_grant_types character varying(256),
    web_server_redirect_uri character varying(256),
    authorities character varying(256),
    access_token_validity integer,
    refresh_token_validity integer,
    additional_information character varying(4096),
    autoapprove character varying(256)
);


--
-- Name: oauth_client_token; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE oauth_client_token (
    token_id character varying(256),
    token bytea,
    authentication_id character varying(256) NOT NULL,
    user_name character varying(256),
    client_id character varying(256)
);


--
-- Name: oauth_code; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE oauth_code (
    code character varying(256),
    authentication bytea
);


--
-- Name: oauth_refresh_token; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE oauth_refresh_token (
    token_id character varying(256),
    token bytea,
    authentication bytea
);


--
-- Name: order; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE "order" (
    id integer NOT NULL,
    customer_id integer NOT NULL,
    creation_time timestamp without time zone DEFAULT now() NOT NULL
);


--
-- Name: order_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE order_id_seq OWNED BY "order".id;


--
-- Name: order_products; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE order_products (
    id integer NOT NULL,
    product_id integer NOT NULL,
    order_id integer NOT NULL
);


--
-- Name: order_products_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE order_products_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: order_products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE order_products_id_seq OWNED BY order_products.id;


--
-- Name: product; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE product (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    price numeric(16,2) NOT NULL
);


--
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE product_id_seq OWNED BY product.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE users (
    username character varying(256),
    password character varying(256),
    enabled boolean
);


--
-- Name: cart id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY cart ALTER COLUMN id SET DEFAULT nextval('cart_id_seq'::regclass);


--
-- Name: cart_products id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY cart_products ALTER COLUMN id SET DEFAULT nextval('cart_products_id_seq'::regclass);


--
-- Name: customer id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY customer ALTER COLUMN id SET DEFAULT nextval('customers_id_seq'::regclass);


--
-- Name: order id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY "order" ALTER COLUMN id SET DEFAULT nextval('order_id_seq'::regclass);


--
-- Name: order_products id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY order_products ALTER COLUMN id SET DEFAULT nextval('order_products_id_seq'::regclass);


--
-- Name: product id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY product ALTER COLUMN id SET DEFAULT nextval('product_id_seq'::regclass);


--
-- Data for Name: authorities; Type: TABLE DATA; Schema: public; Owner: -
--

COPY authorities (username, authority) FROM stdin;
admin	ROLE_ADMIN
\.


--
-- Data for Name: cart; Type: TABLE DATA; Schema: public; Owner: -
--

COPY cart (id, customer_id, creation_time) FROM stdin;
\.


--
-- Name: cart_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('cart_id_seq', 1, false);


--
-- Data for Name: cart_products; Type: TABLE DATA; Schema: public; Owner: -
--

COPY cart_products (id, cart_id, product_id) FROM stdin;
\.


--
-- Name: cart_products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('cart_products_id_seq', 1, false);


--
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: -
--

COPY customer (id, name, creation_time) FROM stdin;
\.


--
-- Name: customers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('customers_id_seq', 1, true);


--
-- Data for Name: oauth_access_token; Type: TABLE DATA; Schema: public; Owner: -
--

COPY oauth_access_token (token_id, token, authentication_id, user_name, client_id, authentication, refresh_token) FROM stdin;
d86f43003ece711eb8feeae04785e487	\\xaced0005737200436f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e636f6d6d6f6e2e44656661756c744f4175746832416363657373546f6b656e0cb29e361b24face0200064c00156164646974696f6e616c496e666f726d6174696f6e74000f4c6a6176612f7574696c2f4d61703b4c000a65787069726174696f6e7400104c6a6176612f7574696c2f446174653b4c000c72656672657368546f6b656e74003f4c6f72672f737072696e676672616d65776f726b2f73656375726974792f6f61757468322f636f6d6d6f6e2f4f417574683252656672657368546f6b656e3b4c000573636f706574000f4c6a6176612f7574696c2f5365743b4c0009746f6b656e547970657400124c6a6176612f6c616e672f537472696e673b4c000576616c756571007e000578707372001e6a6176612e7574696c2e436f6c6c656374696f6e7324456d7074794d6170593614855adce7d002000078707372000e6a6176612e7574696c2e44617465686a81014b597419030000787077080000015827384373787372004c6f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e636f6d6d6f6e2e44656661756c744578706972696e674f417574683252656672657368546f6b656e2fdf47639dd0c9b70200014c000a65787069726174696f6e71007e0002787200446f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e636f6d6d6f6e2e44656661756c744f417574683252656672657368546f6b656e73e10e0a6354d45e0200014c000576616c756571007e0005787074002432363231616262342d353334382d343064332d623332612d6561383861353634343632627371007e000977080000015827392dd078737200256a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c65536574801d92d18f9b80550200007872002c6a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c65436f6c6c656374696f6e19420080cb5ef71e0200014c0001637400164c6a6176612f7574696c2f436f6c6c656374696f6e3b7870737200176a6176612e7574696c2e4c696e6b656448617368536574d86cd75a95dd2a1e020000787200116a6176612e7574696c2e48617368536574ba44859596b8b7340300007870770c000000103f4000000000000374000472656164740005777269746574000574727573747874000662656172657274002431613534393735362d616466632d346531322d623763622d313561633962653335303264	9e034d1b0516071989b51becef87da96	admin	trusted	\\xaced0005737200416f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e70726f76696465722e4f417574683241757468656e7469636174696f6ebd400b02166252130200024c000d73746f7265645265717565737474003c4c6f72672f737072696e676672616d65776f726b2f73656375726974792f6f61757468322f70726f76696465722f4f4175746832526571756573743b4c00127573657241757468656e7469636174696f6e7400324c6f72672f737072696e676672616d65776f726b2f73656375726974792f636f72652f41757468656e7469636174696f6e3b787200476f72672e737072696e676672616d65776f726b2e73656375726974792e61757468656e7469636174696f6e2e416273747261637441757468656e7469636174696f6e546f6b656ed3aa287e6e47640e0200035a000d61757468656e746963617465644c000b617574686f7269746965737400164c6a6176612f7574696c2f436f6c6c656374696f6e3b4c000764657461696c737400124c6a6176612f6c616e672f4f626a6563743b787000737200266a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c654c697374fc0f2531b5ec8e100200014c00046c6973747400104c6a6176612f7574696c2f4c6973743b7872002c6a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c65436f6c6c656374696f6e19420080cb5ef71e0200014c00016371007e00047870737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a65787000000001770400000001737200426f72672e737072696e676672616d65776f726b2e73656375726974792e636f72652e617574686f726974792e53696d706c654772616e746564417574686f72697479000000000000019a0200014c0004726f6c657400124c6a6176612f6c616e672f537472696e673b787074000a524f4c455f41444d494e7871007e000c707372003a6f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e70726f76696465722e4f41757468325265717565737400000000000000010200075a0008617070726f7665644c000b617574686f72697469657371007e00044c000a657874656e73696f6e7374000f4c6a6176612f7574696c2f4d61703b4c000b726564697265637455726971007e000e4c00077265667265736874003b4c6f72672f737072696e676672616d65776f726b2f73656375726974792f6f61757468322f70726f76696465722f546f6b656e526571756573743b4c000b7265736f7572636549647374000f4c6a6176612f7574696c2f5365743b4c000d726573706f6e7365547970657371007e0014787200386f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e70726f76696465722e426173655265717565737436287a3ea37169bd0200034c0008636c69656e74496471007e000e4c001172657175657374506172616d657465727371007e00124c000573636f706571007e0014787074000774727573746564737200256a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c654d6170f1a5a8fe74f507420200014c00016d71007e00127870737200116a6176612e7574696c2e486173684d61700507dac1c31660d103000246000a6c6f6164466163746f724900097468726573686f6c6478703f400000000000067708000000080000000274000a6772616e745f7479706574000870617373776f7264740008757365726e616d6574000561646d696e78737200256a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c65536574801d92d18f9b80550200007871007e0009737200176a6176612e7574696c2e4c696e6b656448617368536574d86cd75a95dd2a1e020000787200116a6176612e7574696c2e48617368536574ba44859596b8b7340300007870770c000000103f40000000000003740004726561647400057772697465740005747275737478017371007e0023770c000000103f400000000000027371007e000d74000b524f4c455f434c49454e547371007e000d740013524f4c455f545255535445445f434c49454e54787371007e001a3f40000000000000770800000010000000007870707371007e0023770c000000103f40000000000000787371007e0023770c000000103f40000000000000787372004f6f72672e737072696e676672616d65776f726b2e73656375726974792e61757468656e7469636174696f6e2e557365726e616d6550617373776f726441757468656e7469636174696f6e546f6b656e000000000000019a0200024c000b63726564656e7469616c7371007e00054c00097072696e636970616c71007e00057871007e0003017371007e00077371007e000b0000000177040000000171007e000f7871007e0033737200176a6176612e7574696c2e4c696e6b6564486173684d617034c04e5c106cc0fb0200015a000b6163636573734f726465727871007e001a3f400000000000067708000000080000000271007e001c71007e001d71007e001e71007e001f780070737200326f72672e737072696e676672616d65776f726b2e73656375726974792e636f72652e7573657264657461696c732e55736572000000000000019a0200075a00116163636f756e744e6f6e457870697265645a00106163636f756e744e6f6e4c6f636b65645a001563726564656e7469616c734e6f6e457870697265645a0007656e61626c65644c000b617574686f72697469657371007e00144c000870617373776f726471007e000e4c0008757365726e616d6571007e000e7870010101017371007e0020737200116a6176612e7574696c2e54726565536574dd98509395ed875b0300007870737200466f72672e737072696e676672616d65776f726b2e73656375726974792e636f72652e7573657264657461696c732e5573657224417574686f72697479436f6d70617261746f72000000000000019a020000787077040000000171007e000f787074000561646d696e	7557faa3052a6cea94f134513f910df1
\.


--
-- Data for Name: oauth_approvals; Type: TABLE DATA; Schema: public; Owner: -
--

COPY oauth_approvals (userid, clientid, scope, status, expiresat, lastmodifiedat) FROM stdin;
\.


--
-- Data for Name: oauth_client_details; Type: TABLE DATA; Schema: public; Owner: -
--

COPY oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) FROM stdin;
trusted		$2a$10$68UnA83d3beFzIX4Ya4oj.7VF1NpZVgFFDSXMk8kByhQjrbMNqFRe	read,write,trust	password,authorization_code,refresh_token,implicit		ROLE_CLIENT,ROLE_TRUSTED_CLIENT	540	600	{}	
\.


--
-- Data for Name: oauth_client_token; Type: TABLE DATA; Schema: public; Owner: -
--

COPY oauth_client_token (token_id, token, authentication_id, user_name, client_id) FROM stdin;
\.


--
-- Data for Name: oauth_code; Type: TABLE DATA; Schema: public; Owner: -
--

COPY oauth_code (code, authentication) FROM stdin;
\.


--
-- Data for Name: oauth_refresh_token; Type: TABLE DATA; Schema: public; Owner: -
--

COPY oauth_refresh_token (token_id, token, authentication) FROM stdin;
\.


--
-- Data for Name: order; Type: TABLE DATA; Schema: public; Owner: -
--

COPY "order" (id, customer_id, creation_time) FROM stdin;
\.


--
-- Name: order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('order_id_seq', 1, false);


--
-- Data for Name: order_products; Type: TABLE DATA; Schema: public; Owner: -
--

COPY order_products (id, product_id, order_id) FROM stdin;
\.


--
-- Name: order_products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('order_products_id_seq', 1, false);


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: -
--

COPY product (id, name, price) FROM stdin;
\.


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('product_id_seq', 1, false);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: -
--

COPY users (username, password, enabled) FROM stdin;
admin	$2a$10$HflApM9IRshXBjGxai4TvuwusknDwS7rtG.qE9koz6TDuqwok.HNa	t
\.


--
-- Name: cart cart_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY cart
    ADD CONSTRAINT cart_pkey PRIMARY KEY (id);


--
-- Name: cart_products cart_products_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY cart_products
    ADD CONSTRAINT cart_products_pkey PRIMARY KEY (id);


--
-- Name: customer customers_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY customer
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);


--
-- Name: oauth_access_token oauth_access_token_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY oauth_access_token
    ADD CONSTRAINT oauth_access_token_pkey PRIMARY KEY (authentication_id);


--
-- Name: oauth_client_details oauth_client_details_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY oauth_client_details
    ADD CONSTRAINT oauth_client_details_pkey PRIMARY KEY (client_id);


--
-- Name: oauth_client_token oauth_client_token_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY oauth_client_token
    ADD CONSTRAINT oauth_client_token_pkey PRIMARY KEY (authentication_id);


--
-- Name: order order_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_pkey PRIMARY KEY (id);


--
-- Name: order_products order_products_id_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY order_products
    ADD CONSTRAINT order_products_id_pk PRIMARY KEY (id);


--
-- Name: order_products order_products_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY order_products
    ADD CONSTRAINT order_products_pkey UNIQUE (id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: users unique_username; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY users
    ADD CONSTRAINT unique_username UNIQUE (username);


--
-- Name: cart_products_unique_index; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX cart_products_unique_index ON cart_products USING btree (cart_id, product_id);


--
-- Name: cart_unique_customer_index; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX cart_unique_customer_index ON cart USING btree (customer_id);


--
-- Name: order_products_unique_index; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX order_products_unique_index ON order_products USING btree (order_id, product_id);


--
-- Name: product_name_unique_index; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX product_name_unique_index ON product USING btree (name);


--
-- Name: authorities authorities_to_user_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY authorities
    ADD CONSTRAINT authorities_to_user_fk FOREIGN KEY (username) REFERENCES users(username) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: cart_products cart_products_to_cart_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY cart_products
    ADD CONSTRAINT cart_products_to_cart_fk FOREIGN KEY (cart_id) REFERENCES cart(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: cart_products cart_products_to_product_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY cart_products
    ADD CONSTRAINT cart_products_to_product_fk FOREIGN KEY (product_id) REFERENCES product(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: cart cart_to_customer_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY cart
    ADD CONSTRAINT cart_to_customer_fk FOREIGN KEY (customer_id) REFERENCES customer(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: order_products order_products_to_order_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY order_products
    ADD CONSTRAINT order_products_to_order_fk FOREIGN KEY (order_id) REFERENCES "order"(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: order_products order_products_to_products_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY order_products
    ADD CONSTRAINT order_products_to_products_fk FOREIGN KEY (product_id) REFERENCES product(id) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: order order_to_customer_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_to_customer_fk FOREIGN KEY (customer_id) REFERENCES customer(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

