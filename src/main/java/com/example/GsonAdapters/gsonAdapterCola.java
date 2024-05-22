package com.example.GsonAdapters;

import com.example.EstructurasDeDatos.Cola;
import com.example.EstructurasDeDatos.Listas.ListaEnlazada;
import com.example.NewInterfaz.ControllerIndividuoPropiedades;
import com.google.gson.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Type;


public class gsonAdapterCola implements JsonSerializer<Cola>, JsonDeserializer<Cola> {

    private static final Logger log = LogManager.getLogger(gsonAdapterCola.class);

    @Override
    public Cola deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        Cola<?> cola = new Cola<>();
        cola.setDatos(context.deserialize(jsonElement, ListaEnlazada.class));
        log.trace("Deserialización de la cola");
        return cola;
    }

    @Override
    public JsonElement serialize(Cola cola, Type type, JsonSerializationContext jsonSerializationContext) {
        log.trace("Serialización de la cola");
        return jsonSerializationContext.serialize(cola.getDatos());
    }
}
