package com.example.SaveInfo;


import com.example.NewInterfaz.Individuos.IndAvanzado;
import com.example.NewInterfaz.Individuos.IndBasico;
import com.example.NewInterfaz.Individuos.IndNormal;
import com.example.NewInterfaz.Individuos.Individuo;
import com.google.gson.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Type;

public class gsonAdapterIndividuo implements JsonSerializer<Individuo>, JsonDeserializer<Individuo> {

    private static final Logger log = LogManager.getLogger(gsonAdapterIndividuo.class);

    @Override
    public JsonElement serialize(Individuo ind, Type type, JsonSerializationContext context) {
        log.info("Serializando individuo " + ind.getID() + " a json.");
        JsonObject individuo = new JsonObject();
        individuo.addProperty("Tipo", ind.getTipo());
        individuo.add("Data", context.serialize(ind));
        return individuo;
    }

    @Override
    public Individuo deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        log.info("Deserializando de json a individuo.");
        JsonObject indNuevo = jsonElement.getAsJsonObject();
        if (indNuevo.get("Tipo").getAsString().contains("0")) {
            return jsonDeserializationContext.deserialize(indNuevo.get("Data"), IndBasico.class);
        } else if (indNuevo.get("Tipo").getAsString().contains("1")) {
            return jsonDeserializationContext.deserialize(indNuevo.get("Data"), IndNormal.class);
        } else if (indNuevo.get("Tipo").getAsString().contains("2")) {
            return jsonDeserializationContext.deserialize(indNuevo.get("Data"), IndAvanzado.class);
        } else {
            log.fatal("Error en la lectura del tipo de individuo");
            throw new JsonParseException("El tipo de individuo no ha sido correctamente identificado al deserializarlo");
        }
    }

}
