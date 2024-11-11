    CREATE TABLE IF NOT EXISTS tbl_users (
    id_users BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    mail VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    city VARCHAR(100) NOT NULL,
    department VARCHAR(100) NOT NULL,
    role INT NOT NULL
    );

    CREATE TABLE IF NOT EXISTS tbl_geolocation (
    id_geolocation BIGSERIAL PRIMARY KEY,
    latitude DECIMAL(9, 6) NOT NULL,  -- permite hasta 9 dígitos, con 6 decimales
    longitude DECIMAL(9, 6) NOT NULL
    );


    CREATE TABLE IF NOT EXISTS tbl_residences (
        id_residences BIGSERIAL PRIMARY KEY,
        name_residence VARCHAR(255) NOT NULL,
        imageUrls VARCHAR(255),
        address VARCHAR(255) NOT NULL,
        city VARCHAR(100) NOT NULL,
        neighborhood VARCHAR(100),
        department VARCHAR(100) NOT NULL,
        rooms VARCHAR(50),
        price INT,
        services VARCHAR(255),
        description TEXT,
        ability INT NOT NULL,
        category VARCHAR(50),
        state BOOLEAN,

        -- Clave foránea para la relación OneToOne con Geolocation
        id_geolocation BIGINT,
        CONSTRAINT fk_geolocation FOREIGN KEY (id_geolocation) REFERENCES tbl_geolocation(id_geolocation) ON DELETE CASCADE,

        -- Clave foránea para la relación ManyToOne con Users
        id_users BIGINT,
        CONSTRAINT fk_users FOREIGN KEY (id_users) REFERENCES tbl_users(id_users) ON DELETE CASCADE
    );

    CREATE TABLE IF NOT EXISTS tbl_residencesFavorites (
        id_residencesFavorites BIGSERIAL PRIMARY KEY,

        -- Clave foránea hacia la tabla Users
        id_users BIGINT NOT NULL,
        CONSTRAINT fk_users_favorites FOREIGN KEY (id_users) REFERENCES tbl_users(id_users) ON DELETE CASCADE,

        -- Clave foránea hacia la tabla Residences
        id_residences BIGINT NOT NULL,
        CONSTRAINT fk_residences_favorites FOREIGN KEY (id_residences) REFERENCES tbl_residences(id_residences) ON DELETE CASCADE
    );




