package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class NumberLib {

    private String result = "";

    private final int TEN = 10;
    private final int HUNDRED = TEN * TEN;
    private final int THOUSAND = TEN * HUNDRED;
    private final int MILLION = THOUSAND * THOUSAND;
    private final long BILLION = MILLION * THOUSAND;
    private double TRILLION = 1000000000000L;

    private String[] units =

            {"",
                    " Один",
                    " Два",
                    " Три",
                    " Четыре",
                    " Пять",
                    " Шесть",
                    " Семь",
                    " Восемь",
                    " Девять"
            };
    private String[] teen =
            {" Десять",
                    " Одиннадцать",
                    " Двенадцать",
                    " Тринадцать",
                    " Четырнадцать",
                    " Пятнадцать",
                    " Шестнадцать",
                    " Семнадцать",
                    " Восемнадцать",
                    " Девятнадцать"
            };
    private String[] tens =
            {"", "",
                    " Двадцать",
                    " Тридцать",
                    " Сорок",
                    " Пятьдесят",
                    " Шестьдесят",
                    " Семьдесят",
                    " Восемьдесят",
                    " Девяносто"
            };
    private String[] hundred =
            {"",
                    " Сто",
                    " Двести",
                    " Триста",
                    " Четыреста",
                    " Пятьсот",
                    " Шестьсот",
                    " Семьсот",
                    " Восемьсот",
                    " Девятьсот",
            };
    private String[] thousand =
            {" Тысяча",
                    " Тысячи",
                    " Тысяч",
                    ""
            };
    private String[] million =
            {" Миллион",
                    " Миллиона",
                    " Миллионов",
                    ""
            };
    private String[] billion =
            {" Миллиард",
                    " Миллиарда",
                    " Миллиардов",
                    ""
            };
    private String[] trillion =
            {" Триллион",
                    " Триллиона",
                    " Триллионов",
                    ""
            };

    boolean isNumber(String s) {
        String regex = "\\d+";
        return s.matches(regex);
    }

    void extractTrillion(long num) {
        long trill = (long) (num / TRILLION);
        extractHundred((int) trill);
        result += trillion[extractMod((int) trill)];
        extractBillion((long) (num % TRILLION));
    }

    void extractBillion(long num) {
        int bill = (int) (num / BILLION);
        extractHundred(bill);
        result += billion[extractMod(bill)];
        extractMillion((int) (num % BILLION));
    }

    void extractMillion(int num) {
        int mill = num / MILLION;
        extractHundred(mill);
        result += million[extractMod(mill)];
        extractThousand(num % MILLION);
    }

    void extractThousand(int num) {
        int thous = num / THOUSAND;
        extractHundred(thous);
        result += thousand[extractMod(thous)];
        extractHundred(num % THOUSAND);
    }

    void extractHundred(int num) {
        result += hundred[num / HUNDRED];
        extractTen(num % HUNDRED);
    }

    void extractTen(int num) {
        if (num < 20 && num > 9) {
            result += teen[num % TEN];
        } else {
            result += tens[num / TEN];
            extractUnit(num % TEN);
        }
    }

    void extractUnit(int num) {
        result += units[num % TEN];
    }

    int extractMod(int num) {
        int mod = num % TEN;
        switch (mod) {
            case 0:
                return 3;
            case 1:
                return 0;
            case 2:
            case 3:
            case 4:
                return 1;
            default:
                return 2;
        }
    }

    private String convertNumber(long number) {
        extractTrillion(number);
        return Character.toString(result.trim().charAt(0)).toUpperCase() + result.trim().substring(1).toLowerCase();
    }

    private long trimZeroes(String num) {
        while (num.charAt(0) == '0') {
            num.substring(0);
        }
        return Long.parseLong(num);
    }

    public ArrayList<String> readFile(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        return readFromBr(br);
    }

    private ArrayList<String> readFromBr(BufferedReader br) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                if (line != null) {
                    list.add(line);
                }
                line = br.readLine();
            }
        } finally {
            br.close();
        }
        return list;
    }

    private ArrayList<String> convertList(List<String> list) {
        ArrayList<String> result = new ArrayList<>();
        for (String s : list) {
            if (isNumber(s)) {
                result.add(convertNumber(trimZeroes(s)));
            }
        }
        return result;
    }

    public ArrayList<String> readFile(InputStream stream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        return convertList(readFromBr(br));
    }

    public void saveToFile(String filepath, ArrayList<String> list) throws IOException {
        BufferedWriter output = null;
        try {
            File file = new File(filepath);
            output = new BufferedWriter(new FileWriter(file));
            for (String text : list)
                output.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) output.close();
        }
    }

}
