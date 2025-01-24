package ir.sayad.lib.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

public class JsonUtil {

    static ObjectMapper mapper = new ObjectMapper();

    public static String toString(Object value) {
        try {
            return getMapper().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            Logger.error("JsonProcessingException", e.getMessage());
            return "";
        }
    }

    private static ObjectMapper getMapper() {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
        return mapper;
    }

    public static <T> T plainToClass(String value, Class<T> valueType) {
        try {
            return getMapper().readValue(value, valueType);
        } catch (IOException e) {
            Logger.error("JsonParseException", e.getMessage());
            return null;
        }
    }

    public static <T, C extends Collection<T>> C plainToClass(Object value, Class<C> collectionClass, Class<T> valueType) {
        try {
            return getMapper().convertValue(value, getMapper().getTypeFactory().constructCollectionType(collectionClass, valueType));
        } catch (Exception e) {
            Logger.error("JsonParseException", e.getMessage());
            return null;
        }
    }


    public static <T, C extends Collection<T>> C plainToClass(String value, Class<C> collectionClass, Class<T> valueType) {
        try {
            return getMapper().readValue(value, getMapper().getTypeFactory().constructCollectionType(collectionClass, valueType));
        } catch (IOException e) {
            Logger.error("JsonParseException", e.getMessage());
            return null;
        }
    }

    public static <T> T plainToClass(Object value, Class<T> valueType) {
        try {
            return getMapper().convertValue(value, valueType);
        } catch (Exception e) {
            Logger.error("JsonParseException", e.getMessage());
            return null;
        }
    }

    public static <T> T plainToClass(Object value, TypeReference<T> valueTypeRef) {
        try {
            return getMapper().convertValue(value, valueTypeRef);
        } catch (Exception e) {
            Logger.error("JsonParseException", e.getMessage());
            return null;
        }
    }

    public static <T> T plainToClass(String value, TypeReference<T> valueTypeRef) {
        try {
            return getMapper().readValue(value, valueTypeRef);
        } catch (IOException e) {
            Logger.error("JsonParseException", e.getMessage());
            return null;
        }
    }

    public static <T> T plainToClass(String value, JavaType javaType) {
        try {
            return getMapper().readValue(value, javaType);
        } catch (IOException e) {
            Logger.error("JsonParseException", e.getMessage());
            return null;
        }
    }

    public static <T, C extends Collection<T>> C plainToClass(String value, Class<C> collectionClass, JavaType javaType) {
        try {
            return getMapper().readValue(value, getMapper().getTypeFactory().constructCollectionType(collectionClass, javaType));
        } catch (IOException e) {
            Logger.error("JsonParseException", e.getMessage());
            return null;
        }
    }

    public static <T, C extends Collection<T>> C plainToClass(Object value, Class<C> collectionClass, JavaType javaType) {
        try {
            return getMapper().convertValue(value, getMapper().getTypeFactory().constructCollectionType(collectionClass, javaType));
        } catch (Exception e) {
            Logger.error("JsonParseException", e.getMessage());
            return null;
        }
    }
}