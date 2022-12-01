<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="users">
   <h2>
      My Profile
  </h2>
  <div style="margin: 0 auto ;">

   <div style=" display: flex;
   width: 120px;
   height: 120px;
   margin: 0 auto;
   margin-bottom: 15px;
   overflow: hidden;
   justify-content: center;  ">
      <img src="${user.get().getProfpicurl()}" alt="Loading img..." >

   </div>
 
 </div>

  <table class="table table-striped">

   <tr>
       <th>Username</th>
       <td>${user.get().getUsername()}</td>
   </tr>
   <tr>
      <th>Name</th>
      <td>${user.get().getName()}</td>
  </tr>
   <tr>
       <th>Surname</th>
       <td>${user.get().getSurname()}</td>
   </tr>
   <tr>
       <th>Email</th>
       <td>${user.get().getEmail()}</td>
   </tr>
   <tr>
       <th>Password</th>
       <td>${user.get().getPassword()}</td>
   </tr>
   <tr>
      <th>Stats</th>
      <td><a>My Statistics</a></td>
  </tr>
</table>
<a class="btn btn-default" href="/users/profile/${user.get().getUsername()}/editProfile">Editar Perfil</a>
</petclinic:layout>
