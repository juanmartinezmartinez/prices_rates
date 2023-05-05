# Precios finales y tarifas


## Descripción de la prueba

En la base de datos de comercio electrónico de la compañía disponemos de la tabla PRICES que refleja el precio final (pvp) y la tarifa que aplica a un producto de una cadena entre unas fechas determinadas. A continuación se muestra un ejemplo de la tabla con los campos relevantes:

### PRICES

|BRAND_ID |START_DATE |END_DATE |PRICE_LIST |PRODUCT_ID |PRIORITY |PRICE |CURR|
|---|---|---|---|---|---|---|---|
|1         |2020-06-14-00.00.00                        |2020-12-31-23.59.59                        |1                        |35455                |0                        |35.50            |EUR|
|1         |2020-06-14-15.00.00                        |2020-06-14-18.30.00                        |2                        |35455                |1                        |25.45            |EUR|
|1         |2020-06-15-00.00.00                        |2020-06-15-11.00.00                        |3                        |35455                |1                        |30.50            |EUR|
|1         |2020-06-15-16.00.00                        |2020-12-31-23.59.59                        |4                        |35455                |1                        |38.95            |EUR|

#### Campos:

**BRAND_ID**: foreign key de la cadena del grupo (1 = ZARA).

**START_DATE** , END_DATE: rango de fechas en el que aplica el precio tarifa indicado.

**PRICE_LIST**: Identificador de la tarifa de precios aplicable.

**PRODUCT_ID**: Identificador código de producto.

**PRIORITY**: Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico).

**PRICE**: precio final de venta.

**CURR**: iso de la moneda.

----
#### _Se pide:_

Construir una aplicación/servicio en SpringBoot que provea una end point rest de consulta  tal que:

* Acepte como parámetros de entrada: fecha de aplicación, identificador de producto, identificador de cadena.
Devuelva como datos de salida: identificador de producto, identificador de cadena, tarifa a aplicar, fechas de aplicación y precio final a aplicar.

* Se debe utilizar una base de datos en memoria (tipo h2) e inicializar con los datos del ejemplo, (se pueden cambiar el nombre de los campos y añadir otros nuevos si se quiere, elegir el tipo de dato que se considere adecuado para los mismos).

## Construcción

El proyecto hace uso de la metodología API First y tiene que ser construido antes de empezar a ejecutarse:

```
mvn clean install
```

La definición del endpoint REST así como de los datos utilizados y que son generados
se encuentran en el fichero *prices_rates.yaml* en la carpeta */src/main/resources*.


## Ejecución

Para ejecutar se tendrá que utilizar el siguiente comando:

```
mvn spring-boot:run
```

La aplicación se ejecutará en el puerto **8088**, si se necesita utilizar otro puerto, 
se puede modificar en el fichero *application.properties*:

```properties
server.port=8088
```

La aplicación hace uso de una base de datos en memoria (H2) que, para este ejemplo,
tiene la consola habilitada en la siguiente ruta:

**http://localhost:8088/h2-console**

El parámetro *JDBC URL* debe tener el siguiente valor: **jdbc:h2:mem:testdb**.

El usuario es **sa** y la contraseña es **password**.

La base de datos se inicializa con la información proporcionada en el fichero *data.sql*.

## Uso

Se han creado unos tests e2e para la comprobación del correcto funcionamiento del endpoint siguiendo los
siguientes casos de prueba:

          Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
          Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
          Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
          Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
          Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)

Para la realización de dichos tests se ha utilizado Postman.
Se ha dejado en la carpeta */src/main/resources* un fichero con las pruebas realizadas con Postman:

    Prices And Fees.postman_collection.json

Las llamadas al endpoint utilizando Postman necesitan fijar el valor del HOST y del PUERTO.