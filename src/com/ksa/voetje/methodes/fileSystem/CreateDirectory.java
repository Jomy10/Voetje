package com.ksa.voetje.methodes.fileSystem;

import java.io.File;
import java.util.Scanner;
public class CreateDirectory {
    public static void createDirectory(String path) {
        File file = new File(path);

        boolean bool = file.mkdirs();
        if (bool) {
            System.out.println("Directory created succesfully!");
        } else {
            System.out.println("Couldn't create the spe cified directory.");
        }
    }

    public static void main(String args[]) {
        System.out.println("Enter the path to create a directory: ");
        Scanner sc = new Scanner(System.in);
        String path = sc.next();
        System.out.println("Enter the name of the desired a directory: ");
        path = path+sc.next();
        //Creating a File object
        File file = new File(path);
        //Creating the directory
        boolean bool = file.mkdirs();
        if(bool){
            System.out.println("Directory created successfully");
        }else{
            System.out.println("Sorry couldnt create specified directory");
        }
    }
}
