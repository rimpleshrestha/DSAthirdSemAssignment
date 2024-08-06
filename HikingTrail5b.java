/*b)
Imagine you're on a challenging hiking trail represented by an array nums, where each element represents the
altitude at a specific point on the trail. You want to find the longest consecutive stretch of the trail you can hike
while staying within a certain elevation gain limit (at most k).
Constraints:
You can only go uphill (increasing altitude).
The maximum allowed elevation gain between two consecutive points is k.
Goal: Find the longest continuous hike you can take while respecting the elevation gain limit.
Examples:
Input:
Trail: [4, 2, 1, 4, 3, 4, 5, 8, and 15], Elevation gain limit (K): 3
Output: 5
Explanation
Longest hike: [1, 3, 4, 5, 8] (length 5) - You can climb from 1 to 3 (gain 2), then to 4 (gain 1), and so on, all within
the limit.
Invalid hike: [1, 3, 4, 5, 8, 15] - The gain from 8 to 15 (7) exceeds the limit */

public class HikingTrail5b {
    // Method to find the longest hike respecting the elevation gain limit
    public static int longestHike(int[] nums, int k) {
        int maxLength = 0; // Maximum length of the valid hike
        int currentLength = 0; // Length of the current valid hike

        for (int end = 0; end < nums.length; end++) {
            // If we are at the first point or it's an uphill
            if (end == 0 || (nums[end] > nums[end - 1] && (nums[end] - nums[end - 1] <= k))) {
                currentLength++; // Increment length for valid uphill
                maxLength = Math.max(maxLength, currentLength); // Update maxLength
            } else {
                // Reset the current length
                currentLength = 1; // Start counting from the current point
            }

            // Check for valid uphill with elevation gain limit
            if (end > 0 && nums[end] <= nums[end - 1]) {
                // Reset the current length when going downhill or the gain is not allowed
                currentLength = 1; // Reset to 1 since the current point starts a new hike
            }
        }

        return maxLength; // Return the maximum length of the valid hike found
    }

    public static void main(String[] args) {
        // Example trail and elevation gain limit
        int[] trail = {4, 2, 1, 4, 3, 4, 5, 8, 15};
        int k = 3; // Maximum allowed elevation gain

        // Calculating  the result
        int result = longestHike(trail, k);

        // Output the longest hike as question says 
        System.out.println("Longest hike length: " + result); // Expected output: 5
    }
}
