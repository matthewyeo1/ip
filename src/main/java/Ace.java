public class Ace {

    public static void setDivider() {
        System.out.println("__________________"
            + "____________________"
            + "____________________"
            + "____________________"
            + "____________________"
            + "____________________"
            + "____________________"
            + "____________________"
        );
    }

    public static void setGap() {
        System.out.println("");
    }

    public static void main(String[] args) {
        String logo =
              " _____   _____   _____                          \n"
            + "|  _  | |  __ | |  ___|                           \n" 
            + "| |_| | | |     | |___                      \n"
            + "|  _  | | |     |  ___|                   \n"
            + "| | | | | |___  | |___                         \n"
            + "|_| |_| |_____| |_____|                             \n";
        setDivider();
        setGap();
        System.out.println("Hello! I am...\n" + logo);
        System.out.println("How can I assist you?");
        setGap();
        setDivider();
        setGap();
        System.out.println("Have a good day.");
        setGap();
        setDivider();
        setGap();
    }
}
