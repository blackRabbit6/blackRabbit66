package Java1;

import java.util.Scanner;

public class ManagerMenu {
    public void display(){
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("관리자 메뉴");
            System.out.println("1. 제품추가");
            System.out.println("2. 제품초기화");
            System.out.println("3. 종료");
            System.out.print("원하는 기능 입력: ");
            int functions = sc.nextInt();

            if (functions == 1) {
                Productinfo productinfo = new Productinfo();
                productinfo.addProduct();
                break;
            } else if (functions == 2) {
                ProductInfoDel productInfoDel = new ProductInfoDel();
                productInfoDel.delProduct();
                break;
            } else if (functions == 3) {
                System.out.println("프로그램 종료");
                break;
            } else {
                System.out.println("잘못된 입력입니다. 다시 입력하세요");
            }
        }
    }
}
