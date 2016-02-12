package ua.skillsup.application.market.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "GOOD")
public class Good {

    @Id
    @Column(name = "GOOD_ID")
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
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

        Good good = (Good) o;

        if (Double.compare(good.price, price) != 0) return false;
        return name != null ? name.equals(good.name) : good.name == null;

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
        return "Good{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
