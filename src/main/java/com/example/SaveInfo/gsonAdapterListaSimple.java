package com.example.SaveInfo;

import com.example.EstructurasDeDatos.Listas.ElementoLS;
import com.example.EstructurasDeDatos.Listas.ListaSimple;
import com.google.gson.*;

import java.lang.reflect.Type;

public class gsonAdapterListaSimple implements JsonSerializer<ListaSimple>, JsonDeserializer<ListaSimple> {

    @Override
    public ListaSimple deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        int i = 0;
        Integer maximo = context.deserialize(jsonObject.get("maximo"), Integer.class);
        Integer numElem = context.deserialize(jsonObject.get(" numElem"),Integer.class);
        ElementoLS[] arrayEementos = new ElementoLS[maximo];
        for (int j = 0; j < maximo; j++) {
            arrayEementos[j]=context.deserialize(jsonObject.get("datos").getAsJsonArray().get(j),ElementoLS.class);
        }
        ListaSimple listaDevolver = new ListaSimple(maximo);
        listaDevolver.setDatos(arrayEementos);
        listaDevolver.setNumElem(numElem);
        return listaDevolver;
    }

    @Override
    public JsonElement serialize(ListaSimple src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject listaJson = new JsonObject();
        listaJson.addProperty("maximo", src.getMaximo());
        listaJson.addProperty("numElem",src.getNumeroElementos());
        JsonArray jsonArray = new JsonArray();
        for (ElementoLS elem : src.getDatos()){
            jsonArray.add(context.serialize(elem).getAsJsonObject());
        }
        listaJson.addProperty("datos",jsonArray.getAsString());
        return jsonArray;
    }
}
