package com.baratex.api.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

import java.io.IOException;

public class PointDeserializer extends JsonDeserializer<Point> {

    private final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

    @Override
    public Point deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        if (node != null && node.has("coordinates")) {
            JsonNode coords = node.get("coordinates");
            double lng = coords.get(0).asDouble();
            double lat = coords.get(1).asDouble();
            return geometryFactory.createPoint(new Coordinate(lng, lat));
        }
        return null;
    }
}