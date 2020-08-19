package com.jm.stacsearchjpa.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.locationtech.jts.geom.Polygon;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BboxJsonSerializer extends JsonSerializer<Polygon> {

    @Override
    public void serialize(Polygon polygon, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Double[] inner = Arrays.asList(
                polygon.getEnvelopeInternal().getMinX(),
                polygon.getEnvelopeInternal().getMinY(),
                polygon.getEnvelopeInternal().getMaxX(),
                polygon.getEnvelopeInternal().getMaxY()).toArray(new Double[4]);
        Double[][]bbox = {inner};
        Map out = new HashMap<String,Double[][]>();
        out.put("bbox",bbox);
        jsonGenerator.writeObject(out);

    }
}
