package io.glide;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * This is where you have to code.
 *
 * See javadoc and associated unit tests to understand what is expected
 */
public class ThisIsWhereYouCode {

    /**
     * input will be a string, but it may not have a file extension. return the file
     * extension (with no period) if it has one, otherwise null
     *
     * @param filename
     * @return null if input is null or filename has no extension and the
     *         extension without the period otherwise
     */
    public String getFileNameExtension(String filename) {
    	if(filename != null && !filename.isBlank() && filename.contains(".")) {
    		int lastDot = filename.lastIndexOf('.');
    		return filename.substring(lastDot+1);
    	}
    	
        return null;
    }

    /**
     * return the longest string contained inside the input array
     *
     * @param array input Array of values
     * @return null if input is null and the longest string otherwise
     */
    public String getLongestString(Object[] array) {
    	
    	Optional<Object> longestElement = Optional.of(array[0]);
    	for(int i = 0; i < array.length; i++) {
    		Optional<Object> currentString = Optional.of(array[i]);
    		if(isString(longestElement) && isString(currentString) && isLongestString(longestElement, currentString)) {
    			longestElement = currentString;
    		} else if (!isString(longestElement) && isString(currentString)) {
    			longestElement = currentString;
    		}
    	}
    	
        return longestElement.get().toString();
    }
    
    private boolean isString(Optional<Object> optObj) {
    	return optObj.isPresent() && optObj.get() instanceof String;
    }
    
    private boolean isLongestString(Optional<Object> longestElement, Optional<Object> currentString) {
    	return longestElement.get().toString().length() < currentString.get().toString().length();
    }

    /**
     * Returns true if both arrays contains the same values in the same order
     *
     * @param array1 first Array to test
     * @param array2 second Array to test
     * @return true if both arrays contains the same values
     */
    public boolean areArraysEquals(String[] array1, String[] array2) {
        return Arrays.equals(array1, array2);
    }

    /**
     * Compress the input string with a very dummy algorithm : repeated letters
     * are replaced by {n}{letter} where n is the number of repetition and
     * {letter} is the letter. n must be superior to 1 (otherwise, simply output
     * the letter)
     *
     * @param input the input string that can only contains letters
     * @return the compressed String or null if the input is null
     */
    public String getCompressedString(String input) {
    	
    	if(input == null) {
    		return null;
    	}
    	
    	StringBuffer finalString = new StringBuffer();;
        Character previouschar = input.charAt(0);
        
        int n = 1;
        for(int i=1; i < input.length(); i+=2){
        	if(previouschar.equals(input.charAt(i))) {
        		n++;
        	} else if (n == 1){
        		finalString.append(previouschar);
        	} else {
        		finalString.append(n);
        		finalString.append(previouschar);
        	}
        	
        	previouschar = input.charAt(i);
        	
        }
        
        return finalString.toString();
    }

    /**
     * Sort the input array of string based on lexicographic order of the
     * corresponding compressed string.
     *
     * Hint : The expected sorting should use the getCompressedString implementation
     *
     * @param array the Array to sort
     * @return the sorted array
     */
    public String[] getSortedArray(String[] array) {
    	//TODO : implement Compressor
    	Arrays.sort(array);
        return array;
    }

}
