package ua.skillsup.application.market.api;


import ua.skillsup.application.market.api.model.GoodDto;
import ua.skillsup.application.market.api.model.SearchFilter;

import java.util.List;

public interface GoodService {

    List<GoodDto> getAll();

    GoodDto create(GoodDto goodDto);

    GoodDto get(Long id);

    GoodDto update(GoodDto goodDto);

    void delete(Long id);

    List<GoodDto> findByParam(SearchFilter filter);

    double getMinPrice();

    double getMaxPrice();

    double getAvgPrice();

    int getGoodCount();
}
