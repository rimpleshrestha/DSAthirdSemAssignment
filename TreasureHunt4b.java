// Class to represent a node in the binary tree
class Node {
    int value; // Value of the node
    Node leftChild; // Left child
    Node rightChild; // Right child
    
    Node(int x) {
        value = x; // Constructor to initialize node value
    }
}

public class TreasureHunt4b {
    private int maxCollectionValue = 0; // Variable to store the maximum sum of magical grove

    // Main method to find the largest magical grove
    public int findLargestMagicalGrove(Node rootNode) {
        traverseTree(rootNode); // Start the depth-first search from the root
        return maxCollectionValue; // Return the maximum sum found
    }

    // Depth-first search method to traverse the tree
    private ResultDetails traverseTree(Node currentNode) {
        if (currentNode == null) {
            // If the node is null, return a base case with sum = 0
            return new ResultDetails(0, true, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        // Recursively get results from left and right subtrees
        ResultDetails leftResult = traverseTree(currentNode.leftChild);
        ResultDetails rightResult = traverseTree(currentNode.rightChild);

        // Check if the current subtree is a valid binary search tree (BST)
        boolean isValidBST = leftResult.isBST && rightResult.isBST // Both left and right must be BSTs
                             && leftResult.max < currentNode.value && currentNode.value < rightResult.min; // Check value constraints

        // Calculate the sum of the current subtree
        int currentSubtreeSum = leftResult.sum + rightResult.sum + currentNode.value;

        // Update maxCollectionValue regardless of whether it's a BST
        if (isValidBST) {
            maxCollectionValue = Math.max(maxCollectionValue, currentSubtreeSum);
        } else {
            // If not a BST, consider the maximum of left and right subtrees
            maxCollectionValue = Math.max(maxCollectionValue, Math.max(leftResult.sum, rightResult.sum));
        }

        // Return the current subtree sum even if it's not a BST
        return new ResultDetails(
            currentSubtreeSum,
            isValidBST,
            Math.min(currentNode.value, leftResult.min),
            Math.max(currentNode.value, rightResult.max)
        );
    }

    // Helper class to hold results of the subtree checks
    private class ResultDetails {
        int sum; // Sum of values in the subtree
        boolean isBST; // Is the subtree a valid BST?
        int min; // Minimum value in the subtree
        int max; // Maximum value in the subtree

        ResultDetails(int sum, boolean isBST, int min, int max) {
            this.sum = sum;
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }

    // Main method to run the program
    public static void main(String[] args) {
        // Construct the tree from the example: [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
        Node rootNode = new Node(1);
        rootNode.leftChild = new Node(4);
        rootNode.rightChild = new Node(3);
        rootNode.leftChild.leftChild = new Node(2);
        rootNode.leftChild.rightChild = new Node(4);
        rootNode.rightChild.leftChild = new Node(2);
        rootNode.rightChild.rightChild = new Node(5);
        rootNode.rightChild.rightChild.leftChild = new Node(4);
        rootNode.rightChild.rightChild.rightChild = new Node(6);

        // Create an instance of the TreasureHunt4b class
        TreasureHunt4b treasureHunt = new TreasureHunt4b();
        // Find the largest magical grove sum
        int result = treasureHunt.findLargestMagicalGrove(rootNode);
        // Print the result
        System.out.println("Largest Magical Grove Sum: " + result); // Expected Output: 20
    }
}