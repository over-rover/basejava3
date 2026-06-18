package webapp.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {
    static void main() {
        int[] nums = {1, 7, 7, 4, 3, 1, 2};

        System.out.println(minValue(nums));

        List<Integer> listNums = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(listNums);

        System.out.println(oddOrEven(listNums));
    }

    public static int minValue(int[] nums) {
        return Arrays.stream(nums)
                .distinct()
                .sorted()
                .reduce(0, (a, b) -> a * 10 + b);
    }

    public static List<Integer> oddOrEven(List<Integer> nums) {
        Integer sum = nums.stream().reduce(0, Integer::sum);

        return nums.stream().filter(n -> n % 2 != sum % 2)
                .collect(Collectors.toList());
    }
}