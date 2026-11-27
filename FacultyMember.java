public class FacultyMember extends LibraryMember {
    public FacultyMember(String nameOfBorrower) {
        this(nameOfBorrower, 0000);
    }

    public FacultyMember(String nameOfBorrower, int id) {
        super(nameOfBorrower, id);
    }

    @Override
    public final int getMaxBooks() {
        return 5;
    }

    @Override
    public final int getPermittedLoanPeriod() {
        return 21;
    }

    @Override
    public final int getGracePeriod() {
        return 3;
    }

    @Override
    public final int getDailyPenaltyRate() {
        return 1;
    }

    @Override
    public final int getMaxPenalty() {
        return 20;
    }
}
