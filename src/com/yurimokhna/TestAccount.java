package com.yurimokhna;

public class TestAccount {
    public final static String WEIGHT_COEFFICIENT = "71371371371371371371371";

    public static void main(String[] args) {
        String valideBic = "049820000";
        String valideChekingAccount = "40302810198205000003";
        String nonValideChekingAccount = "11111111111111111111";
        System.out.println("Результат - " + validateCheckingAccount(valideBic, valideChekingAccount));
        System.out.println("Результат - " + validateCheckingAccount(valideBic, nonValideChekingAccount));

    }


    public static boolean validateCheckingAccount(String bic, String chekingAccount) {
        System.out.println("-----------------------------------");
        System.out.println("Получили на вход bic = " + bic);
        System.out.println("Получили на вход checkingAccount = " + chekingAccount);
        if (bic.length() != 9 || chekingAccount.length() != 20) {
            return false;
        }

        String RKC;
        if (bic.charAt(6) == '0' && bic.charAt(7) == '0') {
            RKC = "0" + bic.charAt(4) + bic.charAt(5);
        } else {
            RKC = bic.substring(bic.length() - 3);
        }

        String fullAccount = RKC + chekingAccount;

        int[] fullAccountArray = new int[fullAccount.length()];
        int[] weightCoefficientArray = new int[WEIGHT_COEFFICIENT.length()];
        for (int i = 0; i < fullAccount.length(); i++) {
            fullAccountArray[i] = Integer.parseInt(String.valueOf(fullAccount.charAt(i)));
        }
        for (int i = 0; i < WEIGHT_COEFFICIENT.length(); i++) {
            weightCoefficientArray[i] = Integer.parseInt(String.valueOf(WEIGHT_COEFFICIENT.charAt(i)));

        }
        int sum = 0;
        for (int i = 0; i < fullAccountArray.length; i++) {
            int temp = 0;
            System.out.print("Умножаем " + fullAccountArray[i] + " на " + weightCoefficientArray[i] + " ");
            System.out.print("Получаем произведение = " +  fullAccountArray[i] * weightCoefficientArray[i] + " ");
            temp = (fullAccountArray[i] * weightCoefficientArray[i]) % 10;
            System.out.print("и берем остаток от деления, равный " + temp + "\n");

            // System.out.print(temp + " ");
            sum += temp;

        }

        System.out.println("Получили сумму младших разрядов полученных произведений равную - " + sum);
        if ((sum % 10) == 0) {
            return true;
        } else {
            return false;
        }
    }

}

