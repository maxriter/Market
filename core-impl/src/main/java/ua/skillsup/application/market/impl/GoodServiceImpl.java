package ua.skillsup.application.market.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.skillsup.application.market.api.GoodService;
import ua.skillsup.application.market.api.model.GoodDto;
import ua.skillsup.application.market.api.model.SearchFilter;
import ua.skillsup.application.market.dao.GoodDao;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GoodServiceImpl implements GoodService {

    @Autowired
    GoodDao goodDao;

    public List<GoodDto> getAll() {
        return goodDao.getAll();
    }

    public GoodDto create(GoodDto goodDto) {
        return goodDao.create(goodDto);
    }

    public GoodDto get(Long id) {
        return goodDao.get(id);
    }

    public GoodDto update(GoodDto goodDto) {
        return goodDao.update(goodDto);
    }

    public void delete(Long id) {
        goodDao.delete(id);
    }

    public List<GoodDto> findByParam(SearchFilter filter) {
        return goodDao.findByParam(filter);
    }

    public double getMinPrice() {
        return goodDao.getMinPrice();
    }

    public double getMaxPrice() {
        return goodDao.getMaxPrice();
    }

    public double getAvgPrice() {
        return goodDao.getAvgPrice();
    }

    public int getGoodCount() {
        return goodDao.getGoodCount();
    }


}
