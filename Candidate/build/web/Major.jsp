<%@page import="Model.Major"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.0/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/styles/main.css">
    <style>
        .table-container {
            margin-left: 2rem;
            margin-right: 2rem;
        }
        .pagination {
            justify-content: center;
            margin-top: 1rem;
        }
        
        #menu {
        	margin: 20px 20px;
		    text-align: center;
		}
		
		#menu ul {
		    display: flex;
		    justify-content: center;
		    align-items: center;
		    gap: 50px;
		    background-color: white;
		    padding: 10px 0;
		    margin: 0;
		}
		
		#menu ul li {
		    background-color: white;
		    list-style: none;
		}
		
		#menu a {
		    background-color: white;
		    text-decoration: none;
		    color: black;
		    font-size: 16px;
		    font-weight: bold;
		}

    </style>
    <title>Major</title>
</head>
<body>
    <% 
         String message = (String) session.getAttribute("message");
        if (message != null && !message.isEmpty()) {
                 session.removeAttribute("message");
        %>
        <span style="color:green"><%= message %></span>
        <%
            }
    %>
    
    <div class="col-sm-9 bg-white" id="menu">
        <ul>
            <li><a href="/candidate/candidate">Candidate</a></li>
            <li><a href="/candidate/experience-candidate">Experience</a></li>
            <li><a href="/candidate/fresher">Fresher</a></li>
            <li><a href="/candidate/intern">Intern</a></li>
            <li><a href="/candidate/skill">Skill</a></li>
            <li><a href="/candidate/university">University</a></li>
            <li><a href="/candidate/major">Major</a></li>
        </ul>
    </div>
    
    <h2 class="text-center">Majors</h2>
    <div style="text-align: center; margin: 20px;">
        <form method="post" action="major">
            <input type="hidden" name="action" value="add">
            Major Name: <input type="text" name="majorName" required>
            <button type="submit">Add Major</button>
        </form>
    </div>

    <div style="text-align: center; margin-bottom: 20px;">
        <form method="get" action="major">
            Major Name: <input type="text" name="majorName">
            <button type="submit">Search</button>
        </form>
    </div>
    
    <div class="table-container">
        <table id="major-table" class="table text-center">
            <tr>
                <th>STT</th>
                <th>Major Name</th>
                <th>Action</th>
            </tr>
    
            <% 
            List<Major> majors = (List<Major>) request.getAttribute("majors"); 
            int stt = 0;
            for (Major major : majors) {
                stt++;
            %>
            <tr>
                <td><%= stt %></td>
                <td><%= major.getMajorName() %></td>
                <td>
                    <form method="post" action="major" style="display:inline;">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="majorId" value="<%= major.getId() %>">
                        <button type="submit" onclick="return confirm('Are you sure you want to delete this major?');">Delete</button>
                    </form>

                    <form method="post" action="major" style="display:inline;">
                        <input type="hidden" name="action" value="edit">
                        <input type="hidden" name="majorId" value="<%= major.getId() %>">
                        <input type="text" name="majorName" value="<%= major.getMajorName() %>" required>
                        <button type="submit">Edit</button>
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
    </div>
</body>
</html>
