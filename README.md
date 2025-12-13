# API Análisis de Laboratorio

Api desarrollada en Springboot para la gestión de resultados de laboratorio.


## Dependencias

| Initializr          | Maven                         |
|---------------------|-------------------------------|
| Spring Web          | Oracle Driver (ojdbc 11)      |
| Springboot Devtools | Oracle pki                    |
| Lombok              | osdt core                     |
| Oracle Driver       | osdt cert                     |
| Spring Data JPA     | Springboot starter validation |


## Endpoints para ambiente Desarrollo: Resultados de análisis

* **POST**: Crear un resultado: http://localhost:8081/api/resultado

* **GET**: Listar los resultados: http://localhost:8081/api/resultado

* **GET**: Obtener resultado por id: http://localhost:8081/api/resultado/{id}

* **PUT**: Actualizar un resultado: http://localhost:8081/api/resultado/{id}

* **DELETE**: Eliminar un resultado: http://localhost:8081/api/resultado/{id}

## Endpoints para ambiente Desarrollo: Laboratorio

* **POST**: Crear un laboratorio: http://localhost:8081/api/laboratorio

* **GET**: Listar los laboratorios: http://localhost:8081/api/laboratorio

* **GET**: Obtener laboratorio por id: http://localhost:8081/api/laboratorio/{id}

* **PUT**: Actualizar un laboratorio: http://localhost:8081/api/laboratorio/{id}

* **DELETE**: Eliminar un laboratorio: http://localhost:8081/api/laboratorio/{id}

## Instrucciones para ejecutar en local

* Copiar archivo .env.example y renombrar a .env
* Completar las variables de entorno relacionadas con el nombre de la base de datos, dirección del Wallet descomprimido, nombre de usuario y password.

```
CONNECTION_ALIAS=abc123def456_tp
WALLET_PATH=/ruta/del/wallet
ORACLE_USERNAME=username
ORACLE_PASSWORD=pass123
API_PORT=8081
```

## Se agrega archivo `data.sql`

Para la carga inicial de datos ubicado en:
```
src/main/resources/data.sql
```

## Ejemplo de request body - Resultado

```json
{
  "fechaAnalisis": "2025-10-30T14:30:00",
  "nombreAnalisis": "Perfil lipidico",
  "resultado": "Hemoglobina: 14.2 g/dL",
  "observaciones": "Paciente sano",
  "idLaboratorio": 2
}
```
