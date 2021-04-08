package com.nathaniel.rxharmony.reflex;

import com.google.gson.*;

import java.lang.reflect.Type;
/**
 * @author nathaniel
 * @version 1.0.0
 * @contact <a href="mailto:nathanwriting@126.com">contact me</a>
 */
public class DoubleDefaultAdapter implements JsonSerializer<Double>, JsonDeserializer<Double> {
    @Override
    public Double deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            if (json.getAsString().equals("") || json.getAsString().equals("null")) {
                //定义为double类型,如果后台返回""或者null,则返回0.00
                return 0.00;
            }
        } catch (Exception ignore) {
        }
        try {
            return json.getAsDouble();
        } catch (NumberFormatException e) {
            throw new JsonSyntaxException(e);
        }
    }

    @Override
    public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src);
    }
}
