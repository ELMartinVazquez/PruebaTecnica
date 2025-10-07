package com.example;

import com.microsoft.playwright.*;
import java.util.List;
import java.util.Scanner;

public class MercadoLibreAutomation {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );
            Page page = browser.newPage();
            page.setDefaultTimeout(60000);

            System.out.println("Entrando a Mercado Libre...");
            page.navigate("https://www.mercadolibre.com/");

            // Seleccionar México como país
            System.out.println("Seleccionando México...");
            page.locator("a[href*='mercadolibre.com.mx']").click();

            // Buscar el término “playstation 5”
            page.waitForSelector("input[name='as_word']");
            page.locator("input[name='as_word']").fill("playstation 5");
            page.keyboard().press("Enter");

            // Esperar resultados
            page.waitForSelector("li.ui-search-layout__item");
            System.out.println("Resultados cargados correctamente.");

            // Filtrar por condición “Nuevos”
            Locator filtroNuevo = page.locator("a:has-text('Nuevo')");
            if (filtroNuevo.count() > 0) {
                filtroNuevo.first().click();
                page.waitForTimeout(3000);
                System.out.println("Filtro aplicado: Nuevos");
            } else {
                System.out.println("No se encontró el filtro de 'Nuevos'.");
            }

            // Filtrar por ubicación “CDMX”
            Locator filtroCDMX = page.locator("a:has-text('Ciudad de México'), a:has-text('CDMX'), a:has-text('Distrito Federal')");
            if (filtroCDMX.count() > 0) {
                filtroCDMX.first().click();
                page.waitForTimeout(3000);
                System.out.println("Filtro aplicado: CDMX");
            } else {
                System.out.println("No se encontró el filtro de CDMX.");
            }

            // Ordenar por “Mayor precio”
            page.waitForTimeout(4000);
            System.out.println("Intentando ordenar por 'Mayor precio'...");

            Locator botonOrdenar = page.locator("button:has-text('Ordenar'), span:has-text('Ordenar'), button:has-text('Más relevantes')");
            if (botonOrdenar.count() > 0) {
                botonOrdenar.first().click();
                page.waitForTimeout(1500);

                Locator opcionMayor = page.locator(
                        "a:has-text('Mayor precio'), " +
                                "a:has-text('Precio más alto'), " +
                                "a:has-text('mayor a menor'), " +
                                "li:has-text('Mayor precio')"
                );
                if (opcionMayor.count() > 0) {
                    opcionMayor.first().click();
                    System.out.println("Filtro aplicado: Mayor precio");
                    page.waitForTimeout(4000);
                } else {
                    System.out.println("No se encontró la opción 'Mayor precio'.");
                }
            } else {
                System.out.println("No se encontró el botón de ordenamiento.");
            }

            // Obtener nombres y precios de los primeros 5 productos
            page.waitForSelector("li.ui-search-layout__item");
            List<ElementHandle> productos = page.querySelectorAll("li.ui-search-layout__item");

            System.out.println("\n=== Primeros 5 productos ===");
            for (int i = 0; i < Math.min(5, productos.size()); i++) {
                ElementHandle producto = productos.get(i);

                // Capturar nombre con múltiples posibles selectores
                String nombre = "Sin nombre";
                ElementHandle nombreElem = producto.querySelector("h2, h3, a.ui-search-item__title, div.ui-search-item__title, span.ui-search-item__title");
                if (nombreElem != null) {
                    nombre = nombreElem.innerText().trim();
                }

                // Capturar precio
                String precio = "Sin precio";
                ElementHandle precioElem = producto.querySelector(".andes-money-amount__fraction, .ui-search-price__part, .ui-search-price__second-line span");
                if (precioElem != null) {
                    precio = precioElem.innerText().trim();
                }

                System.out.println((i + 1) + ". " + nombre + " - $" + precio);
            }

            // Mantener navegador abierto
            System.out.println("\nPresiona Enter para cerrar el navegador...");
            new Scanner(System.in).nextLine();

            browser.close();
        }
    }
}
