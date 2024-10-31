-- tabla temporal
CREATE TEMPORARY TABLE temp_precios (
    idResidence INTEGER,
    precio int
);

-- datos a temporal
SELECT * FROM temp_precios;

COPY temp_precios (idResidence, precio)
FROM 'D:\Documents\ResidenciasGeoreferenciacion\Archivos\precios.csv'
DELIMITER ';'
CSV
HEADER;

UPDATE tbl_residences r
SET price = t.precio
FROM temp_precios t
WHERE r.id_residences = t.idResidence;