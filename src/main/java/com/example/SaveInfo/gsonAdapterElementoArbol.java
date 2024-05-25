package com.example.SaveInfo;

import com.example.EstructurasDeDatos.ElementoArbol;
import com.google.gson.*;

import java.lang.reflect.Type;

public class gsonAdapterElementoArbol implements JsonSerializer<ElementoArbol<Integer>>, JsonDeserializer<ElementoArbol<Integer>> {

    @Override
    public ElementoArbol<Integer> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject elemento = json.getAsJsonObject();
        ElementoArbol<Integer> elementoDevolver = new ElementoArbol<>();
        if (elemento.has("IDIzq")) {
            elementoDevolver.setNodoIzq(new ElementoArbol<>(context.deserialize(elemento.get("IDIzq"), Integer.class)));
        }
        if (elemento.has("IDDch")) {
            elementoDevolver.setNodoDch(new ElementoArbol<>(context.deserialize(elemento.get("IDDch"), Integer.class)));
        }
        elementoDevolver.setDato(context.deserialize(elemento.get("ID"), Integer.class));
        return elementoDevolver;
    }

    @Override
    public JsonElement serialize(ElementoArbol<Integer> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject elemento = new JsonObject();
        elemento.addProperty("ID", src.getDato());
        if (src.getNodoIzq() != null) {
            elemento.addProperty("IDIzq", src.getNodoIzq().getDato());
        }
        if (src.getNodoDch() != null) {
            elemento.addProperty("IDDch", src.getNodoDch().getDato());
        }

        return elemento;
    }
}
