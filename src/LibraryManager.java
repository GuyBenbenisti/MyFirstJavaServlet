import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;
import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;


@WebServlet(
        name = "library",
        urlPatterns = "/LibraryManager"
)
public class LibraryManager extends HttpServlet {

    enum eAction {ADD, TAKE, RETURN, SHOW }

    private static final long serialVersionUID = 1L;
    Library m_Library;

    public LibraryManager() {
    }

    public void init(ServletConfig config)
    {
        m_Library = new Library();
        this.addSomeBooks();
        System.out.println("Added some books to Library");
        System.out.println("Servlet is being initialized");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get response writer
        PrintWriter writer = resp.getWriter();

        // build HTML code
        String htmlRespone = this.bookTableHtml();
        // return response
        writer.println(htmlRespone);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonString = IOUtils.toString(req.getInputStream());
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject)parser.parse(jsonString);
        String actionString = jsonObject.get("action").toString().toUpperCase().replaceAll("\"","");
        jsonObject.remove("action");

        try {
            eAction action = eAction.valueOf(actionString);
            boolean success = false;
            switch(action)
            {
                case ADD:
                    LibraryBook toAdd = gson.fromJson(jsonObject, LibraryBook.class);
                    success = this.addBookToLibrary(toAdd);
                    break;
                case TAKE:
                    LibraryBook toTake = gson.fromJson(jsonObject, LibraryBook.class);
                    success = this.takeBookToLibrary(toTake);
                    break;
                case RETURN:
                    LibraryBook toReturn = gson.fromJson(jsonObject, LibraryBook.class);
                    success = this.returnBookToLibrary(toReturn);
                    break;
                case SHOW:
                    break;
            }
            resp.setStatus((success? HttpServletResponse.SC_OK : HttpServletResponse.SC_NOT_MODIFIED));
        }catch (Exception e)
        {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private boolean takeBookToLibrary(LibraryBook toTake) {
        m_Library.takeBook(toTake);
        return !toTake.isBookAvailable();
    }

    private boolean returnBookToLibrary(LibraryBook toReturn) {
        m_Library.returnBook(toReturn);
        return m_Library.isBookAvailable(toReturn);
    }

    private String bookTableHtml()
    {
        StringBuilder bookTableHtml = new StringBuilder();
        bookTableHtml.append("<style type=\"text/css\">\n" +
                ".tg  {border-collapse:collapse;border-spacing:0;border-color:#aaa;}\n" +
                ".tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aaa;color:#333;background-color:#fff;}\n" +
                ".tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aaa;color:#fff;background-color:#f38630;}\n" +
                ".tg .tg-j2zy{background-color:#FCFBE3;vertical-align:top}\n" +
                ".tg .tg-baqh{text-align:center;vertical-align:top}\n" +
                ".tg .tg-kxef{font-family:\"Comic Sans MS\", cursive, sans-serif !important;;border-color:#efefef;text-align:center;vertical-align:top}\n" +
                ".tg .tg-us36{border-color:inherit;vertical-align:top}\n" +
                ".tg .tg-yw4l{vertical-align:top}\n" +
                "</style>\n" +
                "<table class=\"tg\">\n" +
                "  <tr>\n" +
                "    <th class=\"tg-kxef\">Title</th>\n" +
                "    <th class=\"tg-us36\">Author</th>\n" +
                "    <th class=\"tg-baqh\">Available</th>\n" +
                "  </tr>\n");
        for(Iterator<LibraryBook> availableBooks = m_Library.availableBooks(); availableBooks.hasNext();)
        {
            LibraryBook curBook = availableBooks.next();
            bookTableHtml.append("<tr>\n");
            bookTableHtml.append("<td class=\"tg-yw4l\">" + curBook.getBookTitle() + "</td>\n");
            bookTableHtml.append("<td class=\"tg-j2zy\">" + curBook.getAuthor() + "</td>\n");
            bookTableHtml.append("<td class=\"tg-yw4l\">Yes</td>\n");
            bookTableHtml.append("</tr>\n");
        }

        for(Iterator<LibraryBook> unavailableBooks = m_Library.unavailableBooks(); unavailableBooks.hasNext();)
        {
            LibraryBook curBook = unavailableBooks.next();
            bookTableHtml.append("<tr>\n");
            bookTableHtml.append("<td class=\"tg-yw4l\">" + curBook.getBookTitle() + "</td>\n");
            bookTableHtml.append("<td class=\"tg-j2zy\">" + curBook.getAuthor() + "</td>\n");
            bookTableHtml.append("<td class=\"tg-yw4l\">No</td>\n");
            bookTableHtml.append("</tr>\n");
        }
        bookTableHtml.append("</table>");
        return bookTableHtml.toString();
    }
//    private HttpServletResponse generateResponse(LibraryBook i_Book, HttpServletResponse i_Resp, boolean i_Succes, eAction i_Action) throws IOException {
//        PrintWriter out = i_Resp.getWriter();
//        switch(i_Action){
//            case ADD:
//                if(i_Succes)
//                {
//                    out.append("<h4>\"" + i_Book.getBookTitle()+ "\" was added to the library.</h4></br>");
//                }
//                else
//                {
//                    out.append("<h4>Failed to add \"" + i_Book.getBookTitle()+ "\" to the library.</h4></br>");
//                }
//                break;
//            case TAKE:
//                if(i_Succes)
//                {
//                    out.append("<h4>\"" + i_Book.getBookTitle()+ "\" was taken from the library.</h4></br>");
//                    if(i_Book.getReturnDate() != null)
//                    {
//                        out.append("<h5>Please return it to the library by " + i_Book.getReturnDate().toString() + "</h5></br>");
//                    }
//                }
//                else
//                {
//                    out.println("<h4>Failed to take \"" + i_Book.getBookTitle()+ "\" from the library.</h4></br>");
//                }
//                break;
//            case RETURN:
//                if(i_Succes)
//                {
//                    out.append("<h4>Thanks, \"" + i_Book.getBookTitle()+ "\" was returned to the library, Come again soon.</h4></br>");
//                }
//                else
//                {
//                    out.append("<h4>Failed to return \"" + i_Book.getBookTitle()+ "\" to the library.</h4></br>");
//                }
//                out.flush();
//                break;
//            case SHOW:
//                break;
//        }
//        return i_Resp;
//    }

    private boolean addBookToLibrary(LibraryBook i_LibraryBook)
    {
        boolean addBookSuccessed = false;
        m_Library.addBook(i_LibraryBook);
        addBookSuccessed = m_Library.isBookAvailable(i_LibraryBook);
        return addBookSuccessed;
    }

    private JSONObject getJson(HttpServletRequest req) throws IOException {
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) { /*report an error*/ }

        try {
            JSONObject jsonObject =  HTTP.toJSONObject(jb.toString());
            return jsonObject;
        } catch (JSONException e) {
            // crash and burn
            throw new IOException("Error parsing JSON request string");
        }
    }

    private void addSomeBooks()
    {
        m_Library.addBook(new LibraryBook("Harry Potter and the Goblet of fire", "J.K. Rowling"));
        m_Library.addBook(new LibraryBook("Rich dad, Poor dad", "Robert Kiyosaki "));
        m_Library.addBook(new LibraryBook("Harry Potter and the sorcerer's stone", "J.K. Rowling"));
        m_Library.addBook(new LibraryBook("Harry Potter and the chamber of secrets", "J.K. Rowling"));
        m_Library.addBook(new LibraryBook("Harry Potter and the deathly hallows", "J.K. Rowling"));
    }
}
