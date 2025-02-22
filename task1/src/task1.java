import java.util.ArrayList;

public class task1 {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Требуется 2 аргумента");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        Circular test = new Circular();
        ArrayList<Integer> result = test.circularArray(n, m);

        System.out.println(result);
    }
}

class Circular{
    public ArrayList<Integer> circularArray(int n, int m) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }

        ArrayList<Integer> resultArray = new ArrayList<>();
        int currentIndex = 0;

        do {
            resultArray.add(arr[currentIndex]);
            currentIndex = (currentIndex + m - 1) % n;
        } while (currentIndex != 0);

        return resultArray;
    }
}