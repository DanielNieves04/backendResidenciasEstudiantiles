    CREATE TABLE IF NOT EXISTS tbl_users (
    idUsers BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    mail VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    city VARCHAR(100) NOT NULL,
    department VARCHAR(100) NOT NULL,
    role INT NOT NULL
    );

    CREATE TABLE IF NOT EXISTS tbl_geolocation (
    idGeolocation BIGSERIAL PRIMARY KEY,
    latitude DECIMAL(9, 6) NOT NULL,  -- permite hasta 9 dígitos, con 6 decimales
    longitude DECIMAL(9, 6) NOT NULL
    );


    CREATE TABLE IF NOT EXISTS tbl_residences (
        idResidences BIGSERIAL PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
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
        id_Geolocation BIGINT,
        CONSTRAINT fk_geolocation FOREIGN KEY (id_Geolocation) REFERENCES tbl_geolocation(idGeolocation) ON DELETE CASCADE,

        -- Clave foránea para la relación ManyToOne con Users
        id_Users BIGINT,
        CONSTRAINT fk_users FOREIGN KEY (id_Users) REFERENCES tbl_users(idUsers) ON DELETE CASCADE
    );

    CREATE TABLE IF NOT EXISTS tbl_residencesFavorites (
        idResidencesFavorites BIGSERIAL PRIMARY KEY,

        -- Clave foránea hacia la tabla Users
        id_Users BIGINT NOT NULL,
        CONSTRAINT fk_users_favorites FOREIGN KEY (id_Users) REFERENCES tbl_users(id_users) ON DELETE CASCADE,

        -- Clave foránea hacia la tabla Residences
        id_Residences BIGINT NOT NULL,
        CONSTRAINT fk_residences_favorites FOREIGN KEY (id_Residences) REFERENCES tbl_residences(id_residences) ON DELETE CASCADE
    );




