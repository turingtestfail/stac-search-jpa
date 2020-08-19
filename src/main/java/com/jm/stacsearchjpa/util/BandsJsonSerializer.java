package com.jm.stacsearchjpa.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jm.stacsearchjpa.model.Band;

import java.io.IOException;
import java.util.Set;

public class BandsJsonSerializer extends JsonSerializer<Set<Band>> {
    @Override
    public void serialize(Set<Band> bands, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Band[] bandarray = new Band[bands.size()];
        bands.toArray(bandarray);
        Band[][]bandbox = {bandarray};
        jsonGenerator.writeObject(bandbox);
    }
}
