### Descripción del Sistema de Alquiler de Bicicletas

Estamos desarrollando un sistema de alquiler de bicicletas para una ciudad, diseñado para ser utilizado tanto por clientes registrados como por administradores del sistema. Este sistema tiene como objetivo facilitar el alquiler de bicicletas de manera eficiente y flexible, y se caracteriza por lo siguiente:

#### Funcionamiento General

1. **Registro de Clientes**: Solo los clientes registrados pueden alquilar bicicletas. Los clientes pueden alquilar bicicletas retirándolas de una estación y devolviéndolas en otra diferente.

2. **Disponibilidad**: Siempre hay bicicletas disponibles en las estaciones y espacio suficiente para las devoluciones, garantizando que los clientes siempre puedan alquilar y devolver bicicletas sin problemas.

3. **Cálculo del Costo de Alquiler**: 
   - El costo del alquiler se calcula al devolver la bicicleta. 
   - Incluye un costo fijo y un costo variable por hora (las fracciones de menos de 30 minutos se cobran por minuto).
   - Además, se añade un cargo por la distancia entre la estación de retiro y la de devolución.
   - En ciertos días promocionales, se aplican descuentos si el alquiler se inicia en esos días.

4. **Moneda de Pago**: Los clientes pueden ver el costo del alquiler en diferentes monedas, siendo la moneda predeterminada los Pesos Argentinos, pero se pueden elegir otras monedas al finalizar el alquiler.

#### Funcionalidades del Sistema

El sistema ofrece un API REST que permite realizar las siguientes operaciones:

1. **Consultar Estaciones**: Obtener una lista de todas las estaciones de bicicletas disponibles en la ciudad.

2. **Buscar Estación Más Cercana**: Encontrar la estación de bicicletas más cercana a una ubicación proporcionada por el cliente.

3. **Iniciar Alquiler**: Permitir que un cliente inicie el alquiler de una bicicleta desde una estación específica.

4. **Finalizar Alquiler**: Permitir que un cliente termine su alquiler, calculando y mostrando el costo en la moneda seleccionada.

5. **Agregar Estación**: Permitir a los administradores agregar nuevas estaciones al sistema.

6. **Listar Alquileres**: Obtener un listado de alquileres realizados, aplicando filtros según sea necesario.

#### Detalles Técnicos

- **API Gateway**: Todos los endpoints del sistema se exponen a través de un único punto de entrada, lo que centraliza la gestión de las solicitudes.
  
- **Autenticación y Roles**: 
  - **Administrador**: Puede agregar nuevas estaciones y obtener listados de alquileres.
  - **Cliente**: Puede consultar estaciones, iniciar y finalizar alquileres.

- **Fecha/Hora en Java**: Se utilizarán las clases del paquete `java.time` para manejar fechas y horas de manera eficiente.

- **Códigos de Respuesta HTTP**: Se implementarán correctamente los códigos de respuesta HTTP para reflejar el resultado de las operaciones.

- **Documentación**: Todos los endpoints estarán documentados utilizando Swagger para facilitar su uso y comprensión.

- **Cálculo de Distancia**: La distancia entre estaciones se calculará usando la distancia euclídea simplificada.

Este sistema proporcionará una solución completa y eficiente para el alquiler de bicicletas en la ciudad, facilitando tanto la operación diaria como la gestión administrativa.
