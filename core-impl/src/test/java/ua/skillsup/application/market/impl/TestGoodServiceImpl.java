package ua.skillsup.application.market.impl;

import com.google.common.collect.Iterables;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ua.skillsup.application.market.api.GoodService;
import ua.skillsup.application.market.api.model.GoodDto;
import ua.skillsup.application.market.api.model.SearchFilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

@ContextConfiguration({"classpath*:implApplicationContextTest.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class TestGoodServiceImpl {

    @Autowired
    private GoodService goodService;

    private List<GoodDto> initTestGoods;

    @Before
    public void init() {
        initTestGoods = initTestGoods();
    }

    @After
    public void destroy() {
        initTestGoods.clear();
    }

    private List<GoodDto> initTestGoods() {
        List<GoodDto> list = new ArrayList<>();
        GoodDto goodDto1 = new GoodDto();
        goodDto1.setId(1L);
        goodDto1.setName("Good1");
        goodDto1.setPrice(1);
        GoodDto goodDto2 = new GoodDto();
        goodDto2.setId(2L);
        goodDto2.setName("Good2");
        goodDto2.setPrice(2);
        GoodDto goodDto3 = new GoodDto();
        goodDto3.setId(3L);
        goodDto3.setName("Good3");
        goodDto3.setPrice(3);
        GoodDto goodDto4 = new GoodDto();
        goodDto4.setId(4L);
        goodDto4.setName("Good4");
        goodDto4.setPrice(4);
        GoodDto goodDto5 = new GoodDto();
        goodDto5.setId(5L);
        goodDto5.setName("Good5");
        goodDto5.setPrice(5);
        list.add(goodDto1);
        list.add(goodDto2);
        list.add(goodDto3);
        list.add(goodDto4);
        list.add(goodDto5);
        return list;
    }

    @Test
    @Transactional
    public void testGetAllGoods() {
        List<GoodDto> testGoods = goodService.getAll();
        Assert.assertEquals(initTestGoods.size(), testGoods.size());
        Collections.sort(initTestGoods);
        Collections.sort(testGoods);
        assertTrue(Iterables.elementsEqual(initTestGoods, testGoods));
    }

    @Test
    @Transactional
    public void testCreateGood() {
        GoodDto goodDto = new GoodDto();
        goodDto.setName("testName1");
        goodDto.setPrice(0.01);
        goodService.create(goodDto);
        Assert.assertEquals(goodService.getAll().size(), 6);
        GoodDto testDto = goodService.get(6L);
        Assert.assertEquals(testDto.getId(), new Long(6));
        Assert.assertEquals(testDto.getName(), "testName1");
        Assert.assertEquals(testDto.getPrice(), 0.01, 0);
    }

    @Test
    @Transactional
    public void testSearchGoodById() {
        GoodDto goodDto = goodService.get(1L);
        Assert.assertEquals(goodDto.getId(), new Long(1));
        Assert.assertEquals(goodDto.getName(), "Good1");
        Assert.assertEquals(goodDto.getPrice(), 1, 0);
    }

    @Test
    @Transactional
    public void testUpdateGood() {
        GoodDto goodDto = new GoodDto();
        goodDto.setName("updateName");
        goodDto.setPrice(0.03);
        goodDto.setId(1L);
        goodService.update(goodDto);
        GoodDto good = goodService.get(1L);
        Assert.assertEquals(goodDto, good);
    }

    @Test
    @Transactional
    public void testDeleteGood() {
        GoodDto goodDto = initTestGoods.get(4);
        goodService.delete(goodDto.getId());
        Assert.assertEquals(goodService.getAll().size(), 4);
    }

    @Test
    @Transactional
    public void testFindByParam() {

        SearchFilter generalFilter = new SearchFilter();
        generalFilter.setName("od");
        generalFilter.setPriceFrom(2);
        generalFilter.setPriceTo(4);
        List<GoodDto> generalTestList = goodService.findByParam(generalFilter);
        List<GoodDto> expectedList = new ArrayList<>();
        expectedList.add(initTestGoods.get(1));
        expectedList.add(initTestGoods.get(2));
        expectedList.add(initTestGoods.get(3));
        Collections.sort(generalTestList);
        Collections.sort(expectedList);
        assertTrue(Iterables.elementsEqual(generalTestList, expectedList));

        generalFilter.setId(2L);
        List<GoodDto> testListById = goodService.findByParam(generalFilter);
        Assert.assertEquals(initTestGoods.get(1), testListById.get(0));

        SearchFilter idFilter = new SearchFilter();
        idFilter.setId(4L);
        List<GoodDto> idTestList = goodService.findByParam(idFilter);
        Assert.assertEquals(idTestList.size(), 1);
        Assert.assertEquals(idTestList.get(0), initTestGoods.get(3));

        SearchFilter nameFilter = new SearchFilter();
        nameFilter.setName("test");
        List<GoodDto> nameTestList = goodService.findByParam(nameFilter);
        Assert.assertEquals(nameTestList.size(), 0);
    }

    @Test
    @Transactional
    public void testGetMinPrice() {
        double minPrice = goodService.getMinPrice();
        Assert.assertEquals(minPrice, 1, 0);
    }

    @Test
    @Transactional
    public void testGetMaxPrice() {
        double maxPrice = goodService.getMaxPrice();
        Assert.assertEquals(maxPrice, 5, 0);
    }

    @Test
    @Transactional
    public void testGetAvgPrice() {
        double avgPrice = goodService.getAvgPrice();
        Assert.assertEquals(avgPrice, 3, 0);
    }

    @Test
    @Transactional
    public void testGetGoodCount() {
        int goodCount = goodService.getGoodCount();
        Assert.assertEquals(goodCount, 5);
    }

}
