<%@page import="DTO.FresherCandidateDTO"%>
<%@page import="Model.University"%>
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
    	function openUpdate(id, firstName, lastName, graduationTime) {
            document.getElementById('updateId').value = id;
            document.getElementById('updateFirstName').textContent = firstName;
            document.getElementById('updateLastName').textContent = lastName;
            document.getElementById('updateGraduationTime').value = graduationTime;
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
    <h2 class="text-center">Fresher Candidates</h2>

    <div style="text-align: center; margin: 20px;">
        <form method="get">
            First Name: <input type="text" name="firstName">
            Last Name: <input type="text" name="lastName">
            Graduation Time: <input type="number" name="graduationTime" min="2000" max="2050">
            Rank: <select name="rank">
                <option></option>
                <option value="Giỏi">Giỏi</option>
                <option value="Khá">Khá</option>
                <option value="Trung Bình">Trung Bình </option>
                <option value="Yếu"> Yếu </option>
            </select>
            University <select name="universityName">
                <option></option>
                <% List<University> universities = (List<University>) request.getAttribute("universities");
                for(University university : universities) {
                %> 
                <option value="<%= university.getUniversityName() %>" ><%= university.getUniversityName() %></option>
                <%
                    }
                %>
                    </select>
            Sort By: <select name="sortBy">
                <option value="c.lastName">Last Name</option>
                <option value="fc.graduationTime"> Graduation Time </option>
                <option value="fc.graduationRank"> Rank </option>
                <option value="u.name">University</option>
                <option value="c.birthDate">Birth</option>
                <option value="c.address">Address</option>
            </select>
            Direction: <select name="direction">
                <option value="ASC">ABC</option>
                <option value="DESC">CBA</option>
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
                    <th>Graduation Time</th>
                    <th>rank</th>
                    <th>University</th>
                    <th>Birth</th>
                    <th>Address</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>
            </thead>
            
            <% 
                int currentPage = (int) request.getAttribute("currentPage");
                List<FresherCandidateDTO> freshers = (List<FresherCandidateDTO>) request.getAttribute("freshers");
                int cnt = currentPage * 10 + 1;
                for(FresherCandidateDTO fresher : freshers){
                    %>
                    <tr>
                    <td><%= cnt++ %></td>
                    <td><%= fresher.getFirstName() %></td>
                    <td><%= fresher.getLastName() %></td>
                    <td><%= fresher.getGrauduationtime() %></td>
                    <td><%= fresher.getRank() %></td>
                    <td><%= fresher.getUniversityName() %></td>
                    <td><%= fresher.getBirth()%></td>
                    <td><%= fresher.getAddress() %></td>
                    <td><%= fresher.getPhone() %></td>
                    <td><%= fresher.getEmail() %></td>
                    <td>
                        <button onclick="openUpdate('<%= fresher.getFresherCandidateId() %>', '<%= fresher.getFirstName() %>', '<%= fresher.getLastName() %>', '<%= fresher.getGrauduationtime()%>')">Update</button>
                        <form method="post" action="fresher" style="display: inline;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="<%= fresher.getFresherCandidateId() %>">
                            <button type="submit" >Delete</button>
                        </form>
                    </td>
                    </tr>
                    <%
                }
            %>
        </table>
        
        <div id="updateExperienceSkill" style="display: none; position: fixed; top: 20%; left: 30%; background: white; padding: 20px; border: 1px solid black;">
            <form method="post" action="fresher">
                <input type="hidden" name="action" value="update">
                <input type="hidden" id="updateId" name="id">
                <div>
                    First Name: <span id="updateFirstName"></span>
                </div>
                <div>
                    Last Name: <span id="updateLastName"></span>
                </div>
                <div>
                    Graduation Time: <input type="number" id="updateGraduationTime" name="graduationTime" min="2000" max="2050">
                </div>
                <div>
                    Rank: <select name="rank">
                        <option value="Giỏi">Giỏi</option>
                        <option value="Khá">Khá</option>
                        <option value="Trung Bình">Trung Bình</option>
                        <option value="Yếu">Yếu</option>
                    </select>
                </div>
                <div>
                    University: <select name="universityId">
                        <% for(University university : universities) { %>
                            <option value="<%= university.getId() %>"><%= university.getUniversityName() %></option>
                        <% } %>
                    </select>
                </div>
                <button type="submit"">Update</button>
                <button type="button"" onclick="closeUpdate()">Cancel</button>
            </form>
        </div>
                    
        <div class="pagination">
        <% 
            String firstName = (String) request.getAttribute("firstName");
            String lastName = (String) request.getAttribute("lastName");
            String graduationTime = (String) request.getAttribute("graduationTime");
            String graduationRank = (String) request.getAttribute("graduationRank");
            String universityName = (String) request.getAttribute("universityName");
            String sortBy = (String) request.getAttribute("sortBy");
            String direction = (String) request.getAttribute("direction");
            if (currentPage > 0) {
        %>
        <a href="?firstName=<%= firstName %>&lastName=<%= lastName %>&graduationTime=<%= graduationTime %>&rank=<%= graduationRank %>&universityName=<%= universityName %>&sortBy=<%= sortBy %>&direction=<%= direction %>&page=<%= currentPage - 1 %>">Previous</a>
        <%
            }
        %>
        <span>Page <%= currentPage + 1 %></span>
        <% int totalPage = (int) request.getAttribute("totalPage");
        if(currentPage+1 < totalPage) {
        %>
        <a href="?firstName=<%= firstName %>&lastName=<%= lastName %>&graduationTime=<%= graduationTime %>&rank=<%= graduationRank %>&universityName=<%= universityName %>&sortBy=<%= sortBy %>&direction=<%= direction %>&page=<%= currentPage - 1 %>">Next</a>
        <%
            }
            %>
        </div>
</body>
</html>