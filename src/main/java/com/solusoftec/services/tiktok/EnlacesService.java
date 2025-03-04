package com.solusoftec.services.tiktok;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.BufferedWriter;



@Service
public class EnlacesService {

    // Ruta base para los archivos de configuración
    private final String baseConfigPath = "D:/Joeseep/web_scrap/Selenium_scrape/tiktok_scrape/";
    private static final String LIMITADOR_FILE = "D:/Joeseep/web_scrap/Selenium_scrape/tiktok_scrape/limitador.conf"; // Ruta del archivo limitador.conf
    // Método para obtener todos los elementos de una sección específica en un archivo
    public List<String> getAllItems(String configFileName, String sectionName, String keyName) throws IOException {
        String configFilePath = baseConfigPath + configFileName;
        List<String> lines = Files.readAllLines(Paths.get(configFilePath));

        // Validar existencia de la sección específica en el archivo
        boolean hasSection = lines.stream()
                .anyMatch(line -> line.trim().replaceAll("\\s+", "").equals("[" + sectionName + "]"));
        if (!hasSection) {
            throw new RuntimeException("No se encontró la sección [" + sectionName + "] en el archivo: " + configFileName);
        }

        // Obtener la línea con los elementos
        String itemsLine = lines.stream()
                .filter(line -> line.trim().startsWith(keyName + "="))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontró la línea " + keyName + " en el archivo"));

        if (itemsLine.contains("[") && itemsLine.contains("]")) {
            String itemsContent = itemsLine.substring(itemsLine.indexOf('[') + 1, itemsLine.lastIndexOf(']'));

            // Verificar si itemsContent no está vacío antes de procesarlo
            if (itemsContent.isBlank()) {
                return List.of();  // Retorna una lista vacía si no hay elementos
            }

            return List.of(itemsContent.split(","))
                    .stream()
                    .map(String::trim)
                    .map(s -> s.replaceAll("[\"']", "")) // Eliminar comillas dobles o simples
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException("Formato de la línea " + keyName + " no es válido: " + itemsLine);
        }
    }

    // Guardar la lista de elementos en el archivo
    private void saveItems(List<String> items, String configFileName, String sectionName, String keyName) throws IOException {
        String configFilePath = baseConfigPath + configFileName;
        List<String> lines = Files.readAllLines(Paths.get(configFilePath));
        List<String> updatedLines = lines.stream()
                .map(line -> {
                    if (line.trim().startsWith(keyName + "=")) {
                        // Reemplazar la línea de items con el nuevo contenido
                        return "  " + keyName + "=[" + items.stream().collect(Collectors.joining(", ")) + "]";
                    }
                    return line;
                })
                .collect(Collectors.toList());

        Files.write(Paths.get(configFilePath), updatedLines);
    }

    // Agregar un nuevo elemento
    public void addItem(String configFileName, String sectionName, String keyName, String item) throws IOException {
        List<String> items = getAllItems(configFileName, sectionName, keyName);
        items.add(item);
        saveItems(items, configFileName, sectionName, keyName);
    }

    // Modificar un elemento existente
    public void updateItem(String configFileName, String sectionName, String keyName, int index, String newItem) throws IOException {
        List<String> items = getAllItems(configFileName, sectionName, keyName);
        if (index < 0 || index >= items.size()) {
            throw new RuntimeException("Índice fuera de rango");
        }
        items.set(index, newItem);
        saveItems(items, configFileName, sectionName, keyName);
    }

    // Eliminar un elemento
    public void deleteItem(String configFileName, String sectionName, String keyName, int index) throws IOException {
        List<String> items = getAllItems(configFileName, sectionName, keyName);
        if (index < 0 || index >= items.size()) {
            throw new RuntimeException("Índice fuera de rango");
        }
        items.remove(index);
        saveItems(items, configFileName, sectionName, keyName);
    }

    public int getLimitador() throws IOException {
        // Leer el archivo y obtener el número
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(LIMITADOR_FILE))) {
            String line = reader.readLine().trim();
            return Integer.parseInt(line); // Retorna el número que está en el archivo
        } catch (IOException e) {
            throw new IOException("Error al leer el archivo " + LIMITADOR_FILE, e);
        }
    }

    // Método para actualizar el valor del limitador
    public void updateLimitador(int newLimitador) throws IOException {
        // Escribir el nuevo número en el archivo
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(LIMITADOR_FILE))) {
            writer.write(String.valueOf(newLimitador));
        } catch (IOException e) {
            throw new IOException("Error al escribir en el archivo " + LIMITADOR_FILE, e);
        }
    }
}
