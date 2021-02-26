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
            String linecount = brcount.readLine();
            if (linecount == null || linecount.equals("0")) {
                System.out.println("--Žádné příchozí zprávy--");
            } else {
                System.out.println("V databázi máš" + " " + linecount + " " + "zprávy:");
                System.out.println("------------------------");
                String nextline = br.readLine();
                while (nextline != null) {
                    System.out.println(nextline);
                    content += nextline;
                    nextline = br.readLine();
                }
                System.out.println("------------------------");
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter("conversations.txt"));
            BufferedWriter bwcount = new BufferedWriter(new FileWriter("count.txt"));
            System.out.println("Pro ukončení zápisu zpráv zadej `!konec`");
            while (true){
                String message = sc.nextLine();
                if (message.equals("!konec")) {
                    String conversion = Integer.toString(count);
                    bwcount.write(conversion);
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