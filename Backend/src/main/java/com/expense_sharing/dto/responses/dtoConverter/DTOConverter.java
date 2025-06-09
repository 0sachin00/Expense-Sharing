package com.expense_sharing.dto.responses.dtoConverter;

public interface DTOConverter<E, D> {
    D convert(E entity);
}
