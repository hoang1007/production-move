package vnu.uet.prodmove.utils.converter.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vnu.uet.prodmove.entity.Productdetail;
import vnu.uet.prodmove.utils.productStage.BaseProductStage;

public class ProductStageConverter {
    public static Productdetail toProductDetail(BaseProductStage pStage) {
        try {
            String detail = new ObjectMapper().writeValueAsString(pStage);

            return new Productdetail(detail);
        } catch (JsonProcessingException e) {
            e.printStackTrace();

            return null;
        }
    }

    public static <T> T fromProductDetail(Productdetail productdetail, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(productdetail.getDetail(), clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();

            return null;
        }
    }
}