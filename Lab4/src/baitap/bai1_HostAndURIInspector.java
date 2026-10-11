package Lab4.src.baitap;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

public class bai1_HostAndURIInspector {
    public static void main(String[] args) {
        //1. Xử lý thiếu tham số
        if (args.length < 2) {
            System.err.println("Loi: can truyen du 2 tham so.");
            System.err.println("Cu phap: java HostAndURIInspector <hostname> <URI>");
            return ;
        }

        String hostname = args[0];
        String uriString = args[1];

        //2. Phân giải Hostname
        System.out.println("Ket qua phan giai HOSTNAME: " + hostname);

        try {
            InetAddress[] addresses = Inet4Address.getAllByName(hostname);
            for(InetAddress addr: addresses){
                System.out.println("IP: "+ addr.getHostAddress());
                if(addr instanceof Inet4Address){
                    System.out.println("IPv4");
                } else if ( addr instanceof Inet6Address){
                    System.out.println("IPv6");
                }else{
                    System.out.println("IP khong xac dinh");
                }
                System.out.println("Loopback: " + addr.isLoopbackAddress());
                System.out.println("Site Local: " + addr.isSiteLocalAddress());
                System.out.println();
            }
        } catch (UnknownHostException e) {
            System.err.println("Lỗi Hostname: Khong the phan giai '" + hostname + " ' ");
        }

        //3. Phân giải URI
        System.out.println("Ket qua phan gia: " + uriString);
        try {
            URI uri = new URI(uriString);
            System.out.println("Scheme: " + uri.getScheme());
            System.out.println("Host: " + uri.getHost());
            System.out.println("Port: " + (uri.getPort() == -1 ? "Khong xac dinh" : uri.getPort()));
            System.out.println("Path: " + uri.getPath());
            System.out.println("Query: " + uri.getQuery());
            System.out.println("Fragment: " + uri.getFragment());
        } catch (URISyntaxException e) {
            System.err.println("Loi URI: Cu phap khong hop le. Chi tiet: " + e.getMessage());
        }
    }    
}
