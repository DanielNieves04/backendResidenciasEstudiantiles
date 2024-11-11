COPY tbl_residences(id_residences, name_residence,neighborhood,address,ability,services,city,department,price,state,description,category,rooms)
FROM 'D:\Documents\ResidenciasGeoreferenciacion\Archivos\Todos_los_datos_de_ppa.csv'
DELIMITER ';'
CSV
HEADER;

select * from tbl_residences