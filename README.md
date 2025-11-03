# API Análisis de Laboratorio

Api desarrollada en Springboot para la gestión de resultados de laboratorio.


## Dependencias

|Initializr             |Maven                          |
|-----------------------|-------------------------------|
|Spring Web             |Oracle Driver (ojdbc 11)       |
|Springboot Devtools    |Oracle pki                     |
|Lombok                 |osdt core                      |
|Oracle Driver          |osdt cert                      |
|Spring Data JPA        |Springboot starter validation  |


## Endpoints para ambiente Desarrollo

* **POST**: Crear un resultado: http://localhost:8081/api/resultado

* **GET**: Listar los resultados: http://localhost:8081/api/resultado

* **GET**: Obtener resultado por id: http://localhost:8081/api/resultado/{id}

* **PUT**: Actualizar un resultado: http://localhost:8081/api/resultado/{id}

* **DELETE**: Eliminar un resultado: http://localhost:8081/api/resultado/{id}


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
