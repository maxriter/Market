package ua.skillsup.application.market.api.model;


public class GoodDto implements Comparable {

    private Long id;
    private String name;
    private double price;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodDto goodDto = (GoodDto) o;

        if (Double.compare(goodDto.price, price) != 0) return false;
        if (id != null ? !id.equals(goodDto.id) : goodDto.id != null) return false;
        return name != null ? name.equals(goodDto.name) : goodDto.name == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "GoodDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        GoodDto goodDto = (GoodDto) o;
        return goodDto.getId() < getId() ? -1 : goodDto.getId() == getId() ? 0 : 1;
    }
}

