CREATE TEMP TABLE temp_lat_long (
    id INTEGER,
    latitude NUMERIC,
    longitude NUMERIC
);

COPY temp_lat_long(id, latitude, longitude)
FROM 'D:\Documents\ResidenciasGeoreferenciacion\Archivos\datos_latitud_longitud_convertido.csv'
DELIMITER ','
CSV
HEADER;

UPDATE tbl_residences r
SET latitude = t.latitude,
    longitude = t.longitude
FROM temp_lat_long t
WHERE r.id_residences = t.id;
