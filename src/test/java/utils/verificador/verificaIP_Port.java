package utils.verificador;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class verificaIP_Port {

    static String host = "172.16.20.52";

    public static void pingCheckbyInetAddressisReachable() {
        try {
            InetAddress crunchifyAddr = InetAddress.getByName(host);
            boolean reachable = crunchifyAddr.isReachable(2000);
            if (reachable) {
                System.out.println("VERIFICANDO AMBIENTE ==> Sucesso para o host: " + host);
                Inspecionardor.TipoTeste("sucesso", "Sucesso para o host: " + host, "inicial");
            } else {
                System.out.println("VERIFICANDO AMBIENTE ==> Ping falhou para o host: " + host);
                Inspecionardor.TipoTeste("erro", "Não foi possivel verificar" + host, "inicial");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Inspecionardor.TipoTeste("erro", "Não foi possivel verificar" + e, "inicial");
        }

    }

    public static void pingCheckbyCrunchifyisReachable() {
        try {
            crunchifyAddressReachable(host, 82, 2000);
            System.out.println("\nSobrecarga (host, port, timeout) ==> Sucesso para o host: " + host);
            Inspecionardor.TipoTeste("sucesso", "Sobrecarga (host, port, timeout) ==> Sucesso para o host: " + host, "inicial");
        } catch (Exception e) {
            System.out.println("\nSobrecarga (host, port, timeout) ==> Ping falhou para o host: " + host);
            Inspecionardor.TipoTeste("erro", "Não foi possivel verificar" + e, "inicial");
        }

    }

    public static boolean crunchifyAddressReachable(String address, int port, int timeout) {
        try {
            try (Socket crunchifySocket = new Socket()) {
                crunchifySocket.connect(new InetSocketAddress(address, port), timeout);
            }
            return true;
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    static String hostProd = "odontoconecta.odontoprev.com.br";
    public static void pingCheckbyInetAddressisReachableProd(){
        try {
            InetAddress crunchifyAddr = InetAddress.getByName(hostProd);
            boolean reachable = crunchifyAddr.isReachable(2000);
            if (reachable) {
                System.out.println("VERIFICANDO AMBIENTE ==> Ping bem sucedido para o host: " + hostProd);
                Inspecionardor.TipoTeste("sucesso", "Sucesso para o host: " + hostProd, "inicial");
            } else {
                System.out.println("VERIFICANDO AMBIENTE ==> Ping falhou para o host: " + hostProd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pingCheckbyCrunchifyisReachableProd() {
        try {
            crunchifyAddressReachable(hostProd, 80, 2000);
            System.out.println("\nSobrecarga (host, port, timeout) ==> Sucesso para o host: " + hostProd);
            Inspecionardor.TipoTeste("sucesso", "Sobrecarga (host, port, timeout) ==> Sucesso para o host: " + hostProd, "inicial");
        } catch (Exception e) {
            System.out.println("\nSobrecarga (host, port, timeout) ==> Ping falhou para o host: " + hostProd);
        }

    }

    public static boolean crunchifyAddressReachableProd(String address, int port, int timeout) {
        try {

            try (Socket crunchifySocket = new Socket()) {
                crunchifySocket.connect(new InetSocketAddress(address, port), timeout);
            }
            return true;
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }
    }
}

