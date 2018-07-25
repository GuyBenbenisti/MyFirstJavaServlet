document.getElementById("add").addEventListener('click', addBook);
document.getElementById("take").addEventListener('click', takeBook);
document.getElementById("return").addEventListener('click', returnBook);
url = "http://localhost:8080";

function addBook()
{
    var Book = {
        m_Author:document.getElementById("addAuthor").value,
        m_Title:document.getElementById("addTitle").value,
        action:"add"
    };
    var resp = fetch(url + "/LibraryManager",{
        method: 'POST',
        body:  JSON.stringify(Book),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(function(response) {
        if(response.status === 200)
        {
            var h = document.createElement("H4");
            h.innerText =  "\"" + Book.m_Title + "\" was added to the library.";
            var br = document.createElement("br");
            h.appendChild(br);
            document.body.appendChild(h);
        }else{
            var h = document.createElement("H4");
            h.innerText =  "Failed to add \"" + Book.m_Title + "\" to the library.";
            var br = document.createElement("br");
            h.appendChild(br);
            document.body.appendChild(h);
        }
    })
}

function takeBook()
{
    var Book = {
        m_Author:document.getElementById("takeAuthor").value,
        m_Title:document.getElementById("takeTitle").value,
        action:"take"
    };
    var resp = fetch(url + "/LibraryManager",{
        method: 'POST',
        body:  JSON.stringify(Book),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(function(response) {
        if(response.status === 200)
        {
            var h = document.createElement("H4");
            h.innerText =  "\"" + Book.m_Title + "\" was taken from the library.";
            var br = document.createElement("br");
            h.appendChild(br);
            document.body.appendChild(h);
        }else{
            var h = document.createElement("H4");
            h.innerText =  "Failed to take \"" + Book.m_Title + "\" to the library.";
            var br = document.createElement("br");
            h.appendChild(br);
            document.body.appendChild(h);
        }
    })
}

function returnBook()
{
    var Book = {
        m_Author:document.getElementById("returnAuthor").value,
        m_Title:document.getElementById("returnTitle").value,
        action:"return"
    };
    var resp = fetch(url + "/LibraryManager",{
        method: 'POST',
        body:  JSON.stringify(Book),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(function(response) {
        if(response.status === 200)
        {
            var h = document.createElement("H4");
            h.innerText =  "\"" + Book.m_Title + "\" was returned to the library.";
            var br = document.createElement("br");
            h.appendChild(br);
            document.body.appendChild(h);
        }else{
            var h = document.createElement("H4");
            h.innerText =  "Failed to return \"" + Book.m_Title + "\" to the library.";
            var br = document.createElement("br");
            h.appendChild(br);
            document.body.appendChild(h);
        }
    })

}
