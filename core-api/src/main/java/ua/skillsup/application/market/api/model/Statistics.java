package ua.skillsup.application.market.api.model;


public class Statistics {

    private int goodCount;
    private double maxPrice;
    private double minPrice;
    private double avgPrice;

    public int getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(int goodCount) {
        this.goodCount = goodCount;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(double avgPrice) {
        this.avgPrice = avgPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Statistics stats = (Statistics) o;

        if (goodCount != stats.goodCount) return false;
        if (Double.compare(stats.maxPrice, maxPrice) != 0) return false;
        if (Double.compare(stats.minPrice, minPrice) != 0) return false;
        return Double.compare(stats.avgPrice, avgPrice) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = goodCount;
        temp = Double.doubleToLongBits(maxPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(minPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(avgPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "goodCount=" + goodCount +
                ", maxPrice=" + maxPrice +
                ", minPrice=" + minPrice +
                ", avgPrice=" + avgPrice +
                '}';
    }
}
