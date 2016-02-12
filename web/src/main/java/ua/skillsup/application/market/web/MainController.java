package ua.skillsup.application.market.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import ua.skillsup.application.market.api.GoodService;
import ua.skillsup.application.market.api.model.GoodDto;
import ua.skillsup.application.market.api.model.ResponseMessage;
import ua.skillsup.application.market.api.model.SearchFilter;
import ua.skillsup.application.market.api.model.Statistics;

import java.util.*;

@Controller
public class MainController {

    @Autowired
    GoodService goodService;

    @ResponseBody
    @RequestMapping(value = "/good", method = RequestMethod.POST)
    public GoodDto addGood(@RequestBody GoodDto goodDto) {
        return goodService.create(goodDto);
    }

    @ResponseBody
    @RequestMapping(value = "/good", method = RequestMethod.PUT)
    public GoodDto updateGood(@RequestBody GoodDto goodDto) {
        return goodService.update(goodDto);
    }

    @ResponseBody
    @RequestMapping(value = "/good", method = RequestMethod.DELETE)
    public Long delete(@RequestBody Long id) {
        goodService.delete(id);
        return id;
    }

    @ResponseBody
    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public List<GoodDto> searchByFilter(@RequestBody SearchFilter filter) {
        List<GoodDto> res = goodService.findByParam(filter);
        Collections.sort(res);
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/allGoods", method = RequestMethod.GET)
    public List<GoodDto> findAllGoods() {
        List<GoodDto> res = goodService.getAll();
        Collections.sort(res);
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public Statistics updateStatistics() {
        Statistics statistics = new Statistics();
        statistics.setMaxPrice(goodService.getMaxPrice());
        statistics.setMinPrice(goodService.getMinPrice());
        statistics.setAvgPrice(goodService.getAvgPrice());
        statistics.setGoodCount(goodService.getGoodCount());
        return statistics;
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseMessage handleException(Exception e) {
        return ResponseMessage.errorMessage(e.getMessage());
    }

}
