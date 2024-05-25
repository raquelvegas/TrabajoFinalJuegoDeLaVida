package com.example.SaveInfo;

import com.example.EstructurasDeDatos.ElementoArbol;
import com.example.NewInterfaz.Individuos.Individuo;
import com.google.gson.*;

import java.lang.reflect.Type;

public class gsonAdapterElementoArbol implements JsonSerializer<ElementoArbol<Individuo>>, JsonDeserializer<ElementoArbol<Individuo>> {

    @Override
    public ElementoArbol<Individuo> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject elemento = json.getAsJsonObject();

        return null;
    }

    @Override
    public JsonElement serialize(ElementoArbol<Individuo> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject elemento = new JsonObject();
        elemento.addProperty("ID", src.getDato().getID());
        if (src.getNodoIzq() != null) {
            elemento.addProperty("IDIzq", src.getNodoIzq().getDato().getID());
        }
        if (src.getNodoDch() != null) {
            elemento.addProperty("IDDch", src.getNodoDch().getDato().getID());
        }

        return elemento;
    }
}
