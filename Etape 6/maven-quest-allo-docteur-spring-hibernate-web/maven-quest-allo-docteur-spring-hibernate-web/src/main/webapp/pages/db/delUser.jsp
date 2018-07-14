<%@page import="com.cours.dao.impl.UtilisateurDao"%>
<%
    int idUserToDel;
    UtilisateurDao dao;

    idUserToDel = request.getParameter("id");
    dao = new UtilisateurDao();

    dao.deleteUser(idUserToDel);
%>
    