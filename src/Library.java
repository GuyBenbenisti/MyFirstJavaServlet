import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

class Library {

    private List<LibraryBook> m_AvailableBooks;
    private List<LibraryBook> m_UnavailableBooks;

    Library(){
        m_AvailableBooks = new ArrayList<LibraryBook>();
        m_UnavailableBooks = new ArrayList<LibraryBook>();
    }

    Library(List<LibraryBook> i_Books){
        m_AvailableBooks = i_Books;
        m_UnavailableBooks = new ArrayList<LibraryBook>();
    }

    void addBook(LibraryBook i_ToAdd){
        m_AvailableBooks.add(i_ToAdd);
    }

    void returnBook(LibraryBook i_Book) {
        for (LibraryBook curBook : m_UnavailableBooks) {
            if (curBook.getBookTitle().equals(i_Book.getBookTitle()) && curBook.getAuthor().equals(i_Book.getAuthor())) {
                m_UnavailableBooks.remove(curBook);
                m_AvailableBooks.add(curBook);
                curBook.Return();
                break;
            }
        }
    }

    Iterator<LibraryBook> availableBooks(){
        return m_AvailableBooks.iterator();
    }

    Iterator<LibraryBook> unavailableBooks(){
        return m_UnavailableBooks.iterator();
    }

    boolean takeBook(LibraryBook i_Book){
        String author = i_Book.getAuthor();
        String title = i_Book.getBookTitle();

        Iterator<LibraryBook> itr = m_AvailableBooks.iterator();
        for (Iterator<LibraryBook> it = itr; it.hasNext();) {
            LibraryBook curBook = it.next();
            if(curBook.getAuthor().equals(author) && curBook.getBookTitle().equals(title))
            {
                curBook.TakeBook();
                m_AvailableBooks.remove(curBook);
                m_UnavailableBooks.add(curBook);
                return true;
            }
        }
        return false;
    }

    boolean isBookAvailable(LibraryBook i_Book)
    {
        String author = i_Book.getAuthor();
        String title = i_Book.getBookTitle();

        Iterator<LibraryBook> itr = m_AvailableBooks.iterator();
        for (Iterator<LibraryBook> it = itr; it.hasNext(); ) {
            LibraryBook curBook = it.next();
            if(curBook.getAuthor().equals(author) && curBook.getBookTitle().equals(title))
                return true;
        }
        return false;
    }
}