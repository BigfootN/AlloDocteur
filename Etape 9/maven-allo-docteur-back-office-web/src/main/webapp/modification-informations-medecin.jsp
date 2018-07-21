<!doctype html>
<html class="no-js" lang="">
  <head>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
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
          <a href="./home" class="logo">
            <img src="./assets/images/logo.png" alt="">
          </a>
          <ul class="loginMenu">
            <li><a href="allo-doctor-compte-medecin.html">Connexion</a></li>
          </ul>
           <ul class="nav">
            <li><a href="medecin.html">Médecin</a></li>
            <li><a class="active" href="modification-informations-medecin.html">Informations médecins</a></li>
            <li><a href="recherche-patient.html ">Recherche patient</a></li>
            <li><a href="presence-rendez-vous.html ">Présence rendez-vous</a></li>
            <li><a href="parrainer-medecin.html ">Parrainer un médecin</a></li>
            <li><a href="rendez-vous-journee.html ">Rendez-vous journée</a></li>
          </ul>
        </div>
      </div>

      <div class="container-fluid clearfix">
        <div class="container content-outer personal-information">
          <div class="content-container clearfix">
            <div class="content">
              <h1 class="site-title">
                INFORMATIONS PERSONNELLES DU MÉDECIN
              </h1>
              <div class="formulaire">
                <form action="#" method="post">
                  <p>
                  <select name="civility">
                    <option selected value="Civilité">Civilité</option>
                    <option value="Homme">Homme</option>
                    <option value="Femme">Femme</option>
                  </select>
                </p>
                  <p>
                  <input type="text" value="<%= request.getAttribute("name") %>" name="firstName" placeholder="Prénom">
                  </p>
                  <p>
                  <input type="text" value="<%= request.getAttribute("lastName") %>"  name="name" placeholder="Nom">
                  </p>
                  <p>
                  <input type="text" name="identifier" value="<%= request.getAttribute("email") %>" placeholder="Identifiant">
                  </p>
                  <p>
                  <input type="password" name="password" placeholder="Mot de passe">
                  </p>
                  <p>
                  <input type="text" name="cnfpassword" placeholder="Confirmation mot de passe">
                  </p>
                  <p>
                    <input autocomplete="off" data-toggle="datepicker" type="text" name="naissance" placeholder="Date de naissance">
                  </p>
                  <p>
                  <input type="text" name="tel" value="<%= request.getAttribute("telNumber") %>" placeholder="Numéro de Téléphone">
                  </p>
                  <p>
                  <input type="text" name="numAcreditation" value="<%= request.getAttribute("accrNumber") %>" placeholder="numéro d’accréditation">
                  </p>
                  <p>
                  <input type="text" name="street" value="<%= request.getAttribute("street") %>" placeholder="Rue">
                  </p>
                  <p>
                  <input type="text" name="postaCode" value="<%= request.getAttribute("postalCode") %>" placeholder="Code postale">
                  </p>
                  
                  <p>
                  <select name="city">
                    <option value="ville" selected>Ville</option>
                    <option value="ville" >Paris</option>
                    <option value="ville" >lavale</option>
                    <option value="ville" >Lille</option>
                    <option value="ville" >Lion</option>
                  </select>
                  </p>
                  <p>
                  <input type="text" name="pays" value="<%= request.getAttribute("country") %>" placeholder="Pays">
                  </p>
                  <p>
                  <button type="submit">Modifier</button>
                  </p>
                  <p>
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
