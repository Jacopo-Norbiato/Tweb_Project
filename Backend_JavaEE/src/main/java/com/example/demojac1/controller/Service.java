package com.example.demojac1.controller;

import org.apache.commons.codec.digest.DigestUtils;

class Service {
        /*
        *************************************
        Qui viene utilizzato MD5.
        È una funzione unidirezionale diversa dalla codifica e dalla cifratura perché irreversibile.
        Questa funzione prende in input una stringa di lunghezza arbitraria e ne produce in output
        un'altra a 128 bit.
        */
        public static String encryptMD5(String testoChiaro) {
            String key = DigestUtils.md5Hex(testoChiaro).toUpperCase();
            System.out.println("\t\t\t1 - " + key);
            return key;
        }

        public static boolean checkMD5(String password, String testoChiaro) {
            System.out.println("\t\t\t0 - " + password);
            if (password.equals(encryptMD5(testoChiaro).toUpperCase()))
                return true;
            else
                return false;
        }
    }