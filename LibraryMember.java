public class LibraryMember {
    private String nameOfBorrower;
    private int id;
    private int requestedDays;
    private int actualDays;
    private int borrowedBooksCount;
    private double outstandingPenalty = 0.0;
    private boolean isBlocked = false;

    public LibraryMember(String nameOfBorrower) {
        this(nameOfBorrower, 0000);
    }

    public LibraryMember(String nameOfBorrower, int id) {
        setNameOfBorrower(nameOfBorrower);
        setId(id);
    }

    public String getNameOfBorrower() {
        return nameOfBorrower;
    }

    public void setNameOfBorrower(String nameOfBorrower) {
        this.nameOfBorrower = nameOfBorrower;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void borrowBook(int requestedDays, int actualDays) {
        if (requestedDays <= 0 || requestedDays > getPermittedLoanPeriod()) {
            requestedDays = 1;
        }
        if (actualDays < 0) {
            throw new IllegalArgumentException("المدة الفعلية غير مقبولة");
        }
        if (checkEligibility()) {
            this.requestedDays = requestedDays;
            this.actualDays = actualDays;
            borrowedBooksCount++;
            calculatePenalty();
        }
    }

    public void returnBook() {
        if (borrowedBooksCount > 0) {
            borrowedBooksCount--;
        }
    }

    public void payPenalty(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("مبلغ الدفع يجب أن يكون أكبر من صفر");
        }
        double rest = outstandingPenalty - amount;
        if (rest <= 0) {
            outstandingPenalty = 0;
            if (rest < 0) {
                double positiveRest = Math.abs(rest);
                if (outstandingPenalty == 0.0) {
                    System.out.println("لا توجد عليك غرامة، ولك فائض مالي قدره :" + positiveRest);
                } else {

                    System.out.println("تم سداد الغرامة بالكامل، ولكم فائض مالي قدره :" + positiveRest);
                }
            } else {
                System.out.println("تم استلام كامل قيمة الغرامة دون أي فائض أو متبقي.");
            }
        } else {
            outstandingPenalty = rest;
            System.out.println("تم استلام دفعة جزئية من الغرامة، ولا يزال مستحقاً عليك مبلغ :" + rest);
        }
        block();
    }

    public boolean block() {
        isBlocked = outstandingPenalty >= getMaxPenalty();
        return isBlocked;
    }

    public void calculatePenalty() {
        int currentPenalty = 0;
        int surplus = actualDays - requestedDays - getGracePeriod();
        if (surplus > 0) {
            currentPenalty = surplus * getDailyPenaltyRate();
        }
        outstandingPenalty += currentPenalty;
    }

    public boolean checkEligibility() {
        if (block()) {
            System.out.println("Borrowing is not possible");
            return false;
        }
        if (borrowedBooksCount >= getMaxBooks()) {
            return false;
        }
        return true;
    }

    public void showInfo() {
        System.out.println("name : " + getNameOfBorrower());
        System.out.println("id : " + getId());
        System.out.println("Borrow Book count : " + borrowedBooksCount);
        if (block()) {
            System.out.println("Status : Blocked");
        } else {
            System.out.println("Status : Active");
        }
        System.out.println("Out Standing Penalty : " + outstandingPenalty);
    }

    public int getPermittedLoanPeriod() {
        return 0;
    }// مدة الاستعارة

    public int getMaxBooks() {
        return 0;
    }// الحد الأقصى للكتب

    public int getDailyPenaltyRate() {
        return 0;
    }// الغرامة اليومية

    public int getMaxPenalty() {
        return 0;
    }// الحد الأقصى للغرامة

    public int getGracePeriod() {
        return 0;
    }// فترة السماح
}

