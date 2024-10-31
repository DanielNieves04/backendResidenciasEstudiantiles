CREATE TEMP TABLE temp_data (
    idResidence INTEGER,
    name VARCHAR(255),
	city VARCHAR(100),
	departament TEXT,
	state BOOLEAN,
	descriptiion VARCHAR(250)
);

-- datos a temporal
SELECT * FROM temp_data;

COPY temp_data(idResidence, name, city, departament, state, descriptiion)
FROM 'D:\Documents\ResidenciasGeoreferenciacion\Archivos\datos_faltantes_residencias.csv'
DELIMITER ';'
CSV
HEADER;

UPDATE tbl_residences r
SET
    name = t.name,
    city = t.city,
    department = t.departament,

    state = t.state,
    description = t.descriptiion
FROM temp_data t
WHERE r.id_residences = t.idResidence;

