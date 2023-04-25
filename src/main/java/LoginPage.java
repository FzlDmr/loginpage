
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginPage {
    Scanner scan = new Scanner(System.in);
    String secim;
    String name;
    String lastname;
    String username;
    String email;
    String password;

    List<String> emailList = new ArrayList<>();
    List<String> usernameList = new ArrayList<>();
    List<String> passwordList = new ArrayList<>();
    List<User> userList = new ArrayList<>();


    public void signUp() {

        boolean isEmailValid = false;
        boolean isPasswordValid = false;
        boolean isUsername = false;


        System.out.println("Enter First Name");
        name = scan.next();

        System.out.println("Enter Last Name");
        lastname = scan.next();


        do {

            System.out.println("Enter Your Username");
            username = scan.next();

            if (usernameList.contains(username)) {
                System.out.println("Daha once alinmis kullanici adi. Lutfen farkli bir kullanici adi aliniz");
            } else {
                isUsername = true;
                usernameList.add(username);
            }

        } while (!isUsername);


        do {

            System.out.println("Enter Your Email");
            email = scan.next();

            boolean first, second, third;

            first = email.contains("@gmail");
            second = !email.contains(" ");
            third = email.replaceAll("[^A-Z]", "").length() == 0;


            if (first && second && third) {

                if (emailList.contains(email)) {
                    System.out.println("Bir email ile bir kez uye olunabilir");

                } else {
                    isEmailValid = true;
                    emailList.add(email);
                }

            } else {
                System.out.println("Gecersiz email girdiniz. Lutfen Gecerli email giriniz.");
            }

        } while (!isEmailValid);


        do {

            boolean first, second, third, fourth, fifth, sixth;

            System.out.println("Enter Your Password");
            password = scan.next();

            first = password.length() > 7;
            second = !password.contains(" ");
            third = password.replaceAll("[^A-Z]", "").length() > 0;
            fourth = password.replaceAll("[^a-z]", "").length() > 0;
            fifth = password.replaceAll("[a-zA-Z0-9]", "").length() > 0;
            sixth = password.replaceAll("[^0-9]", "").length() > 0;


            if (first && second && third && fourth && fifth && sixth) {

                isPasswordValid = true;
                passwordList.add(password);

            } else {
                System.out.println("Gecersiz sifre girdiniz. Lutfen Gecerli sifre giriniz.");
            }

        } while (!isPasswordValid);


         /* User user1 = new User(name, lastname, username, email, password);
         userList.add(user1);
         System.out.println("UserList "+userList);

         Burasi giris yapan kullanicilari liste seklinde gostermemizi sagliyor
         */


        System.out.println("Kaydiniz Basarili Olmustur");

        menu();


    }

    protected void login() {

        System.out.println("Enter Your Email");
        email = scan.next();

        if (!emailList.contains(email)) {
            System.out.println("Kullanici kayitli degildir. Lutfen Uye Olunuz ");
            signUp();
        }

        int emailIndex = emailList.indexOf(email);
        boolean isValid;

        do {

            System.out.println("Enter Your Password");
            password = scan.next();
            isValid = passwordList.get(emailIndex).equals(password);
            if (!isValid) {
                System.out.println("Hatali parola girdiniz.Lutfen Tekrar Deneyiniz.");
            }

        } while (!isValid);

        System.out.println("Sitemize Hosgeldiniz");

        menu();


    }


    public void menu() {

        System.out.println("*******HOSGELDINIZ********\n1-Sign Up\n2-Login\n3-Kullanici Listesi\n4-Cikis");
        secim = scan.next();
        switch (secim) {
            case "1":
                signUp();
            case "2":
                login();
            case "3":
                kullaniciGoruntuleme();
                menu();
            case "4":
                System.exit(0);
            default:
                System.out.println("Hatali Giris Yaptiniz. Lutfen Tekrar Deneyiniz.");
                menu();
        }

    }

    public void kullaniciGoruntuleme() {

        for (User user : userList) {
            System.out.println(
                    "Adi      : " + user.getName() +
                            "\nSoyadi   : " + user.getLastname() +
                            "\nUsername : " + user.getUsername() +
                            "\nEmail    : " + user.getEmail() +
                            "\n*****************************");

        }
    }

}