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
    <link rel="stylesheet" href="./assets/styles/bootstrap.css" type="text/css"> 
    <link rel="stylesheet" href="./assets/styles/main.css" type="text/css">
    <link rel="stylesheet" href="./assets/styles/rwd.css" type="text/css">
    <!-- endbuild -->
    
    <!-- build:js scripts/vendor/modernizr.js -->
    <!-- endbuild -->
  </head>
  <body class="clearfix">
    <!--[if IE]>
      <p class="browserupgrade">Vous utilisez un <strong>obsolète</strong> navigateur. Merci de se <a href="http://browsehappy.com/">mettre à jour</a> pour améliorer votre experience.</p>
    <![endif]-->
    
    <div class="container-fluid  no-padding">
      <div class="container-fluid clearfix no-padding header-container">
        <div class="container header-content">
          <a href="./index.html" class="logo">
            <img src="./assets/images/logo.png" alt="">
          </a>
          <ul class="loginMenu">
            <li><a class="active" href="./login">Connexion</a></li>
          </ul>
           <ul class="nav">
            <li><a href="./home">Médecin</a></li>
            <li><a href="./account">Informations médecins</a></li>
            <li><a href="./searchpat">Recherche patient</a></li>
            <li><a href="./appointmentpresence">Présence rendez-vous</a></li>
            <li><a href="./createdoctor">Parrainer un médecin</a></li>
            <li><a href="./rendez-vous-journee.html ">Rendez-vous journée</a></li>
          </ul>
        </div>
      </div>

      <div class="container-fluid clearfix">
        <div class="container content-outer connexion">
          <div class="content-container connexion-container clearfix">
            <div class="content">
              <!-- <h1 class="site-title">
                Bienvenue Mr. Julien sur Compte Allo Docteur
              </h1> -->

               <div class="righ-block">
                 
                 <h3>SE CONNECTER</h3>
                  <form id="login-form" class="login-form" method="post" action="${pageContext.request.contextPath}/login">
                   <input name="login" type="text" placeholder="LOGIN">
                   <input name="password" type="password" placeholder="PASSWORD">
                   <button type="submit">se connecter</button>
                   <div class="clearfix"> </div>
                   <a href="./mot-de-passe-oublie.html" class="forgot-pass-link clearfix">Mot de passe oublié?</a>
                 </form>
               </div>
                
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
    
    <!-- build:js scripts/main.js -->
    <script src="./assets/scripts/jquery.slicknav.js"></script>
    <script src="./assets/scripts/main.js"></script>
    <!-- endbuild -->
  </body>
</html>
