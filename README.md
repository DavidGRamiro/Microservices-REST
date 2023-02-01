# <span style="color:#999999">Microservicios REST

## <span style="color:#f6fee7">Utilidades.
### Este proyecto ha sido desarrollado en lenguaje Java, usando el *IDE Intellij*. Hemos gestionado el despligue de la aplicación con *SprenBoot* y hecho peticiones al servidor con *Postman*.
<hr>

## <span style="color:#f6fee7">Descripción de la aplicación.
Primero, tenemos que saber las entidades con las que vamos a trabajar.

Trabajaremos con los **Comerciales** y con los **Clientes** en mayor medida, pero también gestionaremos los datos de los distintos pedidos dependiendo de que comercial ha llevado acabo la venta, o que cliente ha hecho ese pedido.

### <span style="color:#cbf877">Entidades
1. <u>Comerciales</u>: Los comerciales tienen atributos característicos, con los que vamos a poder identificar rápidamente que pedido ha gestionado, a que cliente, y que comision por venta tiene. Todo comercial será identificado con un ID único.

2. <u>Clientes</u>: Al igual que nuestros comerciales, tendrán un ID único, con el que se le relacionará con los pedidos que ha efectuado, y a su vez, estará directamente relacionado con los comerciales que han llevado acabo la venta.

3. <u>Pedidos</u>: Los pedidos recogerán la información esencial de cada uno de ellos. Por supuesto, tendrá un ID único, al igual que una fecha de venta y un precio. Pedidos, tendra un atributo idCliente y el idComercial para poder establecer una relación entre ellos.


### <span style="color:#cbf877">Repositorio
Para poder gestionar los datos de los comerciales, clientes y pedidos, hemos creado un repositorio para cada uno de ellos. En el repositorio de los comerciales, tendremos los métodos para poder añadir, eliminar, modificar y buscar un comercial. En el de los clientes, tendremos los métodos para añadir, eliminar, modificar y buscar un cliente. Y en el de los pedidos, tendremos, de momento, un método para poder seleccionar los pedidos dependiendo de que comercial lo ha gestionado.

Dependiendo de qué método, necesitaremos hacer algunas *Querys* para poder seleccionar desde nuestra base de datos gracias a **Hibernate**.

### <span style="color:#cbf877">Servicios
A través de los servicios, gestionaremos las peticiones que se le hacen al servidor. En este caso, hemos creado un servicio para gestionar las peticiones de referentes a los comerciales y a los pedidos.
Generamos diferentes Interface que luego implementaran nuestras clases correspondientes, haciendo en todo momento, referencia a nuestros repositorios JPA, que traen todos los métodos que nos hacen falta para la gestión de nuestros métodos, que nos proporciona Hinbernate.


### <span style="color:#cbf877">Controladores
Los controladores son los encargados de gestionar las peticiones que se le hacen al servidor. En este caso, hemos creado un controlador para gestionar las peticiones de  comerciales y a los pedidos.
Por lo tanto, en nuestro proyecto encontraremos PedidoController y ComercialController.

A través de dichas URL, podremos gestionar las peticiones **GET,POST,PUT y DELETE**, y así modificar nuestra base de datos.


### <span style="color:#cbf877">Base de datos
Para poder gestionar los datos de los comerciales, clientes y pedidos, hemos creado una base de datos en **MySQL**. En la base de datos, tendremos las tablas de los comerciales, clientes y pedidos. En cada una de ellas, tendremos los atributos que hemos definido en las entidades. Haremos la conexión mediante SprenBoot y generaremos las entidades a través de *JPABuddy*, extensión proporcionada por Intellij para generar las anotaciones necesarias y las relaciones entre tablas.


### <span style="color:#cbf877">Consultas a tablas.
Consulta a la tabla de **Clientes**
```sql
SELECT * FROM Clientes;
```

![Clientes query](/src/main/resources/img/clientes.jpg)

Consulta a la tabla de **Comerciales**
```sql
SELECT * FROM Comerciales;
```

![Clientes query](/src/main/resources/img/comerciales.jpg)

Consulta a la tabla de **Pedidos**

```sql
SELECT * FROM Pedidos;
```
![Clientes query](/src/main/resources/img/pedidos.jpg)

### <span style="color:#cbf877">Métodos CRUD

Vamos a desarrollar una serie de métodos CRUD, para actualizar, dar de alta o modificar, los datos de un cliente, pedido o comercial.

Para esta documentación, solo nos centraremos en los métodos referentes a los comerciales.

* <u>DAO</u>: Esta carpeta dentro de nuestro proyecto, es la que recogerá, el interfaz y la clase que implementrá esta interfaz. En esta clase, tambien haremos referencia a la clase que extiende de JPARepository, que trae todos los métodos prefefinidos que utilizaremos.


```java
@Override
 public Comerciale insertar(Comerciale comercial) {
        try{
            return cdao.save(comercial);
        }catch(Exception e){
            return null;
        }
    }
```

```java
@Override
  public Comerciale actualizar(Comerciale comercial) {

    try {
        if(buscarPorId(comercial.getId()).isPresent())
            return cdao.save(comercial);
    }catch(Exception e){
        return null;
        }
        return comercial;
    }
```
```java 
@Override
    public boolean eliminar(int idComercial) {
        try{
            cdao.deleteById(idComercial);
            return true;
        }catch(Exception e){
            return false;
        }
    }
```

Estos son los métodos más basicos en métodos CRUD, pero dentro de cada clase, existen mas métodos como por ejemplo la búsqueda de un comercial en concreto por el ID.Para obtener más métodos, nos fijaremos en el código fuente de este proytecto en la carpeta correspondiente.

* <u>Controller</u>: Nos encargaremos de definir las URL que tenemos que ejecutar en POSTMAN para ver las respuestas a la petición que lanzamos. Además, hemos incluido diferentes funcionalidades para que, en el caso de que, la petición no sea satisfactoria, nos devuelva un mensaje de error y nuestro servidor no se pare.

*Alta de un comercial*
```java
 @PostMapping("/alta")
    public Comerciale darALta(@RequestBody Comerciale comercial){
      return cdao.insertar(comercial);
    }
```
*Buscar un comercial por el ID*
```java
  @GetMapping("/uno/{id}")
    public Optional<Comerciale> buscarComercialPorId(@PathVariable("id") int id) {
        return cdao.buscarPorId(id);
    }
```
*Eliminar un comercial*
```java
 @DeleteMapping("/eliminar/{id}")
    public boolean eliminarComercial(@PathVariable("id") int id) {
        return cdao.eliminar(id);
    }
```
*Como deciamos, hemos añadido una funcionalidad extra para que, no solo devuelva el objeto de la petición, si no, que si no encuentra el objeto, nos devuelva un mensaje de error, además de modificar el HTP Status y devolver un 404 NOT_FOUND*

```java
  @GetMapping("/bycliente/{id}")
    public ResponseEntity<?> buscarPorCliente(@PathVariable("id") int id){

       List<Comerciale> lista = cdao.buscarPorIdCliente(id);

       if(!lista.isEmpty())
           return ResponseEntity.status(HttpStatus.OK).body(lista);
       return new ResponseEntity<String>("El cliente seleccionado no tiene pedidos asignados a comercial", HttpStatus.NOT_FOUND);
    }
```

##  <span style="color:#cbf877">Ejecuciones en *POSTMAN*

Utilizamos la aplicación POSTMAN, que es la encargada de actuar como cliente.
A través de las diferentes peticiones que generremos, nos devolverá el body de la petición en formato JSON por defecto.

* <u>Petición GET</u>: Buscar todos.

![GET TODOS](/src/main/resources/img/todos.jpg)

**RESPONSE**
```json
{
        "id": 1,
        "nombre": "Daniel",
        "apellido1": "Sáez",
        "apellido2": "Vega",
        "comision": 0.15
    },
    {
        "id": 2,
        "nombre": "Juan",
        "apellido1": "Gómez",
        "apellido2": "López",
        "comision": 0.13
    },
    {
        "id": 3,
        "nombre": "Diego",
        "apellido1": "Flores",
        "apellido2": "Salas",
        "comision": 0.11
    },
```
* <u>Petición POST</u>: Dar ALTA

Nos fijamos que hemos cambiado el VERBO HTTP. Como por parámetro recibe un objeto, en Postman, seleccionamos body y le pasamos un objeto JSON. El ID no es necesario especificarlo, por que en nuestra base de datos tenemos configurado que el ID será **AUTOINCREMENT**. En la respuesta, nos sale un ID 18, debido a las pruebas que hemos hecho.  

![POST alta](/src/main/resources/img/alta.jpg)

**RESPONSE**

```json
{
    "id": 18,
    "nombre": "David",
    "apellido1": "Gonzalez",
    "apellido2": "Ramiro",
    "comision": 0.02
}
```
EN este momento, podemos visualizar nuestra base de datos en MYSQL o volver hacer una peticion GET a /todos, y veremos que se ha dado de alta el nuevo comercial.

* <u>Petición PUT</u>: Modificar los datos

SI quisieramos modificar unos datos en concreto, utilizaremos el verbo PATCH, pero vamos a modificar todos los datos del nuevo comercial.

![PUT modificiar](/src/main/resources/img/editar.jpg)

En este momento, en editar, si tenemos que indiocar en el cuerpo del mensaje que usuario vamos a modificar, y lo haremos indicando la clave primaria que será el ID del comercial. En estecao, el 18.

Una vez modificado, si consultamos otra vez nuestra base de datos, veremos que el usaurio que antes era David, con el ID 18, ha sido modificado por completo.

**RESPONSE**
```json
{
    "id":18,
    "nombre": "Adrian",
    "apellido1": "Martín",
    "apellido2": "Pérez",
    "comision": 0.08
}
```
* <u>Petición DELETE</u>: Eliminar un comercial por el ID.

Necesitamos pasar por parámetro en la petición que ID de comercial queremos eliminar. Esto, lo haremos en la URL.

![DELETE byID](/src/main/resources/img/eliminar.jpg)

**RESPONSE**

La respuesta de este método la hemos configurado para que nos devuelva un mensaje , indicándonos si el proceso ha sido satisfactorio o no.

En el caso de que se haya eliminado, nos aparecerá un mensaje *El cliente ha sido eliminado*

En el caso de que pasemos por parámetro un ID que no existe, nos aparecerá el mensaje *No se ha podido eliminar el comercial* y nuestra aplicación no se parará.









