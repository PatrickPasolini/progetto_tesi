package it.unibs.main;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.*;

import it.unibs.domain.*;

/**
 * Questa classe fornisce metodi statici per caricare e salvare dati da e verso file JSON.
 * E' possibile caricare dati di persistenza o dati generici, e salvare dati in formato JSON.
 * Utilizza la libreria Gson per la serializzazione e deserializzazione dei dati.
 */
public abstract class JSONParser {
	
	/**
     * Carica dati di persistenza da un file JSON e restituisce un oggetto del tipo specificato come parametro 
     * @param filename Il percorso del file JSON da cui caricare i dati
     * @param dataType Il tipo di classe da cui deserializzare i dati
     * @return Un oggetto contenente i dati deserializzati, oppure null se si verifica un'eccezione
     * @since 1
     */
	public static Object loadPersistence(String filename, Class<?> dataType) {
		try {
			Gson gson = new GsonBuilder()
		                .registerTypeAdapter(Categoria.class, new CategoriaSerializer())
		                .registerTypeAdapter(Categoria.class, new CategoriaDeserializer())
		                .registerTypeAdapter(Utente.class, new UtenteSerializer())
		                .registerTypeAdapter(Utente.class, new UtenteDeserializer())
		                .create();
			
			String json = new String(Files.readAllBytes(Paths.get(filename)));
			
			return gson.fromJson(json, dataType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
     * Salva l'oggetto dato come parametro in formato JSON su un file
     * @param data L'oggetto da serializzare e salvare
     * @param filename Il percorso del file JSON su cui salvare i dati
     * @since 1
     */
	public static void saveDataToJson(Object data, String filename) {
		try {Gson gson = new GsonBuilder()
	                .setPrettyPrinting()
	                .registerTypeAdapter(Categoria.class, new CategoriaSerializer())
	                .registerTypeAdapter(Categoria.class, new CategoriaDeserializer())
	                .registerTypeAdapter(Utente.class, new UtenteSerializer())
	                .registerTypeAdapter(Utente.class, new UtenteDeserializer())
	                .enableComplexMapKeySerialization()
	                .create();
			
			String json = gson.toJson(data); 
			
			FileWriter writer = new FileWriter(filename);
			writer.write(json);
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

/**
 * Serializzatore per gli oggetti Utente e le sottoclassi Fruitore e Configuratore
 * Gestisce la serializzazione degli oggetti in formato JSON.
 * Aggiunge una proprieta' "type" con la Classe
 * @since 2
 */
class UtenteSerializer implements JsonSerializer<Utente> {
    @Override
    public JsonElement serialize(Utente src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.addProperty("type", src.getClass().getName());
        obj.add("data", context.serialize(src));
        return obj;
    }
}

/**
 * Deserializzazione per gli oggetti Utente e le sottoclassi Fruitore e Configuratore
 * Gestisce la deserializzazione degli oggetti da formato JSON.
 * @since 2
 */
class UtenteDeserializer implements JsonDeserializer<Utente> {
    @Override
    public Utente deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        JsonElement element = jsonObject.get("data");
        try {
            return context.deserialize(element, Class.forName(type));
        } catch (ClassNotFoundException cnfe) {
            throw new JsonParseException("Unknown element type: " + type, cnfe);
        }
    }
}

/**
 * Serializzatore per gli oggetti Cateogoria e le sottoclassi Foglia e NonFoglia
 * Gestisce la serializzazione degli oggetti in formato JSON.
 * Aggiunge una proprieta' "type" con la Classe
 * @since 2
 */
class CategoriaSerializer implements JsonSerializer<Categoria> {
    @Override
    public JsonElement serialize(Categoria src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.addProperty("type", src.getClass().getName());
        obj.add("data", context.serialize(src));
        return obj;
    } 
}

/**
 * Deserializzazione per gli oggetti Cateogoria e le sottoclassi Foglia e NonFoglia
 * Gestisce la deserializzazione degli oggetti da formato JSON.
 * @since 2
 */
class CategoriaDeserializer implements JsonDeserializer<Categoria> {
    @Override
    public Categoria deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        JsonElement element = jsonObject.get("data");
        try {
            return context.deserialize(element, Class.forName(type));
        } catch (ClassNotFoundException cnfe) {
            throw new JsonParseException("Unknown element type: " + type, cnfe);
        }
    }
}