package com.expense_sharing.dto.responses.dtoConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DTOConverterFactory {
    private final Map<Class<?>, DTOConverter<?, ?>> converters = new HashMap<>();

    @Autowired
    public DTOConverterFactory(List<DTOConverter<?, ?>> converterList) {
        for (DTOConverter<?, ?> converter : converterList) {
            Class<?> entityType = (Class<?>) ((ParameterizedType) converter.getClass()
                    .getGenericInterfaces()[0]).getActualTypeArguments()[0];
            converters.put(entityType, converter);
        }
    }

    @SuppressWarnings("unchecked")
    public <E, D> D convertToDto(E entity) {
        DTOConverter<E, D> converter = (DTOConverter<E, D>) converters.get(entity.getClass());
        if (converter == null) {
            throw new IllegalArgumentException("No converter found for " + entity.getClass());
        }
        return converter.convert(entity);
    }

    @SuppressWarnings("unchecked")
    public <E, D> List<D> convertToDtoList(List<E> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        Class<?> entityClass = entities.getFirst().getClass();
        DTOConverter<E, D> converter = (DTOConverter<E, D>) converters.get(entityClass);
        if (converter == null) {
            throw new IllegalArgumentException("No converter found for " + entityClass);
        }

        return entities.stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }
}
