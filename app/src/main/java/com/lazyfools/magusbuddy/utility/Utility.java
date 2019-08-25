package com.lazyfools.magusbuddy.utility;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;

public class Utility {
    public static SpannableString getSmallCapsString(String input) {
        // values needed to record start/end points of blocks of lowercase letters
        char[] chars = input.toCharArray();
        int currentBlock = 0;
        int[] blockStarts = new int[chars.length];
        int[] blockEnds = new int[chars.length];
        boolean blockOpen = false;

        // record where blocks of lowercase letters start/end
        for (int i = 0; i < chars.length; ++i) {
            char c = chars[i];
            if (c >= 'a' && c <= 'z') {
                if (!blockOpen) {
                    blockOpen = true;
                    blockStarts[currentBlock] = i;
                }
                // replace with uppercase letters
                chars[i] = (char) (c - 'a' + '\u0041');
            }
            else if (isHungarianLowercase(c)) {
                if (!blockOpen) {
                    blockOpen = true;
                    blockStarts[currentBlock] = i;
                }
                switch (c){
                    case 'ő': chars[i] = 'Ő'; break;
                    case 'ű': chars[i] = 'Ű'; break;
                    default: chars[i] = (char) (c - 'a' + '\u0041'); break;
                }
            }
            else {
                if (blockOpen) {
                    blockOpen = false;
                    blockEnds[currentBlock] = i;
                    ++currentBlock;
                }
            }
        }

        // add the string end, in case the last character is a lowercase letter
        blockEnds[currentBlock] = chars.length;

        // shrink the blocks found above
        SpannableString output = new SpannableString(String.valueOf(chars));
        for (int i = 0; i < Math.min(blockStarts.length, blockEnds.length); ++i) {
            output.setSpan(new RelativeSizeSpan(0.8f), blockStarts[i], blockEnds[i], Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        }

        return output;
    }

    private static boolean isHungarianLowercase(char c) {
        return (c == 'é' || c == 'á' || c == 'ű' || c == 'ú' || c == 'ő' || c == 'ó' || c == 'ü' || c == 'ö' || c == 'í');
    }
}
