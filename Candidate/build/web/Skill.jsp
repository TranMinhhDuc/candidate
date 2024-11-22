<%@page import="Model.Skill"%>
<%@page import="Model.Candidate"%>
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
    
    <script>
    	
    	
    </script>
    <title>Candidate</title>
</head>
<body>

    <%
        String message = (String) session.getAttribute("message");
        if (message != null && !message.isEmpty()) {
            session.removeAttribute("message");
    %>
        <div id="message" style="position:fixed; top:10%; left:30%; background:white; padding:20px; border:1px solid black; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); z-index:1000;">
            <span style="color:green"><%= message %></span>
            <button id="cancelButton" style="margin-left: 10px; padding: 5px; cursor: pointer;">Close</button>
        </div>
        <script>
            document.getElementById("cancelButton").addEventListener("click", function() {
                document.getElementById("message").style.display = "none";
            });
        </script>
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
    <h2 class="text-center">Experience Candidate</h2>
	<div style="text-align: center; margin: 20px;">
	    <form method="post" action="skill">
	        <input type="hidden" name="action" value="add">
	        Skill Name: <input type="text" name="skillName" required>
	        <button type="submit">Add Skill</button>
	    </form>
	</div>

	<div style="text-align: center; margin-bottom: 20px;">
            <form method="get">
                Skill Name: <input type="text" name="skillName">
                <button type="submit">Search</button>
            </form>
        </div>
    <div class="table-container">
	    <table id="device-usage-table" class="table text-center">
	        <tr>
	            <th>STT</th>
	            <th>Skill Name</th>
	            <th>Action</th>
	        </tr>
	
	        <% 
	        List<Skill> skills = (List<Skill>) request.getAttribute("skills"); 
	        int stt = 0;
	        for (Skill skill : skills) {
	            stt++;
	        %>
	        <tr>
	            <td><%= stt %></td>
	            <td><%= skill.getName() %></td>
	            <td>
	                <form method="post" action="skill" style="display:inline;">
	                    <input type="hidden" name="action" value="delete">
	                    <input type="hidden" name="skillId" value="<%= skill.getId() %>">
	                    <button type="submit" onclick="return confirm('Are you sure you want to delete this skill?');">Delete</button>
	                </form>
	
	                <form method="post" action="skill" style="display:inline;">
	                    <input type="hidden" name="action" value="edit">
	                    <input type="hidden" name="skillId" value="<%= skill.getId() %>">
	                    <input type="text" name="skillName" value="<%= skill.getName() %>" required>
	                    <button type="submit">Edit</button>
	                </form>
	            </td>
	        </tr>
	        <% } %>
	    </table>
	</div>
    
</body>
</html>