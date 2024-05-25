package com.example.SaveInfo;

import com.example.EstructurasDeDatos.ArbolBinario;

import com.example.EstructurasDeDatos.ElementoArbol;
import com.google.gson.*;

import java.lang.reflect.Type;

public class gsonAdapterArbolBinario implements JsonSerializer<ArbolBinario>, JsonDeserializer<ArbolBinario> {

    @Override
    public JsonElement serialize(ArbolBinario arbolBinario, Type type, JsonSerializationContext jsonSerializationContext) {
        return jsonSerializationContext.serialize(arbolBinario.getRaiz());
    }

    @Override
    public ArbolBinario deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        ArbolBinario<?> arbol = new ArbolBinario<>();
        arbol.setRaizEl(jsonDeserializationContext.deserialize(jsonElement, ElementoArbol.class));
        return arbol;
    }
}
