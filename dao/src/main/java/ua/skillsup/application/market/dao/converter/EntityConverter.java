package ua.skillsup.application.market.dao.converter;


import ua.skillsup.application.market.api.model.GoodDto;
import ua.skillsup.application.market.dao.entity.Good;

public class EntityConverter {

    private EntityConverter() {
    }

    public static Good convert(GoodDto goodDto) {
        if (goodDto == null) {
            return null;
        }
        Good good = new Good();
        good.setId(goodDto.getId());
        good.setName(goodDto.getName());
        good.setPrice(goodDto.getPrice());
        return good;
    }

    public static GoodDto convert(Good good) {
        if (good == null) {
            return null;
        }
        GoodDto goodDto = new GoodDto();
        goodDto.setId(good.getId());
        goodDto.setName(good.getName());
        goodDto.setPrice(good.getPrice());
        return goodDto;
    }

}

