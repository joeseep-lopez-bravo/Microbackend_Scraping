# API de Extracción de Datos de Redes Sociales

## Descripción

Esta API permite extraer y gestionar publicaciones, imágenes, videos, enlaces y credenciales desde distintas redes sociales como Facebook, Instagram, X (Twitter) y TikTok.

## Versión

1.0.0

## Servidores

- **Local:** `http://localhost:8080`

## Endpoints y Estructuras de Respuesta

### Publicaciones

- `GET /{red_social}/publicaciones` - Obtiene publicaciones paginadas de una red social.
  ```json
  {
    "content": [
      {
        "id": 1,
        "usuario": "ejemplo",
        "contenido": "Texto de la publicación",
        "fecha": "2024-01-01",
        "reacciones": 10
      }
    ]
  }
  ```
- `GET /{red_social}/publicaciones/{id}` - Obtiene una publicación por ID.
  ```json
  {
    "id": 1,
    "usuario": "ejemplo",
    "contenido": "Texto de la publicación",
    "fecha": "2024-01-01",
    "reacciones": 10
  }
  ```

### Imágenes

- `GET /{red_social}/imagenes` - Obtiene todas las imágenes extraídas.
  ```json
  [
    {
      "id": 1,
      "url": "http://localhost:8080/facebook/imagenes/watch/imagen_1_1.jpg",
      "publicacionId": 1
    }
  ]
  ```
- `GET /{red_social}/imagenes/{id}` - Obtiene una imagen por ID.
  ```json
  {
    "id": 1,
    "url": "http://localhost:8080/facebook/imagenes/watch/imagen_1_1.jpg",
    "publicacionId": 1
  }
  ```

### Videos

- `GET /{red_social}/videos` - Obtiene todos los videos extraídos.
  ```json
  [
    {
      "id": 1,
      "url": "http://localhost:8080/facebook/videos/watch/video_1.mp4",
      "publicacionId": 1
    }
  ]
  ```
- `GET /{red_social}/videos/{id}` - Obtiene un video por ID.
  ```json
  {
    "id": 1,
    "url": "http://localhost:8080/facebook/videos/watch/video_1.mp4",
    "publicacionId": 1
  }
  ```

### Comentarios

- `GET /{red_social}/publicaciones/{id}/comentarios` - Obtiene comentarios de una publicación.
  ```json
  {
    "content": [
      {
        "id": 1,
        "usuario": "ejemplo",
        "comentario": "Texto del comentario",
        "fecha": "2024-01-01"
      }
    ]
  }
  ```
- `GET /{red_social}/comentarios` - Obtiene todos los comentarios extraídos.
  ```json
  [
    {
      "id": 1,
      "usuario": "ejemplo",
      "comentario": "Texto del comentario",
      "fecha": "2024-01-01"
    }
  ]
  ```
- `GET /{red_social}/comentarios/{id}` - Obtiene un comentario por ID.
  ```json
  {
    "id": 1,
    "usuario": "ejemplo",
    "comentario": "Texto del comentario",
    "fecha": "2024-01-01"
  }
  ```

### Multimedia de Publicaciones

- `GET /{red_social}/publicaciones/{id}/multimedia` - Obtiene archivos multimedia asociados a una publicación (JPG, PNG, MP4).
  ```json
  [
    {
      "tipo": "imagen",
      "id": 1,
      "url": "http://localhost:8080/facebook/imagenes/watch/imagen_1_1.jpg",
      "publicacionId": 1
    }
  ]
  ```

### Descarga de Archivos

- `GET /{red_social}/imagenes/watch/{filename}` - Obtiene una imagen específica.
- `GET /{red_social}/videos/watch/{filename}` - Obtiene un video específico.

### Credenciales de Facebook

- `GET /facebook/credentials` - Obtiene las credenciales almacenadas.
  ```json
  [
    {
      "usernameKey": "934399812",
      "passwordKey": "SergioMaldito1"
    },
    {
      "usernameKey": "daniel@gmail.com",
      "passwordKey": "E721"
    }
  ]
  ```

### CRUD de Enlaces de Perfiles de Facebook

- `GET /facebook/enlaces/perfiles` - Obtiene todos los perfiles almacenados.
  ```json
  [
    "https://www.facebook.com/santiagoolmedopolicia",
    "https://www.facebook.com/profile.php?id=100086973733765",
    "https://www.facebook.com/tenenciapolitica.goaltal.33"
  ]
  ```
- `POST /facebook/enlaces/perfiles` - Agrega un nuevo perfil.
- `PUT /facebook/enlaces/perfiles/{index}` - Modifica un perfil existente.
- `DELETE /facebook/enlaces/perfiles/{index}` - Elimina un perfil.

###  Obtener archivos multimedia de una publicación
**GET** `/facebook/publicaciones/{id}/multimedia`

**Respuesta:**
```json
[
    {
        "tipo": "imagen",
        "id": 1,
        "url": "aqui no hay imagenes",
        "publicacionId": 1,
        "contenido": null,
        "typeImg": null,
        "ruta": [
            "http://localhost:8080/facebook/imagenes/watch/imagen_1_1.jpg"
        ]
    },
    {
        "tipo": "imagen",
        "id": 2,
        "url": "sin imagen",
        "publicacionId": 1,
        "contenido": null,
        "typeImg": "picture",
        "ruta": []
    }
]
```
## Scheduler y ejecucion
Esta seccion la gestión de tareas programadas para la ejecución de scrapers en Facebook. Incluye operaciones para crear, obtener, eliminar y ejecutar manualmente scrapers de diferentes tipos.

## Endpoints

### Crear una tarea de Scraper
**Endpoint:**
```
POST /facebook/scraper/crear
```

**Descripción:**
Crea una nueva tarea programada para ejecutar un scraper de Facebook.

**Cuerpo de la petición (JSON):**
```json
{
  "scraperType": "perfil",
  "endTime": "2028-03-06T23:59:59",
  "executionDates": ["2025-03-06"],
  "executionTimes": ["00:54"]
}
```

**Respuestas:**
- `200 OK` - Devuelve la tarea creada.
- `400 Bad Request` - Datos inválidos.

---

### Obtener todas las tareas de Scraper
**Endpoint:**
```
GET /facebook/scraper/todos
```

**Descripción:**
Obtiene todas las tareas de scrapers almacenadas en el sistema.

**Respuesta esperada:**
```json
[
  {
    "id": 1,
    "scraperType": "perfil",
    "endTime": "2028-03-06T23:59:59",
    "executionDates": ["2025-03-06"],
    "executionTimes": ["00:54"]
  }
]
```

**Respuestas:**
- `200 OK` - Devuelve la lista de tareas programadas.

---

### Eliminar una tarea de Scraper
**Endpoint:**
```
DELETE /facebook/scraper/eliminar/{id}
```

**Descripción:**
Elimina una tarea programada identificada por su ID.

**Parámetros:**
- `id` (Long) - Identificador de la tarea a eliminar.

**Respuestas:**
- `200 OK` - Confirmación de eliminación exitosa.
- `404 Not Found` - Si la tarea no existe.

---

### Ejecutar manualmente un Scraper
**Endpoint:**
```
POST /facebook/scraper/ejecutar-manual/{tipo}
```

**Descripción:**
Ejecuta manualmente un scraper específico, sin necesidad de que esté programado.

**Parámetros:**
- `tipo` (String) - Tipo de scraper a ejecutar (`perfil`, `paginas`, `grupos`, `imagen`, `videos`, `todos`).

**Ejemplo de llamada:**
```
POST /facebook/scraper/ejecutar-manual/perfil
```

**Respuestas:**
- `200 OK` - Confirma la ejecución manual del scraper.
- `400 Bad Request` - Si el tipo de scraper no es válido.

## Notas adicionales
- El sistema permite programar tareas para ejecutarse automáticamente en fechas y horarios específicos.
- La ejecución automática está definida en un cron job que revisa las tareas activas y las ejecuta cuando corresponde.
- Para ejecutar manualmente un scraper, se utiliza un proceso en Python ubicado en `D:\Joeseep\web_scrap\Selenium_scrape\facebook_scrape\execute_fb_scrape.py`.

# **API de Filtrado de Publicaciones de Facebook**

## **Descripción**
Este servicio permite obtener publicaciones filtradas según diferentes criterios, como el usuario, palabras clave, tipo de contenido (imagen, video o ambos), orden y rango de días. Además, la respuesta está paginada para facilitar su manejo.

---

## **Endpoint: Filtrar Publicaciones con Multimedia**

### **URL**
`GET /filtrarPublicaciones`

### **Parámetros de Consulta (Query Params)**

| Parámetro         | Tipo     | Requerido | Valor por defecto | Descripción |
|------------------|---------|-----------|-------------------|-------------|
| `usuario`        | String  | No        | `null`            | Filtra por el nombre de usuario que realizó la publicación. |
| `palabraClave`   | String  | No        | `null`            | Filtra publicaciones que contengan una palabra clave en el texto. |
| `tipo`           | String  | No        | `"ambos"`        | Filtra por tipo de publicación: `"imagen"`, `"video"` o `"ambos"`. |
| `orden`          | String  | No        | `"DESC"`         | Orden de los resultados: `"ASC"` (ascendente) o `"DESC"` (descendente). |
| `rangoDias`      | Integer | No        | `null`            | Filtra publicaciones realizadas dentro de los últimos `n` días. |
| `orden_adicional`| String  | No        | `null`            | Criterio de ordenamiento adicional para las publicaciones. |
| `size`           | Integer | No        | `10`              | Cantidad de publicaciones por página. |
| `page`           | Integer | No        | `0`               | Número de la página a recuperar (empezando desde 0). |

---


### **Obtener publicaciones de un usuario específico ordenadas por fecha descendente**
```http
GET /filtrarPublicaciones?usuario=juanperez&orden=DESC
```

### ** Obtener publicaciones con la palabra clave "evento" en los últimos 7 días**
```http
GET /filtrarPublicaciones?palabraClave=evento&rangoDias=7
```

### **Filtrar publicaciones con tipo "imagen" y paginadas de 5 en 5**
```http
GET /filtrarPublicaciones?tipo=imagen&size=5&page=2
```

---

## **Respuesta Exitosa (200 OK)**
```json
{
  "content": [
    {
      "id": 1,
      "usuario": "juanperez",
      "texto": "Gran evento esta noche!",
      "tipo": "imagen",
      "fecha": "2024-03-06T20:30:00",
      "multimediaUrl": "https://facebook.com/images/evento.jpg"
    },
    {
      "id": 2,
      "usuario": "maria23",
      "texto": "Nuevo producto lanzado",
      "tipo": "video",
      "fecha": "2024-03-05T14:15:00",
      "multimediaUrl": "https://facebook.com/videos/lanzamiento.mp4"
    }
  ],
  "totalPages": 5,
  "totalElements": 50,
  "size": 10,
  "number": 0
}
```

---

## **Posibles Errores**

| Código | Descripción |
|--------|------------|
| 400 Bad Request | Alguno de los parámetros tiene un formato inválido. |
| 500 Internal Server Error | Error interno al procesar la solicitud. |

---

## **Notas Adicionales**
- El sistema utiliza paginación por defecto con `size=10`.
- Si no se proporciona un tipo específico (`tipo`), se filtrarán tanto imágenes como videos.
- Si el orden (`orden`) no se especifica, el sistema ordenará por defecto en orden descendente (`DESC`).

🚀 **¡Listo para implementar!**




## Contacto
Para dudas o soporte, contactar con el equipo de desarrollo.

## Autenticación

Actualmente, la API no requiere autenticación.

## Tecnologías

- OpenAPI 3.0.1
- JSON como formato de respuesta
- Servidor local en `http://localhost:8080`

## Contacto

Si tienes preguntas o problemas, por favor contacta con el equipo de desarrollo.




---

Esta documentación cubre todos los endpoints, incluyendo los nuevos de archivos multimedia. Avísame si necesitas más ajustes.
