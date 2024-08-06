/* 2b
Imagine you're at a movie theater with assigned seating. You have a seating chart represented by an array nums
where nums[i] represents the seat number at row i. You're looking for two friends to sit together, considering their
seat preferences and your limitations:
Seating Distance: Your friends prefer to sit close together, with a maximum allowed seat difference of indexDiff.
Imagine indexDiff = 2, meaning they'd be comfortable within 2 seats of each other (e.g., seats 3 and 5).
Movie Preference: They also want similar movie tastes, requiring a difference in their seat numbers (abs(nums[i] -
nums[j])) to be within valueDiff. Think of valueDiff = 1 as preferring movies with similar ratings (e.g., seats 4 and
5 for movies rated 4.5 and 5 stars).
Your task is to determine if there are two friends (represented by two indices i and j) who can sit together while
satisfying both the seating distance and movie preference limitations.
Example:
Input:
Seating chart: nums = [2, 3, 5, 4, 9]
Seating distance limit: indexDiff = 2
Movie preference limit: valueDiff = 1
Output: true
Possible pairs:
(0, 1): Seat difference (1) within indexDiff, movie difference (1) within valueDiff.
(3, 4): Seat difference (1) within indexDiff, movie difference (5) exceeds valueDiff.
In this scenario, you can find two friends who can sit together, so the answer would be true.*/



public class MovieTheatreQuestion {
    /* 
     * Determines if two friends can sit together based on seating 
     * distance and movie preference.
     * 
     *  nums : Array that represents seat numbers in the theatre.
     *  indexDiff: Maximum allowed index difference for seating.
     * valueDiff: Maximum allowed value difference for movie preference.
     * return  true if friends can sit together, otherwise false.
     */
    public static boolean whoCanSitWithTogether(int[] nums, int indexDiff, int valueDiff) {
        // Loop through each seat number in the nums array 
        for (int i = 0; i < nums.length; i++) {
            // Checking previous seats within the allowed index 
            for (int j = Math.max(0, i - indexDiff); j < i; j++) {
                // Checking if both conditions are met
                if (Math.abs(nums[i] - nums[j]) <= valueDiff) {
                    return true; // Valid pair found
                }
            }
        }
        return false; // No valid pairs found
    }

    public static void main(String[] args) {
        // Array of seat numbers according to the question
        int[] nums = {2, 3, 5, 4, 9};
        int indexDiff = 2; // Maximum allowed index difference
        int valueDiff = 1; // Maximum allowed value difference

        // Determining if friends can sit together 
        boolean answer = whoCanSitWithTogether(nums, indexDiff, valueDiff);
        System.out.println("Can friends sit together? " + answer); // Expected Output: true

    }
}
