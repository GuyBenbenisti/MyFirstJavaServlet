class Book
{
    private String m_Title;
    private String m_Author;

    String getBookTitle() {
        return m_Title;
    }

    void setBookTitle(String m_Title) {
        this.m_Title = m_Title;
    }

    String getAuthor() {
        return m_Author;
    }

    void setAuthor(String m_Author) {
        this.m_Author = m_Author;
    }


    Book(String i_Title, String i_Author)
    {
        this.m_Author = i_Author;
        this.m_Title = i_Title;
    }
}