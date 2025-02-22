import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class task4 {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Требуется аргумент");
            return;
        }
        File file = new File(args[0]);

        ArrayList<Integer> nums = new ArrayList<Integer>();

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                nums.add(Integer.parseInt(sc.nextLine()));
            }
        }
        catch (Exception e) {
            System.out.println(e + "Файл не найден");
            e.printStackTrace();
        }

        Collections.sort(nums);
        int count = 0;
        int median;

        if (nums.size() % 2 != 0) {
            median = nums.get(nums.size() / 2);
        } else {
            median = (nums.get(nums.size() / 2 - 1) + nums.get(nums.size() / 2)) / 2;
        }

        for (int i = 0; i < nums.size(); i++) {
            count += Math.abs(nums.get(i) - median);
        }

        System.out.println(count);
    }
}
