package com.ch.study.architecture.array.doublepoint;

public class LongestPalindrome {

    public String longestPalindrome(String s) {
        String maxStr = "";
        for (int i = 0; i < s.length(); i++) {
            String palindrome1 = getPalindrome1(s, i);
            String palindrome2 = getPalindrome2(s, i);
            String maxPalindrome = palindrome1.length() > palindrome2.length() ? palindrome1 : palindrome2;
            maxStr = maxStr.length() > maxPalindrome.length() ? maxStr : maxPalindrome;
        }
        return maxStr;
    }

    public String getPalindrome1(String s, int center) {
        int slow = center;
        int fast = center;
        while (slow >= 0 && fast < s.length() && s.charAt(slow) == s.charAt(fast)) {
            slow--;
            fast++;
        }
        if (slow < 0 || fast > s.length() - 1 || s.charAt(slow) != s.charAt(fast)) {
            slow++;
            fast--;
        }
        return s.substring(slow, ++fast);
    }

    public String getPalindrome2(String s, int center) {
        int slow = center;
        int fast = center + 1;
        if (fast >= s.length()) {
            return "";
        }
        while (slow >= 0 && fast < s.length() && s.charAt(slow) == s.charAt(fast)) {
            slow--;
            fast++;
        }
        if (slow < 0 || fast > s.length() - 1 || s.charAt(slow) != s.charAt(fast)) {
            slow++;
            fast--;
        }
        return s.substring(slow, ++fast);
    }

}
