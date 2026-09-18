public class Main {
    public static void main(String[] args) {
        var emf = jakarta.persistence.Persistence.createEntityManagerFactory("hsf302FU");
        System.out.println("Tao bang thanh cong!");
        emf.close();
    }
}