package com.example.SaveInfo;

import com.example.NewInterfaz.Grafo_Conocimiento.Accion;
import com.google.gson.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Type;

public class gsonAdapterAccion implements JsonSerializer<Accion>, JsonDeserializer<Accion> {

    private static final Logger log = LogManager.getLogger(gsonAdapterAccion.class);

    @Override
    public JsonElement serialize(Accion src, Type typeOfSrc, JsonSerializationContext context) {
        log.info("Serializando Accion");
        JsonObject accion = new JsonObject();
        accion.addProperty("Tipo", src.getTipo());
//        accion.add("Data", context.serialize(src));
        return accion;
    }

    @Override
    public Accion deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }
}
