
CREATE TABLE Cliente (
    id_cliente        NUMBER(10) PRIMARY KEY,
    nombre            VARCHAR2(50),
    apellido          VARCHAR2(50),
    telefono          VARCHAR2(15),
    email             VARCHAR2(100),
    domicilio         VARCHAR2(100),
    sexo              VARCHAR2(20)
);

CREATE TABLE Entrenador (
    id_entrenador       NUMBER(10) PRIMARY KEY,
    nombre              VARCHAR2(50),
    apellido            VARCHAR2(50),
    fecha_contratacion  DATE,
    especialidad        VARCHAR2(50),
    estado              VARCHAR2(30)
);

CREATE TABLE Pagos (
    id_pago     NUMBER(10) PRIMARY KEY,
    id_cliente  NUMBER(10) NOT NULL,
    metodo_pago VARCHAR2(30) NOT NULL,
    monto       NUMBER(10,2) NOT NULL,
    fecha_pago  DATE NOT NULL,
    CONSTRAINT fk_pago_cliente
        FOREIGN KEY (id_cliente)
        REFERENCES Cliente(id_cliente)
        ON DELETE CASCADE
);

CREATE TABLE Asistencia (
    id_asistencia    NUMBER(10) PRIMARY KEY,
    id_cliente       NUMBER(10) NOT NULL,
    hora_entrada     DATE NOT NULL,
    hora_salida      DATE NOT NULL,
    fecha_asistencia DATE NOT NULL,
    CONSTRAINT fk_asistencia_cliente
        FOREIGN KEY (id_cliente)
        REFERENCES Cliente(id_cliente)
);

CREATE TABLE Asignacion_Entrenador (
    id_cliente    NUMBER(10),
    id_entrenador NUMBER(10),
    PRIMARY KEY (id_cliente, id_entrenador),
    CONSTRAINT fk_ae_cliente
        FOREIGN KEY (id_cliente)
        REFERENCES Cliente(id_cliente),
    CONSTRAINT fk_ae_entrenador
        FOREIGN KEY (id_entrenador)
        REFERENCES Entrenador(id_entrenador)
        ON DELETE CASCADE
);

CREATE TABLE Membresia (
    id_membresia   NUMBER PRIMARY KEY,
    id_cliente     NUMBER(10) NOT NULL,
    tipo_membresia VARCHAR2(50) NOT NULL,
    fecha_inicio   DATE NOT NULL,
    fecha_fin      DATE,
    estado         VARCHAR2(20) NOT NULL,
    CONSTRAINT fk_membresia_cliente
        FOREIGN KEY (id_cliente) REFERENCES SYSTEM.Cliente(id_cliente)
);

CREATE TABLE Rutina (
    id_rutina     NUMBER PRIMARY KEY,
    nombre_rutina VARCHAR2(50) NOT NULL
);

CREATE TABLE Nivel (
    id_nivel NUMBER PRIMARY KEY,
    nivel    VARCHAR2(50) NOT NULL
);

CREATE TABLE Objetivo (
    id_objetivo NUMBER PRIMARY KEY,
    objetivo    VARCHAR2(100) NOT NULL
);

CREATE TABLE Plan_Rutina (
    id_plan       NUMBER PRIMARY KEY,
    id_rutina     NUMBER NOT NULL,
    id_nivel      NUMBER NOT NULL,
    id_objetivo   NUMBER NOT NULL,
    duracion_dias NUMBER NOT NULL,
    CONSTRAINT fk_plan_rutina   FOREIGN KEY (id_rutina)   REFERENCES SYSTEM.Rutina(id_rutina),
    CONSTRAINT fk_plan_nivel    FOREIGN KEY (id_nivel)    REFERENCES SYSTEM.Nivel(id_nivel),
    CONSTRAINT fk_plan_objetivo FOREIGN KEY (id_objetivo) REFERENCES SYSTEM.Objetivo(id_objetivo)
);

CREATE TABLE Cliente_Plan_Rutina (
    id_cliente NUMBER(10),
    id_plan    NUMBER,
    PRIMARY KEY (id_cliente, id_plan),
    CONSTRAINT fk_cpr_cliente FOREIGN KEY (id_cliente) REFERENCES SYSTEM.Cliente(id_cliente),
    CONSTRAINT fk_cpr_plan    FOREIGN KEY (id_plan)    REFERENCES SYSTEM.Plan_Rutina(id_plan)
);

INSERT INTO Cliente VALUES (376827, 'Isabela',  'Solis',     '6141186710', 'isa@gmail.com',     'Sauco 2735',   'Mujer');
INSERT INTO Cliente VALUES (376900, 'Omar',     'Pereda',    '6142678985', 'om@gmail.com',      'Homero 5895',  'Hombre');
INSERT INTO Cliente VALUES (377112, 'Janely',   'Monreal',   '6149758745', 'jan@gmail.com',     'Rio 2548',     'Mujer');
INSERT INTO Cliente VALUES (351498, 'Jazmin',   'Castillo',  '6146989852', 'jaz@gmail.com',     'Roma 4879',    'Mujer');
INSERT INTO Cliente VALUES (378001, 'Carlos',   'Mendoza',   '6143456789', 'carlos@gmail.com',  'Pino 1200',    'Hombre');
INSERT INTO Cliente VALUES (378002, 'Fernanda', 'Lopez',     '6144567890', 'fer@gmail.com',     'Olmo 4521',    'Mujer');
INSERT INTO Cliente VALUES (378003, 'Luis',     'Ramirez',   '6145678901', 'luis@gmail.com',    'Cedro 874',    'Hombre');
INSERT INTO Cliente VALUES (378004, 'Andrea',   'Torres',    '6146789012', 'andrea@gmail.com',  'Nogal 3321',   'Mujer');
INSERT INTO Cliente VALUES (378005, 'Miguel',   'Hernandez', '6147890123', 'miguel@gmail.com',  'Juarez 998',   'Hombre');
INSERT INTO Cliente VALUES (378006, 'Daniela',  'Reyes',     '6148901234', 'daniela@gmail.com', 'Libertad 741', 'Mujer');

INSERT INTO Entrenador VALUES (10,  'Juan',     'Gomez',     TO_DATE('2022-05-10','YYYY-MM-DD'), 'Cardio',     'Ocupado');
INSERT INTO Entrenador VALUES (20,  'Selena',   'Romero',    TO_DATE('2025-06-21','YYYY-MM-DD'), 'Pilates',    'Disponible');
INSERT INTO Entrenador VALUES (30,  'Mirna',    'Lord',      TO_DATE('2018-01-02','YYYY-MM-DD'), 'Spinning',   'Lleno');
INSERT INTO Entrenador VALUES (40,  'Lucas',    'Hernandez', TO_DATE('2018-06-16','YYYY-MM-DD'), 'Musculo',    'Ocupado');
INSERT INTO Entrenador VALUES (50,  'Andrea',   'Solis',     TO_DATE('2020-03-12','YYYY-MM-DD'), 'Yoga',       'Disponible');
INSERT INTO Entrenador VALUES (60,  'Carlos',   'Mendoza',   TO_DATE('2019-08-25','YYYY-MM-DD'), 'Crossfit',   'Ocupado');
INSERT INTO Entrenador VALUES (70,  'Fernanda', 'Lopez',     TO_DATE('2021-11-05','YYYY-MM-DD'), 'Zumba',      'Disponible');
INSERT INTO Entrenador VALUES (80,  'Miguel',   'Torres',    TO_DATE('2017-09-14','YYYY-MM-DD'), 'Pesas',      'Lleno');
INSERT INTO Entrenador VALUES (90,  'Daniela',  'Ruiz',      TO_DATE('2023-02-18','YYYY-MM-DD'), 'Funcional',  'Disponible');
INSERT INTO Entrenador VALUES (100, 'Ricardo',  'Perez',     TO_DATE('2016-07-30','YYYY-MM-DD'), 'Boxeo',      'Ocupado');

INSERT INTO Membresia VALUES (1,  376827, 'Basica',   TO_DATE('2025-01-01','YYYY-MM-DD'), TO_DATE('2025-02-01','YYYY-MM-DD'), 'Vencida');
INSERT INTO Membresia VALUES (2,  376900, 'Premium',  TO_DATE('2025-02-10','YYYY-MM-DD'), TO_DATE('2025-08-10','YYYY-MM-DD'), 'Activa');
INSERT INTO Membresia VALUES (3,  377112, 'VIP',      TO_DATE('2025-03-05','YYYY-MM-DD'), TO_DATE('2026-03-05','YYYY-MM-DD'), 'Activa');
INSERT INTO Membresia VALUES (4,  351498, 'Basica',   TO_DATE('2025-01-15','YYYY-MM-DD'), TO_DATE('2025-02-15','YYYY-MM-DD'), 'Vencida');
INSERT INTO Membresia VALUES (5,  378001, 'Premium',  TO_DATE('2025-04-01','YYYY-MM-DD'), TO_DATE('2025-10-01','YYYY-MM-DD'), 'Activa');
INSERT INTO Membresia VALUES (6,  378002, 'VIP',      TO_DATE('2025-05-20','YYYY-MM-DD'), TO_DATE('2026-05-20','YYYY-MM-DD'), 'Activa');
INSERT INTO Membresia VALUES (7,  378003, 'Basica',   TO_DATE('2025-02-01','YYYY-MM-DD'), TO_DATE('2025-03-01','YYYY-MM-DD'), 'Vencida');
INSERT INTO Membresia VALUES (8,  378004, 'Premium',  TO_DATE('2025-06-01','YYYY-MM-DD'), TO_DATE('2025-12-01','YYYY-MM-DD'), 'Activa');
INSERT INTO Membresia VALUES (9,  378005, 'VIP',      TO_DATE('2025-01-10','YYYY-MM-DD'), TO_DATE('2026-01-10','YYYY-MM-DD'), 'Suspendida');
INSERT INTO Membresia VALUES (10, 378006, 'Basica',   TO_DATE('2025-03-15','YYYY-MM-DD'), TO_DATE('2025-04-15','YYYY-MM-DD'), 'Vencida');

INSERT INTO Rutina VALUES (1, 'Pierna');
INSERT INTO Rutina VALUES (2, 'Full body');
INSERT INTO Rutina VALUES (3, 'Abs');
INSERT INTO Rutina VALUES (4, 'Cardio');
INSERT INTO Rutina VALUES (5, 'Espalda');

INSERT INTO Nivel VALUES (1, 'Principiante');
INSERT INTO Nivel VALUES (2, 'Intermedio');
INSERT INTO Nivel VALUES (3, 'Avanzado');

INSERT INTO Objetivo VALUES (1, 'Perdida de grasa');
INSERT INTO Objetivo VALUES (2, 'Hipertrofia');
INSERT INTO Objetivo VALUES (3, 'Fuerza');
INSERT INTO Objetivo VALUES (4, 'Resistencia');

INSERT INTO Plan_Rutina VALUES (1, 1, 1, 1, 30);
INSERT INTO Plan_Rutina VALUES (2, 2, 2, 2, 45);
INSERT INTO Plan_Rutina VALUES (3, 3, 3, 3, 60);
INSERT INTO Plan_Rutina VALUES (4, 4, 1, 4, 20);
INSERT INTO Plan_Rutina VALUES (5, 1, 2, 2, 40);

INSERT INTO Cliente_Plan_Rutina VALUES (376827, 1);
INSERT INTO Cliente_Plan_Rutina VALUES (376900, 2);
INSERT INTO Cliente_Plan_Rutina VALUES (377112, 3);
INSERT INTO Cliente_Plan_Rutina VALUES (351498, 4);
INSERT INTO Cliente_Plan_Rutina VALUES (378001, 5);
INSERT INTO Cliente_Plan_Rutina VALUES (378002, 1);
INSERT INTO Cliente_Plan_Rutina VALUES (378003, 2);
INSERT INTO Cliente_Plan_Rutina VALUES (378004, 3);
INSERT INTO Cliente_Plan_Rutina VALUES (378005, 4);
INSERT INTO Cliente_Plan_Rutina VALUES (378006, 5);

INSERT INTO Asistencia VALUES (1,  376827, TO_DATE('01-05-2026 08:00','DD-MM-YYYY HH24:MI'), TO_DATE('01-05-2026 10:00','DD-MM-YYYY HH24:MI'), TO_DATE('01-05-2026','DD-MM-YYYY'));
INSERT INTO Asistencia VALUES (2,  376900, TO_DATE('02-05-2026 09:00','DD-MM-YYYY HH24:MI'), TO_DATE('02-05-2026 11:00','DD-MM-YYYY HH24:MI'), TO_DATE('02-05-2026','DD-MM-YYYY'));
INSERT INTO Asistencia VALUES (3,  377112, TO_DATE('03-05-2026 07:30','DD-MM-YYYY HH24:MI'), TO_DATE('03-05-2026 09:30','DD-MM-YYYY HH24:MI'), TO_DATE('03-05-2026','DD-MM-YYYY'));
INSERT INTO Asistencia VALUES (4,  351498, TO_DATE('04-05-2026 10:00','DD-MM-YYYY HH24:MI'), TO_DATE('04-05-2026 12:00','DD-MM-YYYY HH24:MI'), TO_DATE('04-05-2026','DD-MM-YYYY'));
INSERT INTO Asistencia VALUES (5,  378001, TO_DATE('05-05-2026 08:15','DD-MM-YYYY HH24:MI'), TO_DATE('05-05-2026 10:15','DD-MM-YYYY HH24:MI'), TO_DATE('05-05-2026','DD-MM-YYYY'));
INSERT INTO Asistencia VALUES (6,  378002, TO_DATE('06-05-2026 09:20','DD-MM-YYYY HH24:MI'), TO_DATE('06-05-2026 11:20','DD-MM-YYYY HH24:MI'), TO_DATE('06-05-2026','DD-MM-YYYY'));
INSERT INTO Asistencia VALUES (7,  378003, TO_DATE('07-05-2026 07:45','DD-MM-YYYY HH24:MI'), TO_DATE('07-05-2026 09:45','DD-MM-YYYY HH24:MI'), TO_DATE('07-05-2026','DD-MM-YYYY'));
INSERT INTO Asistencia VALUES (8,  378004, TO_DATE('08-05-2026 08:30','DD-MM-YYYY HH24:MI'), TO_DATE('08-05-2026 10:30','DD-MM-YYYY HH24:MI'), TO_DATE('08-05-2026','DD-MM-YYYY'));
INSERT INTO Asistencia VALUES (9,  378005, TO_DATE('09-05-2026 09:10','DD-MM-YYYY HH24:MI'), TO_DATE('09-05-2026 11:10','DD-MM-YYYY HH24:MI'), TO_DATE('09-05-2026','DD-MM-YYYY'));
INSERT INTO Asistencia VALUES (10, 378006, TO_DATE('10-05-2026 08:50','DD-MM-YYYY HH24:MI'), TO_DATE('10-05-2026 10:50','DD-MM-YYYY HH24:MI'), TO_DATE('10-05-2026','DD-MM-YYYY'));

INSERT INTO Pagos VALUES (1,  376827, 'Efectivo',      250, TO_DATE('01-MAY-2026','DD-MON-YYYY'));
INSERT INTO Pagos VALUES (2,  378002, 'Tarjeta',        400, TO_DATE('02-MAY-2026','DD-MON-YYYY'));
INSERT INTO Pagos VALUES (3,  376900, 'Transferencia', 350, TO_DATE('03-MAY-2026','DD-MON-YYYY'));
INSERT INTO Pagos VALUES (4,  376827, 'Efectivo',      200, TO_DATE('04-MAY-2026','DD-MON-YYYY'));
INSERT INTO Pagos VALUES (5,  377112, 'Tarjeta',        500, TO_DATE('05-MAY-2026','DD-MON-YYYY'));
INSERT INTO Pagos VALUES (6,  378001, 'Transferencia', 150, TO_DATE('06-MAY-2026','DD-MON-YYYY'));
INSERT INTO Pagos VALUES (7,  378005, 'Efectivo',      275, TO_DATE('07-MAY-2026','DD-MON-YYYY'));
INSERT INTO Pagos VALUES (8,  378002, 'Tarjeta',        320, TO_DATE('08-MAY-2026','DD-MON-YYYY'));
INSERT INTO Pagos VALUES (9,  376827, 'Transferencia', 450, TO_DATE('09-MAY-2026','DD-MON-YYYY'));
INSERT INTO Pagos VALUES (10, 378006, 'Efectivo',      600, TO_DATE('10-MAY-2026','DD-MON-YYYY'));

INSERT INTO Asignacion_Entrenador VALUES (376827, 10);
INSERT INTO Asignacion_Entrenador VALUES (376900, 20);
INSERT INTO Asignacion_Entrenador VALUES (377112, 30);
INSERT INTO Asignacion_Entrenador VALUES (351498, 40);
INSERT INTO Asignacion_Entrenador VALUES (378001, 50);
INSERT INTO Asignacion_Entrenador VALUES (378002, 60);
INSERT INTO Asignacion_Entrenador VALUES (378003, 70);
INSERT INTO Asignacion_Entrenador VALUES (378004, 80);
INSERT INTO Asignacion_Entrenador VALUES (378005, 90);
INSERT INTO Asignacion_Entrenador VALUES (378006, 100);

COMMIT;
