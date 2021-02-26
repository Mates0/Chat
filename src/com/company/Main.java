package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int count = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Zadej jméno:");
        String jmeno = sc.nextLine();
        try {
            BufferedReader br = new BufferedReader(new FileReader("conversations.txt"));
            BufferedReader brcount = new BufferedReader(new FileReader("count.txt"));
            String content = "";
            String nextline = br.readLine();
            if (br.readLine() == null || br.readLine().equals("0")) {
                System.out.println("--Žádné příchozí zprávy--");
            } else {
                System.out.println("V databázi máš" + " " + brcount.readLine() + " " + "zprávy:");
                System.out.println("------------------------");
                while (nextline != null) { //* TODO
                    content += nextline;
                    nextline = br.readLine();
                    System.out.println(nextline);
                }
                System.out.println("------------------------");
                br.close(); //
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter("conversations.txt"));
            BufferedWriter bwcount = new BufferedWriter(new FileWriter("count.txt"));
            System.out.println("Pro ukončení zápisu zpráv zadej `!konec`");
            while (true){
                String message = sc.nextLine();
                if (message.equals("!konec")) {
                    String conversion = Integer.toString(count);
                    bwcount.write(conversion);
                    br.close();
                    brcount.close();
                    bw.close();
                    bwcount.close();
                    System.exit(0);
                }
                count++;
                bw.write(jmeno + ":" + " " + message);
                System.out.println(jmeno + ":" + " " + message);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("error");
        }
    }
}
