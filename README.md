# TP FINAL

### Usuario y contraseña usados para la base
**User:** root      **Password:** root

### Crear base de datos 
```bash
    CREATE DATABASE final;
```

### Crear tabla de jugadores 
```bash
    CREATE TABLE `player` (
	  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
	  `NAME` varchar(100) NOT NULL,
	  PRIMARY KEY (`ID`)
	) CHARSET=utf8;
```

### Crear tabla de ganadores 
```bash
    CREATE TABLE `winners` (
	  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
	  `NAME` varchar(100) NOT NULL,
	  `PLAYER_ID` int(10) NOT NULL,
	  PRIMARY KEY (`ID`)
	) CHARSET=utf8;
```

### Endpoints

Endpoints para usar (El servidor levanta en puerto 9000):

* **GET** [http://localhost:9000/api/players] - Traer todos los players
* **POST** [http://localhost:9000/api/players/add] - Agrega un player pasando el name por el body
* **DELETE** [http://localhost:9000/api/players/{id}] - Elimina el player
* **GET** [http://localhost:9000/api/players/{id}] - Devuelve la info del player
* **GET** [http://localhost:9000/api/winners] - Devuelve la lista de ganadores
* **GET** [http://localhost:9000/api/winners/random] - Genera un ganador y lo devuelve
* **GET** [http://localhost:9000/api/winners/csv] - Devuelve un CSV con la lista de ganadores
