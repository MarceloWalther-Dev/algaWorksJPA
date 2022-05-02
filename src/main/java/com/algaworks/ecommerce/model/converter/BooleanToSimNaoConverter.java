package com.algaworks.ecommerce.model.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

//primeiro generic é da entidade e o segundo é do banco de dados
@Converter
public class BooleanToSimNaoConverter implements AttributeConverter<Boolean, String> {

    //converter de boolean para string, pois o banco aceita somente string e não boolean
    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return Boolean.TRUE.equals(attribute) ? "SIM" : "NAO";
    }

    //converter do que está vindo do banco de dados no caso a string sim ou nao para boolean
    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "SIM".equals(dbData)? Boolean.TRUE : Boolean.FALSE;
    }
}
