/* Question 3
a)
Imagine a small community with n houses, numbered 0 to n-1. Some houses have restrictions against becoming
friends, represented by pairs in the restrictions list. For example, if [0, 1] is in the list, houses 0 and 1 cannot be
directly or indirectly friends (through common friends).
Residents send friend requests to each other, represented by pairs in the requests list. Your task is to determine if
each friend request can be accepted based on the current friendship network and the existing restrictions.
Example2:
Input:
Number of houses: 3
Restrictions: [0, 1]
[0, 1] (House 0 and House 1 cannot be friends)
Friend requests: [[0, 2], [2,1]]
[0, 2] (House 0 requests friendship with House 2)
[2, 1] (House 2 requests friendship with House 1)
Outcome: [approved, denied]
Request 0: Approved (House 0 and 2 don't have any restrictions)
Request 1: Denied (House 2 and 1 would be indirectly connected through House 0, violating the restriction).
Example 2:
Input:
Number of Houses = 5
Restrictions = [[0, 1], [1, 2], [2, 3]]
Requests = [[0, 4], [1, 2], [3, 1], [3, 4]]
Output: [approved, denied, approved, denied]
Explanation:
Request 0: house 0 and house 4 can be friends, so they become direct friends.
Request 1: house 1 and house 2 cannot be friends since they are directly restricted.
Request 2: house 3 and house 1 can be friends, so they become direct friends.
Request 3: house 3 and house 4 cannot be friends since person 0 and person 1 would be indirect friends (0--4--3--
1).
*/

import java.util.*; // needs to be edited 

public class Friendship_Network_3a{
    static class UnionFind {
        private int[] parent;
        public UnionFind(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i; // in the beginning each house is its own parent
            }
        }
        public int find(int house) {
            if (parent[house] != house) {
                parent[house] = find(parent[house]); //Path compression applied to find the root directly 
            }
            return parent[house];
        }
        public void union(int house1, int house2) {
            int root_1 = find(house1);
            int root_2 = find(house2);
            if (root_1 != root_2) {
                parent[root_1] = root_2; // Union the two sets
            }
        }
    }

    public static List<String> handleRequests(int n, int[][] restrictions, int[][] requests) {
        UnionFind uf = new UnionFind(n);
        List<String> result = new ArrayList<>();
        
        // Processing  each request
        for (int[] request : requests) {
            int house1 = request[0];
            int house2 = request[1];
            
            // Check if connecting house1 and house2 would violate any restrictions
            boolean canConnect = true;
            for (int[] restriction : restrictions) {
                int r1 = restriction[0], r2 = restriction[1];
                if ((uf.find(house1) == uf.find(r1) && uf.find(house2) == uf.find(r2)) ||
                    (uf.find(house1) == uf.find(r2) && uf.find(house2) == uf.find(r1))) {
                    canConnect = false;
                    break;
                }
            }
            
            if (canConnect) {
                result.add("approved");
                uf.union(house1, house2);
            } else {
                result.add("denied");
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        // Example Input regarding the question
        int n = 5; // Number of houses
        int[][] restrictions = {{0, 1}, {1, 2}, {2, 3}};
        int[][] requests = {{0, 4}, {1, 2}, {3, 1}, {3, 4}};
        
        // Getting  the result of friend requests
        List<String> result = handleRequests(n, restrictions, requests);
        System.out.println(result); // Expected Output: [approved, denied, approved, denied]
    }
}