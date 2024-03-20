import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] errorScores = new int[N];
        for (int i = 0; i < N; i++) {
            errorScores[i] = scanner.nextInt();
        }
        int P = scanner.nextInt();
        int Q = scanner.nextInt();

        int minProjects = minProjectsToZeroErrorScore(N, P, Q, errorScores);
        System.out.println(minProjects);
    }

    public static int minProjectsToZeroErrorScore(int N, int P, int Q, int[] errorScores) {
        int minProjects = 0;
        
        while (hasErrors(errorScores)) {
            minProjects++;
            int minErrorIndex = findMinErrorIndex(errorScores);
            errorScores[minErrorIndex] = Math.max(0, errorScores[minErrorIndex] - P);
            
            for (int i = 0; i < N; i++) {
                if (errorScores[i] > 0 && i != minErrorIndex) {
                    errorScores[i] = Math.max(0, errorScores[i] - Q);
                }
            }
        }
        
        return minProjects;
    }

    private static boolean hasErrors(int[] errorScores) {
        for (int errorScore : errorScores) {
            if (errorScore > 0) {
                return true;
            }
        }
        return false;
    }

    private static int findMinErrorIndex(int[] errorScores) {
        int minErrorIndex = 0;
        int minError = Integer.MAX_VALUE;
        
        for (int i = 0; i < errorScores.length; i++) {
            if (errorScores[i] < minError && errorScores[i] > 0) {
                minError = errorScores[i];
                minErrorIndex = i;
            }
        }
        
        return minErrorIndex;
    }
}