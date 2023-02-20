package common;

import java.util.ArrayList;
import java.util.List;

public class CarFilter {
    // 사용자 정의 Predicate 사용
    public static List<Car> filterCarByCustomPredicate(List<Car> cars, CarPredicate p) {
        List<Car> resultCars = new ArrayList<>();
        for(Car car : cars)
            if (p.test(car)) {
                resultCars.add(car);
            }

        return resultCars;
    }
}
