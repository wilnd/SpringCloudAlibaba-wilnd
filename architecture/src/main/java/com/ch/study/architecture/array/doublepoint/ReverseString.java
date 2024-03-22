package com.ch.study.architecture.array.doublepoint;

public class ReverseString {

    public void reverseString(char[] s) {
        int slow = 0;
        int fast = s.length - 1;
        char temp;
        while (slow < fast) {
            temp = s[slow];
            s[slow] = s[fast];
            s[fast] = temp;
            slow++;
            fast--;
        }
    }

}
