package com.freetymekiyan.algorithms.level.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 535. Encode and Decode TinyURL
 * <p>
 * Note: This is a companion problem to the System Design problem: Design TinyURL.
 * TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it
 * returns a short URL such as http://tinyurl.com/4e9iAk.
 * <p>
 * Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode
 * algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be
 * decoded to the original URL.
 * <p>
 * Related Topics: Hash Table, Math
 * Similar Questions: (M) Design TinyURL
 */
public class EncodeAndDecodeTinyURL {

    public class Codec {

        private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private Map<String, String> map = new HashMap<>();

        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            String shortUrl;
            do {
                shortUrl = randomKey();
            } while (map.containsKey(shortUrl)); // Make sure the key doesn't duplicate with previous keys.
            map.put(shortUrl, longUrl);
            return shortUrl;
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return map.get(shortUrl);
        }

        /**
         * Generate a random key/short url for an incoming long url.
         */
        private String randomKey() {
            StringBuilder sb = new StringBuilder();
            Random r = new Random(System.currentTimeMillis());
            for (int i = 0; i < 6; i++) {
                sb.append(ALPHABET.charAt(r.nextInt(ALPHABET.length())));
            }
            return sb.toString();
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
}
