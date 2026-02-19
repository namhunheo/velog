// 배열의 반복 횟수 줄이기 예시
// - 입력된 배열의 요소 중 소수 찾는 문제



// 소수 찾기 (1 제외, 2이상 중 자기 자신만 나눠지는 수)
// 결과 저장할 리스트, arr 범위 반복, isPrime 판단, 맞으면 리스트 추가
//public static void main(String[] args) {
//    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//    List<Integer> result = new ArrayList<>();
//
//    for (i=0; i < arr.length; i++){
//        boolean isPrime = true;
//        for (int j=2; j < arr[i]; j++) {
//            if (arr[i] % j == 0) {
//                isPrime = false;
//                break;
//            }
//        }
//        if (arr[i] != 1 && isPrime)
//            result.add(arr[i]);
//    }
//    System.out.println(result);
//}

//////////////////////////////////////
// 백준 11720번 : 숫자의 합

import java.util.Scanner;

public class Sum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        String numbers = sc.next();
        int sum = 0;

        for (int i=0; i < N; i++) {
            sum += numbers.chatAt(i) - '0';
        }
        System.out.println(sum);
    }
}
