<%@ page import="java.util.List,com.cours.allo.docteur.dao.entities.RendezVous" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html class="no-js" lang="">
  <head>
    <meta charset="utf-8">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Allo Docteur</title>

    <link rel="apple-touch-icon" href="apple-touch-icon.png">
    <!-- Place favicon.ico in the root directory -->

    <!-- build:css styles/vendor.css -->
    <!-- bower:css -->
    <!-- endbower -->
    <!-- endbuild -->

    <!-- build:css styles/main.css -->
    <link rel="stylesheet" href="./assets/styles/bootstrap.css"> 
    <link rel="stylesheet" href="./assets/styles/main.css">
    <link rel="stylesheet" href="./assets/styles/rwd.css">

    <!-- endbuild -->
    
    <!-- build:js scripts/vendor/modernizr.js -->
    <!-- endbuild -->
  </head>
  <body class="clearfix">
    <!--[if IE]>
      <p class="browserupgrade">Vous utilisez un <strong>obsolète</strong> navigateur. Merci de se <a href="http://browsehappy.com/">mettre à jour</a> pour améliorer votre experience.</p>
    <![endif]-->
    
    <div class="container-fluid  no-padding ">
      <div class="container-fluid clearfix no-padding header-container ">
        <div class="container header-content">
          <a href="./index.html" class="logo">
            <img src="./assets/images/logo.png" alt="">
          </a>
          <ul class="loginMenu">
            <li><a href="./login">Connexion</a></li>
          </ul>
           <ul class="nav">
            <li><a href="./home">Médecin</a></li>
            <li><a href="./account">Informations médecins</a></li>
            <li><a href="./searchpat">Recherche patient</a></li>
            <li><a class="active" href="./appointmentpresence">Présence rendez-vous</a></li>
            <li><a href="./createdoctor">Parrainer un médecin</a></li>
            <li><a href="./apptday">Rendez-vous journée</a></li>
          </ul>
        </div>
      </div>

      <div class="container-fluid clearfix">
        <div class="container content-outer presence-rendez-vous">
          <div class="content-container clearfix">
            <div class="content">
              <h1 class="site-title">
                PRÉSENCE AU RENDEZ VOUS
              </h1>
                
              <table class="presence">
                <thead>
                  <tr>
                    <th>Prénom et Nom</th>
                    <th>Identifiant</th>
                    <th>Numéro de sécurité sociale</th>
                    <th>Numéro de téléphone</th>
                    <th>Adresse</th>
                    <th>Date</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach items="${rdvlist}" var="rdv" varStatus="status">
                    <c:set var="patient" value="${rdv.patientRdv}"/>
                    <c:set var="user" value="${patient.userPatient}"/>
                    <tr>
                      <td title="Prénom et Nom"><c:out value="${user.civilite} ${user.prenom} ${user.nom}"/></td>
                      <td title="Identifiant"><c:out value="${user.identifiant}"/></td>
                      <td title="Numéro de sécurité sociale"><c:out value="${patient.numeroSecuriteSociale}"/></td>
                      <td title="Numéro de téléphone"><c:out value="${patient.numeroTelephone}"/></td>
                      <td title="Adresse">${addrList[status.index].rue}</td>
                      <td title="Date"></td>
                      <td title="">
                        <c:choose>
                          <c:when test="${rdv.present == true}">
                            Present
                          </c:when>
                          <c:otherwise>
                            Absent
                          </c:otherwise>
                        </c:choose>
                      </td>
                  </c:forEach>
                </tbody>
              </table>

            </div>
          </div>
        </div>
      </div>
      
    </div>
    
    

    <!-- build:js scripts/vendor.js -->
    <!-- bower:js -->
    <script src="./assets/scripts/jquery.js"></script>
    <!-- endbower -->
    <!-- endbuild -->
    
    <script src="./assets/scripts/jquery.slicknav.js"></script> 
    <!-- build:js scripts/main.js -->
    <script src="./assets/scripts/main.js"></script>
    <!-- endbuild -->
  </body>
</html>
