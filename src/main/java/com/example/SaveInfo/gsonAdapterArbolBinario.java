package com.example.SaveInfo;

import com.example.EstructurasDeDatos.ArbolBinario;

import com.google.gson.*;

import java.lang.reflect.Type;

public class gsonAdapterArbolBinario implements JsonSerializer<ArbolBinario>, JsonDeserializer<ArbolBinario> {

    @Override
    public JsonElement serialize(ArbolBinario arbolBinario, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject arbolJson = new JsonObject();
        return arbolJson;
    }

    @Override
    public ArbolBinario deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return null;
    }
}
