/* Imagine you have a secret decoder ring with rotating discs labeled with the lowercase alphabet. You're given a
message s written in lowercase letters and a set of instructions shifts encoded as tuples (start_disc, end_disc,
direction). Each instruction represents rotating the discs between positions start_disc and end_disc (inclusive)
either clockwise (direction = 1) or counter-clockwise (direction = 0). Rotating a disc shifts the message by one
letter for each position moved on the alphabet (wrapping around from ‘z’ to ‘a’ and vice versa).
Your task is to decipher the message s by applying the rotations specified in shifts in the correct order.
Example:
In Input: s = "hello", shifts = [[0, 1, 1], [2, 3, 0], [0, 2, 1]]
Output: jglko
Shifts:
(0, 1, 1) - Rotate discs 0 and 1 clockwise (h becomes i, e becomes f)
(2, 3, 0) - Rotate discs 2 and 3 counter-clockwise (both l becomes k)
(0, 2, 1) - Rotate discs 0, 1, and 2 clockwise (i becomes j, f becomes g, and k becomes l)*/


public class MessageDecoder1b {

    //implement the method to decode the message based on shifts
    public static String decodeMessage(String s, int[][] shifts) {
        // Convert the message to a character array for manipulation
        char[] messageDecoded = s.toCharArray();

        // Processing  each shift instruction
        for (int[] shift : shifts) {
            int startingIndex = shift[0]; // Starting disc index
            int endingIndex = shift[1]; // Ending disc index
            int direction = shift[2]; // Direction of rotation (0 for counter-clockwise, 1 for clockwise)

            // Determine the shift value based on the direction using if-else
            int shiftValue;
            if (direction == 1) {
                shiftValue = 1; // this will shit cloclwise
            } else {
                shiftValue = -1; // this will shift anti-clockwise 
            }

            // Rotate the specified discs
            for (int i = startingIndex; i <= endingIndex; i++) {
                // Apply rotation and wrap around using modulo
                messageDecoded[i] = rotateCharacter(messageDecoded[i], shiftValue);
            }
        }

        // Convert character array back to string and return
        return new String(messageDecoded);
    }

    // Helper method to rotate a character by the given shift value
    private static char rotateCharacter(char c, int shiftValue) {
        // Calculate the new character with wrapping around the alphabet
        return (char) ('a' + (c - 'a' + shiftValue + 26) % 26);
    }

    public static void main(String[] args) {
        // Example input
        String message = "hello";
        int[][] shifts = {{0, 1, 1}, {2, 3, 0}, {0, 2, 1}};

        // Decode the message
        String decodedMessage = decodeMessage(message, shifts);

        // Output the decoded message
        System.out.println("The Decoded Message is : " + decodedMessage); // output: jglko
    }
}
