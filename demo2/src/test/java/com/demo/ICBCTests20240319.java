package com.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ICBCTests20240319 {

    class Fruit {
        private FruitType fruitType;
        private BigDecimal weight;
        private BigDecimal totalPrice;
        private BigDecimal discount = BigDecimal.ONE;

        public Fruit() {
        }

        public Fruit(FruitType fruitType, BigDecimal weight) {
            this.fruitType = fruitType;
            this.weight = weight;
        }

        public Fruit(FruitType fruitType, BigDecimal weight, BigDecimal discount) {
            this.fruitType = fruitType;
            this.weight = weight;
            this.discount = discount;
        }

        public FruitType getFruitType() {
            return fruitType;
        }

        public void setFruitType(FruitType fruitType) {
            this.fruitType = fruitType;
        }

        public BigDecimal getWeight() {
            return weight;
        }

        public void setWeight(BigDecimal weight) {
            this.weight = weight;
        }

        public BigDecimal getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(BigDecimal totalPrice) {
            this.totalPrice = totalPrice;
        }

        public BigDecimal getDiscount() {
            return discount;
        }

        public void setDiscount(BigDecimal discount) {
            this.discount = discount;
        }
    }

    enum FruitType {
        APPLE("苹果", new BigDecimal("8")),
        STRAWBERRY("草莓", new BigDecimal("13")),
        MANGO("芒果", new BigDecimal("20"));

        private final String name;
        private final BigDecimal unitPrice;

        FruitType(String name, BigDecimal unitPrice) {
            this.name = name;
            this.unitPrice = unitPrice;
        }

        public String getName() {
            return name;
        }

        public BigDecimal getUnitPrice() {
            return unitPrice;
        }
    }

    BigDecimal priceCalculator(List<Fruit> fruitList) {
        if (fruitList.stream().anyMatch(fruit -> fruit.getWeight().compareTo(BigDecimal.ZERO) < 0)) {
            throw new RuntimeException("水果斤数为大于等于 0 的整数");
        }

        BigDecimal result = BigDecimal.ZERO;
        for (Fruit fruit : fruitList) {
            FruitType fruitType = fruit.getFruitType();
            BigDecimal unitPrice = fruitType.getUnitPrice();
            BigDecimal discount = fruit.getDiscount();
            BigDecimal weight = fruit.getWeight();

            BigDecimal totalPrice = unitPrice.multiply(discount).multiply(weight);
            result = result.add(totalPrice);
        }
        return result;

    }

    BigDecimal promotion(BigDecimal originPrice, BigDecimal reach, BigDecimal reduce) {
        if (originPrice.compareTo(reach) < 0) {
            return originPrice;
        }
        return originPrice.subtract(originPrice.divideAndRemainder(reach)[0].multiply(reduce));
    }

    /**
     * 1、有一家超市，出售苹果和草莓。其中苹果 8 元/斤，草莓 13 元/斤。
     * 现在顾客 A 在超市购买了若干斤苹果和草莓，需要计算一共多少钱？
     * 请编写函数，对于 A 购买的水果斤数 (水果斤数为大于等于 0 的整数)，计算并返回所购买商品的总价。
     */
    @Test
    void test1() {
        Fruit apple = new Fruit(FruitType.APPLE, new BigDecimal("10"));
        Fruit strawberry = new Fruit(FruitType.STRAWBERRY, new BigDecimal("10"));

        List<Fruit> fruitList = new ArrayList<>();
        fruitList.add(apple);
        fruitList.add(strawberry);
        BigDecimal result = priceCalculator(fruitList);

        System.out.println("result = " + result);
    }

    /**
     * 2、超市增加了一种水果芒果，其定价为 20 元/斤。
     * 现在顾客 B 在超市购买了若干斤苹果、 草莓和芒果，需计算一共需要多少钱？
     * 请编写函数，对于 B 购买的水果斤数 (水果斤数为大于等于 0 的整数)，计算并返回所购买商品的总价。
     */
    @Test
    void test2() {
        Fruit apple = new Fruit(FruitType.APPLE, new BigDecimal("10"));
        Fruit strawberry = new Fruit(FruitType.STRAWBERRY, new BigDecimal("10"));
        Fruit mango = new Fruit(FruitType.MANGO, new BigDecimal("10"));

        List<Fruit> fruitList = new ArrayList<>();
        fruitList.add(apple);
        fruitList.add(strawberry);
        fruitList.add(mango);
        BigDecimal result = priceCalculator(fruitList);

        System.out.println("result = " + result);
    }

    /**
     * 3、超市做促销活动，草莓限时打 8 折。
     * 现在顾客 C 在超市购买了若干斤苹果、 草莓和芒果，需计算一共需要多少钱？
     * 请编写函数，对于 C 购买的水果斤数 (水果斤数为大于等于 0 的整数)，计算并返回所购买商品的总价。
     */
    @Test
    void test3() {
        Fruit apple = new Fruit(FruitType.APPLE, new BigDecimal("10"));
        Fruit strawberry = new Fruit(FruitType.STRAWBERRY, new BigDecimal("10"), new BigDecimal("0.8"));
        Fruit mango = new Fruit(FruitType.MANGO, new BigDecimal("10"));

        List<Fruit> fruitList = new ArrayList<>();
        fruitList.add(apple);
        fruitList.add(strawberry);
        fruitList.add(mango);
        BigDecimal result = priceCalculator(fruitList);

        System.out.println("result = " + result);
    }

    /**
     * 4、促销活动效果明显，超市继续加大促销力度，购物满 100 减 10 块。
     * 现在顾客 D 在超市购买了若干斤苹果、 草莓和芒果，需计算一共需要多少钱？
     * 请编写函数，对于 D 购买的水果斤数 (水果斤数为大于等于 0 的整数)，计算并返回所购买商品的总价。
     */
    @Test
    void test4() {
        Fruit apple = new Fruit(FruitType.APPLE, new BigDecimal("10"));
        Fruit strawberry = new Fruit(FruitType.STRAWBERRY, new BigDecimal("10"), new BigDecimal("0.8"));
        Fruit mango = new Fruit(FruitType.MANGO, new BigDecimal("10"));

        List<Fruit> fruitList = new ArrayList<>();
        fruitList.add(apple);
        fruitList.add(strawberry);
        fruitList.add(mango);
        BigDecimal result = priceCalculator(fruitList);
        BigDecimal resultAfterPromotion = promotion(result, new BigDecimal("100"), new BigDecimal("10"));

        System.out.println("result = " + resultAfterPromotion);
    }
}

