<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <title>My Library</title>
  </head>
    <h1>My Library</h1>
      <p>This is a simple library managment page.</p>
      <p>You have the options to take a book, return it, or add a new book to the library.</p>
      <p>Book's have titles, and authors. (May have catagories in the future)</p>
    <body>
      <h3>Add a new Book</h3> </br>
      Author:<input type="text" id="addAuthor"> Title:<input type="text" id="addTitle"> <input type="button" id="add" value="Add"></input><br><br/>
      <h3 id="takeHead">Take a Book:</h3></br>
      Author:<input type="text" id="takeAuthor"> Title:<input type="text" id="takeTitle"> <input type="button" id="take" value="Take"></input><br><br/>
      <h3>Return a Book:</h3></br>
      Author:<input type="text" id="returnAuthor"> Title:<input type="text" id="returnTitle"> <input type="button" id="return" value="Return"></input><br><br/>
      <form name="showBooks" method="get" action="LibraryManager">
        <h3>Show available books:</h3><input type="submit" id="show" value="Show"></input><br><br/>
      </form>

    </body>
  <script src = "client.js"></script>
</html>