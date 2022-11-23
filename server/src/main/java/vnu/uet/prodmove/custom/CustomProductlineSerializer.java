package vnu.uet.prodmove.custom;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.entity.Productline;

public class CustomProductlineSerializer extends JsonSerializer<Productline>{

    @Override
    public void serialize(Productline productline, JsonGenerator gen, SerializerProvider provider) throws IOException {
        Map<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("id", productline.getId());
        jsonObject.put("brand", productline.getBrand());
        jsonObject.put("phone", productline.getPhone());

        gen.writeObject(jsonObject);
    }
}
