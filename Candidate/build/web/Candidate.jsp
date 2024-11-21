<%@page import="Model.Major"%>
<%@page import="Model.University"%>
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
    <link rel="stylesheet" href=" /styles/main.css">
    <style>
        .table-container {
            margin-left: 2rem;  /* Lề trái */
            margin-right: 2rem; /* Lề phải */
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
		    align-items: center; /* Đảm bảo căn giữa theo chiều dọc nếu cần */
		    gap: 50px;
		    background-color: white;
		    padding: 10px 0; /* Thêm khoảng cách trên dưới */
		    margin: 0; /* Loại bỏ lề mặc định */
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
    	
    	function openUpdateCandidate(candidateId, firtName, lastName, birth, address, phone, email, candidateType) {
    		document.getElementById('updateCandidateId').value = candidateId;
    		document.getElementById('updateFirstName').value = firtName;
    		document.getElementById('updateLastName').value = lastName;
    		document.getElementById('updateBirth').value = birth;
    		document.getElementById('updateAddress').value = address;
    		document.getElementById('updatePhone').value = phone;
    		document.getElementById('updateEmail').value = email;
    		document.getElementById('updateCandidateType').value = candidateType;
    		document.getElementById("updateCandidate").style.display = "block";
		}
    	
    	function closeCandidate() {
    		document.getElementById("updateCandidate").style.display = "none";
    	}
    	
    	function openAddCandidate() {
    	    document.getElementById("addCandidate").style.display = "block";
    	}

    	function closeAddCandidate() {
    	    document.getElementById("addCandidate").style.display = "none";
    	}
        
        function openAddSkill(candidateId, firstName, lastName) {
        document.getElementById('addSkillCandidateId').value = candidateId;
        document.getElementById('addFirstName').textContent = firstName;
        document.getElementById('addLastName').textContent = lastName;
        document.getElementById("addSkill").style.display = "block";
        }

        function closeAddSkill() {
            document.getElementById("addSkill").style.display = "none";
        }

        function openAddIntern(candidateId) {
            document.getElementById('addInternCandidateId').value = candidateId;
            document.getElementById("addIntern").style.display = "block";
        }

        function closeAddIntern() {
            document.getElementById("addIntern").style.display = "none";
        }

        function openAddFresher(candidateId) {
            document.getElementById('addFresherCandidateId').value = candidateId;
            document.getElementById("addFresher").style.display = "block";
        }

        function closeAddFresher() {
            document.getElementById("addFresher").style.display = "none";
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
	<div style="text-align: center; margin: 20px;">
	    <button onclick="openAddCandidate()">Add Candidate</button>
	</div>
	<div style="text-align: center; margin-bottom: 20px;">
        <form method="get">
            First Name: <input type="text" name="firstName">
            Last Name: <input type="text" name="lastName">
            Birth: <input type="number" name="birth" min="1980" max="2010">
            Address: <input type="text" name="address">
            Candidate Type: <input type="number" name="candidateType" min="0" max="2">
            <button type="submit">Search</button>
        </form>
    </div>
    <div class="table-container">
        <h2 class="text-center">Candiate</h2>
        <table id="device-usage-table" class="table text-center">
              <tr>
              		<th>STT</th>
                  <th>First Name</th>
                  <th>Last Name</th>
                  <th>Birth</th>
                  <th>Address</th>
                  <th>Phone</th>
                  <th>Email</th>
                  <th>Candidate Type</th>
                  <th>Action</th>
              </tr>
              
              <% 
                  int currentPage = (int) request.getAttribute("currentPage");
                  List<Candidate> candidates = (List<Candidate>) request.getAttribute("candidates");
	              int stt = currentPage*10+1;
              		for(Candidate candidate:candidates){
	            	  %>
	            	  	<tr>
	            	  		<td><%= stt++ %></td>
	            	  		<td><%= candidate.getFirstName() %></td>
	            	  		<td><%= candidate.getLastName() %></td>
	            	  		<td><%= candidate.getBirthDate() %></td>
	            	  		<td><%= candidate.getAddress() %></td>
	            	  		<td><%= candidate.getPhone() %></td>
	            	  		<td><%= candidate.getEmail() %></td>
	            	  		<td><%= candidate.getCandidateType() %></td>
	            	  		<td>
	                            <form method="post" style="display:inline;">
	                                <input type="hidden" name="action" value="delete">
	                                <input type="hidden" name="candidateid" value="<%= candidate.getId()%>">
	                                <button type="submit" onclick="return confirm('xóa ứng viên này?');">Delete</button>
	                            </form>
	                                <button onclick="openUpdateCandidate(
								        '<%= candidate.getId() %>',
								        '<%= candidate.getFirstName() %>',
								        '<%= candidate.getLastName() %>',
								        '<%= candidate.getBirthDate() %>',
								        '<%= candidate.getAddress() %>',
								        '<%= candidate.getPhone() %>',
								        '<%= candidate.getEmail() %>',
								        '<%= candidate.getCandidateType() %>'
								    )">Update</button>
                                                 
                                        <% if (candidate.getCandidateType().equals("0")) {
                                            %>
                                            <button onclick="openAddSkill('<%= candidate.getId()%>', '<%= candidate.getFirstName()%>', '<%= candidate.getLastName()%>')">Add Skill</button>
                                            <%
                                            }else if(candidate.getCandidateType().equals("1")){
                                                %>
                                                <button onclick="openAddFresher('<%= candidate.getId() %>')">Add University</button>
                                                <%
                                            }else {
                                                %>
                                                <button onclick="openAddIntern('<%= candidate.getId() %>')">Add University</button>
                                                <%
                                                }
                                        %>       
	                        </td>
	            	  	</tr>
	            	  <%
	              }
              %>
        </table>
    </div>
    
    <div id="addCandidate" style="display:none; position:fixed; top:10%; left:30%; background:white; padding:20px; border:1px solid black;">
        <form method="post">
            <input type="hidden" name="action" value="add">
            First Name: <input type="text" name="firstName" required><br>
            Last Name: <input type="text" name="lastName" required><br>
            Birth Date: <input type="number" name="birthDate" required><br>
            Address: <input type="text" name="address" required><br>
            Phone: <input type="text" name="phone" required><br>
            Email: <input type="email" name="email" required><br>
            Candidate Type: <input type="number" name="candidateType" min="0" max="2" required><br>
            <button type="submit">Add</button>
            <button type="button" onclick="closeAddCandidate()">Cancel</button>
        </form>
    </div>
	
    <div id="updateCandidate" style="display:none; position:fixed; top:10%; left:30%; background:white; padding:20px; border:1px solid black;">
        <form method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" id="updateCandidateId" name="id">
            First Name: <input type="text" id="updateFirstName" name="firstName"><br>
            Last Name: <input type="text" id="updateLastName" name="lastName"><br>
            Birth Date: <input type="text" id="updateBirth" name="birthDate"><br>
            Address: <input type="text" id="updateAddress" name="address"><br>
            Phone: <input type="text" id="updatePhone" name="phone"><br>
            Email: <input type="email" id="updateEmail" name="email"><br>
            Candidate Type: <input type="number" id="updateCandidateType" name="candidateType" min="0" max="2"><br>
            <button type="submit">Update</button>
            <button type="button" onclick="closeCandidate()">Cancel</button>
        </form>
    </div>
        
    <div id="addSkill" style="display:none; position:fixed; top:10%; left:30%; background:white; padding:20px; border:1px solid black;">
        <form method="post">
            <input type="hidden" name="action" value="addSkill">
            <input type="hidden" id="addSkillCandidateId" name="candidateId">
            First Name: <span id="addFirstName"></span><br>
            Last Name: <span id="addLastName"></span><br>
            Skill Name: <select name="skillId">
                <% List<Skill> skills = (List<Skill>) request.getAttribute("skills");
                for (Skill skill:skills) {
                %>
                <option name="skill" value="<%= skill.getId() %>"><%= skill.getName() %></option>
                <%
                    }
                %>
            </select>
            <button type="submit">Add Skill</button>
            <button type="button" onclick="closeAddSkill()">Cancel</button>
        </form>
    </div>

            
    <div id="addFresher" style="display:none; position:fixed; top:10%; left:30%; background:white; padding:20px; border:1px solid black;">
        <form method="post">
            <input type="hidden" name="action" value="addFresher">
            <input type="hidden" id="addFresherCandidateId" name="candidateId">
            University: <select name="universityId" style="margin:10px">
                <% List<University> universities = (List<University>) request.getAttribute("universities");
                for (University university:universities) {
                %>
                <option name="skill" value="<%= university.getId() %>"><%= university.getUniversityName()%></option>
                <%
                    }
                %>
            </select> <br>
            Graduation Year: <input type="number" name="graduationYear" required min="1990" max="2002" style="margin:10px"><br>
            Rank: <select name="rank">
                <option value="Giỏi">Giỏi</option>
                <option value="Khá">Khá</option>
                <option value="Trung Bình">Trung Bình</option>
                <option value="Yếu">Yếu</option>
            </select>
            <button type="submit">Add Fresher</button>
            <button type="button" onclick="closeAddFresher()">Cancel</button>
        </form>
    </div>
            
            
    <div id="addIntern" style="display:none; position:fixed; top:10%; left:30%; background:white; padding:20px; border:1px solid black;">
        <form method="post">
            <input type="hidden" name="action" value="addIntern">
            <input type="hidden" id="addInternCandidateId" name="candidateId">
            University: <select name="universityId">
                <% 
                for (University university:universities) {
                %>
                <option value="<%= university.getId() %>"><%= university.getUniversityName()%></option>
                <%
                    }
                %>
            </select>
            <br><br>
            Major: <select name="majorId">
                <% List<Major> majors = (List<Major>) request.getAttribute("majors");
                for (Major major :majors) {
                %>
                <option name="skill" value="<%= major.getId() %>"><%= major.getMajorName()%></option>
                <%
                    }
                %>
            </select>
            <br><br>
            Semester:<input name="semester" type="number" min="1" max="3" required>
            <button type="submit">Add Intern</button>
            <button type="button" onclick="closeAddIntern()">Cancel</button>
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