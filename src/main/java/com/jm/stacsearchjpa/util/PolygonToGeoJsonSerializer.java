package com.jm.stacsearchjpa.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.geotools.geojson.geom.GeometryJSON;
import org.locationtech.jts.geom.Polygon;
import org.wololo.geojson.GeoJSON;
import org.wololo.jts2geojson.GeoJSONWriter;

import java.io.IOException;
import java.io.StringWriter;

public class PolygonToGeoJsonSerializer extends JsonSerializer<Polygon> {
    @Override
    public void serialize(Polygon polygon, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        GeoJSONWriter writer = new GeoJSONWriter();
        GeoJSON json = writer.write(polygon);
        String jsonstring = json.toString();
        jsonGenerator.writeString(jsonstring);
    }
}
