package com.freetymekiyan.algorithms.other;

class RateLimiter {

    private long lastTime = System.currentTimeMillis();
    private int count;

    public static void main(String[] args) {
        final RateLimiter r = new RateLimiter();
        Thread t = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                    int count = r.rateLimiter();
                    System.out.println("rate is: " + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    private int rateLimiter() {
        if (System.currentTimeMillis() - lastTime > 1000) {
            count = 0;
            lastTime = System.currentTimeMillis();
        }
        count++;
        if (count > 5) {
            // do something
            return count;
        }
        api();
        return count;
    }

    private void api() {
        System.out.println("API called");
    }
}