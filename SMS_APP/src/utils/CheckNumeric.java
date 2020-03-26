/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author sunflower
 */
public class CheckNumeric {
    public static boolean checkNumeric(final String str){
        if(str == null || str.equals("")){
            return false;
        }
        return str.chars().allMatch(Character::isDigit);
    }
}
