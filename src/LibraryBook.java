import java.time.LocalDate;

public class LibraryBook extends  Book
{
    private LocalDate m_DateTaken;
    private LocalDate m_ReturnDate;
    private boolean m_Available;

    LibraryBook(String i_Title, String i_Author) {
        super(i_Title, i_Author);
        m_Available = true;
    }

    void returnBook(){
        m_DateTaken = null;
        m_ReturnDate = null;
    }

    LocalDate TakeBook()
    {
        m_DateTaken = LocalDate.now();
        m_ReturnDate = m_DateTaken.plusMonths(1);
        m_Available = false;
        return m_ReturnDate;
    }

    boolean isBookAvailable(){
        return m_Available;
    }

    LocalDate getDateTaken() {
        return m_DateTaken;
    }

    LocalDate getReturnDate() {
        return m_ReturnDate;
    }

    void setReturnDate(LocalDate m_ReturnDate) {
        this.m_ReturnDate = m_ReturnDate;
    }

    void Return() {
        this.m_DateTaken = null;
        this.m_ReturnDate = null;
        this.m_Available = true;
    }
}
