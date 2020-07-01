public class LeapYear {
    public static boolean isLeapYear(int year) {
        /** Return true if this year is a leap year, false otherwise. */
        if (year % 4 != 0) {
            return false;
        }

        if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        assert(args.length == 1);

        int year = Integer.parseInt(args[0]);
        if (isLeapYear(year)) {
            System.out.println(args[0] + " is a leap year.");
        } else {
            System.out.println(args[0] + " is not a leap year.");
        }
    }
}