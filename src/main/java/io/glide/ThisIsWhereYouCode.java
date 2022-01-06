package io.glide;

import java.util.ArrayList;
import java.util.Arrays;
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
    	
    	List<Object> listPrep = new ArrayList<Object>();
    	
    	for(Object obj : array) {
    		if(obj instanceof Object[]) {
    			listPrep.addAll(buildStringArray((Object[]) obj));
    		} else if (obj instanceof String) {
    			listPrep.add(obj);
    		}
    	}
    	
        return getLongestElement(listPrep.toArray()).get().toString();
    }
    
    private List<Object>  buildStringArray(Object[] array) {
    	List<Object> arrayPrep = new ArrayList<Object>();
    	for(Object obj : array) {
    		if(obj instanceof String) {
    			arrayPrep.add(obj);
    		} else if (obj instanceof Object[]) {
    			arrayPrep.addAll(buildStringArray((Object[]) obj));
    		}
    	}
    	
    	return arrayPrep;
    }
    
    private Optional<Object> getLongestElement(Object[] array) {
    	Optional<Object> longestElement = Optional.of(array[0]);
    	for(int i = 0; i < array.length; i++) {
    		Optional<Object> currentString = Optional.of(array[i]);
    		if(isString(longestElement) && isString(currentString) && isLongestString(longestElement, currentString)) {
    			longestElement = currentString;
    		} else if (!isString(longestElement) && isString(currentString)) {
    			longestElement = currentString;
    		}
    	}
    	
    	return longestElement;
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
    	
    	if(input == null || input.isEmpty()) {
    		return null;
    	}
    	
    	StringBuffer finalString = new StringBuffer();;
        Character previouschar = input.charAt(0);

        int n = 0;
        for(int i=0; i < input.length(); i++){
        	if(previouschar.equals(input.charAt(i))) {
        		n++;
        	} else if (n > 1) {
        		finalString.append(n);
        		finalString.append(previouschar);
        		n = 1;
        	} else {
        		finalString.append(previouschar);
        	}
        	previouschar = input.charAt(i);
        }
        
        if(n > 1) {
        	finalString.append(n);
        	finalString.append(previouschar);
        } else {
        	finalString.append(previouschar);
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
    	
    	for(int i = 0; i < array.length; i++) {
    		array[i] = getCompressedString(array[i]);
    	}
    	
    	Arrays.sort(array);
    	
    	int nbLetters = 1;
    	
    	for(int i = 0; i< array.length; i++) {
    		StringBuffer finalString = new StringBuffer();

            for(int j=0; j < array[i].length(); j++){
            	
            	if(isInteger(array[i].charAt(j)+"")) {
            		nbLetters = Integer.parseInt(array[i].charAt(j)+"");
            		while(nbLetters > 0) {
            			finalString.append(array[i].charAt(j+1));
            			nbLetters--;
            		}
            		j++;
            	} else {
            		finalString.append(array[i].charAt(j));
            	}
            }
            array[i] = finalString.toString(); 
    	}
        return array;
    }
    
    private static boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException e) { 
            return false; 
        }
        return true;
    }

}
