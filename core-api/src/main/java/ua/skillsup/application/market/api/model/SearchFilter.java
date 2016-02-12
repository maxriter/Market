package ua.skillsup.application.market.api.model;

public class SearchFilter {
    Long id;
    String name;
    double priceFrom;
    double priceTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(double priceFrom) {
        this.priceFrom = priceFrom;
    }

    public double getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(double priceTo) {
        this.priceTo = priceTo;
    }

    @Override
    public String toString() {
        return "SearchFilter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", priceFrom=" + priceFrom +
                ", priceTo=" + priceTo +
                '}';
    }
}

