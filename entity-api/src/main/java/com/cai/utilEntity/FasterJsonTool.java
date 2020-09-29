package com.cai.utilEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 序列化
 */
public class FasterJsonTool {
    private static final ObjectMapper OBJECT_MAPPER = createObjectMapper();

    public FasterJsonTool() {
    }

    public static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new AfterburnerModule());
        objectMapper.configure( SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.setSerializationInclusion( JsonInclude.Include.NON_NULL);
        return objectMapper;
    }

    public static String writeListValueAsString(Collection<? extends Jsonable> jsonableList) {
        List<Map<String, Object>> list = new LinkedList ();
        Iterator var2 = jsonableList.iterator();

        while(var2.hasNext()) {
            Jsonable jsonable = (Jsonable)var2.next();
            list.add(jsonable.getMap4Json());
        }

        return writeValueAsString((Object)list);
    }

    public static String writeValueAsString(Jsonable jsonable) {
        return writeValueAsString((Object)jsonable.getMap4Json());
    }

    public static String writeValueAsString(Object obj) {
        if (obj instanceof Jsonable) {
            return writeValueAsString((Jsonable)obj);
        } else {
            try {
                return OBJECT_MAPPER.writeValueAsString(obj);
            } catch (IOException var2) {
                throw new IllegalArgumentException(var2);
            }
        }
    }

    public static <T> T readValue(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (IOException var3) {
            throw new IllegalArgumentException(var3);
        }
    }

    public static <T> T readValue(InputStream src, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.readValue(src, valueType);
        } catch (IOException var3) {
            throw new IllegalArgumentException(var3);
        }
    }

    public static ArrayList<Map<String, Object>> readValue2List(String json) {
        try {
            ArrayList<Map<String, Object>> list = (ArrayList)OBJECT_MAPPER.readValue(json, TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, Map.class));
            return list;
        } catch (IOException var3) {
            throw new IllegalArgumentException(var3);
        }
    }

    public static <T> ArrayList<T> readValue2List(String json, Class<T> clazz) {
        try {
            return (ArrayList)OBJECT_MAPPER.readValue(json, TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, clazz));
        } catch (IOException var3) {
            throw new IllegalArgumentException(var3);
        }
    }

    public static <T> T readValue(String json, TypeReference<T> typeReference) {
        try {
            return OBJECT_MAPPER.readValue(json, typeReference);
        } catch (IOException var3) {
            throw new IllegalArgumentException(var3);
        }
    }

    public static <T> ArrayList<T> readValue2List(String json, TypeReference<List<T>> typeReference) {
        try {
            return (ArrayList)OBJECT_MAPPER.readValue(json, typeReference);
        } catch (IOException var3) {
            throw new IllegalArgumentException(var3);
        }
    }

    public static <K, V> Map<K, V> readValue2Map(String json, Class<K> keyClazz, Class<V> valueClazz) {
        try {
            return (Map)OBJECT_MAPPER.readValue(json, TypeFactory.defaultInstance().constructMapType(Map.class, keyClazz, valueClazz));
        } catch (IOException var4) {
            throw new IllegalArgumentException(var4);
        }
    }

    public static <T, K> T readValue(String json, Class<T> clazz, Class<K> parameterClasses) {
        try {
            return OBJECT_MAPPER.readValue(json, TypeFactory.defaultInstance().constructParametrizedType(clazz, clazz, new Class[]{parameterClasses}));
        } catch (IOException var4) {
            throw new IllegalArgumentException(var4);
        }
    }

    public static <T, K, V> T readValue(String json, Class<T> clazz, Class<K> parametersFor, Class<V> parameterClasses) {
        try {
            return OBJECT_MAPPER.readValue(json, TypeFactory.defaultInstance().constructParametrizedType(clazz, parametersFor, new Class[]{parameterClasses}));
        } catch (IOException var5) {
            throw new IllegalArgumentException(var5);
        }
    }

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

    /** @deprecated */
    @Deprecated
    public ObjectMapper getMapper() {
        return OBJECT_MAPPER;
    }
}
