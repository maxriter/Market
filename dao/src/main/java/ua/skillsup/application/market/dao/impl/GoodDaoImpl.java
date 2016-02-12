package ua.skillsup.application.market.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.skillsup.application.market.api.exceptions.IncorrectPriceException;
import ua.skillsup.application.market.api.exceptions.NameAlreadyExistException;
import ua.skillsup.application.market.api.model.GoodDto;
import ua.skillsup.application.market.api.model.SearchFilter;
import ua.skillsup.application.market.dao.GoodDao;
import ua.skillsup.application.market.dao.entity.Good;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

import static ua.skillsup.application.market.dao.converter.EntityConverter.convert;

@Repository
@Transactional
public class GoodDaoImpl implements GoodDao {

    @PersistenceContext
    private EntityManager em;

    private List<GoodDto> convertGoodList(List<Good> goods) {
        List<GoodDto> result = new ArrayList<>();
        for (Good good : goods) {
            result.add(convert(good));
        }
        return result;
    }

    public List<GoodDto> getAll() {
        List list = em.createQuery("SELECT e FROM Good e").getResultList();
        return convertGoodList(list);
    }

    public GoodDto create(GoodDto goodDto) {
        if (goodDto.getPrice() < 0) throw new IncorrectPriceException("Price isn't correct");
        if (isGoodNameExist(goodDto.getName())) throw new NameAlreadyExistException("Name already exist");
        Good good = convert(goodDto);
        em.persist(good);
        em.flush();
        return convert(good);
    }

    public GoodDto get(Long id) {
        return convert(em.find(Good.class, id));
    }

    public GoodDto update(GoodDto goodDto) {
        if (goodDto.getPrice() < 0) throw new IncorrectPriceException("Price isn't correct");
        Good good = convert(goodDto);
        em.merge(good);
        em.flush();
        return convert(good);
    }

    public void delete(Long id) {
        Good good = em.find(Good.class, id);
        em.remove(good);
        em.flush();
    }

    public List<GoodDto> findByParam(SearchFilter filter) {
        if ((filter.getPriceFrom() < 0) || (filter.getPriceTo() < 0))
            throw new IncorrectPriceException("Price isn't correct");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Good> query = cb.createQuery(Good.class);
        Root<Good> goodRoot = query.from(Good.class);
        List<Predicate> predicates = new ArrayList<>();
        if (filter.getName() != null) {
            predicates.add(cb.like(goodRoot.get("name"), "%" + filter.getName() + "%"));
        }
        if (filter.getId() != null) {
            predicates.add(cb.equal(goodRoot.get("id"), filter.getId()));
        }
        if (filter.getPriceFrom() != 0) {
            predicates.add(cb.greaterThanOrEqualTo(goodRoot.get("price"), filter.getPriceFrom()));
        }
        if (filter.getPriceTo() != 0) {
            predicates.add(cb.lessThanOrEqualTo(goodRoot.get("price"), filter.getPriceTo()));
        }
        query.select(goodRoot)
                .where(predicates.toArray(new Predicate[]{}));
        List<Good> goods = em.createQuery(query).getResultList();
        return convertGoodList(goods);

    }

    public double getMinPrice() {
        List<Double> priceList = getPriceList();
        return Collections.min(priceList);
    }

    public double getMaxPrice() {
        List<Double> priceList = getPriceList();
        return Collections.max(priceList);
    }

    public double getAvgPrice() {
        List<Double> priceList = getPriceList();
        int sum = 0;
        for (double price : priceList) {
            sum += price;
        }
        return sum / priceList.size();
    }

    public int getGoodCount() {
        return getAll().size();
    }

    public List<Double> getPriceList() {
        List<Double> result = Arrays.asList((double) 0);
        if (getAll().isEmpty()) {
            return result;
        }
        return em.createQuery("SELECT price FROM Good").getResultList();
    }

    private boolean isGoodNameExist(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Good> query = cb.createQuery(Good.class);
        Root<Good> goodRoot = query.from(Good.class);
        query.select(goodRoot)
                .where(cb.like(goodRoot.get("name"), name));
        List<Good> result = em.createQuery(query).getResultList();
        return (result.size() != 0);
    }

}
