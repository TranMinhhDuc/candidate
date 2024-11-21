<%@page import="DTO.InternCandidateDTO"%>
<%@page import="Model.University"%>
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
    <script>
        function openUpdate(id, firstName, lastName, major, semester, universityId) {
            document.getElementById('updateId').value = id;
            document.getElementById('updateFirstName').textContent = firstName;
            document.getElementById('updateLastName').textContent = lastName;
            document.getElementById('updateMajor').value = major;
            document.getElementById('updateSemester').value = semester;
            document.getElementById('updateUniversityId').value = universityId;
            document.getElementById("updateInternSkill").style.display = "block";
        }
        
        function closeUpdate() {
            document.getElementById("updateInternSkill").style.display = "none";
        }
    </script>
    <title>Intern Candidates</title>
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
    
    <h2 class="text-center">Intern Candidates</h2>

    <div style="text-align: center; margin: 20px;">
        <form method="get">
            First Name: <input type="text" name="firstName">
            Last Name: <input type="text" name="lastName">
            Major: <input type="text" name="major">
            Semester: <input type="number" name="semester">
            University: 
            <select name="universityName">
                <option></option>
                <% List<University> universities = (List<University>) request.getAttribute("universities");
                for(University university : universities) { %> 
                    <option value="<%= university.getUniversityName() %>"><%= university.getUniversityName() %></option>
                <% } %>
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
                    <th>Major</th>
                    <th>Semester</th>
                    <th>University</th>
                    <th>Birth</th>
                    <th>Address</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>
            </thead>
            <% 
                List<InternCandidateDTO> interns = (List<InternCandidateDTO>) request.getAttribute("interns");
                for(InternCandidateDTO intern : interns) {
            %>
            <tr>
                <td>#</td>
                <td><%= intern.getFirstName() %></td>
                <td><%= intern.getLastName() %></td>
                <td><%= intern.getMajor() %></td>
                <td><%= intern.getSemester() %></td>
                <td><%= intern.getUniversityName() %></td>
                <td><%= intern.getBirth() %></td>
                <td><%= intern.getAddress() %></td>
                <td><%= intern.getPhone() %></td>
                <td><%= intern.getEmail() %></td>
                <td>
                    <button onclick="openUpdate('<%= intern.getFresherCandidateId()%>', '<%= intern.getFirstName() %>', '<%= intern.getLastName() %>', '<%= intern.getMajor() %>', '<%= intern.getSemester() %>', '<%= intern.getUniversityId() %>')">Update</button>
                    <form method="post" action="intern" style="display: inline;">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="<%= intern.getFresherCandidateId()%>">
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
        
        <div id="updateInternSkill" style="display: none; position: fixed; top: 20%; left: 30%; background: white; padding: 20px; border: 1px solid black;">
            <form method="post" action="intern">
                <input type="hidden" name="action" value="update">
                <input type="hidden" id="updateId" name="id">
                <div>First Name: <span id="updateFirstName"></span></div>
                <div>Last Name: <span id="updateLastName"></span></div>
                <div>Major: <input type="text" id="updateMajor" name="major"></div>
                <div>Semester: <input type="number" id="updateSemester" name="semester"></div>
                <div>
                    University: 
                    <select id="updateUniversityId" name="universityId">
                        <% for(University university : universities) { %>
                            <option value="<%= university.getId() %>"><%= university.getUniversityName() %></option>
                        <% } %>
                    </select>
                </div>
                <button type="submit">Update</button>
                <button type="button" onclick="closeUpdate()">Cancel</button>
            </form>
        </div>
    </div>
</body>
</html>
