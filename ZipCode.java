/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ziptestdemo;
/**
 *
 * @author 2472557
 */
public class ZipCode {
    public int Zip;
    
    public ZipCode(int zip) {
        if (zip < 0 || zip > 99999) {
            System.out.println(zip + " zip code is more than 5 digits");
            this.Zip = zip;
        } else {
            this.Zip = zip;
        }
    }
    
    public ZipCode(String code) {
        this.Zip = parseBarCode(code);
    }
    
    public String GetBarCode() {
        if (Zip < 0 || Zip > 99999) {
            return "Invalid ZIP";
        }

        String zipStr = String.format("%05d", Zip);

        String barCode = "1";
        for (int i = 0; i < zipStr.length(); i++) {
            char digit = zipStr.charAt(i);

            switch (digit) {
                case '0': barCode += "11000"; 
                break;
                case '1': barCode += "00011"; 
                break;
                case '2': barCode += "00101"; 
                break;
                case '3': barCode += "00110"; 
                break;
                case '4': barCode += "01001"; 
                break;
                case '5': barCode += "01010"; 
                break;
                case '6': barCode += "01100"; 
                break;
                case '7': barCode += "10001"; 
                break;
                case '8': barCode += "10010"; 
                break;
                case '9': barCode += "10100"; 
                break;
                default:
                    System.out.println("Error: Invalid digit in ZIP.");
                    return "Invalid ZIP";
            }
        }
        barCode += "1";

        return barCode;
    }
    
     private int parseBarCode(String code) {
        if (code == null || code.length() != 27) {
            System.out.println("Error: bar code must be in multiples of 5-binary digits");
            return 0;
        }

        if (code.charAt(0) != '1' || code.charAt(code.length() - 1) != '1') {
            System.out.println("Error: bar code missing a 1 at start or end");
            return 0;
        }

        String middle = code.substring(1, code.length() - 1);

        String zipResult = "";
            int countOnes = 0;

        for (int i = 0; i < 25; i += 5) {
            String group = middle.substring(i, i + 5);
            for (int j = 0; j < group.length(); j++) {
                if (group.charAt(j) != '0' && group.charAt(j) != '1') {
                    System.out.println("bar code character: " + group.charAt(j) + " must be '0' or '1'");
                    
                }
            }
        
            for (int j = 0; j < group.length(); j++) {
                if (group.charAt(j) == '1') {
                    countOnes++;
                }
            }

            //if (countOnes != 2) {
              //  System.out.println("Error: Each group must have exactly 2 ones.");
              //  return 0;
            //}

            int[] weights = {7, 4, 2, 1, 0};
            int sum = 0;
            for (int j = 0; j < 5; j++) {
                if (group.charAt(j) == '1') {
                    sum += weights[j];
                }
            }

            if (sum == 11) {
                zipResult += "0";
            } else if (sum >= 0 && sum <= 9) {
                zipResult += sum;
            } else {
                System.out.println(group + " has invalid sequence in the bar code");
               // return 0;
            }
        }

        return Integer.parseInt(zipResult);
    }
    
}
