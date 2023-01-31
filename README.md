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





