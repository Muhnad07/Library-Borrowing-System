public class Main {
    public static void main(String[] args) {
        LibraryMember mr = new FacultyMember("Hamza");
        LibraryMember student = new StudentMember("Muhnad", 9601);
        student.borrowBook(7, 10);  // الغرامة: 6
        student.payPenalty(2);      // المتبقي: 4
        student.payPenalty(4);      // المتبقي: 0
        student.returnBook();
        student.showInfo();
        System.out.println("*--------------------*");
        student.borrowBook(7, 20);  // الغرامة: 36
        student.showInfo();         // يحدّث حالة الحظر
        student.borrowBook(7, 8);
        System.out.println("*--------------------*");
        student.payPenalty(10);     // المتبقي: 26، فينرفع الحظر
        student.borrowBook(7, 8);   // تقبل الاستعارة
        System.out.println("*--------------------*");
        mr.borrowBook(22, 6);
        mr.showInfo();
        System.out.println("*--------------------*");
       //mr.borrowBook(10, -1);
        try {
            mr.borrowBook(10, -1);
        } catch (IllegalArgumentException e) {
            System.out.println("تم رفض الاستعارة: " + e.getMessage());
        }
    }
}


