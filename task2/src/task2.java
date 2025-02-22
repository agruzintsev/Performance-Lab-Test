import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Требуется 2 аргумента");
            return;
        }

        File fileCircle = new File(args[0]);
        File filePoints = new File(args[1]);

        List<double[]> coordinates = new ArrayList<double[]>();

        double circleX = 0;
        double circleY = 0;
        double circleR = 0;

        try {
            Scanner sc = new Scanner(fileCircle);

            circleX = sc.nextDouble();
            circleY = sc.nextDouble();
            circleR = sc.nextDouble();

            sc.close();
        }
        catch(Exception e){
            System.out.println("Файл не найден");
            e.printStackTrace();
        }

        try {
            Scanner sc = new Scanner(filePoints);

            while(sc.hasNext()){
                coordinates.add(new double[]{sc.nextDouble(), sc.nextDouble()});
            }
        }
        catch(Exception e){
            System.out.println("Файл не найден");
            e.printStackTrace();
        }

        for (int i = 0; i < coordinates.size(); i++) {
            double x = coordinates.get(i)[0];
            double y = coordinates.get(i)[1];

            int result = checkPoint(x, y, circleX, circleY, circleR);

            System.out.println(result);
        }
    }

    public static int checkPoint (double x, double y, double circleX, double circleY, double circleR) {
        double result = Math.pow(x-circleX, 2) + Math.pow(y-circleY, 2);
        if (result > Math.pow(circleR, 2)) {
            return 2;
        } else if (result < Math.pow(circleR, 2)) {
            return 1;
        } else {
            return 0;
        }
    }
}

