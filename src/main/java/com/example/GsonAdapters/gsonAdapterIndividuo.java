package com.example.GsonAdapters;


import com.example.NewInterfaz.Individuos.Individuo;
import com.google.gson.*;

import java.lang.reflect.Type;

public class gsonAdapterIndividuo implements JsonSerializer<Individuo>, JsonDeserializer<Individuo> {

    @Override
    public JsonElement serialize(Individuo ind, Type type, JsonSerializationContext context) {
        JsonObject individuo = new JsonObject();

    }

    @Override
    public Individuo deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return null;
    }

}
