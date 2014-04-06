package atarigoclient;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Draw extends Thread implements Serializable {

    private static final long serialVersionUID = 1L;
    int[][] table;
    char firstPlayer;
    char secondPlayer;
    char noPlayer;
    char horizontalline;
    char verticalline;
    BufferedReader in = null;

    Draw(FileInputStream inifile) {
        Properties ini = new Properties();
        try {
            ini.load(inifile);
            firstPlayer = (ini.getProperty("firstplayer", "x")).charAt(0);
            secondPlayer = (ini.getProperty("secondplayer", "0")).charAt(0);
            noPlayer = (ini.getProperty("noplayer", "-")).charAt(0);
            horizontalline = (ini.getProperty("horizontalline", "-")).charAt(0);
            verticalline = (ini.getProperty("verticalline", "|")).charAt(0);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    Draw() {
        firstPlayer = 'x';
        secondPlayer = '0';
        noPlayer = '-';
        horizontalline = '-';
        verticalline = '|';
    }

    public void ilustrate() {
        int i, j;
        System.out.print("\n\n\n");
        System.out.print("   ");
        for (i = 0; i < table.length; i++) {
            System.out.print(i + 1);
            if (i < 9) {
                System.out.print(" ");
            }
        }
        System.out.println();
        System.out.print("   ");
        for (i = 0; i < table.length; i++) {
            System.out.print(horizontalline);
            System.out.print(horizontalline);
        }
        System.out.println();


        for (i = 0; i < table.length; i++) {
            System.out.print(i + 1);
            if (i < 9) {
                System.out.print(" ");
            }
            System.out.print(verticalline);
            for (j = 0; j < table[0].length; j++) {
                if (table[i][j] == 0) {
                    System.out.print(noPlayer);
                } else if (table[i][j] == 1) {
                    System.out.print(firstPlayer);
                } else if (table[i][j] == 2) {
                    System.out.print(secondPlayer);
                }
                System.out.print(" ");
                if (j >= 9) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public void associateOutputFile(BufferedReader newfile) {
        in = newfile;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String antet;
                String raspuns;

                antet = in.readLine();

                if (antet.compareTo("0") == 0) {
                    raspuns = in.readLine();
                    System.out.println(raspuns);
                    //break;
                    System.exit(MIN_PRIORITY);
                } else if (antet.compareTo("1") == 0) {
                    raspuns = in.readLine();
                    System.out.println(raspuns);
                } else if (antet.compareTo("2") == 0) {
                    int size = Integer.parseInt(in.readLine());
                    table = new int[size][size];
                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            table[i][j] = Integer.parseInt(in.readLine());
                        }
                    }
                    ilustrate();
                }
                //System.out.println("  Optiune: ");
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
