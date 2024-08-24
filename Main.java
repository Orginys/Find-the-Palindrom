class Solution {
    public String nearestPalindromic(String n) {
        // Parse the input string n into a long integer for numerical comparisons
        long num = Long.parseLong(n);

        // Calculate the list of possible nearest palindromes
        List<Long> candidates = getCandidates(n);

        // Initialize a variable to store the closest palindrome
        long closest = -1;

        // Iterate through each candidate palindrome
        for (long candidate : candidates) {
            // If the candidate is not equal to the original number
            if (candidate != num) {
                // If no closest palindrome has been found yet, or if this candidate is closer than the previously found closest palindrome
                if (closest == -1 || Math.abs(candidate - num) < Math.abs(closest - num) ||
                        (Math.abs(candidate - num) == Math.abs(closest - num) && candidate < closest)) {
                    closest = candidate; // Update the closest palindrome
                }
            }
        }

        // Return the closest palindrome as a string
        return String.valueOf(closest);
    }

    private List<Long> getCandidates(String n) {
        // Create a list to store potential candidates for the closest palindrome
        List<Long> candidates = new ArrayList<>();
        int len = n.length();

        // Add edge cases to the candidate list
        candidates.add((long)Math.pow(10, len - 1) - 1); // Smallest number with one less digit
        candidates.add((long)Math.pow(10, len) + 1); // Smallest number with one more digit

        // Find the prefix of the number to generate middle candidates
        long prefix = Long.parseLong(n.substring(0, (len + 1) / 2));

        // Generate palindromes by modifying the prefix
        for (long i = prefix - 1; i <= prefix + 1; i++) {
            StringBuilder sb = new StringBuilder();
            String prefixStr = String.valueOf(i);
            sb.append(prefixStr);

            // If the length of n is odd, we skip the middle character while mirroring
            String reverse = new StringBuilder(prefixStr).reverse().toString();
            sb.append(reverse.substring(len % 2));

            // Add the generated palindrome to the candidate list
            candidates.add(Long.parseLong(sb.toString()));
        }

        return candidates; // Return the list of potential palindromes
    }
}