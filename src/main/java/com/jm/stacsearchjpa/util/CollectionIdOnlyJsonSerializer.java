package com.jm.stacsearchjpa.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jm.stacsearchjpa.model.Collection;

import java.io.IOException;

public class CollectionIdOnlyJsonSerializer  extends JsonSerializer<Collection> {
    @Override
    public void serialize(Collection collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(collection.getId());
    }
}
