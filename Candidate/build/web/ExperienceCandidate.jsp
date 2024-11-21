<%@page import="Model.Skill"%>
<%@page import="DTO.ExperienceCandidateDTO"%>
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
    <link rel="stylesheet" href=" /styles/main.css">
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
    	function openUpdate(id, firstName, lastName) {
            document.getElementById('updateId').value = id;
            document.getElementById('updateFirstName').textContent = firstName;
            document.getElementById('updateLastName').textContent = lastName;
            document.getElementById("updateExperienceSkill").style.display = "block";
        }
        
        function closeUpdate() {
            document.getElementById("updateExperienceSkill").style.display = "none";
        }
    	
    </script>
    <title>Candidate</title>
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
    <h2 class="text-center">Experience Candidates</h2>
    <div style="text-align: center; margin: 20px;">
        <form method="get">
            First Name: <input type="text" name="firstName">
            Last Name: <input type="text" name="lastName">
            Skill: <select name="skillName">
                <option></option>
                    <%
                        List<Skill> skills = (List<Skill>) request.getAttribute("skills");
                        for(Skill skill:skills) {
                        %>
                        <option name="skillName" value="<%= skill.getName()%>"><%= skill.getName() %></option>
                        <%
                            }
                        %>
                    </select>
            <button type="submit">Search</button>
        </form>
    </div>
    
    <div class="table-container">
        <table class="table table-bordered table-hover text-center">
            <thead>
                <tr>
                    <th>STT</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Skill</th>
                    <th>Birth</th>
                    <th>Address</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int currentPage = (int) request.getAttribute("currentPage");
                    List<ExperienceCandidateDTO> candidates = (List<ExperienceCandidateDTO>) request.getAttribute("candidates");
                    if (candidates != null && !candidates.isEmpty()) {
                        int count = currentPage*10+1;
                        for (ExperienceCandidateDTO candidate : candidates) {
                %>
                <tr>
                    <td><%= count++ %></td>
                    <td><%= candidate.getFirstName() %></td>
                    <td><%= candidate.getLastName() %></td>
                    <td><%= candidate.getSkillName() %></td>
                    <td><%= candidate.getBirth() %></td>
                    <td><%= candidate.getAddress() %></td>
                    <td><%= candidate.getPhone() %></td>
                    <td><%= candidate.getEmail() %></td>
                    <td>
                        <form method="post" style="display:inline;">
                             <input type="hidden" name="action" value="delete">
                             <input type="hidden" name="candidateSkillId" value="<%= candidate.getCandidateSkillId() %>">
                             <button type="submit" onclick="return confirm('xóa ứng viên này?');">Delete</button>
                         </form>
                             
                             <button onclick="openUpdate('<%= candidate.getCandidateSkillId()%>', '<%= candidate.getFirstName()%>', '<%= candidate.getLastName()%>')">Update</button>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="9">No candidates found</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    <div id="updateExperienceSkill" style="display:none; position:fixed; top:10%; left:30%; background:white; padding:20px; border:1px solid black;">
        <form method="post">
            <input type="hidden" name="action" value="update">
            id:<input type="hidden" id="updateId" name="id">
            First Name: <span id="updateFirstName"></span> <br>
            Last Name: <span id="updateLastName"></span><br>
            <label>Skill</label>
            <select name="skillId">
                <%
                for(Skill skill:skills) {
                %>
                <option name="skillId" value="<%= skill.getId() %>"><%= skill.getName() %></option>
                <%
                    }
                %>
                
            </select> <br> <br>
            <button type="submit">Update</button>
            <button type="button" onclick="closeUpdate()">Cancel</button>
        </form>
    </div>
    
    <div class="pagination">
        <% 
            if (currentPage > 0) {
        %>
            <a href="?page=<%= currentPage - 1 %>">Previous</a>
        <%
            }
        %>
        <span>Page <%= currentPage + 1 %></span>
        <a href="?page=<%= currentPage + 1 %>">Next</a>
    </div>
</body>
</html>