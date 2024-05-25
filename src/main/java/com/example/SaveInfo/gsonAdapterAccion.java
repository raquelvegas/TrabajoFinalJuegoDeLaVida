package com.example.SaveInfo;

import com.example.NewInterfaz.Grafo_Conocimiento.Acción;
import com.google.gson.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Type;

public class gsonAdapterAccion implements JsonSerializer<Acción>, JsonDeserializer<Acción> {

    private static final Logger log = LogManager.getLogger(gsonAdapterAccion.class);

    @Override
    public JsonElement serialize(Acción src, Type typeOfSrc, JsonSerializationContext context) {
        log.info("Serializando Acción");
        JsonObject accion = new JsonObject();
        accion.addProperty("Tipo", src.getTipo());
//        accion.add("Data", context.serialize(src));
        return accion;
    }

    @Override
    public Acción deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }
}
