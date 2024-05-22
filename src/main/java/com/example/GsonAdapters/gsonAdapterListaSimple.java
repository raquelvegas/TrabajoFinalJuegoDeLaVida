package com.example.GsonAdapters;


import com.example.EstructurasDeDatos.Listas.ElementoLS;
import com.example.EstructurasDeDatos.Listas.ListaSimple;
import com.google.gson.*;

import java.lang.reflect.Type;

public class gsonAdapterListaSimple implements JsonSerializer<ListaSimple>, JsonDeserializer<ListaSimple> {

    @Override
    public JsonElement serialize(ListaSimple lista, Type type, JsonSerializationContext context) {
        JsonObject listaJson = new JsonObject();
        listaJson.addProperty("maximo", lista.getMaximo());
        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < lista.getNumeroElementos(); i++) {
            jsonArray.add(context.serialize(lista.getElemento(i)).getAsJsonObject());
        }
        listaJson.addProperty("datos",jsonArray.getAsString());
        listaJson.addProperty("numElem",lista.getNumeroElementos());
        return jsonArray;
    }

    @Override
    public ListaSimple deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        for (JsonElement elemento : jsonObject.get("datos").getAsJsonArray()){
            ElementoLS[]
        }

    }

}
