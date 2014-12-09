package com.cuketest.reporting.cukeframework.fixtures

/**
 * Created by David on 12/6/2014.
 */
class PortFinder {


    private static int MIN_PORT_NUMBER = 30000
    private static int MAX_PORT_NUMBER = 45000

    static int getOpenPort() {
        int randomVal = Math.abs(new Random().nextInt()) % (MAX_PORT_NUMBER - MIN_PORT_NUMBER) + MIN_PORT_NUMBER
        println ("random val1 = " + randomVal)
        while (!available(randomVal)) {
            randomVal = Math.abs(new Random().nextInt()) % (MAX_PORT_NUMBER - MIN_PORT_NUMBER) + MIN_PORT_NUMBER
            println ("random val2 = " + randomVal)
        }

        return randomVal
    }

    private static boolean available(int port) {
        if (port < MIN_PORT_NUMBER || port > MAX_PORT_NUMBER) {
            throw new IllegalArgumentException("Invalid start port: " + port);
        }

        ServerSocket ss = null;
        DatagramSocket ds = null;
        try {
            ss = new ServerSocket(port);
            ss.setReuseAddress(true);
            ds = new DatagramSocket(port);
            ds.setReuseAddress(true);
            return true;
        } catch (IOException e) {
        } finally {
            if (ds != null) {
                ds.close();
            }

            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                    /* should not be thrown */
                }
            }
        }

        return false;
    }
}


