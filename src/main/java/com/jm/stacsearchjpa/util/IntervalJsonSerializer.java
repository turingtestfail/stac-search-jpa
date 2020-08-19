package com.jm.stacsearchjpa.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jm.stacsearchjpa.model.Interval;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class IntervalJsonSerializer extends JsonSerializer<Interval> {
    @Override
    public void serialize(Interval dateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Date[] inner = Arrays.asList(
                dateTime.getMin(),
                dateTime.getMax()).toArray(new Date[2]);
        Date[][]interval = {inner};
        Map out = new HashMap<String,Double[][]>();
        out.put("interval",interval);
        jsonGenerator.writeObject(out);
    }
}
