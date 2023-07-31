import Network.BattleServer;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) {
        BattleServer b = new BattleServer();
        b.start(9999,args[0]);
    }
}