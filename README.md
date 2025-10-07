# Prueba Técnica – Automatización Mercado Libre

Este proyecto automatiza una búsqueda en **Mercado Libre México** utilizando **Playwright con Java**.  
El script realiza los siguientes pasos:

🚀 Descripción del flujo
1. Entra al sitio web de Mercado Libre.  
2. Selecciona México como país.  
3. Busca el término **“PlayStation 5”**.  
4. Filtra por condición **“Nuevos”**.  
5. Filtra por ubicación **“CDMX”**.  
6. Ordena los resultados de **mayor a menor precio**.  
7. Obtiene el nombre y el precio de los primeros 5 productos.  
8. Muestra los resultados en la consola.  

---

Tecnologías usadas
- **Java 17**
- **Playwright 1.49.0**
- **Maven**
- **IntelliJ IDEA**

---

 Cómo ejecutar el proyecto

1. **Clonar el repositorio:**

   git clone https://github.com/ELMartinVazquez/PruebaTecnica.git
   
2. Abrir el proyecto en IntelliJ IDEA o cualquier IDE compatible con Maven.

3. Instalar las dependencias:
(El IDE las descargará automáticamente al abrir el pom.xml)

4. Ejecutar el proyecto:
   src/main/java/com/example/MercadoLibreAutomation.java

5. Luego presiona el botón (Run)

Resultado Esperado

Entrando a Mercado Libre...
Seleccionando México...
Resultados cargados correctamente.
Filtro aplicado: Nuevos
Filtro aplicado: CDMX
Filtro aplicado: Mayor precio

=== Primeros 5 productos ===
1. Consola Playstation 5 Standard - $12,000
2. Playstation 5 Slim - $8,700
3. Playstation 5 Standard Blanco - $8,500
4. Playstation 5 Standard - $7,500
5. Playstation 5 Standard 825gb Blanco - $7,500
