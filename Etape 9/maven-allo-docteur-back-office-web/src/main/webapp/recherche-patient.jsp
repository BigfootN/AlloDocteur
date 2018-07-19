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
            <li><a href="allo-doctor-compte-medecin.html">Connexion</a></li>
          </ul>
           <ul class="nav">
            <li><a href="medecin.html">Médecin</a></li>
            <li><a href="modification-informations-medecin.html">Informations médecins</a></li>
            <li><a class="active" href="recherche-patient.html ">Recherche patient</a></li>
            <li><a href="presence-rendez-vous.html ">Présence rendez-vous</a></li>
            <li><a href="parrainer-medecin.html ">Parrainer un médecin</a></li>
            <li><a href="rendez-vous-journee.html ">Rendez-vous journée</a></li>
          </ul>
        </div>
      </div>

      <div class="container-fluid clearfix">
        <div class="container content-outer search-patient">
          <div class="content-container clearfix">
            <div class="content">
              <h1 class="site-title">
                RECHERCHE PATIENT
              </h1>
                <form class="search-form" action="" method="get">
                  <select name="city">
                    <option value="ville" selected>entrez Critère de recherche</option>
                    <option value="ville" >Prénom</option>
                    <option value="ville" >Nom</option>
                    <option value="ville" >numéro de sécurité sociale</option>
                  </select>
                  <input type="text" name="searchName" placeholder="Entrez la valeur">
                  <button type="submit">Rechercher</button>
                </form>
              
              <table>
                <thead>
                  <tr>
                    <th title="Prénom et Nom">Prénom et Nom</th>
                    <th>Identifiant</th>
                    <th>Numéro de sécurité sociale</th>
                    <th>Numéro de téléphone</th>
                    <th>Adresse</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td title="Prénom et Nom">Mr Faris CORONA</td>
                    <td title="Identifiant">Mich@el2001</td>
                    <td title="Numéro de sécurité sociale">235146587452698</td>
                    <td title="Numéro de téléphone">03365221144</td>
                    <td title="Adresse">Avenue la résistance, n°2, 75000, Paris France </td>
                  </tr>
                  <tr>
                    <td title="Prénom et Nom">Mr Faris CORONA</td>
                    <td title="Identifiant">Mich@el2001</td>
                    <td title="Numéro de sécurité sociale">235146587452698</td>
                    <td title="Numéro de téléphone">03365221144</td>
                    <td title="Adresse">Avenue la résistance, n°2, 75000, Paris France </td>
                  </tr>
                  <tr>
                    <td title="Prénom et Nom">Mr Faris CORONA</td>
                    <td title="Identifiant">Mich@el2001</td>
                    <td title="Numéro de sécurité sociale">235146587452698</td>
                    <td title="Numéro de téléphone">03365221144</td>
                    <td title="Adresse">Avenue la résistance, n°2, 75000, Paris France </td>
                  </tr>
                  <tr>
                    <td title="Prénom et Nom">Mr Faris CORONA</td>
                    <td title="Identifiant">Mich@el2001</td>
                    <td title="Numéro de sécurité sociale">235146587452698</td>
                    <td title="Numéro de téléphone">03365221144</td>
                    <td title="Adresse">Avenue la résistance, n°2, 75000, Paris France </td>
                  </tr>
                  
                  

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